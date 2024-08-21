package controller;

import dto.request.UserRequestDto;
import dto.response.AuthResponseDto;
import dto.response.UserApprovalResponseDto;
import dto.response.UserResponseDto;
import exception.Exception;
import handler.MemberInputHandler;
import common.Role;
import dto.response.AdminResponseDto;
import java.util.List;
import library.Script;
import common.ValidCheck;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import service.AdminService;
import service.UserService;
import service.serviceImpl.AdminServiceImpl;
import service.serviceImpl.UserServiceImpl;

public class AdminController {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static MemberInputHandler memberInputHandler = new MemberInputHandler();
    private static ValidCheck validCheck = new ValidCheck();
    private static Script script = new Script();
    private static AdminService adminService = new AdminServiceImpl();
    private static UserService userService = new UserServiceImpl();
    private static AuthResponseDto auth = new AuthResponseDto();


    /**
     * '회원 관리' 메뉴 선택 1. 조회 | 2. 수정 | 3. 권한 설정 | 4. 삭제 | 5. 이전 메뉴
     */
    public void manageMember(AuthResponseDto user) throws IOException {
        auth = user;
        while (true) {
            script.manageMember();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To5(menu);

            switch (menu) {
                case "1" -> viewMember();
                case "2" -> editMember();
                case "3" -> setMemberRole();
                case "4" -> delete();
                case "5" -> {
                    return;
                }
            }
        }
    }

    /**
     * '회원 관리 > 조회' 메뉴 선택 1. 직원 조회 | 2. 쇼핑몰 사업자 회원 조회 | 3. 이전 메뉴
     */
    private void viewMember() throws IOException {

        while (true) {
            try {
                script.viewMember();
                String menu = br.readLine().trim();
                validCheck.validateMenuNumber1To3(menu);

                switch (menu) {
                    case "1" -> viewAdmin();
                    case "2" -> viewUser();
                    case "3" -> {
                        return;
                    }
                }
                script.viewSuccess();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            }
        }

    }

    /**
     * '회원 관리 > 조회 > 직원 조회' 메뉴 선택 1. 직원 상세 조회 | 2. 직원 전체 조회 | 3. 권한별 직원 조회 | 4. 이전 메뉴
     */
    private void viewAdmin() throws IOException {
        while (true) {
            try {
                script.viewAdmin();
                String menu = br.readLine().trim();
                validCheck.validateMenuNumber1To4(menu);

                switch (menu) {
                    case "1" -> viewAdminDetail();
                    case "2" -> viewAllAdmin();
                    case "3" -> viewAdminByRole();
                    case "4" -> {
                        return;
                    }
                }

                script.viewSuccess();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            }
        }
    }

    /**
     * '회원 관리 > 조회 > 직원 조회 > 직원 상세 조회'
     */
    private void viewAdminDetail() {
        try {
            script.getAdminId();
            int id = validCheck.validateNumber(br.readLine());
            AdminResponseDto response = adminService.findById(id);
            script.adminInfo(response);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * '회원 관리 > 조회 > 직원 조회 > 직원 전체 조회'
     */
    private void viewAllAdmin() {
        List<AdminResponseDto> list = adminService.findAll();
        script.adminListTitle();
        list.forEach(l -> script.adminList(l));
        script.adminListBorder();
    }

    /**
     * '회원 관리 > 조회 > 직원 조회 > 권한별 직원 조회' 메뉴 선택 1. 총 관리자 | 2. 창고 관리자 | 3. 일반
     */
    private void viewAdminByRole() throws IOException {
        script.viewMemberRole();
        String menu = br.readLine().trim();
        validCheck.validateMenuNumber1To3(menu);
        String role = "";

        switch (menu) {
            case "1" -> role = Role.SUPER_ADMIN.toString();
            case "2" -> role = Role.ADMIN.toString();
            case "3" -> role = Role.EMPLOYEE.toString();
        }

        List<AdminResponseDto> list = adminService.findByRole(role);
        script.adminListTitle();
        list.forEach(l -> script.adminList(l));
        script.adminListBorder();
    }


    /**
     * '회원 관리 > 조회 > 쇼핑몰 사업자 회원 조회' 메뉴 선택 1. 쇼핑몰 회원 상세 조회 | 2. 쇼핑몰 회원 전체 조회 | 3. 승인 대기자 조회 | 4. 이전
     * 메뉴
     */
    private void viewUser() {

        while (true) {
            try {
                script.viewUser();
                String menu = br.readLine().trim();
                validCheck.validateMenuNumber1To4(menu);

                switch (menu) {
                    case "1" -> viewUserDetail();
                    case "2" -> viewAllUser();
                    case "3" -> viewPendingApproval();
                    case "4" -> {
                        return;
                    }
                }
                script.viewSuccess();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            }
        }
    }
    private void viewUserDetail() throws IOException {
        int targetUserId = memberInputHandler.getUserIdInput();
        UserResponseDto response = userService.findById(targetUserId);
        script.userInfo(response);
    }

    private void viewAllUser() {
        List<UserResponseDto> list = userService.findAll();
        script.userListTitle();
        list.forEach(l -> script.userList(l));
        script.userListBorder();
    }

    private void viewPendingApproval() {
        List<UserApprovalResponseDto> list = userService.findByApproval();
        script.userApprovalListTitle();
        list.forEach(l -> script.userApprovalList(l));
        script.userApprovalListBorder();
    }

    /**
     * '회원 관리 > 수정' 메뉴 선택 1. 회원 정보 수정 | 2. 비밀번호 수정 | 3. 이전 메뉴
     */
    private void editMember() {

        while (true) {
            try {
                script.editMember();
                String menu = br.readLine().trim();
                validCheck.validateMenuNumber1To3(menu);

                switch (menu) {
                    case "1" -> editAdmin();
                    case "2" -> editPwd();
                    case "3" -> {
                        return;
                    }
                }
                script.updateSuccess();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                script.updateFailure();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                script.updateFailure();
            }
        }
    }

    /**
     * '회원 관리 > 수정 > 회원 정보 수정'
     */
    private void editAdmin() throws IOException {
        adminService.updateAdmin(auth.getId(), memberInputHandler.updateAdmin());
    }

    /**
     * '회원 관리 > 수정 > 비밀번호 수정'
     */
    public void editPwd() throws IOException {
        String adminId = memberInputHandler.getIdInput();
        adminService.updatePwd(adminId, memberInputHandler.updateAdminPwd());
    }

    /**
     * '회원 관리 > 권한 설정' 메뉴 선택 1. 직원 권한 | 2. 직원 부서 및 직급 | 3. 쇼핑몰 사업자 가입 승인 | 4. 이전 메뉴
     */
    private void setMemberRole() {

        while (true) {
            try {
                script.setMemberPermission();
                String menu = br.readLine().trim();
                validCheck.validateMenuNumber1To4(menu);

                switch (menu) {
                    case "1" -> setAdminRole();
                    case "2" -> setDeptAndPosition();
                    case "3" -> approveUser();
                    case "4" -> {
                        return;
                    }
                }
                script.updateSuccess();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                script.updateFailure();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                script.updateFailure();
            }
        }
    }

    /**
     * '회원 관리 > 권한 설정 > 직원 권한' 권한 선택 입력값: 사원 번호, 권한 1. 총 관리자 | 2. 창고 관리자 | 3. 일반
     */
    private void setAdminRole() throws IOException {
        int targetEmployeeId = memberInputHandler.getAdminIdInput();
        adminService.updateRole(auth.getId(), targetEmployeeId,
            memberInputHandler.updateAdminRole());
    }

    /**
     * '회원 관리 > 권한 설정 > 직원 부서 및 직급' 권한 선택
     */
    private void setDeptAndPosition() throws IOException {
        int targetEmployeeId = memberInputHandler.getAdminIdInput();
        adminService.updateAdminDeptPos(auth.getId(), targetEmployeeId,
            memberInputHandler.updateAdminDeptPos());
    }

    /**
     * '회원 관리 > 권한 설정 > 쇼핑몰 사업자 권한 승인' 권한 선택
     */
    private void approveUser() throws IOException {
        int targetUserId = memberInputHandler.getUserIdInput();
        userService.updateApprovalStatus(auth.getId(), targetUserId,
            memberInputHandler.updateApprovalStatus());
    }

    /**
     * '회원 관리 > 삭제' 메뉴 선택 1. 직원 삭제 | 2. 쇼핑몰 사업자 회원 삭제 | 3. 이전 메뉴
     */
    private void delete() throws IOException {

        while (true) {
            try {
                script.deleteMember();
                String menu = br.readLine().trim();
                validCheck.validateMenuNumber1To3(menu);

                switch (menu) {
                    case "1" -> deleteAdmin();
                    case "2" -> deleteUser();
                    case "3" -> {
                        return;
                    }
                }

                script.deleteSuccess();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                script.deleteFailure();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                script.deleteFailure();
            }
        }
    }


    /**
     * '회원 관리 > 삭제 > 직원 삭제'
     */
    private void deleteAdmin() throws IOException {
        int targetEmployeeId = memberInputHandler.getAdminIdInput();
        script.confirm();
        String menu = br.readLine().trim();
        validCheck.validateMenuNumber1To2(menu);

        switch (menu) {
            case "1":
                adminService.deleteAdmin(targetEmployeeId);
                break;
            case "2":
                break;
        }
    }

    private void deleteUser() throws IOException {
        int targetUserId = memberInputHandler.getUserIdInput();

        script.confirm();
        String menu = br.readLine().trim();
        validCheck.validateMenuNumber1To2(menu);

        switch (menu) {
            case "1":
                UserRequestDto request = new UserRequestDto();
                userService.delete(targetUserId);
                break;
            case "2":
                break;
        }
    }
}
