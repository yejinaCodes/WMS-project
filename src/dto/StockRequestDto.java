package dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import lombok.Data;

@Data
public class StockRequestDto {
  private int id;
  private int cell_id;
  private String product_id;
  private int supplier_id;
  private LocalDate incoming_date;
  private LocalDate created_at;
  private int box_quantity;
  private char box_size;
  private String status;
  private String remarks;
  private Optional<String> loading_instr;
  private Optional<Integer> stock_request_id;

  public StockRequestDto() {
  }

  public StockRequestDto(String product_id, int box_quantity, char box_size,
      String incoming_date, int cell_id, int supplier_id, String remarks) {
    this.product_id = product_id;
    this.box_quantity = box_quantity;
    this.box_size = box_size;
    this.cell_id = cell_id;
    this.supplier_id = supplier_id;
    this.status = "PENDING";
    this.remarks = remarks;
    setCreated_at();
    setIncoming_date(incoming_date);
  }

  public void setIncoming_date(String date){ //오전, 오후 추가
    LocalDate time = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    this.incoming_date = time;
  }
  public void setIncoming_date(LocalDate date){
    this.incoming_date = date;
  }

  public void setCreated_at(){
    this.created_at = LocalDate.now();
  }

}
