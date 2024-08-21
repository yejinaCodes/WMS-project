import controller.StockRequestController;

public class StockRequestMain {

  public static void main(String[] args) {
    try {
      StockRequestController poc = new StockRequestController();

      //로그인 정보에 따라 진행. 사용자 아이디 보내주기.
      //poc.userMenu();
      poc.adminMenu();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

