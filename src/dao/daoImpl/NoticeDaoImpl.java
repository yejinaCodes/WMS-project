package dao.daoImpl;

import config.ConnectionFactory;
import dao.NoticeDao;
import dto.request.NoticeRequestDto;
import dto.response.NoticeResponseDto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao {

    private static Connection connection = null;
    private static ResultSet rs = null;

    @Override
    public void createNotice(NoticeRequestDto request) {
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("INSERT INTO Notice ")
            .append("(author, title, content, created_at)")
            .append("VALUES (?, ?, ?, ?)").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, request.getAuthor());
            pstmt.setString(2, request.getTitle());
            pstmt.setString(3, request.getContent());
            pstmt.setString(4, request.getCreatedAt());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }
    }

    @Override
    public NoticeResponseDto findById(int id) {
        NoticeResponseDto response = null;
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("{call notice_detail(?, ?, ?, ?, ?, ?, ?)}").toString();

        try {
            CallableStatement cstmt = connection.prepareCall(query);

            cstmt.setInt(1, id);

            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            cstmt.registerOutParameter(6, Types.VARCHAR);
            cstmt.registerOutParameter(7, Types.VARCHAR);

            cstmt.executeQuery();

            String author = cstmt.getString(2);
            String title = cstmt.getString(3);
            String content = cstmt.getString(4);
            int viewCount = cstmt.getInt(5);
            String createdAt = cstmt.getString(6);
            String updatedAt = cstmt.getString(7);
            response = new NoticeResponseDto(id, author, title, content, viewCount, createdAt, updatedAt);

            cstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }
        return response;
    }

    @Override
    public List<NoticeResponseDto> findAll() {
        List<NoticeResponseDto> response = new ArrayList<>();
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("SELECT n.id, a.name AS author, n.title, n.view_count, n.created_at ")
            .append("FROM Notice n ")
            .append("JOIN Admin a ON a.id = n.author ").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                response.add(new NoticeResponseDto(rs));
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
    public void update(int id, NoticeRequestDto request) {
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("UPDATE Notice ")
            .append("SET ")
            .append("title = ?, content = ?, updated_at = ? ")
            .append("WHERE id = ?").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, request.getTitle());
            pstmt.setString(2, request.getContent());
            pstmt.setString(3, request.getUpdatedAt());
            pstmt.setInt(4, id);

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
            .append("DELETE FROM Notice ")
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
