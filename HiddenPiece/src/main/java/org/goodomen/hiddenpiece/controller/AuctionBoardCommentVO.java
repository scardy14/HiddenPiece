package org.goodomen.hiddenpiece.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionBoardCommentVO {
	private long postNo;
	private long commentNo;
	private String id;
	private String timePosted;
	private String content;
	private String commentStatus;
}
