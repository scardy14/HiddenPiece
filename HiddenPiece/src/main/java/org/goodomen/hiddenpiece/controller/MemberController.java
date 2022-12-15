package org.goodomen.hiddenpiece.controller;

import org.goodomen.hiddenpiece.model.mapper.MemberMapper;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberMapper memberMapper;
	
	
}
