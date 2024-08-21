package common;

public enum ErrorCode {
    NULL_VALUE(404, "❗️❗️️입력값이 비어 있습니다.️❗️️\n❗️❗️️공백이 아닌 실제 값을 입력해 주세요.❗️❗️️"),
    INVALID_VALUE(404, "❗️❗️️입력 오류가 발생했습니다.❗️❗️️\n❗️❗️️다시 시도해 주세요.❗️❗️️"),
    INVALID_MENU_OPTION(404, "❗️❗️️유효하지 않은 메뉴 번호입니다.️❗️️\n❗️❗️️올바른 번호를 입력해 주세요❗️❗️️"),
    INVALID_NUMBER(404, "❗️❗️️숫자를 입력해 주세요.❗️❗️️"),
    INVALID_LENGTH_10(404, "❗️❗️️입력 길이는 10자 이하이어야 합니다.❗️❗️️"),
    INVALID_LENGTH_30(404, "❗️❗️️입력 길이는 30자 이하이어야 합니다.❗️❗️️"),
    INVALID_LENGTH_50(404, "❗️❗️️입력 길이는 50자 이하이어야 합니다.❗️❗️️"),
    INVALID_ID(404, "❗️❗️️유효하지 않은 ID입니다.❗️❗️️\n❗️❗️️ID는 5자 이상 15자 이하의 영문과 숫자로만 이루어져야 합니다.❗️❗️️"),
    INVALID_PASSWORD(404, "❗️❗️️유효하지 않은 비밀번호입니다.❗️❗️️\n❗️❗️️비밀번호는 8자 이상 20자 이하로 영문자, 숫자, 특수문자(.!@#$%^)만 포함되어야 합니다.❗️❗️️"),
    INVALID_EMAIL(404, "❗️❗️️유효하지 않은 이메일 주소입니다. 올바른 형식으로 입력해 주세요.❗️❗️️\n❗️❗️️예시> example@gmail.com❗️❗️️"),
    INVALID_PHONE(404, "❗️❗️️유효하지 않은 전화번호입니다. 올바른 형식으로 입력해 주세요.❗️❗️️\n❗️❗️️예시> 01012345678❗️❗️️"),
    INVALID_BIZ_NO(404, "❗️❗️️유효하지 않은 사업자 번호입니다. 올바른 형식으로 입력해 주세요.❗️❗️️\n❗️❗️️예시> 000-00-00000❗️❗️️"),
    USER_NOT_FOUND(404, "❗️❗️️존재하지 않는 아이디 입니다.❗️❗️"),
    PASSWORD_NOT_FOUND(404, "❗️❗️️잘못된 비밀번호 입니다.❗️❗️"),
    FAILURE_LOGIN(404, "❗️❗️️최대 시도 횟수를 초과했습니다.❗️❗️"),
    ID_DUPLICATE(409, "❗️❗️️이미 존재하는 ID입니다. 다른 ID를 선택해주세요.❗️❗️"),
    EMAIL_DUPLICATE(409, "❗️❗️️이미 존재하는 이메일 입니다. 다른 이메일을 선택해주세요.❗️❗️"),
    PHONE_DUPLICATE(409, "❗️❗️️이미 존재하는 전화번호 입니다. 다른 전화번호를 선택해주세요.❗️❗️"),
    BIZ_NO_DUPLICATE(409, "❗️❗️️이미 존재하는 사업자 번호 입니다. 관리자에게 문의해주세요.❗️❗️"),

    NOREQUESTAVAILABLE(410, "존재하지 않은 입고 요청서 입니다.️");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getError() { return "[" + code + "]" + " " + message; }
}
