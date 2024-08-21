package controller;

import common.ValidCheck;
import dto.request.UserRequestDto;
import dto.response.AuthResponseDto;
import dto.response.UserResponseDto;
import exception.Exception;
import handler.MemberInputHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import library.Script;
import service.UserService;
import service.serviceImpl.UserServiceImpl;

public class UserController {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static MemberInputHandler memberInputHandler = new MemberInputHandler();
    private static ValidCheck validCheck = new ValidCheck();
    private static Script script = new Script();
    private static UserService userService = new UserServiceImpl();
    private static AuthResponseDto auth = new AuthResponseDto();

    /**
     * '회원 관리' 메뉴 선택 1. 정보 조회 | 2. 정보 수정 | 3. 비밀번호 변경 | 4. 탈퇴 | 5. 이전 메뉴
     */
    public void manageUser(AuthResponseDto user) throws IOException {
        auth = user;

        while (true) {
            script.manageUser();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To5(menu);

            switch (menu) {
                case "1" -> viewInfo();
                case "2" -> editUser();
                case "3" -> editPwd();
                case "4" -> unregister();
                case "5" -> {
                    return;
                }
            }
        }
    }

    /**
     * '회원 관리 > 정보 조회'
     */
    private void viewInfo() {
        try {
            UserResponseDto response = userService.findById(auth.getId());
            script.userInfo(response);
            script.viewSuccess();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            script.viewFailure();
        }
    }

    /**
     * '회원 관리 > 정보 수정'
     */
    private void editUser() {
        try {
            userService.updateUser(auth.getId(), memberInputHandler.updateUser());
            script.updateSuccess();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            script.updateFailure();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            script.updateFailure();
        }
    }

    /**
     * '회원 관리 > 비밀번호 수정'
     */
    public void editPwd() throws IOException {
        try {
            String bizNo = memberInputHandler.getBusinessNumberInput();
            userService.updateUserPwd(bizNo, memberInputHandler.updateUserPwd());
            script.updateSuccess();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            script.updateFailure();
        }
    }

    /**
     * '회원 관리 > 탈퇴' '계속 진행하시겠습니까?' 1. 예 | 2. 아니요
     */
    private void unregister() throws IOException {
        script.confirm();
        String menu = br.readLine().trim();
        validCheck.validateMenuNumber1To2(menu);

        switch (menu) {
            case "1":
                UserRequestDto request = new UserRequestDto();
                userService.unregister(auth.getId(), request);
                script.unregister();
                break;
            case "2":
                break;
        }
    }
}
