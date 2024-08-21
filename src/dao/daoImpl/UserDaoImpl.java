package dao.daoImpl;

import common.ErrorCode;
import common.Status;
import config.ConnectionFactory;
import dao.UserDao;
import dto.request.UserApprovalRequestDto;
import dto.request.UserRequestDto;
import dto.response.AuthResponseDto;
import dto.response.UserApprovalResponseDto;
import dto.response.UserResponseDto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static Connection connection = null;
    private static ResultSet rs = null;

    @Override
    public void createUser(UserRequestDto request) {
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("INSERT INTO User ")
            .append(
                "(name, business_number, company_name, user_id, password, salt, email, phone, zip_code, address, created_at)")
            .append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, request.getName());
            pstmt.setString(2, request.getBusinessNumber());
            pstmt.setString(3, request.getCompanyName());
            pstmt.setString(4, request.getUserId());
            pstmt.setString(5, request.getPassword());
            pstmt.setString(6, request.getSalt());
            pstmt.setString(7, request.getEmail());
            pstmt.setString(8, request.getPhone());
            pstmt.setString(9, request.getZipCode());
            pstmt.setString(10, request.getAddress());
            pstmt.setString(11, request.getCreatedAt());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                String errorMessage = e.getMessage();

                if (errorMessage.contains("user.business_number")) {
                    System.err.println(ErrorCode.BIZ_NO_DUPLICATE.getError());
                } else if (errorMessage.contains("user.user_id")) {
                    System.out.println(ErrorCode.ID_DUPLICATE.getError());
                } else if (errorMessage.contains("user.email")) {
                    System.out.println(ErrorCode.EMAIL_DUPLICATE.getError());
                } else if (errorMessage.contains("user.phone")) {
                    System.out.println(ErrorCode.PHONE_DUPLICATE.getError());
                }
            } else {
                System.err.println(e.getMessage());
            }
        } finally {
            ConnectionFactory.getInstance().close();
        }
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<UserResponseDto> response = new ArrayList<>();
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("SELECT id, company_name, business_number, name ")
            .append("FROM User ").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                response.add(new UserResponseDto(rs));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }

        return response;
    }

    @Override
    public UserResponseDto findById(int id) {
        UserResponseDto response = null;
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("SELECT * ")
            .append("FROM User ")
            .append("WHERE id = ?").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                response = new UserResponseDto(rs);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }

        return response;
    }

    @Override
    public List<UserApprovalResponseDto> findByApproval() {
        List<UserApprovalResponseDto> response = new ArrayList<>();
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append(
                "SELECT u.id AS user_id, u.company_name, u.business_number, u.name, ua.approval_status, u.created_at ")
            .append("FROM User u ")
            .append("JOIN (SELECT ua.user_id, ua.approval_status, ua.rejection_reason ")
            .append("FROM UserApproval ua ")
            .append("JOIN (SELECT user_id, MAX(created_at) AS created_at ")
            .append("FROM UserApproval ")
            .append("GROUP BY user_id) latest ")
            .append("ON latest.created_at = ua.created_at AND latest.user_id = ua.user_id) ua ")
            .append("ON u.id = ua.user_id ")
            .append("WHERE ua.approval_status = 'PENDING'").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                response.add(new UserApprovalResponseDto(rs));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }

        return response;
    }

    @Override
    public String findUserId(String bizNo) {
        String response = "";
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("SELECT CONCAT(LEFT(user_id, 3), REPEAT('*', LENGTH(user_id) - 3)) AS user_id ")
            .append("FROM User ")
            .append("WHERE business_number = ?").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, bizNo);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                response = rs.getString("user_id");
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }

        return response;
    }

    @Override
    public AuthResponseDto findAuth(String userId) {
        AuthResponseDto response = null;
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("{call user_login(?, ?, ?, ?, ?, ?)}").toString();

        try {
            CallableStatement cstmt = connection.prepareCall(query);

            cstmt.setString(1, userId);

            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            cstmt.registerOutParameter(6, Types.VARCHAR);

            cstmt.executeQuery();

            int id = cstmt.getInt(2);
            String password = cstmt.getString(3);
            String salt = cstmt.getString(4);
            String approvalStatus = cstmt.getString(5);
            String rejectionReason = cstmt.getString(6);
            response = new AuthResponseDto(id, password, salt, approvalStatus, rejectionReason);

            cstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }
        return response;
    }


    @Override
    public void updateUser(int id, UserRequestDto request) {
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("UPDATE User ")
            .append("SET ")
            .append("name = ?, business_number = ?, company_name = ?, user_id = ?, ")
            .append("email = ?, phone = ?, zip_code = ?, address = ?, updated_at = ? ")
            .append("WHERE id = ?").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, request.getName());
            pstmt.setString(2, request.getBusinessNumber());
            pstmt.setString(3, request.getCompanyName());
            pstmt.setString(4, request.getUserId());
            pstmt.setString(5, request.getEmail());
            pstmt.setString(6, request.getPhone());
            pstmt.setString(7, request.getZipCode());
            pstmt.setString(8, request.getAddress());
            pstmt.setString(9, request.getUpdatedAt());
            pstmt.setInt(10, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }
    }

    @Override
    public void updateUserPwd(String bizNo, UserRequestDto request) {
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("UPDATE User ")
            .append("SET ")
            .append("password = ? , salt = ?, updated_at = ?")
            .append("WHERE business_number = ?").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, request.getPassword());
            pstmt.setString(2, request.getSalt());
            pstmt.setString(3, request.getUpdatedAt());
            pstmt.setString(4, bizNo);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }
    }

    @Override
    public void updateApprovalStatus(int auth, int id, UserApprovalRequestDto request) {
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("INSERT INTO UserApproval ")
            .append("(user_id, approver_id, approval_status, rejection_reason, created_at) ")
            .append("VALUES (?, ?, ?, ?, ?) ").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setInt(2, auth);
            pstmt.setString(3, String.valueOf(request.getApprovalStatus()));
            pstmt.setString(4, request.getRejectionReason());
            pstmt.setString(5, request.getCreatedAt());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }
    }

    @Override
    public void unregister(int id, UserRequestDto request) {
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("UPDATE User ")
            .append("SET ")
            .append("is_unregister = ? , updated_at = ?, unregistered_at = ?")
            .append("WHERE id = ?").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, request.getIsUnregister());
            pstmt.setString(2, request.getUpdatedAt());
            pstmt.setString(3, request.getUnregisteredAt());
            pstmt.setInt(4, 5);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }
    }

    @Override
    public void delete(int id) {
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("DELETE FROM User ")
            .append("WHERE id = ?").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }
    }
}
