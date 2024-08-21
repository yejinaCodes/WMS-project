package common;

public enum Menu {
  USER_TYPE("1. 쇼핑몰 | 2. 어드민\n"),
  USER_TYPE_SELECT("회원 유형 번호 입력 > "),

  MENU_SELECT("메뉴 선택 > "),
  ROLE_SELECT("권한 선택 > "),
  STATUS_SELECT("승인 상태 선택 > "),

  MENU_AUTH("1. 회원 가입 | 2. 로그인 | 3. 아이디 찾기 | 4. 비밀번호 재설정\n"),

  /**
   * 관리자 메뉴
   */
  MENU_ADMIN(
      "[메뉴]\n1. 회원 관리 | 2. 재무 관리 | 3. 창고 관리 | 4. 재고 관리\n5. 입고 관리 | 6. 출고 관리 | 7. 고객 센터 | 8. 로그아웃\n"),


  /**
   * 관리자 <회원 관리> 메뉴
   */
  MENU_ADMIN_MEMBER("[회원 관리]\n1. 조회 | 2. 수정 | 3. 권한 설정 | 4. 삭제 | 5. 이전 메뉴\n"),
  MENU_ADMIN_MEMBER_VIEW("[회원 관리 > 조회]\n1. 직원 조회 | 2. 쇼핑몰 사업자 회원 조회 | 3. 이전 메뉴\n"),
  MENU_ADMIN_MEMBER_VIEW_ADMIN("[회원 관리 > 조회 > 직원 조회]\n1. 직원 상세 조회 | 2. 직원 전체 조회 | 3. 권한별 직원 조회 | 4. 이전 메뉴\n"),
  MENU_ADMIN_DEPARTMENT("[부서]\n1. 인사 | 2. 창고 | 3. 배송 | 4. 개발 | 5. 회계\n"),
  MENU_ADMIN_POSITION("[직급]\n1. 총 관리자 | 2. 창고 관리자 | 3. 일반\n"),
  MENU_ADMIN_ROLE("[직원 권한]\n1. 총 관리자 | 2. 창고 관리자 | 3. 일반\n"),
  MENU_ADMIN_MEMBER_VIEW_USER(
      "[회원 관리 > 조회 > 쇼핑몰 사업자 회원 조회]\n1. 쇼핑몰 회원 상세 조회 | 2. 쇼핑몰 회원 전체 조회 | 3. 승인 대기자 조회 | 4. 이전 메뉴\n"),
  MENU_ADMIN_MEMBER_EDIT("[회원 관리 > 수정]\n1. 회원 정보 수정 | 2. 비밀번호 수정 | 3. 이전 메뉴\n"),
  MENU_ADMIN_MEMBER_PERMISSION("[회원 관리 > 권한 설정]\n1. 직원 권한 | 2. 직원 부서 및 직급 | 3. 쇼핑몰 사업자 가입 승인 | 4. 이전 메뉴\n"),
  MENU_ADMIN_DELETE("[회원 관리 > 삭제]\n1. 직원 삭제 | 2. 쇼핑몰 사업자 회원 삭제 | 3. 이전 메뉴\n"),


  /**
   * <고객 센터> 메뉴
   */
  MENU_SUPPORT("[고객 센터]\n1. 공지사항 | 2. 게시글 | 3. 1:1 문의 내역 | 4. 이전 메뉴\n"),


  /**
   * 쇼핑몰 유저 메뉴
   */
  MENU_USER("[메뉴]\n1. 회원 관리 | 2. 창고 관리 | 3. 재고 관리\n4. 입고 관리 | 5. 출고 관리 | 6. 고객 센터\n7. 로그아웃\n"),


  /**
   * 쇼핑몰 유저 <회원 관리> 메뉴
   */
  MENU_USER_MEMBER("[회원 관리]\n1. 정보 조회 | 2. 정보 수정 | 3. 비밀번호 변경 | 4. 탈퇴 | 5. 이전 메뉴\n"),


  /**
   * 승인 상태 항목
   */
  STATUS_MENU("[Status]\n1. 대기 | 2. 승인 | 3. 거절\n"),


  /**
   * 입력 항목
   */
  ADMIN_ID_INPUT("사번을 입력해 주세요 > "),
  USER_ID_INPUT("회원 번호를 입력해 주세요 > "),
  BIZ_NO_INPUT("사업자 번호를 입력해 주세요 > "),
  NOTICE_ID_INPUT("공지사항 번호를 입력해 주세요 > "),
  REJECTION_REASON_INPUT("승인 거절 사유를 입력해 주세요 > "),

  /**
   * 공통 메뉴 항목
   */
  MENU_CRUD("1. 생성 | 2. 조회 | 3. 수정 | 4. 삭제 | 5. 이전 메뉴\n"),
  MENU_VIEW("1. 전체 조회 | 2. 상세 조회 | 3. 이전 메뉴\n"),
  CONFIRM("계속 진행하시겠습니까?\n1. 예 | 2. 아니요"),

  LOGIN("\n[로그인]\n"),

  BORDER_LINE("------------------------------------------------------------\n"),
  BOARD_SEPARATOR("############################################################\n"),


  USERMENUSELECT("1. 입고요청서 작성 | 2. 입고요청서 조회 | 3. 입고요청서 수정 | 4. 입고요청서 삭제 | 5. 나가기"),
  ADMINMENUSELECT("1. 입고요청서 조회 | 2. 입고요청서 승인 | 3. 입고지시서 출력 | 4. 나가기"),

  FINDCONDITION("1. 모든 요청서 조회 | 2. 요청서 한 개 조회 | 3. 입고 상태별 조회 | 4. 생성 날짜별 조회 | 5. 물건 ID별 조회 | 6. 입고 예정일별 조회"),
  //유저가 창고일 경우 공급자 ID별 조회도 가능하게 해주기
  INSERTSEARCHID("읽을 요청서ID를 입력하시오"),
  INSERTPRODUCTID("읽을 상품ID를 입력하시오"),
  INSERTCREATEDDATE("읽을 요청서의 생성날짜를 입력하시오"),
  INSERTINCOMINGDATE("읽을 요청서의 입고날짜를 입력하시오"),
  FINDSTATUSWMS("1. PENDING | 2. APPROVED"), //wms관리자
  STOCKREQUESTCOLUMN("요청ID\t물건ID\t\t박스수량\t박스사이즈\t\t입고상태\t\t\t\t입고예정일\t\t\t\t\t입고서생성일\t\t\t\t\t비고"
      + "\n-------------------------------------------------------------------------------------------"),
  INSTRUCOLUMN("지시서ID\t요청서ID\t박스수량\t입고서생성일\t적재방법\t비고"),
  UPDATESTATUS("승인할 요청서ID 입력 | 더 이상 입력하지 않을 경우 Enter를 치시오"),
  UPDATEREQUEST("\n**PENDING 상태 중 수정할 요청서ID 입력하시오**"),
  DELETEWARNING("\n**입고요청서 취소 메뉴입니다**"),
  DELETEREQUEST("\n**PENDING 상태 중 취소할 요청서ID를 입력하시오**"),
  PRINTINSTRC("입고지시서 출력 메뉴입니다.");
  private final String description;

  Menu(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
  }
