package org.goodomen.hiddenpiece.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionBoardLikesVO {
	private String id;
	private long postNo;
}
