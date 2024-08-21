package common;

public enum Form {

  PRODUCTID("상품 ID: "),
  BOXQUANTITY("박스 수량: "),
  BOXSIZE("박스 사이즈: "),
  CELLID("셀 구역 ID: "),
  INCOMINGDATE("입고 예정 날짜: "),
  REMARKS("비고: ");

  private final String description;

  Form(String description){
    this.description = description;
  }

  public String getDescription(){
    return description;
  }

}
