package dao;

import dto.request.NoticeRequestDto;
import dto.response.NoticeResponseDto;
import java.util.List;

public interface NoticeDao {
    void createNotice(NoticeRequestDto request);

    NoticeResponseDto findById(int id);

    List<NoticeResponseDto> findAll();

    void update(int id, NoticeRequestDto request);

    void delete(int id);
}
