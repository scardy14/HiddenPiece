package org.goodomen.hiddenpiece.test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HiddenPieceMemberTest {
	/*
	private Logger logger=LoggerFactory.getLogger(getClass());
	private MemberMapper memberMapper;
	
	@Autowired
	public HiddenPieceMemberTest(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	
	@Test
	public void login() {
		MemberVO memberVO = new MemberVO("yerin0110", "a", null, null, null, null, 0, null, null, null);
		MemberVO resultVO = memberMapper.login(memberVO);
		logger.debug("login vo: {}", resultVO);
	}
	@Test
	public void findId() {
		String email = "jyhong20@naver.com";
		String address = "상일동";
		String name = "홍주영";
		String tel = "01063462516";
		String id = memberMapper.findId(email,address,name,tel);
		System.out.println(id);
	}
	
	@Test
	public void findPassword() {
		String id = "scardy";
		String email = "jyhong20@naver.com";
		String name = "홍주영";
		String tel = "01063462516";
		String password = memberMapper.findPassword(id,email,name,tel);
		System.out.println(password);
	}
	
	@Test
	 public void registerMember() {
		MemberVO memberVO=new MemberVO("jaja5","b","000@gamil.com","01083219231","111115","안양",0,"봉구","문주노","1");
		memberMapper.registerMember(memberVO);
		System.out.println("회원가입완료");
	}

   @Test
   public void updateMember() {
      MemberVO memberVO = new MemberVO("jaja","aa","000@naver.com","01012341234","111111","수원",1000,"봉식","아이유","1");
      logger.debug("수정전 member {}",memberMapper.findMemberById(memberVO.getId()));
      memberMapper.updateMember(memberVO);
      logger.debug("수정후 member {}",memberMapper.findMemberById(memberVO.getId()));
   }

	
	@Test
	public void deleteMember() {
		String id = "yerin0110";
		memberMapper.deleteMember(id);
		System.out.println("회원탈퇴가 완료되었습니다");
	}
	
	@Test
	public void checkId() {
		String id = "java";
		int result = memberMapper.checkId(id);
		System.out.println(result);
	}
	@Test
	public void checkAccount() {
		String accountNo = "11111800";
		MemberVO memberVO = memberMapper.findMemberByAccount(accountNo);
		System.out.println(memberVO);
		if(memberVO.getId()!=null) {
			System.out.println(1);
		}else {
			System.out.println(2);
		}

	}
	
	@Test
	public void checkAccountInfo() {
		String accountNo = "111113";
		AccountVO accountVO = memberMapper.findAccountInfoByAccountNo(accountNo);
		System.out.println(accountVO);
	}
	@Test
	public void exchangePoint() {
			String id="java1";
			String name="퇴근중";
			long balance=10000;
			String accountNo="111113";
			String bank="코스타은행";
			memberMapper.withdrawPoint(balance,accountNo,bank);
			memberMapper.exchangePoint(balance,name);
			System.out.println("계좌에서 포인트로 환전되었습니다");
			
	}
	@Test
	public void exchangeToAccount() {
		String accountNo = "144322";
		String bank = "국민";
		long point = 10000;
		String name = "김자바";
		String id = "java";
		memberMapper.withdrawPoint(point,name,id);
		System.out.println("포인트 출금완료");
		memberMapper.depositAccount(point,accountNo,bank);
		System.out.println("입금완료");
		MemberVO memberVO = (MemberVO) memberMapper.findMemberById(id);
		System.out.println(memberVO);
	}
	@Test
	public void findPointbyId() {
		String id = "java";
		long point = memberMapper.findPointbyId(id);
		System.out.println(point+"원");
	}
	
	@Test
	public void findAuctionBoardStatus1ById() {
		String id = "scardy";
		ArrayList<AuctionBoardPostVO> list = memberMapper.findAuctionBoardStatus1ById(id);
		for(AuctionBoardPostVO vo:list)		
			logger.debug("list:{}", vo);
	}
	
	@Test
	public void findAuctionBoardStatus0ById() {
		String id = "scardy";
		ArrayList<AuctionBoardPostVO> list = memberMapper.findAuctionBoardStatus0ById(id);
		for(AuctionBoardPostVO vo:list)		
			logger.debug("list:{}", vo);
	}
	@Test
	public void findFreeBoardStatus0ById() {
		String id = "scardy";
		ArrayList<FreeBoardVO> list = memberMapper.findFreeBoardStatus0ById(id);
		for(FreeBoardVO vo:list)		
			logger.debug("list:{}", vo);
	}
	@Test
	public void findFreeBoardStatus1ById() {
		String id = "scardy";
		ArrayList<FreeBoardVO> list = memberMapper.findFreeBoardStatus1ById(id);
		for(FreeBoardVO vo:list)		
			logger.debug("list:{}", vo);
	}
	@Test
	public void totalCountMember() {
		int result = memberMapper.totalCountMember();
			System.out.println(result);	
	}
	@Test
	public void findBalanceByAccountNo() {
		String accountNo = "111113";
		long result = memberMapper.findBalanceByAccountNo(accountNo);
		System.out.println(result);
	}
	@Test
	public void findShareBoardStatus1ById() {
		String id = "scardy";
		ArrayList<ShareBoardVO> list = memberMapper.findShareBoardStatus1ById(id);
		for(ShareBoardVO vo:list)		
			logger.debug("list:{}", vo);
	}
	@Test
	public void findShareBoardStatus0ById() {
		String id = "scardy";
		ArrayList<ShareBoardVO> list = memberMapper.findShareBoardStatus0ById(id);
		for(ShareBoardVO vo:list)		
			logger.debug("list:{}", vo);
	}
	*/
	
}
