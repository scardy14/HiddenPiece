package org.goodomen.hiddenpiece.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionBoardPostVO {
	private long postNo;
	private String id;
	private String title;
	private String content;
	private String photo;
	private long startPrice;
	private long currentPrice;
	private long sellPrice;
	private String timePosted;
	private long hits;
	private String endDate;
	private String nowId;
	private long postStatus;
}
