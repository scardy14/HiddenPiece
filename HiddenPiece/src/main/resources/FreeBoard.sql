--
insert into ACCOUNT_INFO values(
	'110-486-552153',
	'신한',
	700000
)

--
insert into HP_MEMBER values(
	'hunjin',
	'seltise@naver.com',
	'구성',
	0,
	'김훈진',
	'진',
	'110-486-552153',
	'a',
	'010-2326-8830',
	1
)

--
insert into FREEBOARD values(
	freeboard_seq.nextval,
	'hunjin',
	'왜',
	'안될까',
	sysdate,
	0,
	1
)

--
select * from freeBoard where post_no=2;

--
			SELECT * from 
	(select ROWNUM rm, post_no,contend, time_posted,title,hits,post_status,id from
		(select post_no,contend, time_posted,title,hits, post_status, hpm.id
		from freeboard fb
		inner join hp_member hpm on hpm.id=fb.id
		where post_status=1 or post_status=2 or post_status=3 
		order by post_no desc))

		where rm between 	1 and 100
