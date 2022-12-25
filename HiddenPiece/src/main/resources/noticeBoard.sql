----------------------------------------------------------------------
--------------------------공지게시판-------------------------------------
--공지게시판 테이블 생성
CREATE TABLE NoticeBoard (
	post_no NUMBER NOT NULL,
	id VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	contend clob NOT NULL,
	time_posted DATE NOT NULL,
	hits NUMBER DEFAULT 0 NOT NULL,
	post_status NUMBER DEFAULT 1 NOT NULL,
	CONSTRAINT PK_NoticeBoard PRIMARY KEY (post_no),
	CONSTRAINT FK_NoticeBoard_id FOREIGN KEY (id) REFERENCES HP_Member(id) ON DELETE CASCADE
)

-- 공지게시판 시퀀스 생성
CREATE SEQUENCE NoticeBoard_seq;

-- 회원 정보 상태 조회
select status from HP_MEMBER where id='java1';
select status from HP_MEMBER where id='hunjin';

--
		SELECT *
		  FROM (
		  			SELECT ROWNUM, a.post_no ,a.id ,a.title ,a.content ,a.photo ,a.start_price ,a.current_price ,a.sell_price ,a.time_posted ,a.hits ,a.end_date ,a.now_id , a.post_status
	  				  FROM AuctionBoard a, Bid_List b
	 				 WHERE a.post_no = b.post_no
	   							AND a.id = 'scardy'
	   							AND b.id = 'scardy'
	   							AND a.post_no = b.post_no
	   				 ORDER BY b.bid_no ASC
		  		)
		WHERE ROWNUM BETWEEN 	1 and 2

--
		SELECT * 
		  FROM (
		  		SELECT a.post_no ,a.id ,a.title ,a.content ,a.photo ,a.start_price ,a.current_price ,a.sell_price ,a.time_posted ,a.hits ,a.end_date ,a.now_id , a.post_status
	  			  FROM AuctionBoard a, Bid_List b
	 			 WHERE a.post_no = b.post_no
	   							AND a.id = 'scardy'
	   							AND b.id = 'scardy'
	   							AND a.post_no = b.post_no
	   			ORDER BY ROWNUM DESC
		  		)
		  WHERE ROWNUM BETWEEN 1 AND 2
	
