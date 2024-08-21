package service.serviceImpl;

import dao.daoImpl.StockRequestDaoImpl;
import dto.StockRequestDto;
import java.sql.SQLException;
import java.util.ArrayList;
import service.StockRequestService;

public class StockRequestServiceImpl implements StockRequestService {
  StockRequestDaoImpl po = new StockRequestDaoImpl();
  @Override
  public void create(StockRequestDto stockRequest) {
    po.create(stockRequest);
  }

  @Override
  public ArrayList<StockRequestDto> findByAll() throws SQLException {
    return po.findByAll();
  }

  @Override
  public ArrayList<StockRequestDto> findById(int requestId){
    return po.findById(requestId);
  }

  @Override
  public ArrayList<StockRequestDto> findByProductId(String productId){
    return po.findByProductId(productId);
  }

  @Override
  public ArrayList<StockRequestDto> findByCreatedDate(String createdDate){
    return po.findByCreatedDate(createdDate);
  }

  @Override
  public ArrayList<StockRequestDto> findByIncomingDate(String incomingDate){
    return po.findByIncomingDate(incomingDate);
  }


  @Override
  public ArrayList<StockRequestDto> findByStatus(int status){
    return po.findByStatus(status);
  }

  @Override
  public boolean updateStatus(ArrayList<Integer> updateList){
    return po.updateStatus(updateList);
  }


  @Override
  public boolean updateForm(int formID, StockRequestDto form) throws SQLException {
    return po.updateForm(formID, form);
  }
  @Override
  public void delete(int formID){
    po.delete(formID);
  }

  @Override
  public ArrayList<StockRequestDto> printInstr(){
    return po.printInstr();
  }


}
