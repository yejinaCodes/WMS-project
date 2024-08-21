package common;

public enum Board {
    NOTICE_ID("공지사항 번호: "),
    BOARD_ID("게시글 번호: "),
    AUTHOR("작성자: "),
    TITLE("제목: "),
    CONTENT("내용: "),
    IS_PRIVATE_SELECT("1:1 문의 여부 (1: Yes, 2: No): "),
    IS_PRIVATE("1:1 문의 여부: "),
    VIEW_COUNT("조회수: "),
    CREATED_AT("게시글 등록일: "),
    UPDATED_AT("게시글 수정일: "),
    BORDER_LINE("+----+------------+-------------------------+-------------+-----------------------+"),
    LIST("| id |    작성자    |           제목           |     조회수    |          등록일         |");

    private final String description;

    Board(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
