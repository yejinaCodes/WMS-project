package service;

import dto.request.BoardRequestDto;
import dto.response.BoardResponseDto;
import java.util.List;

public interface BoardService {

    void createBoard(BoardRequestDto request);

    BoardResponseDto findById(int id);

    List<BoardResponseDto> findAllPublic();

    List<BoardResponseDto> findAllPrivateAdmin();

    List<BoardResponseDto> findAllPrivateUser(int id);

    void update(int id, BoardRequestDto request);

    void delete(int id);
}
