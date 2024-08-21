package service.serviceImpl;

import dao.BoardDao;
import dao.daoImpl.BoardDaoImpl;
import dto.request.BoardRequestDto;
import dto.response.BoardResponseDto;
import java.util.List;
import service.BoardService;

public class BoardServiceImpl implements BoardService {

    private static BoardDao boardDao = new BoardDaoImpl();

    @Override
    public void createBoard(BoardRequestDto request) {
        boardDao.createBoard(request);
    }

    @Override
    public BoardResponseDto findById(int id) {
        return boardDao.findById(id);
    }

    @Override
    public List<BoardResponseDto> findAllPublic() {
        return boardDao.findAllPublic();
    }

    @Override
    public List<BoardResponseDto> findAllPrivateAdmin() {
        return boardDao.findAllPrivateAdmin();
    }

    @Override
    public List<BoardResponseDto> findAllPrivateUser(int id) {
        return boardDao.findAllPrivateUser(id);
    }

    @Override
    public void update(int id, BoardRequestDto request) {
        boardDao.update(id, request);
    }

    @Override
    public void delete(int id) {
        boardDao.delete(id);
    }
}
