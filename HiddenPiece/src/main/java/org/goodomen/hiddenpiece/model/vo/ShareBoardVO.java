package org.goodomen.hiddenpiece.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareBoardVO {
	private int post_no;
	private String id;
	private String title;
	private String content;
	private String photo;
	private String time_posted;
	private int hits;
	private String post_status;
}
