package org.goodomen.hiddenpiece.Scheduler;

import org.goodomen.hiddenpiece.model.service.AuctionBoardService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DBScheduler {
	private final AuctionBoardService auctionBoardService;
	
	@Scheduled(cron = "*/10 * * * * *")//매 10초마다 실행함
	public void updateAuctionBoardPostStatus() {
		auctionBoardService.updatePostStatusTo2();
		auctionBoardService.updatePostStatusTo3();
	}
}
