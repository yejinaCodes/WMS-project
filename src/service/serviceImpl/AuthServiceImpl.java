package service.serviceImpl;

import common.ErrorCode;
import common.Member;
import common.Status;
import dao.AdminDao;
import dao.UserDao;
import dao.daoImpl.AdminDaoImpl;
import dao.daoImpl.UserDaoImpl;
import dto.request.AuthRequestDto;
import dto.response.AuthResponseDto;
import javax.naming.AuthenticationException;
import security.Encrypt;
import service.AuthService;

public class AuthServiceImpl implements AuthService {

    private Encrypt encrypt = new Encrypt();
    private AdminDao adminDao = new AdminDaoImpl();
    private UserDao userDao = new UserDaoImpl();


    @Override
    public AuthResponseDto loginUser(AuthRequestDto user) throws AuthenticationException {
        String userId = user.getId();
        AuthResponseDto auth = userDao.findAuth(userId);

        if (auth.getId() == 0) {
            throw new AuthenticationException(ErrorCode.USER_NOT_FOUND.getMessage());
        }

        String inputPwd = encrypt.getEncrypt(user.getPassword(), auth.getSalt());
        if (!inputPwd.equals(auth.getPassword())) {
            throw new AuthenticationException(ErrorCode.PASSWORD_NOT_FOUND.getMessage());
        }

        Status status = auth.getApprovalStatus();

        switch (status) {
            case PENDING -> System.out.println(Member.PENDING_STATUS.getDescription());
            case REJECTED -> System.out.println(Member.REJECTED_STATUS.getDescription() + auth.getRejectionReason());
            case APPROVED -> {
                return auth;
            }
        }
        return null;
    }

    @Override
    public AuthResponseDto loginAdmin(AuthRequestDto user) throws AuthenticationException {
        String adminId = user.getId();
        AuthResponseDto auth = adminDao.findAuth(adminId);

        if (auth == null) {
            throw new AuthenticationException(ErrorCode.USER_NOT_FOUND.getMessage());
        }

        String inputPwd = encrypt.getEncrypt(user.getPassword(), auth.getSalt());
        if (!inputPwd.equals(auth.getPassword())) {
            throw new AuthenticationException(ErrorCode.PASSWORD_NOT_FOUND.getMessage());
        }
        return auth;
    }
}
