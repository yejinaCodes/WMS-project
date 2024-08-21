package dao.daoImpl;

import config.ConnectionFactory;
import dao.BoardDao;
import dto.request.BoardRequestDto;
import dto.response.BoardResponseDto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImpl implements BoardDao {

    private static Connection connection = null;
    private static ResultSet rs = null;

    @Override
    public void createBoard(BoardRequestDto request) {
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("INSERT INTO Board ")
            .append("(author, title, content, is_private, created_at) ")
            .append("VALUES (?, ?, ?, ?, ?)").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, request.getAuthor());
            pstmt.setString(2, request.getTitle());
            pstmt.setString(3, request.getContent());
            pstmt.setBoolean(4, request.isPrivate());
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
    public BoardResponseDto findById(int id) {
        BoardResponseDto response = null;
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("{call board_detail(?, ?, ?, ?, ?, ?, ?, ?)}").toString();

        try {
            CallableStatement cstmt = connection.prepareCall(query);

            cstmt.setInt(1, id);

            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            cstmt.registerOutParameter(6, Types.VARCHAR);
            cstmt.registerOutParameter(7, Types.VARCHAR);
            cstmt.registerOutParameter(8, Types.VARCHAR);

            cstmt.executeQuery();

            String author = cstmt.getString(2);
            String title = cstmt.getString(3);
            String content = cstmt.getString(4);
            boolean isPrivate = cstmt.getBoolean(5);
            int viewCount = cstmt.getInt(5);
            String createdAt = cstmt.getString(6);
            String updatedAt = cstmt.getString(7);
            response = new BoardResponseDto(id, author, title, content, isPrivate, viewCount, createdAt, updatedAt);

            cstmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionFactory.getInstance().close();
        }
        return response;
    }

    @Override
    public List<BoardResponseDto> findAllPublic() {
        List<BoardResponseDto> response = new ArrayList<>();
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("SELECT b.id, u.name AS author, b.title, b.view_count, b.created_at ")
            .append("FROM Board b ")
            .append("JOIN User u ON u.id = b.author ")
            .append("WHERE b.is_private = 0").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                response.add(new BoardResponseDto(rs));
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
    public List<BoardResponseDto> findAllPrivateAdmin() {
        List<BoardResponseDto> response = new ArrayList<>();
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("SELECT b.id, u.name AS author, b.title, b.view_count, b.created_at ")
            .append("FROM Board b ")
            .append("JOIN User u ON u.id = b.author ")
            .append("WHERE b.is_private = 1").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                response.add(new BoardResponseDto(rs));
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
    public List<BoardResponseDto> findAllPrivateUser(int id) {
        List<BoardResponseDto> response = new ArrayList<>();
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("SELECT b.id, u.name AS author, b.title, b.view_count, b.created_at ")
            .append("FROM Board b ")
            .append("JOIN User u ON u.id = b.author ")
            .append("WHERE b.is_private = 1 AND author = ?").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                response.add(new BoardResponseDto(rs));
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
    public void update(int id, BoardRequestDto request) {
        connection = ConnectionFactory.getInstance().open();
        String query = new StringBuilder()
            .append("UPDATE Board ")
            .append("SET ")
            .append("title = ?, content = ?, is_private = ?, updated_at = ? ")
            .append("WHERE id = ?").toString();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, request.getTitle());
            pstmt.setString(2, request.getContent());
            pstmt.setBoolean(3, request.isPrivate());
            pstmt.setString(4, request.getUpdatedAt());
            pstmt.setInt(5, id);

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
            .append("DELETE FROM Board ")
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
