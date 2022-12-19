package org.goodomen.hiddenpiece.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardVO {
	private long postNo;
	private String contend;
	private String timePosted;
	private String title;
	private long hits;
	private int postStatus;
	private String id;
}
