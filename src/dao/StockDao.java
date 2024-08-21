package dao;


import dto.StockDto;
import java.util.List;

public interface StockDao {
    List<StockDto> findStockAdmin();
    List<StockDto> findStockUser(int uID);
}