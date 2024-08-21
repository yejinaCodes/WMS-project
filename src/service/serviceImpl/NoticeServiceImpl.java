package service.serviceImpl;

import dao.NoticeDao;
import dao.daoImpl.NoticeDaoImpl;
import dto.request.NoticeRequestDto;
import dto.response.NoticeResponseDto;
import java.util.List;
import service.NoticeService;

public class NoticeServiceImpl implements NoticeService {

    private static NoticeDao noticeDao = new NoticeDaoImpl();

    @Override
    public void createNotice(NoticeRequestDto request) {
        noticeDao.createNotice(request);
    }

    @Override
    public NoticeResponseDto findById(int id) {
        return noticeDao.findById(id);
    }

    @Override
    public List<NoticeResponseDto> findAll() {
        return noticeDao.findAll();
    }

    @Override
    public void update(int id, NoticeRequestDto request) {
        noticeDao.update(id, request);
    }

    @Override
    public void delete(int id) {
        noticeDao.delete(id);
    }
}
