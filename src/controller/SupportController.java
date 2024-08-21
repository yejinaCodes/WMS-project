package controller;

import common.ValidCheck;
import dto.request.BoardRequestDto;
import dto.request.NoticeRequestDto;
import dto.response.AuthResponseDto;
import dto.response.BoardResponseDto;
import dto.response.NoticeResponseDto;
import exception.Exception;
import handler.BoardInputHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import library.Script;
import service.BoardService;
import service.NoticeService;
import service.serviceImpl.BoardServiceImpl;
import service.serviceImpl.NoticeServiceImpl;

public class SupportController {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BoardInputHandler inputHandler = new BoardInputHandler();
    private static ValidCheck validCheck = new ValidCheck();
    private static Script script = new Script();
    private static BoardService boardService = new BoardServiceImpl();
    private static NoticeService noticeService = new NoticeServiceImpl();
    private static AuthResponseDto auth = new AuthResponseDto();


    /**
     * [직원] '고객 센터' 메뉴 선택 1. 공지사항 | 2. 게시글 | 3. 1:1 문의 내역 | 4. 이전 메뉴
     */
    public void handleSupportMenu(AuthResponseDto admin) throws IOException {
        auth = admin;

        while (true) {
            script.supportMenu();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To4(menu);

            switch (menu) {
                case "1" -> handleNoticeMenu();
                case "2" -> viewBoard();
                case "3" -> viewBoardPrivateAdmin();
                case "4" -> {
                    return;
                }
            }
        }
    }

    /**
     * [직원] '고객 센터 > 공지사항' 메뉴 선택 1. 생성 | 2. 조회 | 3. 수정 | 4. 삭제 | 5. 이전 메뉴
     */
    private void handleNoticeMenu() throws IOException {

        while (true) {
            script.showCRUDMenu();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To5(menu);

            switch (menu) {
                case "1" -> createNotice();
                case "2" -> viewNotice();
                case "3" -> updateNotice();
                case "4" -> deleteNotice();
                case "5" -> {
                    return;
                }
            }
        }
    }

    /**
     * [쇼핑몰 회원] '고객 센터' 메뉴 선택 1. 공지사항 | 2. 게시글 | 3. 1:1 문의 내역 | 4. 이전 메뉴
     */
    public void handleUserSupportMenu(AuthResponseDto user) throws IOException {
        auth = user;

        while (true) {
            script.supportMenu();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To4(menu);

            switch (menu) {
                case "1" -> viewNotice();
                case "2" -> handleBoardMenu();
                case "3" -> viewBoardPrivateUser();
                case "4" -> {
                    return;
                }
            }
        }
    }

    /**
     * [쇼핑몰 회원] '고객 센터 > 게시판' 메뉴 1. 생성 | 2. 조회 | 3. 수정 | 4. 삭제 | 5. 이전 메뉴
     */
    private void handleBoardMenu() throws IOException {

        while (true) {
            script.showCRUDMenu();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To5(menu);

            switch (menu) {
                case "1" -> createBoard();
                case "2" -> viewBoard();
                case "3" -> updateBoard();
                case "4" -> deleteBoard();
                case "5" -> {
                    return;
                }
            }
        }
    }

    /**
     * '고객 센터 > 공지사항 > 조회' 메뉴 1. 전체 조회 | 2. 상세 조회 | 3. 이전 메뉴
     */
    private void viewNotice() {
        while (true) {
            try {
                script.showViewMenu();
                String menu = br.readLine().trim();
                validCheck.validateMenuNumber1To3(menu);

                switch (menu) {
                    case "1" -> viewNoticeList();
                    case "2" -> viewNoticeDetail();
                    case "3" -> {
                        return;
                    }
                }

                script.viewSuccess();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            }

        }
    }

    /**
     * '고객 센터 > 게시판 > 조회' 메뉴 1. 전체 조회 | 2. 상세 조회 | 3. 이전 메뉴
     */
    private void viewBoard() {
        while (true) {
            try {
                script.showViewMenu();
                String menu = br.readLine().trim();
                validCheck.validateMenuNumber1To3(menu);

                switch (menu) {
                    case "1" -> viewBoardList();
                    case "2" -> viewBoardDetail();
                    case "3" -> {
                        return;
                    }
                }

                script.viewSuccess();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            }
        }
    }

    /**
     * '고객 센터 > 공지사항 > 조회' 메뉴 1. 전체 조회 | 2. 상세 조회 | 3. 이전 메뉴
     */
    private void viewBoardPrivateAdmin() throws IOException {
        while (true) {
            try {
                script.showViewMenu();
                String menu = br.readLine().trim();
                validCheck.validateMenuNumber1To3(menu);

                switch (menu) {
                    case "1" -> viewAdminPrivateInquiry();
                    case "2" -> viewBoardDetail();
                    case "3" -> {
                        return;
                    }
                }
                script.viewSuccess();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            }
        }
    }

    /**
     * '고객 센터 > 공지사항 > 조회' 메뉴 1. 전체 조회 | 2. 상세 조회 | 3. 이전 메뉴
     */
    private void viewBoardPrivateUser() throws IOException {

        while (true) {
            try {
                script.showViewMenu();
                String menu = br.readLine().trim();
                validCheck.validateMenuNumber1To3(menu);

                switch (menu) {
                    case "1" -> viewUserPrivateInquiry();
                    case "2" -> viewBoardDetail();
                    case "3" -> {
                        return;
                    }
                }
                script.viewSuccess();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                script.viewFailure();
            }
        }
    }

    /**
     * [직원] '고객 센터 > 공지사항 > 생성' 메뉴
     */
    private void createNotice() {
        try {
            NoticeRequestDto notice = inputHandler.createNotice(auth.getId());
            noticeService.createNotice(notice);
            script.createSuccess();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            script.createFailure();
        }
    }

    /**
     * '고객 센터 > 공지사항 > 전체 조회' 메뉴
     */
    private void viewNoticeList() {
        List<NoticeResponseDto> list = noticeService.findAll();
        script.boardListTitle();
        list.forEach(l -> script.noticeList(l));
        script.boardListBorder();
    }

    /**
     * '고객 센터 > 공지사항 > 상세 조회' 메뉴
     */
    private void viewNoticeDetail() {
        try {
            int targetNotice = inputHandler.getNoticeIdInput();
            NoticeResponseDto response = noticeService.findById(targetNotice);
            script.noticeInfo(response);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * [직원] '고객 센터 > 공지사항 > 수정' 메뉴
     */
    private void updateNotice() {
        try {
            int targetNotice = inputHandler.getNoticeIdInput();
            noticeService.update(targetNotice, inputHandler.updateNotice());
            script.updateSuccess();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            script.updateFailure();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            script.updateFailure();
        }
    }

    /**
     * [직원] '고객 센터 > 공지사항 > 삭제' 메뉴
     */
    private void deleteNotice() {
        try {
            int targetNotice = inputHandler.getNoticeIdInput();

            script.confirm();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To2(menu);

            switch (menu) {
                case "1":
                    noticeService.delete(targetNotice);
                    break;
                case "2":
                    break;
            }

            script.deleteSuccess();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            script.deleteFailure();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            script.deleteFailure();
        }
    }

    /**
     * [쇼핑몰 회원] '고객 센터 > 게시판 > 생성' 메뉴
     */
    private void createBoard() {
        try {
            BoardRequestDto board = inputHandler.createBoard(auth.getId());
            boardService.createBoard(board);
            script.createSuccess();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            script.createFailure();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            script.createFailure();
        }
    }

    /**
     * '고객 센터 > 게시판 > 전체 조회' 메뉴
     */
    private void viewBoardList() {
        List<BoardResponseDto> list = boardService.findAllPublic();
        script.boardListTitle();
        list.forEach(l -> script.boardList(l));
        script.boardListBorder();
    }

    /**
     * '고객 센터 > 게시판 > 상세 조회' 메뉴
     */
    private void viewBoardDetail() {
        try {
            int targetBoard = inputHandler.getNoticeIdInput();
            BoardResponseDto response = boardService.findById(targetBoard);
            script.boardInfo(response);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * [쇼핑몰 회원] '고객 센터 > 게시판 > 수정' 메뉴
     */
    private void updateBoard() {
        try {
            int targetBoard = inputHandler.getBoardIdInput();
            boardService.update(targetBoard, inputHandler.updateBoard());
            script.updateSuccess();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            script.updateFailure();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            script.updateFailure();
        }
    }

    /**
     * [쇼핑몰 회원] '고객 센터 > 게시판 > 삭제' 메뉴
     */
    private void deleteBoard() {
        try {
            int targetBoard = inputHandler.getBoardIdInput();

            script.confirm();
            String menu = br.readLine().trim();
            validCheck.validateMenuNumber1To2(menu);

            switch (menu) {
                case "1":
                    boardService.delete(targetBoard);
                    break;
                case "2":
                    break;
            }

            script.deleteSuccess();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            script.deleteFailure();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            script.deleteFailure();
        }
    }

    /**
     * [관리자] '고객 센터 > 1대1 문의 내역' 메뉴
     */
    private void viewAdminPrivateInquiry() {
        List<BoardResponseDto> list = boardService.findAllPrivateAdmin();
        script.boardListTitle();
        list.forEach(l -> script.boardList(l));
        script.boardListBorder();
    }

    /**
     * [쇼핑몰] '고객 센터 > 1대1 문의 내역' 메뉴
     */
    private void viewUserPrivateInquiry() {
        List<BoardResponseDto> list = boardService.findAllPrivateUser(auth.getId());
        script.boardListTitle();
        list.forEach(l -> script.boardList(l));
        script.boardListBorder();
    }

}
