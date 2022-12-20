-- AWS IP: 54.180.2.161
-- AWS ID: scott
-- AWS PW: tiger
-- AWS TableName: xe

SELECT * FROM AuctionBoard

CREATE TABLE Account_Info(
	account_no NUMBER NOT NULL,
	bank VARCHAR2(100) NOT NULL,
	balance NUMBER NOT NULL,
	CONSTRAINT PK_Account_Info PRIMARY KEY (account_no)
)
INSERT INTO Account_Info VALUES(333308,'카카오뱅크','124124124')
INSERT INTO Account_Info VALUES(99150201,'국민','70000000')
insert into ACCOUNT_INFO(account_no, bank, balance) values(111111,'국한은행',763000);
insert into ACCOUNT_INFO(account_no, bank, balance) values(111112,'신일은행',3000);
insert into ACCOUNT_INFO(account_no, bank, balance) values(111113,'코스타은행',21);
insert into ACCOUNT_INFO(account_no, bank, balance) values(111114,'제민은행',300000000);
insert into ACCOUNT_INFO(account_no, bank, balance) values(111115,'KBK은행',303940100);
insert into ACCOUNT_INFO(account_no, bank, balance) values(111116,'국한은행',293800);
insert into ACCOUNT_INFO(account_no, bank, balance) values(111117,'신일은행',39990600);
insert into ACCOUNT_INFO(account_no, bank, balance) values(111118,'코스타은행',998000);
insert into ACCOUNT_INFO(account_no, bank, balance) values(111119,'제민은행',7804000);
insert into ACCOUNT_INFO(account_no, bank, balance) values(111120,'KBK은행',111200);

-- 회원 조회
SELECT * FROM Account_Info




CREATE TABLE HP_Member(
	id VARCHAR2(100) NOT NULL,
	tel NUMBER NOT NULL,
	email VARCHAR2(100) NOT NULL,
	address VARCHAR2(100) NOT NULL,
	point NUMBER NOT NULL,
	name VARCHAR2(100) NOT NULL,
	nickname VARCHAR2(100) NOT NULL,
	status VARCHAR2(100) NOT NULL,
	account_no NUMBER NOT NULL,	
	CONSTRAINT PK_HP_Member PRIMARY KEY (id),
	CONSTRAINT FK_HP_Member_accountno FOREIGN KEY (account_no) REFERENCES Account_Info(account_no) ON DELETE CASCADE
)
-- 계좌 생성하기
INSERT INTO ACCOUNT_INFO values('99150201', '국민', 50000);
INSERT INTO ACCOUNT_INFO values('144322', '국민', 5000);
-- 회원 입력하기
INSERT INTO HP_Member VALUES('yerin0110','yerin0110@naver.com','봉명동',1000000,'김예린','해피예리니','99150201', 'a', 01093124050, 1);
INSERT INTO HP_Member VALUES('java','java@naver.com','오리',5000,'김자바','쏘해피자바','144322', 'a', 01081085855, 1);

select * from HP_Member where id='yerin0110';
INSERT INTO HP_Member VALUES('scardy',01063462516,'jyhong20@naver.com','상일동',1414141414,'홍주영','코스타짱돌','회원',333308)
INSERT INTO HP_Member VALUES('yerin0110',01093124050,'yerin0110@naver.com','봉명동',1000000,'김예린','해피예리니','회원',99150201)

--회원 정보 수정
select * from HP_Member
update HP_Member set password='c',email='dkakaksl',tel='01038291023',address='수원',nickName='봉태시기',name='문쭈노' where id='jaja';
		
update spring_member set password='b',name='아이유2',address='종로' where id='java' ;	


CREATE TABLE AuctionBoard(
	post_no NUMBER NOT NULL,
	id VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content clob NOT NULL,
	photo VARCHAR2(100) NOT NULL,
	start_price NUMBER NOT NULL,
	current_price NUMBER NOT NULL,
	sell_price NUMBER NOT NULL,
	time_posted DATE DEFAULT sysdate NOT NULL,
	hits NUMBER DEFAULT 0 NOT NULL ,
	end_date DATE NOT NULL,
	now_id VARCHAR2(100) NOT NULL,
	post_status NUMBER DEFAULT 1 NOT NULL,
	CONSTRAINT PK_AuctionBoard PRIMARY KEY (post_no),
	CONSTRAINT FK_AuctionBoard_ID FOREIGN KEY (id) REFERENCES HP_Member(id) ON DELETE CASCADE
)
-- 경매게시판 게시물 등록
INSERT INTO AuctionBoard VALUES(AuctionBoard_seq.nextval,'scardy','고양이보고가세요','꾸깃콘 많이 사랑해주세요','꾸깃.PNG',1000,1500,2500,sysdate,DEFAULT,sysdate,'scardy',DEFAULT)
INSERT INTO AuctionBoard VALUES(AuctionBoard_seq.nextval,'yerin0110','개 목줄 팝니다','우리 강아지 하네스로 갈아탔습니다 목줄 튼튼해요 최대 5미터 이고요 합법적으로 2미터 까지만 사용하세요', 'puppy.PNG',500,500,3000,sysdate,DEFAULT,sysdate,'scardy',DEFAULT)
INSERT INTO AuctionBoard VALUES(AuctionBoard_seq.nextval,'scardy','돌아다니는 접시','지혼자 돌아다니는 김밥천국 접시 팝니다 나쁘지않아요','접시.PNG',5000,6000,10000,sysdate,DEFAULT,sysdate,'yerin0110',DEFAULT)
INSERT INTO AuctionBoard VALUES(AuctionBoard_seq.nextval,'yerin0110','역사깊은씨디','다합해서 싸게 넘깁니다 희소성 있어요 ','씨디.PNG',100000,150000,200000,sysdate,DEFAULT,sysdate,'scardy',DEFAULT)
INSERT INTO AuctionBoard VALUES(AuctionBoard_seq.nextval,'yerin0110','녹지않는눈사람모형','폭염이와도 녹지않는 눈사람입니다. 아이들 장난감으로 좋아요 겨울 놀이 해보셔요 ','녹지않는눈사람.PNG',7000,9000,15000,sysdate,DEFAULT,sysdate,'scardy',DEFAULT)

--경매게시판 게시물 리스트
SELECT * FROM AuctionBoard WHERE post_status=1 order by post_no desc 

-- 경매게시판 댓글 등록
INSERT INTO AuctionBoard_Comment VALUES(AuctionBoard_Comment_seq.nextval, 1, 'yerin0110', '이거 어디서 사셨나요',sysdate, 1)
INSERT INTO AuctionBoard_Comment VALUES(AuctionBoard_Comment_seq.nextval, 1, 'yerin0110', '귀엽네요',sysdate, 1)

-- 경매게시판 댓글 조회
select * from AUCTIONBOARD_COMMENT
SELECT id, content,time_posted FROM AuctionBoard_Comment  WHERE post_status=1

-- 경매게시판 댓글 삭제(상태 0으로 바꾸기)
UPDATE AuctionBoard_Comment SET comment_status=0 WHERE comment_no=23

--경매게시판 댓글 조회
SELECT * FROM AuctionBoard_Comment WHERE comment_no=22

-- 찜한 목록 조회
SELECT * FROM AuctionBoard_Likes WHERE id='yerin0110';

이름 계좌번호 은행명 포인트 from
UPDATE Account_Info 
   SET Point += 5000
 WHERE (SELECT a.account_no
 		  FROM HP_Member m, account_Info a
 		 WHERE m.account_no = a.account_no
 		   AND m.name = '홍주영'
 		   AND 
 
 		)