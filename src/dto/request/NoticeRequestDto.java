package dto.request;

import library.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeRequestDto {
    private int id;
    private int author;
    private String title;
    private String content;
    private int viewCount;
    private String createdAt;
    private String updatedAt;

    /**
     * 공지사항 생성
     */
    public NoticeRequestDto(int author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.getTime();
    }

    /**
     * 공지사항 수정
     */
    public NoticeRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.getTime();
    }
}

