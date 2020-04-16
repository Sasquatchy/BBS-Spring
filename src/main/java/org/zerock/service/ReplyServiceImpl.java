package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


//트랜잭션이 모두 걸린다.
@Transactional
@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;

	@Setter(onMethod_=@Autowired)
	private BoardMapper boardMapper;
	
	@Override
	public void register(ReplyVO vo) {
		
		mapper.insert(vo);
		boardMapper.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public ReplyVO get(Integer key) {
		return mapper.select(key);
	}

	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int remove(Integer key) {
		ReplyVO vo = mapper.select(key);
		mapper.delete(key);
		return boardMapper.updateReplyCnt(vo.getBno(), -1);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getListCount(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReplyVO> getSimpleList(Integer bno) {
		return mapper.list(bno);
	}

	//트랜잭션 연습
	@Override
	public void addTest(String str) {

		mapper.insert2(str);
		mapper.insert1(str);
		
	}

}
