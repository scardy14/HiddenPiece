package org.goodomen.hiddenpiece.model.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO implements Serializable {
	private static final long serialVersionUID = 5024864320551167008L;
	private String id;
	private String password;
	private String email;
	private String tel;
	private String accountNo;
	private String address;
	private long point;
	private String nickName;
	private String name;
	private String status;
}
