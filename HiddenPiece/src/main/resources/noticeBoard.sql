----------------------------------------------------------------------
--------------------------공지게시판-------------------------------------
--공지게시판

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

CREATE SEQUENCE NoticeBoard_seq;

select status from HP_MEMBER where id='java1';
select status from HP_MEMBER where id='hunjin';