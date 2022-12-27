package org.goodomen.hiddenpiece.model.vo;

public class Criteria {
	
	// 특정 페이지 조회를 위한 클래스
	private int page; // 현재 페이지 번호
	private int perPageNum; // 페이지당 보여줄 게시글의 개수
	
	private int totalCount;
	private String searchKeyword;
	private String loginId;
	private int category;
	private String status;
	private String price;
	

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getPageStart() {
		// 특정 페이지의 범위를 정하는 구간, 현재 페이지의 게시글 시작 번호
		// 0 ~ 10 , 10 ~ 20 이런식으로
		return (this.page -1) * perPageNum+1; 
	}

	public int getTotalCount() {
		return totalCount;
	}

	public Criteria() {
		// 기본 생성자 : 최초 게시판에 진입시 필요한 기본값
		this.page = 1;
		this.perPageNum = 12;
	}
	
	public Criteria(int perPageNum) {
		// 기본 생성자 : 최초 게시판에 진입시 필요한 기본값
		this.page = 1;
		this.perPageNum = perPageNum;
	}
	// 현재 페이지 번호 page : getter, setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			
		} else {
			this.page = page;
		}	
	}

	
	// 페이지당 보여줄 게시글의 개수 perPageNum : getter, setter
	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	
	

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", totalCount=" + totalCount
				+ ", searchKeyword=" + searchKeyword + ", loginId=" + loginId + ", category=" + category + ", status="
				+ status + ", price=" + price + "]";
	}
	
	
	
	
}
