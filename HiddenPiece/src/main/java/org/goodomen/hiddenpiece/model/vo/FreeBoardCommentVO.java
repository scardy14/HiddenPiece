package org.goodomen.hiddenpiece.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardCommentVO {
	private long postNo;
	private long commentNo;
	private String id;
	private String content;
	private String timePosted;
	private String commentStatus;
}
