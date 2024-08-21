package controller;

import common.Form;
import common.Menu;
import dto.StockRequestDto;
import exception.Exception;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.stream.Stream;
import library.Script;
import service.serviceImpl.StockRequestServiceImpl;

public class StockRequestController {
  public static int supplier_id = 1; //나중에 userDto.getID하기
  public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  Script script = new Script();
  StockRequestServiceImpl stockRequestService = new StockRequestServiceImpl();
  public static boolean q = false;
  ArrayList<StockRequestDto> StockRequestList = new ArrayList<StockRequestDto>();
  ArrayList<Integer> approvedList = new ArrayList<Integer>();

  public void userMenu(){
    System.out.println("**회원전용 메뉴 화면입니다**");
    try {
      while (!q) {
        System.out.println();
        script.selectUserMenu();
        String menu = br.readLine();
        //menu 유효성 검사하기 validation check 추가하기
        switch (menu) {
          case "1":
            createStockRequest();
            break;
          case "2":
            readByCondition();
            break;
          case "3":
            updateRequest();
            break;
          case "4":
            script.cancelStockRequest();
            deleteRequest();
            break;
          case "5":
            q = true;
            break;
        }
      }
    } catch(IOException | SQLException e){
      throw new RuntimeException(e);
    }
  }

  public void adminMenu(){
    System.out.println("**관리자 전용 메뉴입니다**");
    try {
      while (!q) {
        System.out.println();
        script.selectAdminMenu();
        String menu = br.readLine();
        //menu 유효성 검사하기 validation check 추가하기
        switch (menu) {
          case "1":
            readByCondition();
            break;
          case "2":
            updateStatus();
            break;
          case "3":
            printRequestInstr();
            break;
          case "4":
            q = true;
            break;
        }
      }
    } catch(IOException | SQLException e){
      throw new RuntimeException(e);
    }
  }

  public void printDB(ArrayList<StockRequestDto> list){
    System.out.println(Menu.STOCKREQUESTCOLUMN.getDescription());
    StockRequestList.forEach(stockrequest -> System.out.println(
        stockrequest.getId() + "\t\t\t" + stockrequest.getProduct_id() + "\t\t\t"
            + stockrequest.getBox_quantity() + "\t\t\t" + stockrequest.getBox_size() + "\t\t\t\t"
            + stockrequest.getStatus() + "\t\t\t" + stockrequest.getIncoming_date() + "\t\t\t"
            + stockrequest.getCreated_at() + "\t\t\t" + stockrequest.getRemarks()));
  }

  public StockRequestDto getFormInput() throws IOException {
    System.out.print(Form.PRODUCTID.getDescription());
    String productID = br.readLine();
    System.out.print(Form.BOXQUANTITY.getDescription());
    int boxQuantity = Integer.parseInt(br.readLine());
    System.out.print(Form.BOXSIZE.getDescription());
    char boxSize = br.readLine().charAt(0);
    System.out.print(Form.CELLID.getDescription());
    int cellID = Integer.parseInt(br.readLine());
    System.out.print(Form.INCOMINGDATE.getDescription());
    String incomingDate = br.readLine();
    System.out.print(Form.REMARKS.getDescription());
    String remarks = br.readLine();

    StockRequestDto stockRequest = new StockRequestDto(productID, boxQuantity,
        boxSize, incomingDate, cellID, supplier_id, remarks);

    return stockRequest;
  }

  public void createStockRequest() throws IOException, SQLException {
    //handler에서 처리하지 않아도 될 것 같음.
    stockRequestService.create(getFormInput());
  }

  public void readByCondition() throws IOException, SQLException {
    script.readStockRequest();
    String condition = br.readLine();
    StockRequestList.clear();
    switch (condition){
      case "1": //findAll
        StockRequestList = stockRequestService.findByAll();
        printDB(StockRequestList);
        break;
      case "2": //findById
        System.out.println(Menu.INSERTSEARCHID.getDescription());
        int requestId = Integer.parseInt(br.readLine());
        try{
          StockRequestList = stockRequestService.findById(requestId);
        }catch(Exception e){
          System.out.println(e.getMessage());
        }
        printDB(StockRequestList);
        break;
      case "3": //findByStatus
        script.readStockRequestStatus(); //wms 관리자 용.delete항목이 없음
        int status = Integer.parseInt(br.readLine());
        StockRequestList = stockRequestService.findByStatus(status);
        printDB(StockRequestList);
        break;
      case "4": //findByCreatedDate
        System.out.println(Menu.INSERTCREATEDDATE.getDescription());
        String createdDate = br.readLine();
        StockRequestList = stockRequestService.findByCreatedDate(createdDate);
        printDB(StockRequestList);
        break;
      case "5": //findByProductId
        System.out.println(Menu.INSERTPRODUCTID.getDescription());
        String productId = br.readLine();
        try{
          StockRequestList = stockRequestService.findByProductId(productId);
        }catch(Exception e){
          System.out.println(e.getMessage());
        }
        printDB(StockRequestList);
        break;
      case "6": //findByIncomingDate
        System.out.println(Menu.INSERTINCOMINGDATE.getDescription());
        String incomingDate = br.readLine();
        StockRequestList = stockRequestService.findByIncomingDate(incomingDate);
        printDB(StockRequestList);
        break;
    }
  }

  public void readByCondition(int status){
    //창고관리자용
    //pending list만 보여주기
    StockRequestList = stockRequestService.findByStatus(1);
    printDB(StockRequestList);
  }

  public void updateStatus() throws IOException, SQLException {
    //pending인 요청서만 read
    readByCondition(1); //3번 케이스로 가야함.

    //입고 요청서 승인을 여러개 할 수 있음. 입고요청서ID가 들어있는 list를 보내기
    script.updateStockRequest();
    approvedList.clear();

    try {
      //Stream, lambda사용
      Stream<Integer> inputID = Stream.generate(() -> {
        try {
          String input = br.readLine().trim();
          if (input.trim().isEmpty()) {
            return null;
          }
          return Integer.parseInt(input);
        } catch (IOException e) {
          e.printStackTrace();
          return null;
        } catch (NumberFormatException e) {
          System.out.println("숫자를 입력하시오");
          return null;
        }
      }).takeWhile(input -> input != null);

      // 스트림을 처리하여 각 ID를 리스트에 추가
      inputID.forEach(input -> {
        if (input != null) {
          approvedList.add(input);
        }
      });
    } finally {
      //요청완료 바꾸기
      if(stockRequestService.updateStatus(approvedList)){
        System.out.println("success");
      };
    }
  }

  public void updateRequest(){
    //pending list만 보여주기
    StockRequestList = stockRequestService.findByStatus(1);
    printDB(StockRequestList);
    try{
        script.updateRequestForm();
        //update할 요청서ID 받고 수정하기
        int requestID = Integer.parseInt(br.readLine());
        stockRequestService.updateForm(requestID, getFormInput());

    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch(Exception e){
      System.out.println(e.getMessage());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteRequest() throws IOException {

    //pending list 보여주기
    StockRequestList = stockRequestService.findByStatus(1);
    printDB(StockRequestList);

    System.out.println(Menu.DELETEREQUEST.getDescription());
    int requestID = Integer.parseInt(br.readLine());

    //해당 요청서 delete하기
    try{
      stockRequestService.delete(requestID);
    }catch(Exception e){
      e.getMessage();
    }
  }

  //입고 지시서는 입고 승인후 작성됨.
  public void printRequestInstr(){
    script.printInstruc();
    StockRequestList.clear();

    StockRequestList = stockRequestService.printInstr();
    System.out.println(Menu.INSTRUCOLUMN.getDescription());
    StockRequestList.forEach(instr -> System.out.println(
        instr.getId() + "\t\t\t\t" + instr.getStock_request_id().orElse(null)+ "\t\t\t\t"
            + instr.getBox_quantity() + "\t\t" + instr.getCreated_at() + "\t"
            + instr.getLoading_instr().orElse(null) + "\t\t\t" + instr.getRemarks() + "\t\t\t"));

  }

}
