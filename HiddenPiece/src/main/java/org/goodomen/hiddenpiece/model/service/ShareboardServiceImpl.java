package org.goodomen.hiddenpiece.model.service;

import java.util.List;
import java.util.Map;

import org.goodomen.hiddenpiece.model.mapper.ShareBoardMapper;
import org.goodomen.hiddenpiece.model.vo.CriteriaAndIdVO;
import org.goodomen.hiddenpiece.model.vo.ShareBoardVO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShareboardServiceImpl implements ShareboardService{
	private final ShareBoardMapper shareboardMapper;
	@Override
	public int findShareCount() {
		return shareboardMapper.findShareCount();
	}

	@Override
	public int findShareCountTag(CriteriaAndIdVO cri) {
		return shareboardMapper.findShareCountTag(cri);
	}

	@Override
	public List<Map<String, Object>> findShareList(CriteriaAndIdVO cri) {
		if(cri.getTag().equals("0")) {
			return shareboardMapper.findShareList(cri);
		} else {
			return shareboardMapper.findShareListTag(cri);
		}		
	}

	@Override
	public void writeSharePost(ShareBoardVO shareboardVO) {
		shareboardMapper.writeSharePost(shareboardVO);
	}

}
