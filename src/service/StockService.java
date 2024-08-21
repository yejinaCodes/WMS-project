package service;


import dto.StockDto;
import java.util.List;

public interface StockService {
    List<StockDto> getAllStock(); // 전체 재고 조회
    List<StockDto> getUserStock(int uID);    // 특정 사용자의 재고 조회
    List<StockDto> getFilteredAdminStock(String category, int type);  // 대분류, 중분류, 소분류에 따른 재고 조회
    List<StockDto> getFilteredUserStock(int uID, String category, int type);
}