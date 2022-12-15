package org.goodomen.hiddenpiece.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.model.vo.MemberVO;

@Mapper
public interface MemberMapper {

	MemberVO login(MemberVO memberVO);

	String findId(String email, String address, String name, String tel);

	String findPassword(String id, String email, String name, String tel);
	
}
