package org.goodomen.hiddenpiece.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareBoardVO {
	private int postNo;
	private String id;
	private String title;
	private String content;
	private String photo;
	private String timePosted;
	private int hits;
	private String postStatus;
}
