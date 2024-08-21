package dto.response;

import common.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserApprovalResponseDto {

    private int id;
    private int userId;
    private int approverId;
    private String name;
    private Status approvalStatus;
    private String rejectionReason;
    private String createdAt;
    private String businessNumber;
    private String companyName;

    public UserApprovalResponseDto(ResultSet rs) throws SQLException {
        this.userId = rs.getInt("user_id");
        this.companyName = rs.getString("company_name");
        this.businessNumber = rs.getString("business_number");
        this.name = rs.getString("name");
        this.approvalStatus = Status.valueOf(rs.getString("approval_status"));
        this.createdAt = rs.getString("created_at");
    }

    public String formatAdminList() {
        return String.format("| %-2d | %-20s \t| %-15s\t| %-9s\t| %-13s\t| %-20s\t|",
            userId,
            companyName,
            businessNumber,
            name,
            approvalStatus,
            createdAt
        );
    }
}
