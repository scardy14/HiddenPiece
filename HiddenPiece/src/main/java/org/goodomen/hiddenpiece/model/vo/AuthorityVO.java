package org.goodomen.hiddenpiece.model.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityVO implements Serializable{
	private static final long serialVersionUID = -2678594156611120659L;
	private String id;
	private String authority;
}
