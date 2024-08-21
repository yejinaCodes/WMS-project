package common;

public enum ResponseMessage{

    LOGIN_SUCCESS("\n로그인 성공 🙂\n"),
    LOGIN_FAILURE("\n🚨로그인 실패🚨\n"),
    LOGOUT("\n로그아웃 되었습니다. 👋\n"),
    CREATE_SUCCESS("\n정보 생성 성공 🙂\n"),
    CREATE_FAILURE("\n🚨정보 생성 실패🚨\n"),
    VIEW_SUCCESS("\n정보 조회 성공 🙂\n"),
    VIEW_FAILURE("\n🚨정보 조회 실패🚨\n"),
    UPDATE_SUCCESS("\n정보 수정 성공 🙂\n"),
    UPDATE_FAILURE("\n🚨정보 수정 실패🚨\n"),
    DELETE_SUCCESS("\n정보 삭제 성공 🙂\n"),
    DELETE_FAILURE("\n🚨정보 삭제 실패🚨\n"),
    UNREGISTER("\n탈퇴 성공 🙂\n");


    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
