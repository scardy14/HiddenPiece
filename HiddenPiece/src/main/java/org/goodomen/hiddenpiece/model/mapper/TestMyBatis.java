package org.goodomen.hiddenpiece.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMyBatis {
	void testMethod(String id);
}
