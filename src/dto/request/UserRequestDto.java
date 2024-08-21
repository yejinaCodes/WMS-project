package dto.request;

import common.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import library.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserRequestDto {
    private static final int TRUE = 1;
    private static final int FALSE = 0;

    private int id;
    private String name;
    private String businessNumber;
    private String companyName;
    private String userId;
    private String password;
    private String salt;
    private String email;
    private String phone;
    private String zipCode;
    private String address;
    private int isUnregister;
    private String createdAt;
    private String updatedAt;
    private int updatedAdminId;
    private String updatedAdminAt;
    private String unregisteredAt;

    /**
     * 회원 가입
     */
    public UserRequestDto(String name, String businessNumber, String companyName,
        String userId, String salt, String password, String email, String phone,
        String zipCode, String address) {
        this.name = name;
        this.businessNumber = businessNumber;
        this.companyName = companyName;
        this.userId = userId;
        this.salt = salt;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.zipCode = zipCode;
        this.address = address;
        this.createdAt = LocalDateTime.getTime();
    }

    /**
     * 정보 수정
     */
    public UserRequestDto(String name, String businessNumber, String companyName, String userId,
        String email, String phone, String zipCode, String address) {
        this.name = name;
        this.businessNumber = businessNumber;
        this.companyName = companyName;
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.zipCode = zipCode;
        this.address = address;
        this.updatedAt = LocalDateTime.getTime();
    }

    /**
     * 비밀번호 수정
     */
    public UserRequestDto(String salt, String password) {
        this.salt = salt;
        this.password = password;
        this.updatedAt = LocalDateTime.getTime();
    }

    /**
     * 탈퇴 수정
     */
    public UserRequestDto() {
        this.isUnregister = TRUE;
        this.updatedAt = LocalDateTime.getTime();
        this.unregisteredAt = LocalDateTime.getTime();
    }

    @Getter
    @NoArgsConstructor
    public static class UserApprovalRequestDto {

        private int id;
        private int userId;
        private int approverId;
        private Status approvalStatus;
        private String rejectionReason;
        private String createdAt;
        private String businessNumber;
        private String companyName;

        public UserApprovalRequestDto(ResultSet rs) throws SQLException {
            this.userId = rs.getInt("user_id");
            this.businessNumber = rs.getString("business_number");
            this.companyName = rs.getString("company_name");
            this.createdAt = rs.getString("created_at");
            this.approvalStatus = Status.valueOf(rs.getString("approval_status"));
        }
    }
}