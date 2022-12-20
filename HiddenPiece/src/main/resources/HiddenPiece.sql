-------------------------회원정보--------------------------------------
--계좌정보
CREATE TABLE Account_Info(
	account_no VARCHAR2(100) NOT NULL,
	bank VARCHAR2(100) NOT NULL,
	balance NUMBER NOT NULL,
	CONSTRAINT PK_Account_Info PRIMARY KEY (account_no)
)
select * from ACCOUNT_INFO;


insert into ACCOUNT_INFO(account_no, bank, balance) values('111111','국한은행',763000);
insert into ACCOUNT_INFO(account_no, bank, balance) values('111112','신일은행',3000);
insert into ACCOUNT_INFO(account_no, bank, balance) values('111113','코스타은행',21);
insert into ACCOUNT_INFO(account_no, bank, balance) values('111114','제민은행',300000000);
insert into ACCOUNT_INFO(account_no, bank, balance) values('111115','KBK은행',303940100);
insert into ACCOUNT_INFO(account_no, bank, balance) values('111116','국한은행',293800);
insert into ACCOUNT_INFO(account_no, bank, balance) values('111117','신일은행',39990600);
insert into ACCOUNT_INFO(account_no, bank, balance) values('111118','코스타은행',998000);
insert into ACCOUNT_INFO(account_no, bank, balance) values('111119','제민은행',7804000);
insert into ACCOUNT_INFO(account_no, bank, balance) values('111120','KBK은행',111200);

commit





--회원정보
CREATE TABLE HP_Member(
	id VARCHAR2(100) NOT NULL,
	email VARCHAR2(100) NOT NULL,
	address VARCHAR2(100) NOT NULL,
	point NUMBER NOT NULL,
	name VARCHAR2(100) NOT NULL,
	nickname VARCHAR2(100) NOT NULL,
	account_no VARCHAR2(100) NOT NULL,	
	password VARCHAR2(100) NOT NULL,
	tel VARCHAR2(100) NOT NULL,	
	status NUMBER NOT NULL,
	CONSTRAINT PK_HP_Member PRIMARY KEY (id),
	CONSTRAINT FK_HP_Member_accountno FOREIGN KEY (account_no) REFERENCES Account_Info(account_no) ON DELETE CASCADE
)

select * from HP_MEMBER
--회원권한
CREATE TABLE Grantee(
	id VARCHAR2(100) NOT NULL,
	HP_level NUMBER NOT NULL,
	CONSTRAINT PK_Grantee PRIMARY KEY (id),
	CONSTRAINT FK_Grantee_ID FOREIGN KEY (id) REFERENCES HP_Member(id) ON DELETE CASCADE
)
---------------------------------------------------------------------
------------------------경매게시판--------------------------------------
--경매게시판
CREATE SEQUENCE AuctionBoard_seq;
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
--경매게시판댓글
select * from AUCTIONBOARD;
CREATE SEQUENCE AuctionBoard_Comment_seq;
CREATE TABLE AuctionBoard_Comment (
	comment_no NUMBER NOT NULL,
	post_no NUMBER NOT NULL,	
	id VARCHAR2(100) NOT NULL,
	content clob NOT NULL,
	time_posted DATE DEFAULT sysdate NOT NULL,
	comment_status NUMBER DEFAULT 1 NOT NULL,
	CONSTRAINT PK_AuctionBoard_Comment PRIMARY KEY (comment_no, post_no),
	CONSTRAINT FK_AuctionBoard_Comment_PostNo FOREIGN KEY (post_no) REFERENCES AuctionBoard(post_no) ON DELETE CASCADE,
	CONSTRAINT FK_AuctionBoard_Comment_ID FOREIGN KEY (id) REFERENCES HP_Member(id) ON DELETE CASCADE
)
--경매게시판 찜하기
CREATE TABLE AuctionBoard_Likes (
	post_no NUMBER NOT NULL,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT PK_AuctionBoard_Likes PRIMARY KEY (post_no, id),
	CONSTRAINT FK_AuctionBoard_Likes_PostNo FOREIGN KEY (post_No) REFERENCES AuctionBoard(post_No) ON DELETE CASCADE,
	CONSTRAINT FK_AuctionBoard_Likes_ID FOREIGN KEY (id) REFERENCES HP_Member(id) ON DELETE CASCADE
)
-- 경매게시판 찜하기 목록
SELECT * FROM AuctionBoard_Likes

--경매게시판경매참여물품
CREATE TABLE AuctionBoard_Entry (
	post_no NUMBER NOT NULL,
	id VARCHAR2(100) NOT NULL,
	CONSTRAINT PK_AuctionBoard_Entry PRIMARY KEY (post_no, id),
	CONSTRAINT FK_AuctionBoard_Entry_PostNo FOREIGN KEY (post_No) REFERENCES AuctionBoard(post_No) ON DELETE CASCADE,
	CONSTRAINT FK_AuctionBoard_Entry_ID FOREIGN KEY (id) REFERENCES HP_Member(id) ON DELETE CASCADE
)
---------------------------------------------------------------------
------------------------포인트입출력기록----------------------------------
--포인트입출력기록
CREATE TABLE In_And_Out(
	id VARCHAR2(100) NOT NULL,
	time_posted DATE DEFAULT sysdate NOT NULL,
	in_and_out NUMBER NOT NULL,
	method VARCHAR2(100) NOT NULL,
	post_no NUMBER NOT NULL,
	CONSTRAINT PK_In_And_Out_id PRIMARY KEY (id, time_posted),
	CONSTRAINT FK_In_And_Out_id FOREIGN KEY (id) REFERENCES HP_Member(id) ON DELETE CASCADE,
	CONSTRAINT FK_In_And_Out_postno FOREIGN KEY (post_no) REFERENCES AuctionBoard(post_no) ON DELETE CASCADE,
	CONSTRAINT CK_In_And_Out_method CHECK (method IN ('경매','계좌'))
)
----------------------------------------------------------------------
--------------------------자유게시판-------------------------------------
--자유게시판
CREATE SEQUENCE FreeBoard_seq;
CREATE TABLE FreeBoard (
	post_no NUMBER NOT NULL,
	id VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	contend clob NOT NULL,
	time_posted DATE NOT NULL,
	hits NUMBER DEFAULT 0 NOT NULL,
	post_status NUMBER DEFAULT 1 NOT NULL,
	CONSTRAINT PK_FreeBoard PRIMARY KEY (post_no),
	CONSTRAINT FK_FreeBoard_id FOREIGN KEY (id) REFERENCES HP_Member(id) ON DELETE CASCADE
)
--자유게시판댓글
CREATE TABLE FreeBoard_Comment(
	post_no NUMBER NOT NULL,
	comment_no NUMBER NOT NULL,
	id VARCHAR2(100) NOT NULL,
	time_posted DATE DEFAULT sysdate NOT NULL,
	content clob NOT NULL,
	comment_status NUMBER DEFAULT 1 NOT NULL,
	CONSTRAINT PK_FreeBoard_Comment PRIMARY KEY (post_no,comment_no),
	CONSTRAINT FK_FreeBoard_Comment_postno FOREIGN KEY (post_no) REFERENCES FreeBoard(post_no) ON DELETE CASCADE,
	CONSTRAINT FK_FreeBoard_Comment_id FOREIGN KEY (id) REFERENCES HP_Member(id) ON DELETE CASCADE	
)
----------------------------------------------------------------------

)