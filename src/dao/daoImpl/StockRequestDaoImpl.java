package dao.daoImpl;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import common.ErrorCode;
import config.ConnectionFactory;
import dao.StockRequestDao;
import dto.StockRequestDto;
import exception.Exception;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class StockRequestDaoImpl implements StockRequestDao {

  ArrayList<StockRequestDto> stockRequestDb = new ArrayList<StockRequestDto>();
  private static ResultSet rs = null;


  @Override
  public void create(StockRequestDto stockRequest) {
    Connection connection = ConnectionFactory.getInstance().open();

    String query = new StringBuilder()
        .append("INSERT INTO stockRequest (supplierID, productID, boxQuantity, boxSize, "
            + "incomingDate, cellID, approvalStatus, remarks, createdAt)")
        .append("VALUES(?,?,?,?,?,?,?,?,?)")
        .toString();

    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      pstmt.setString(1, String.valueOf(stockRequest.getSupplier_id()));
      pstmt.setString(2, stockRequest.getProduct_id());
      pstmt.setString(3, String.valueOf(stockRequest.getBox_quantity()));
      pstmt.setString(4, String.valueOf(stockRequest.getBox_size()));
      pstmt.setString(5, String.valueOf(stockRequest.getIncoming_date()));
      pstmt.setString(6, String.valueOf(stockRequest.getCell_id()));
      pstmt.setString(7, stockRequest.getStatus());
      pstmt.setString(8, stockRequest.getRemarks());
      pstmt.setString(9, String.valueOf(stockRequest.getCreated_at()));

      pstmt.executeUpdate();
      pstmt.close();
      ConnectionFactory.getInstance().close();
      System.out.println("\n**요청서 입력 완료**");

    } catch (Exception | SQLException e) {
      System.err.println("입고 요청서 DB 전송 실패");
    }
    ConnectionFactory.getInstance().close();
  }

  @Override
  public ArrayList<StockRequestDto> findByAll() throws SQLException {
    stockRequestDb.clear();
    Connection connection = ConnectionFactory.getInstance().open();

    String query = new StringBuilder()
        .append("SELECT * from stockRequest")
        .toString();

    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        StockRequestDto stockRequest = new StockRequestDto();
        stockRequest.setId(rs.getInt("ID"));
        stockRequest.setProduct_id(rs.getString("productID"));
        stockRequest.setBox_quantity(rs.getInt("boxQuantity"));
        stockRequest.setBox_size(rs.getString("boxSize").charAt(0));
        stockRequest.setCell_id(rs.getInt("cellID"));
        stockRequest.setStatus(rs.getString("approvalStatus"));
        stockRequest.setRemarks(rs.getString("remarks"));
        stockRequest.setCreated_at(LocalDate.parse(rs.getString("createdAt")));
        stockRequest.setIncoming_date(LocalDate.parse(rs.getString("incomingDate")));

        stockRequestDb.add(stockRequest);
        stockRequest = null;
      }

      pstmt.close();
      ConnectionFactory.getInstance().close();

    } catch (Exception e) {
      System.err.println("입고 요청서 DB list 받기 실패");
    }
    ConnectionFactory.getInstance().close();
    return stockRequestDb;
  }

  @Override
  public ArrayList<StockRequestDto> findById(int requestId){
    stockRequestDb.clear();
    Connection connection = ConnectionFactory.getInstance().open();

    String query = new StringBuilder()
        .append("SELECT * from stockRequest ")
        .append("WHERE ID = ?")
        .toString();

    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      pstmt.setInt(1, requestId);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        StockRequestDto stockRequest = new StockRequestDto();

        stockRequest.setId(rs.getInt("ID"));
        stockRequest.setProduct_id(rs.getString("productID"));
        stockRequest.setBox_quantity(rs.getInt("boxQuantity"));
        stockRequest.setBox_size(rs.getString("boxSize").charAt(0));
        stockRequest.setCell_id(rs.getInt("cellID"));
        stockRequest.setStatus(rs.getString("approvalStatus"));
        stockRequest.setRemarks(rs.getString("remarks"));
        stockRequest.setCreated_at(LocalDate.parse(rs.getString("createdAt")));
        stockRequest.setIncoming_date(LocalDate.parse(rs.getString("incomingDate")));

        stockRequestDb.add(stockRequest);
      }
      pstmt.close();
      ConnectionFactory.getInstance().close();
    }catch (Exception | SQLException e) {
      System.err.println("DB 입고요청서 불러오기 실패");
      System.err.println(ErrorCode.NOREQUESTAVAILABLE.getMessage());

    }
    return stockRequestDb;
  }

  @Override
  public ArrayList<StockRequestDto> findByProductId(String productId){
    stockRequestDb.clear();
    Connection connection = ConnectionFactory.getInstance().open();

    String query = new StringBuilder()
        .append("SELECT * from stockRequest ")
        .append("WHERE productID = ?")
        .toString();
    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      pstmt.setString(1, productId);

      rs = pstmt.executeQuery();
      while(rs.next()){
        StockRequestDto stockRequest = new StockRequestDto();

        stockRequest.setId(rs.getInt("ID"));
        stockRequest.setProduct_id(rs.getString("productID"));
        stockRequest.setBox_quantity(rs.getInt("boxQuantity"));
        stockRequest.setBox_size(rs.getString("boxSize").charAt(0));
        stockRequest.setCell_id(rs.getInt("cellID"));
        stockRequest.setStatus(rs.getString("approvalStatus"));
        stockRequest.setRemarks(rs.getString("remarks"));
        stockRequest.setCreated_at(LocalDate.parse(rs.getString("createdAt")));
        stockRequest.setIncoming_date(LocalDate.parse(rs.getString("incomingDate")));

        stockRequestDb.add(stockRequest);
      }
      pstmt.close();
      ConnectionFactory.getInstance().close();

    }catch (Exception | SQLException e) {
      System.err.println("DB 입고요청서 불러오기 실패");
    }
    return stockRequestDb;
  }

  @Override
  public ArrayList<StockRequestDto> findByCreatedDate(String createdDate){
    stockRequestDb.clear();
    Connection connection = ConnectionFactory.getInstance().open();

    String query = new StringBuilder()
        .append("SELECT * from stockRequest ")
        .append("WHERE createdAt = ?")
        .toString();
    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      pstmt.setString(1, createdDate);
      rs = pstmt.executeQuery();

      while(rs.next()) {
        StockRequestDto stockRequest = new StockRequestDto();

        stockRequest.setId(rs.getInt("ID"));
        stockRequest.setProduct_id(rs.getString("productID"));
        stockRequest.setBox_quantity(rs.getInt("boxQuantity"));
        stockRequest.setBox_size(rs.getString("boxSize").charAt(0));
        stockRequest.setCell_id(rs.getInt("cellID"));
        stockRequest.setStatus(rs.getString("approvalStatus"));
        stockRequest.setRemarks(rs.getString("remarks"));
        stockRequest.setCreated_at(LocalDate.parse(rs.getString("createdAt")));
        stockRequest.setIncoming_date(LocalDate.parse(rs.getString("incomingDate")));

        stockRequestDb.add(stockRequest);
      }
      pstmt.close();
      ConnectionFactory.getInstance().close();
    }catch (Exception | SQLException e) {
      System.err.println("DB 입고요청서 불러오기 실패");
    }
    return stockRequestDb;

  }

  @Override
  public ArrayList<StockRequestDto> findByIncomingDate(String incomingDate){
    stockRequestDb.clear();
    Connection connection = ConnectionFactory.getInstance().open();

    String query = new StringBuilder()
        .append("SELECT * from stockRequest ")
        .append("WHERE incomingDate = ?")
        .toString();

    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      Date sqlDate = Date.valueOf(LocalDate.parse(incomingDate, formatter));
      pstmt.setDate(1, sqlDate);
      //pstmt.setString(1, incomingDate);
      rs = pstmt.executeQuery();

      while(rs.next()) {
        StockRequestDto stockRequest = new StockRequestDto();

        stockRequest.setId(rs.getInt("ID"));
        stockRequest.setProduct_id(rs.getString("productID"));
        stockRequest.setBox_quantity(rs.getInt("boxQuantity"));
        stockRequest.setBox_size(rs.getString("boxSize").charAt(0));
        stockRequest.setCell_id(rs.getInt("cellID"));
        stockRequest.setStatus(rs.getString("approvalStatus"));
        stockRequest.setRemarks(rs.getString("remarks"));
        stockRequest.setCreated_at(LocalDate.parse(rs.getString("createdAt")));
        stockRequest.setIncoming_date(LocalDate.parse(rs.getString("incomingDate")));

        stockRequestDb.add(stockRequest);
      }
      pstmt.close();
      ConnectionFactory.getInstance().close();
    }catch (Exception | SQLException e) {
      System.err.println("DB 입고요청서 불러오기 실패");
      System.err.println(ErrorCode.NOREQUESTAVAILABLE.getMessage());
    }
    return stockRequestDb;
  }

  @Override
  public ArrayList<StockRequestDto> findByStatus(int status) {
    stockRequestDb.clear();
    Connection connection = ConnectionFactory.getInstance().open();

    String query = new StringBuilder()
        .append("SELECT * from stockRequest ")
        .append("WHERE approvalStatus like ? ")
        .append("ORDER BY createdAt asc")
        .toString();

    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      pstmt.setString(1, status == 1 ? "PENDING" : "APPROVED");
      rs = pstmt.executeQuery();
      while (rs.next()) {
        StockRequestDto stockRequest = new StockRequestDto();

        stockRequest.setId(rs.getInt("ID"));
        stockRequest.setProduct_id(rs.getString("productID"));
        stockRequest.setBox_quantity(rs.getInt("boxQuantity"));
        stockRequest.setBox_size(rs.getString("boxSize").charAt(0));
        stockRequest.setCell_id(rs.getInt("cellID"));
        stockRequest.setStatus(rs.getString("approvalStatus"));
        stockRequest.setRemarks(rs.getString("remarks"));
        stockRequest.setCreated_at(LocalDate.parse(rs.getString("createdAt")));
        stockRequest.setIncoming_date(LocalDate.parse(rs.getString("incomingDate")));

        stockRequestDb.add(stockRequest);
      }
      pstmt.close();
      ConnectionFactory.getInstance().close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return stockRequestDb;
  }

  @Override
  public boolean updateStatus(ArrayList<Integer> updateList) {
    stockRequestDb.clear();
    boolean result = false;
    Connection connection = ConnectionFactory.getInstance().open();

    String query = new StringBuilder()
        .append("UPDATE stockRequest ")
        .append("SET approvalStatus = 'APPROVED' ")
        .append("WHERE approvalStatus = 'PENDING' and ID = ?")
        .toString();

    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      //procedure사용하기
      Stream<Integer> strm = updateList.stream();
      strm.forEach(id -> {
            try {
              pstmt.setInt(1, id);
              pstmt.executeUpdate();
            } catch (SQLException e) {
              throw new RuntimeException(e);
            }
          }
      );
      result = true;
      pstmt.close();
      ConnectionFactory.getInstance().close();

    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    }
    return result;
  }

  @Override
  public boolean updateForm(int formID, StockRequestDto updateForm) throws SQLException {
    Connection connection = ConnectionFactory.getInstance().open();

    String query = new StringBuilder()
        .append("UPDATE stockRequest ")
        .append("SET productID = ?, boxQuantity = ?, boxSize = ?, "
            + "incomingDate = ?, cellID = ?, remarks = ? ")
        .append("WHERE ID = ?")
        .toString();

    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      pstmt.setString(1, String.valueOf(updateForm.getProduct_id()));
      pstmt.setString(2, String.valueOf(updateForm.getBox_quantity()));
      pstmt.setString(3, String.valueOf(updateForm.getBox_size()));
      pstmt.setDate(4, Date.valueOf(updateForm.getIncoming_date()));
      pstmt.setString(5, String.valueOf(updateForm.getCell_id()));
      pstmt.setString(6, String.valueOf(updateForm.getRemarks()));
      pstmt.setString(7, String.valueOf(formID));

      pstmt.executeUpdate();
      System.out.println("\n**수정 완료**");
      pstmt.close();
      ConnectionFactory.getInstance().close();
    } catch (RuntimeException e) {
      System.err.println(e.getMessage());
      return false;
    }
    return true;
  }

  @Override
  public void delete(int formID) {
    Connection connection = ConnectionFactory.getInstance().open();

    String query = new StringBuilder()
        .append("DELETE FROM stockRequest ")
        .append("WHERE ID = ?")
        .toString();

    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      pstmt.setString(1, String.valueOf(formID));
      int rowsAffected = pstmt.executeUpdate();

      pstmt.close();
      ConnectionFactory.getInstance().close();
      if (rowsAffected == 0) {
        System.err.println(ErrorCode.NOREQUESTAVAILABLE.getMessage());
      } else {
        System.out.println("**취소 완료**");
      }
    } catch (SQLException e) {
      if ("02000".equals(e.getSQLState())) {
        System.err.println("해당 요청서는 DB에 존재하지 않습니다.");
      } else {
        e.printStackTrace();
      }
    }
  }

  @Override
  public ArrayList<StockRequestDto> printInstr(){
    Connection connection = ConnectionFactory.getInstance().open();
    stockRequestDb.clear();
    String query = new StringBuilder()
        .append("SELECT * FROM receivingInstructions") //CellID도 가지고 와야 함.
        .toString();

    try {
      PreparedStatement pstmt = connection.prepareStatement(query);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        StockRequestDto stockRequest = new StockRequestDto();

        stockRequest.setId(rs.getInt("ID"));
        stockRequest.setStock_request_id(Optional.ofNullable(rs.getInt("stockRequestID")));
        stockRequest.setBox_quantity(rs.getInt("boxUnit"));
        stockRequest.setCreated_at(LocalDate.parse(rs.getString("createdAt")));
        //stockRequest.setCell_id(rs.getInt("cellID"));
        stockRequest.setLoading_instr(Optional.ofNullable(rs.getString("loadingInstrc")));
        stockRequest.setRemarks(rs.getString("remarks"));;

        stockRequestDb.add(stockRequest);
      }
      pstmt.close();
      ConnectionFactory.getInstance().close();

    } catch (Exception e) {
      System.out.println("입고지시서 DB list 받기 실패");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    ConnectionFactory.getInstance().close();
    return stockRequestDb;
  }
}