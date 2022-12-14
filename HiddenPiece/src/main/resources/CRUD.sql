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

INSERT INTO HP_Member VALUES('scardy',01063462516,'jyhong20@naver.com','상일동',1414141414,'홍주영','코스타짱돌','회원',333308)

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
INSERT INTO AuctionBoard VALUES(AuctionBoard_seq.nextval,'scardy','고양이보고가세요','꾸깃콘 많이 사랑해주세요','꾸깃.PNG',1000,1500,2500,sysdate,DEFAULT,sysdate,'scardy',DEFAULT)