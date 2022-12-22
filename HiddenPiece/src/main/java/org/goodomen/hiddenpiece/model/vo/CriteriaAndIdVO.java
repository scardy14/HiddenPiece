package org.goodomen.hiddenpiece.model.vo;

import lombok.Data;

@Data
public class CriteriaAndIdVO {
	
	// 특정 페이지 조회를 위한 클래스
	private int page; // 현재 페이지 번호
	private int perPageNum; // 페이지당 보여줄 게시글의 개수
	private int totalCount;
	private String id;
	private String tag;
	public int getPageStart() {
		// 특정 페이지의 범위를 정하는 구간, 현재 페이지의 게시글 시작 번호
		// 0 ~ 10 , 10 ~ 20 이런식으로
		return (this.page -1) * perPageNum+1;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public CriteriaAndIdVO() {
		// 기본 생성자 : 최초 게시판에 진입시 필요한 기본값
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			
		} else {
			this.page = page;
		}	
	}


	public void setPerPageNum(int perPageNum) {
		int cnt = this.perPageNum;
		
		if(perPageNum != cnt) {
			this.perPageNum = cnt;	
		} else {
			this.perPageNum = perPageNum;
		}
		
	}
}
