package dao;

import dto.StockRequestDto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StockRequestDao {

  void create(StockRequestDto stockRequest) throws IOException, SQLException;

  ArrayList<StockRequestDto> findByAll() throws SQLException;
  ArrayList<StockRequestDto> findById(int requestId) throws SQLException;
  ArrayList<StockRequestDto> findByProductId(String productId) throws SQLException;
  ArrayList<StockRequestDto> findByCreatedDate(String createdDate) throws SQLException;
  ArrayList<StockRequestDto> findByIncomingDate(String incomindDate) throws SQLException;

  ArrayList<StockRequestDto> findByStatus(int status) throws SQLException;


  boolean updateStatus(ArrayList<Integer> updateList) throws SQLException;

  boolean updateForm(int formID,StockRequestDto updateForm) throws SQLException;

  void delete(int formID);
  ArrayList<StockRequestDto> printInstr();

}
