package org.goodomen.hiddenpiece.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.goodomen.hiddenpiece.model.vo.MemberVO;

@Mapper
public interface MemberMapper {

	public MemberVO login(MemberVO memberVO);

	public int registerMember(MemberVO memberVO);
	
}
