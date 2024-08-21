package handler;

import common.ValidCheck;
import dto.request.BoardRequestDto;
import dto.request.NoticeRequestDto;
import exception.Exception;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import library.Script;

public class BoardInputHandler {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Script script = new Script();
    private static ValidCheck validCheck = new ValidCheck();

    public NoticeRequestDto createNotice(int author) throws IOException {
        NoticeRequestDto notice = new NoticeRequestDto(author, getTitleInput(), getContentInput());
        return notice;
    }

    public NoticeRequestDto updateNotice() throws IOException {
        return new NoticeRequestDto(getTitleInput(), getContentInput());
    }

    public BoardRequestDto createBoard(int author) throws IOException {
        BoardRequestDto board = new BoardRequestDto(author, getTitleInput(), getContentInput(), getPrivateInput());
        return board;
    }

    public BoardRequestDto updateBoard() throws IOException {
        return new BoardRequestDto(getTitleInput(), getContentInput(), getPrivateInput());
    }


    public String getTitleInput() throws IOException {
        while (true) {
            try {
                script.getTitle();
                return validCheck.validateStringLength30(br.readLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getContentInput() throws IOException {
        while (true) {
            script.getContent();
            return br.readLine();
        }
    }


    public boolean getPrivateInput() throws IOException {
        boolean isPrivate = false;
        while (true) {
            try {
                script.getIsPrivate();
                String menu = br.readLine().trim();
                validCheck.validateMenuNumber1To2(menu);

                switch (menu) {
                    case "1" -> {
                        return !isPrivate;
                    }
                    case "2" -> {
                        return isPrivate;
                    }

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public int getNoticeIdInput() throws IOException {
        try {
            script.getNoticeId();
            return validCheck.validateNumber(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getNoticeIdInput();
        }
    }


    public int getBoardIdInput() throws IOException {
        try {
            script.getBoardId();
            return validCheck.validateNumber(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getBoardIdInput();
        }
    }
}
