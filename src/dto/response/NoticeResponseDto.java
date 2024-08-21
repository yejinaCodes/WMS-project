package dto.response;

import java.sql.ResultSet;
import java.sql.SQLException;
import library.ResultSetReader;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeResponseDto {
    private int id;
    private String author;
    private String title;
    private String content;
    private int viewCount;
    private String createdAt;
    private String updatedAt;

    private static ResultSetReader rsr = new ResultSetReader();

    public NoticeResponseDto(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.author = rs.getString("author");
            this.title = rs.getString("title");
            this.content = rsr.getColumnValue(rs, "content", String.class);
            this.viewCount = rs.getInt("view_count");
            this.createdAt = rs.getString("created_at");
            this.updatedAt = rsr.getColumnValue(rs,"created_at", String.class);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public NoticeResponseDto(int id, String author, String title, String content, int viewCount, String createdAt, String updatedAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String formatNoticeList() {
        return String.format("| %2d | %-9s\t| %-20s \t| %10d\t| %-20s\t|",
            id,
            author,
            title,
            viewCount,
            createdAt
        );
    }
}
