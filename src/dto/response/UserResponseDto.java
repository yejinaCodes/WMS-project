package dto.response;

import java.sql.ResultSet;
import java.sql.SQLException;
import library.ResultSetReader;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private int id;
    private String name;
    private String businessNumber;
    private String companyName;
    private String userId;
    private String password;
    private String email;
    private String phone;
    private String zipCode;
    private String address;
    private Integer isUnregister;
    private String createdAt;
    private String updatedAt;
    private Integer updatedAdminId;
    private String updatedAdminAt;
    private String unregisteredAt;

    private static ResultSetReader rsr = new ResultSetReader();

    public UserResponseDto(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.businessNumber = rs.getString("business_number");
        this.companyName = rs.getString("company_name");
        this.userId = rsr.getColumnValue(rs,"user_id", String.class);
        this.password = rsr.getColumnValue(rs,"password", String.class);
        this.email = rsr.getColumnValue(rs,"email", String.class);
        this.phone = rsr.getColumnValue(rs,"phone", String.class);
        this.zipCode = rsr.getColumnValue(rs,"zip_code", String.class);
        this.address = rsr.getColumnValue(rs,"address", String.class);
        this.isUnregister = rsr.getColumnValue(rs,"is_unregister", Integer.class);
        this.createdAt = rsr.getColumnValue(rs,"created_at", String.class);
        this.updatedAt = rsr.getColumnValue(rs,"updated_at", String.class);
        this.updatedAdminId = rsr.getColumnValue(rs,"updated_admin_id", Integer.class);
        this.updatedAdminAt = rsr.getColumnValue(rs,"updated_admin_at", String.class);
        this.unregisteredAt = rsr.getColumnValue(rs,"unregistered_at", String.class);
    }
    public String formatAdminList() {
        return String.format("| %-2d | %-20s \t| %-15s\t| %-9s\t|",
            id,
            companyName,
            businessNumber,
            name
        );
    }
}
