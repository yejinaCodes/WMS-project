package dto.request;

import library.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    private int id;
    private int author;
    private String title;
    private String content;
    private boolean isPrivate;
    private String createdAt;
    private String updatedAt;

    /**
     * 공지사항 생성
     */
    public BoardRequestDto(int author, String title, String content, boolean isPrivate) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.isPrivate = isPrivate;
        this.createdAt = LocalDateTime.getTime();
    }

    /**
     * 공지사항 수정
     */
    public BoardRequestDto(String title, String content, boolean isPrivate) {
        this.title = title;
        this.content = content;
        this.isPrivate = isPrivate;
        this.updatedAt = LocalDateTime.getTime();
    }
}

