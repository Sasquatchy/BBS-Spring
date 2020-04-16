package org.zerock.service;

import java.util.List;

import org.zerock.domain.ReplyVO;

public interface ReplyService extends GenericService<ReplyVO, Integer>{

	
	public List<ReplyVO> getSimpleList(Integer bno);

	//트랜잭션 연습
	public void addTest(String str);
}
