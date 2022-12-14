package org.goodomen.hiddenpiece.model.service;

import org.goodomen.hiddenpiece.model.mapper.TestMyBatis;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{
	private final TestMyBatis testMyBatis;

	@Override
	public void TestMyBatisTestMethod(String id) {
		testMyBatis.testMethod(id);
		
	}
}
