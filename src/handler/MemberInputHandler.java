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
import dto.request.UserApprovalRequestDto;
import dto.request.UserRequestDto;
import dto.response.AuthResponseDto;
import exception.Exception;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import library.Script;
import security.Encrypt;

public class MemberInputHandler {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Script script = new Script();
    private static ValidCheck validCheck = new ValidCheck();
    private static Encrypt encrypt = new Encrypt();
    private static UserDao userDao = new UserDaoImpl();

    public AdminRequestDto createAdmin() throws IOException {
        String salt = getsalt();
        AdminRequestDto admin = new AdminRequestDto(getNameInput(), getIdInput(), salt, getPwdInput(salt), getPhoneInput());
        return admin;
    }

    public AdminRequestDto updateAdmin() throws IOException {
        AdminRequestDto admin = new AdminRequestDto(getNameInput(), getIdInput(), getPhoneInput());
        return admin;
    }

    public AdminRequestDto updateAdminPwd() throws IOException {
        String salt = getsalt();
        AdminRequestDto admin = new AdminRequestDto(salt, getPwdInput(salt));
        return admin;
    }

    public AdminRequestDto updateAdminRole() throws IOException {
        AdminRequestDto admin = new AdminRequestDto(getRoleInput());
        return admin;
    }

    public AdminRequestDto updateAdminDeptPos() throws IOException {
        AdminRequestDto admin = new AdminRequestDto(getDepartmentInput(), getPositionInput());
        return admin;
    }

    public UserApprovalRequestDto updateApprovalStatus() throws IOException {
        Status status = getApprovalStatusInput();
        if (status.equals(Status.REJECTED)) {
            return new UserApprovalRequestDto(status, getReasonInput(status));
        } else {
            return  new UserApprovalRequestDto(status, null);
        }
    }

    public UserRequestDto createUser() throws IOException {
        String salt = getsalt();

        UserRequestDto user = new UserRequestDto(getNameInput(), getBusinessNumberInput(), getCompanyNameInput(),
            getIdInput(), salt, getPwdInput(salt), getEmailInput(), getPhoneInput(), getZipCodeInput(), getAddressInput());
        return user;
    }

    public AuthRequestDto login() throws IOException {
        AuthRequestDto auth = new AuthRequestDto(getIdInput(), getPwdInput());
        return auth;
    }

    public UserRequestDto updateUser() throws IOException {
        UserRequestDto user = new UserRequestDto(getNameInput(), getBusinessNumberInput(), getCompanyNameInput(),
            getIdInput(), getEmailInput(), getPhoneInput(), getZipCodeInput(), getAddressInput());
        return user;
    }

    public UserRequestDto updateUserPwd() throws IOException {
        String salt = getsalt();
        UserRequestDto user = new UserRequestDto(salt, getPwdInput(salt));
        return user;
    }

    public int getAdminIdInput() throws IOException {
        try {
            script.getAdminId();
            return validCheck.validateNumber(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getAdminIdInput();
        }
    }

    public int getUserIdInput() throws IOException {
        try {
            script.getUserId();
            return validCheck.validateNumber(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getUserIdInput();
        }
    }

    public String getNameInput() throws IOException {
        try {
            System.out.print(Member.NAME.getDescription());
            return validCheck.validateStringLength30(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getNameInput();
        }
    }

    public String getBusinessNumberInput() throws IOException {
        try {
            System.out.print(Member.BUSINESS_NUMBER.getDescription());
            return validCheck.validateBizNo(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getBusinessNumberInput();
        }
    }

    public String getCompanyNameInput() throws IOException {
        try {
            System.out.print(Member.COMPANY_NAME.getDescription());
            return validCheck.validateStringLength50(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getCompanyNameInput();
        }
    }

    public String getIdInput() throws IOException {
        try {
            System.out.print(Member.ID.getDescription());
            return validCheck.validateId(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getIdInput();
        }
    }

    public String getsalt() throws IOException {
        return encrypt.getSalt();
    }

    public String getPwdInput(String salt) throws IOException {
        try {
            System.out.print(Member.PW.getDescription());
            return encrypt.getEncrypt(validCheck.validatePw(br.readLine()), salt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getPwdInput(salt);
        }
    }

    public String getPwdInput() throws IOException {
        try {
            System.out.print(Member.PW.getDescription());
            return validCheck.validatePw(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getPwdInput();
        }
    }

    public String getEmailInput() throws IOException {
        try {
            System.out.print(Member.EMAIL.getDescription());
            return validCheck.validateEmail(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getEmailInput();
        }
    }

    public Department getDepartmentInput() throws IOException {
        try {
            Department department = null;

            script.getDepartment();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To5(menu);

            switch (menu) {
                case "1" -> department = Department.valueOf("HR");
                case "2" -> department = Department.valueOf("WAREHOUSE");
                case "3" -> department = Department.valueOf("DELIVERY");
                case "4" -> department = Department.valueOf("DEVELOPMENT");
                case "5" -> department = Department.valueOf("ACCOUNTING");
            }
            return department;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getDepartmentInput();
        }
    }

    public Position getPositionInput() throws IOException {
        try {
            Position position = null;

            script.getPosition();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To3(menu);

            switch (menu) {
                case "1":
                    position = Position.valueOf("SUPER_ADMIN");
                    break;
                case "2":
                    position = Position.valueOf("ADMIN");
                    break;
                case "3":
                    position = Position.valueOf("EMPLOYEE");
                    break;
            }
            return position;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getPositionInput();
        }
    }

    public Role getRoleInput() throws IOException {
        try {
            Role role = null;

            script.getRole();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To3(menu);

            switch (menu) {
                case "1":
                    role = Role.valueOf("SUPER_ADMIN");
                    break;
                case "2":
                    role = Role.valueOf("ADMIN");
                    break;
                case "3":
                    role = Role.valueOf("EMPLOYEE");
                    break;
            }
            return role;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getRoleInput();
        }
    }

    public String getPhoneInput() throws IOException {
        try {
            System.out.print(Member.PHONE.getDescription());
            return validCheck.validatePhone(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getPhoneInput();
        }
    }

    public String getZipCodeInput() throws IOException {
        try {
            System.out.print(Member.ZIP_CODE.getDescription());
            return validCheck.validateStringLength10(br.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getZipCodeInput();
        }
    }

    public String getAddressInput() throws IOException {
        try {
            System.out.print(Member.ADDRESS.getDescription());
            return br.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getAddressInput();
        }
    }

    public Status getApprovalStatusInput() throws IOException {
        try {
            Status status = null;

            script.getStatus();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To3(menu);

            switch (menu) {
                case "1":
                    status = Status.valueOf("PENDING");
                    break;
                case "2":
                    status = Status.valueOf("APPROVED");
                    break;
                case "3":
                    status = Status.valueOf("REJECTED");
                    break;
            }
            return status;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getApprovalStatusInput();
        }
    }

    public String getReasonInput(Status status) {
        try {
            script.getRejectionReason();
            return br.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return getReasonInput(status);
        }
    }
}
