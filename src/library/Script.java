package library;

import common.Board;
import common.Member;
import common.Menu;
import common.ResponseMessage;
import dto.response.AdminResponseDto;
import dto.response.BoardResponseDto;
import dto.response.NoticeResponseDto;
import dto.response.UserApprovalResponseDto;
import dto.response.UserResponseDto;

public class Script {

    private static StringBuilder script = new StringBuilder();

    private void print() {
        System.out.println(script);
        script.delete(0, script.length());
    }

    /**
     * 서비스 이용자의 타입 받기
     * 1. 쇼핑몰 | 2. 어드민
     */
    public void selectUserType() {
        script.append(Menu.BOARD_SEPARATOR.getDescription())
            .append(Menu.USER_TYPE.getDescription())
            .append(Menu.USER_TYPE_SELECT.getDescription());
        print();
    }

    /**
     * 메뉴 선택
     * 1. 회원 가입 | 2. 로그인 | 3. 아이디 찾기 | 4. 비밀번호 재설정
     */
    public void selectLoginOrRegister() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_AUTH.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * 회원(쇼핑몰) 페이지 메뉴 선택
     * 1. 회원 관리 | 2. 창고 관리 | 3. 재고 관리
     * 4. 입고 관리 | 5. 출고 관리 | 6. 고객 센터
     * 7. 로그아웃
     */
    public void userMainMenu() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_USER.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * 어드민 페이지 메뉴 선택
     * 1. 회원 관리 | 2. 재무 관리 | 3. 창고 관리 | 4. 재고 관리
     * 5. 입고 관리 | 6. 출고 관리 | 7. 고객 센터 | 8. 로그아웃
     */
    public void adminMainMenu() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * '회원 관리' 메뉴 선택
     * 1. 조회 | 2. 수정 | 3. 권한 설정 | 4. 삭제 | 5. 이전 메뉴
     */
    public void manageMember() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN_MEMBER.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * '회원 관리 > 조회' 메뉴 선택
     * 1. 직원 조회 | 2. 쇼핑몰 사업자 회원 조회 | 3. 이전 메뉴
     */
    public void viewMember() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN_MEMBER_VIEW.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * '회원 관리 > 조회 > 직원 조회' 메뉴 선택
     * 1. 직원 상세 조회 | 2. 직원 전체 조회 | 3. 권한별 직원 조회 | 4. 이전 메뉴
     */
    public void viewAdmin() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN_MEMBER_VIEW_ADMIN.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * '회원 관리 > 조회 > 직원 조회 > 3. 권한별 직원 조회'
     */
    public void viewMemberRole() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN_ROLE.getDescription())
            .append(Menu.ROLE_SELECT.getDescription());
        print();
    }

    /**
     * '회원 관리 > 조회 > 쇼핑몰 사업자 회원 조회' 메뉴 선택
     * 1. 쇼핑몰 회원 상세 조회 | 2. 쇼핑몰 회원 전체 조회 | 3. 승인 대기자 조회 | 4. 이전 메뉴
     */
    public void viewUser() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN_MEMBER_VIEW_USER.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * '회원 관리 > 수정' 메뉴 선택
     * 1. 회원 정보 수정 | 2. 비밀번호 수정 | 3. 이전 메뉴
     */
    public void editMember() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN_MEMBER_EDIT.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * '회원 관리 > 권한 설정' 메뉴 선택
     * 1. 직원 권한 | 2. 직원 부서 및 직급 | 3. 쇼핑몰 사업자 가입 승인 | 4. 이전 메뉴
     */
    public void setMemberPermission() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN_MEMBER_PERMISSION.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * '회원 관리 > 삭제' 메뉴 선택
     * 1. 직원 삭제 | 2. 쇼핑몰 사업자 회원 삭제 | 3. 이전 메뉴
     */
    public void deleteMember() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN_DELETE.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * '회원 관리' 메뉴 선택
     * 1. 정보 조회 | 2. 정보 수정 | 3. 비밀번호 변경 | 4. 탈퇴 | 5. 이전 메뉴
     */
    public void manageUser() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_USER_MEMBER.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * 진행 확인
     */
    public void confirm() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.CONFIRM.getDescription());
        print();
    }

    /**
     * 사원 번호 입력 받기
     */
    public void getAdminId() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.ADMIN_ID_INPUT.getDescription());
        print();
    }

    /**
     * 회원 ID 입력 받기
     */
    public void getUserId() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.USER_ID_INPUT.getDescription());
        print();
    }

    /**
     * 사업자 번호 입력 받기
     */
    public void getBizNo() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.BIZ_NO_INPUT.getDescription());
        print();
    }

    /**
     * 부서 입력 받기
     */
    public void getDepartment() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN_DEPARTMENT.getDescription())
            .append(Menu.ROLE_SELECT.getDescription());
        print();
    }

    /**
     * 직급 입력 받기
     */
    public void getPosition() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN_POSITION.getDescription())
            .append(Menu.ROLE_SELECT.getDescription());
        print();
    }

    /**
     * 권한 입력 받기
     */
    public void getRole() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_ADMIN_ROLE.getDescription())
            .append(Menu.ROLE_SELECT.getDescription());
        print();
    }

    /**
     * 승인 상태 입력 받기
     */
    public void getStatus() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.STATUS_MENU.getDescription())
            .append(Menu.STATUS_SELECT.getDescription());
        print();
    }

    /**
     * 승인 거절 이유 입력 받기
     */
    public void getRejectionReason() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.REJECTION_REASON_INPUT.getDescription());
        print();
    }

    /**
     * 로그인
     */
    public void login() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.LOGIN.getDescription());
        print();
    }

    /**
     * 로그인 성공
     */
    public void loginSuccess() {
        script.append(ResponseMessage.LOGIN_SUCCESS.getMessage());
        print();
    }

    /**
     * 로그인 실패
     */
    public void loginFailure() {
        script.append(ResponseMessage.LOGIN_FAILURE.getMessage());
        print();
    }

    /**
     * 로그아웃
     */
    public void logout() {
        script.append(ResponseMessage.LOGOUT.getMessage());
        print();
    }

    /**
     * 탈퇴
     */
    public void unregister() {
        script.append(ResponseMessage.UNREGISTER.getMessage());
        print();
    }

    /**
     * 정보 생성 성공
     */
    public void createSuccess() {
        script.append(ResponseMessage.CREATE_SUCCESS.getMessage());
        print();
    }

    /**
     * 정보 생성 실패
     */
    public void createFailure() {
        script.append(ResponseMessage.CREATE_FAILURE.getMessage());
        print();
    }

    /**
     * 정보 조회 성공
     */
    public void viewSuccess() {
        script.append(ResponseMessage.VIEW_SUCCESS.getMessage());
        print();
    }

    /**
     * 정보 조회 실패
     */
    public void viewFailure() {
        script.append(ResponseMessage.VIEW_FAILURE.getMessage());
        print();
    }

    /**
     * 정보 수정 성공
     */
    public void updateSuccess() {
        script.append(ResponseMessage.UPDATE_SUCCESS.getMessage());
        print();
    }

    /**
     * 정보 수정 실패
     */
    public void updateFailure() {
        script.append(ResponseMessage.UPDATE_FAILURE.getMessage());
        print();
    }

    /**
     * 정보 삭제 성공
     */
    public void deleteSuccess() {
        script.append(ResponseMessage.DELETE_SUCCESS.getMessage());
        print();
    }

    /**
     * 정보 삭제 실패
     */
    public void deleteFailure() {
        script.append(ResponseMessage.DELETE_FAILURE.getMessage());
        print();
    }

    /**
     * 직원 내역 조회 title
     */
    public void adminListTitle() {
        script.append(Member.ADMIN_BORDER_LINE.getDescription()).append("\n")
            .append(Member.ADMIN_LIST.getDescription()).append("\n")
            .append(Member.ADMIN_BORDER_LINE.getDescription());
        print();
    }

    /**
     * 직원 내역 조회 Border line
     */
    public void adminListBorder() {
        script.append(Member.ADMIN_BORDER_LINE.getDescription());
        print();
    }

    /**
     * 직원 전체 내역 조회
     */
    public void adminList(AdminResponseDto response) {
        script.append(response.formatAdminList());
        print();
    }

    /**
     * 쇼핑몰 유저 내역 조회 title
     */
    public void userListTitle() {
        script.append(Member.USER_BORDER_LINE.getDescription()).append("\n")
            .append(Member.USER_LIST.getDescription()).append("\n")
            .append(Member.USER_BORDER_LINE.getDescription());
        print();
    }

    /**
     * 쇼핑몰 유저 내역 조회 Border line
     */
    public void userListBorder() {
        script.append(Member.USER_BORDER_LINE.getDescription());
        print();
    }

    /**
     * 쇼핑몰 유저 전체 내역 조회
     */
    public void userList(UserResponseDto response) {
        script.append(response.formatAdminList());
        print();
    }

    /**
     * 쇼핑몰 유저 가입 승인 내역 조회 title
     */
    public void userApprovalListTitle() {
        script.append(Member.USER_APPROVAL_BORDER_LINE.getDescription()).append("\n")
            .append(Member.USER_APPROVAL_LIST.getDescription()).append("\n")
            .append(Member.USER_APPROVAL_BORDER_LINE.getDescription());
        print();
    }

    /**
     * 쇼핑몰 유저 가입 승인 내역 조회 Border line
     */
    public void userApprovalListBorder() {
        script.append(Member.USER_APPROVAL_BORDER_LINE.getDescription());
        print();
    }

    /**
     * 쇼핑몰 유저 가입 승인 전체 내역 조회
     */
    public void userApprovalList(UserApprovalResponseDto response) {
        script.append(response.formatAdminList());
        print();
    }

    /**
     * 아이디 찾기 조회
     */
    public void accountId(String response) {
        script.append(Menu.BORDER_LINE.getDescription()).append("\n")
            .append(Member.ID.getDescription()).append(response).append("\n");
        print();
    }
    public void selectUserMenu(){
        script.append(Menu.USERMENUSELECT.getDescription());
        print();
    }
    public void selectAdminMenu(){
        script.append(Menu.ADMINMENUSELECT.getDescription());
        print();
    }
    public void readStockRequest(){
        script.append(Menu.FINDCONDITION.getDescription());
        print();
    }
    public void readStockRequestStatus(){
        script.append(Menu.FINDSTATUSWMS.getDescription());
        print();
    }
    public void updateStockRequest(){
        script.append(Menu.UPDATESTATUS.getDescription());
        print();
    }
    public void updateRequestForm(){
        script.append(Menu.UPDATEREQUEST.getDescription());
        print();
    }

    public void cancelStockRequest(){
        script.append(Menu.DELETEWARNING.getDescription());
        print();
    }

    public void printInstruc(){
        script.append(Menu.PRINTINSTRC.getDescription());
        print();
    }

    /**
     * '고객 센터' 메뉴 선택
     * 1. 공지사항 | 2. 게시글 | 3. 1:1 문의 내역 | 4. 이전 메뉴
     */
    public void supportMenu() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_SUPPORT.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * 1. 생성 | 2. 조회 | 3. 수정 | 4. 삭제 | 5. 이전 메뉴
     */
    public void showCRUDMenu() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_CRUD.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * 1. 전체 조회 | 2. 상세 조회 | 3. 이전 메뉴
     */
    public void showViewMenu() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Menu.MENU_VIEW.getDescription())
            .append(Menu.MENU_SELECT.getDescription());
        print();
    }

    /**
     * 공지사항 & 게시글
     */
    public void getTitle() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Board.TITLE.getDescription());
        print();
    }

    public void getContent() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Board.CONTENT.getDescription());
        print();
    }

    public void getIsPrivate() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Board.IS_PRIVATE_SELECT.getDescription());
        print();
    }

    public void getNoticeId() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Board.NOTICE_ID.getDescription());
        print();
    }

    public void getBoardId() {
        script.append(Menu.BORDER_LINE.getDescription())
            .append(Board.BOARD_ID.getDescription());
        print();
    }


    /**
     * 공지사항 내역 조회 title
     */
    public void boardListTitle() {
        script.append(Board.BORDER_LINE.getDescription()).append("\n")
            .append(Board.LIST.getDescription()).append("\n")
            .append(Board.BORDER_LINE.getDescription());
        print();
    }

    /**
     * 공지사항 내역 조회 Border line
     */
    public void boardListBorder() {
        script.append(Board.BORDER_LINE.getDescription());
        print();
    }

    /**
     * 공지시항 전체 내역 조회
     */
    public void noticeList(NoticeResponseDto response) {
        script.append(response.formatNoticeList());
        print();
    }

    /**
     * 게시글 전체 내역 조회
     */
    public void boardList(BoardResponseDto response) {
        script.append(response.formatBoardList());
        print();
    }

    /**
     * 상세 내역
     */
    /**
     * 직원 상세 내역 조회
     */
    public void adminInfo(AdminResponseDto response) {
        script.append(Menu.BORDER_LINE.getDescription()).append("\n")
            .append(Member.ADMIN_ID.getDescription()).append(response.getId()).append("\n")
            .append(Member.NAME.getDescription()).append(response.getName()).append("\n")
            .append(Member.ID.getDescription()).append(response.getAdminId()).append("\n")
            .append(Member.ADMIN_COMPANY_EMAIL.getDescription()).append(response.getCompanyEmail()).append("\n")
            .append(Member.DEPARTMENT.getDescription()).append(response.getDepartment()).append("\n")
            .append(Member.POSITION.getDescription()).append(response.getPosition()).append("\n")
            .append(Member.ROLE.getDescription()).append(response.getRole()).append("\n")
            .append(Member.PHONE.getDescription()).append(response.getPhone()).append("\n")
            .append(Member.CREATED_AT.getDescription()).append(response.getCreatedAt()).append("\n")
            .append(Member.AUTHORIZER_ID.getDescription()).append(response.getAuthorizerId()).append("\n")
            .append(Member.UPDATED_AT.getDescription()).append(response.getUpdatedAt()).append("\n");
        print();
    }

    /**
     * 쇼핑몰 유저 상세 내역 조회
     */
    public void userInfo(UserResponseDto response) {
        script.append(Menu.BORDER_LINE.getDescription()).append("\n")
            .append(Member.USER_ID.getDescription()).append(response.getId()).append("\n")
            .append(Member.NAME.getDescription()).append(response.getName()).append("\n")
            .append(Member.BUSINESS_NUMBER.getDescription()).append(response.getBusinessNumber()).append("\n")
            .append(Member.COMPANY_NAME.getDescription()).append(response.getCompanyName()).append("\n")
            .append(Member.ID.getDescription()).append(response.getUserId()).append("\n")
            .append(Member.EMAIL.getDescription()).append(response.getEmail()).append("\n")
            .append(Member.PHONE.getDescription()).append(response.getPhone()).append("\n")
            .append(Member.ZIP_CODE.getDescription()).append(response.getZipCode()).append("\n")
            .append(Member.ADDRESS.getDescription()).append(response.getAddress()).append("\n")
            .append(Member.IS_UNREGISTER.getDescription()).append(response.getIsUnregister()).append("\n")
            .append(Member.CREATED_AT.getDescription()).append(response.getCreatedAt()).append("\n")
            .append(Member.UPDATED_AT.getDescription()).append(response.getUpdatedAt()).append("\n")
            .append(Member.UPDATED_ADMIN_ID.getDescription()).append(response.getUpdatedAdminId()).append("\n")
            .append(Member.UPDATED_ADMIN_AT.getDescription()).append(response.getUpdatedAdminAt()).append("\n")
            .append(Member.UNREGISTERED_AT.getDescription()).append(response.getUnregisteredAt()).append("\n");
        print();
    }

    /**
     * 공지사항 상세 내역 조회
     */
    public void noticeInfo(NoticeResponseDto response) {
        script.append(Menu.BORDER_LINE.getDescription()).append("\n")
            .append(Board.AUTHOR.getDescription()).append(response.getAuthor()).append("\n")
            .append(Board.TITLE.getDescription()).append(response.getTitle()).append("\n")
            .append(Board.CONTENT.getDescription()).append(response.getContent()).append("\n")
            .append(Board.VIEW_COUNT.getDescription()).append(response.getViewCount()).append("\n")
            .append(Board.CREATED_AT.getDescription()).append(response.getCreatedAt()).append("\n")
            .append(Board.UPDATED_AT.getDescription()).append(response.getUpdatedAt()).append("\n");
        print();
    }

    /**
     * 게시글 상세 내역 조회
     */
    public void boardInfo(BoardResponseDto response) {
        script.append(Menu.BORDER_LINE.getDescription()).append("\n")
            .append(Board.AUTHOR.getDescription()).append(response.getAuthor()).append("\n")
            .append(Board.IS_PRIVATE.getDescription()).append(response.isPrivate()).append("\n")
            .append(Board.TITLE.getDescription()).append(response.getTitle()).append("\n")
            .append(Board.CONTENT.getDescription()).append(response.getContent()).append("\n")
            .append(Board.VIEW_COUNT.getDescription()).append(response.getViewCount()).append("\n")
            .append(Board.CREATED_AT.getDescription()).append(response.getCreatedAt()).append("\n")
            .append(Board.UPDATED_AT.getDescription()).append(response.getUpdatedAt()).append("\n");
        print();
    }
}
