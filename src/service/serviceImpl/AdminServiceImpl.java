package service.serviceImpl;

import dao.AdminDao;
import dao.daoImpl.AdminDaoImpl;
import dto.request.AdminRequestDto;
import dto.response.AdminResponseDto;
import java.util.List;
import service.AdminService;

public class AdminServiceImpl implements AdminService {

    private static AdminDao adminDao = new AdminDaoImpl();
    @Override
    public void createAdmin(AdminRequestDto request) {
        adminDao.createAdmin(request);
    }

    @Override
    public AdminResponseDto findById(int id) {
        return adminDao.findById(id);
    }

    @Override
    public List<AdminResponseDto> findAll() {
        return adminDao.findAll();
    }

    @Override
    public List<AdminResponseDto> findByRole(String role) {
        return adminDao.findByRole(role);
    }

    @Override
    public String findAdminId(int id) {
        return adminDao.findAdminId(id);
    }

    @Override
    public void updateAdmin(int auth, AdminRequestDto request) {
        adminDao.updateAdmin(auth, request);
    }

    @Override
    public void updatePwd(String adminId, AdminRequestDto request) {
        adminDao.updatePwd(adminId, request);
    }

    @Override
    public void updateRole(int auth, int targetEmployeeId, AdminRequestDto request) {
        adminDao.updateRole(auth, targetEmployeeId, request);

    }

    @Override
    public void updateAdminDeptPos(int auth, int targetEmployeeId, AdminRequestDto request) {
        adminDao.updateAdminDeptPos(auth, targetEmployeeId, request);
    }

    @Override
    public void deleteAdmin(int targetEmployeeId) {
        adminDao.deleteAdmin(targetEmployeeId);
    }
}
