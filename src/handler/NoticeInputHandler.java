package handler;

import common.Department;
import common.Member;
import common.Position;
import common.Role;
import common.Status;
import common.ValidCheck;
import dao.UserDao;
import dao.daoImpl.UserDaoImpl;
import dto.request.AdminRequestDto;
import dto.request.AuthRequestDto;
import dto.request.NoticeRequestDto;
import dto.request.UserApprovalRequestDto;
import dto.request.UserRequestDto;
import exception.Exception;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import library.Script;
import security.Encrypt;

public class NoticeInputHandler {

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


    public int getNoticeIdInput() throws IOException {
        try {
            script.getNoticeId();
            return validCheck.validateNumber(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getNoticeIdInput();
        }
    }
}
