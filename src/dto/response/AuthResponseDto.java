package dto.response;

import common.Department;
import common.Position;
import common.Role;
import common.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthResponseDto {

    private int id;
    private String password;
    private String salt;
    private Status approvalStatus;
    private String rejectionReason;
    private Department department;
    private Role role;

    public AuthResponseDto(int id, String password, String salt, String approvalStatus,
        String rejectionReason) {
        this.id = id;
        this.password = password;
        this.salt = salt;
        this.approvalStatus = Optional.ofNullable(approvalStatus)
            .map(s -> {
                try {
                    return Status.valueOf(s);
                } catch (IllegalArgumentException e) {
                    return null;
                }
            })
            .orElse(null);
        this.rejectionReason = rejectionReason;
    }

    public AuthResponseDto(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.password = rs.getString("pwd");
        this.salt = rs.getString("salt");
        this.department = Optional.ofNullable(rs.getString("department"))
            .map(d -> {
                try {
                    return Department.valueOf(d);
                } catch (IllegalArgumentException e) {
                    return null;
                }
            })
            .orElse(null);
        this.role = Optional.ofNullable(rs.getString("role"))
            .map(d -> {
                try {
                    return Role.valueOf(d);
                } catch (IllegalArgumentException e) {
                    return null;
                }
            })
            .orElse(null);
    }
}
