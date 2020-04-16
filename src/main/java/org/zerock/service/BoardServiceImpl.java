package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

//이놈이 실제 일하는 놈이기에 Bean으로 등록한다. 어노테이션 @service를 이용해서 자동 등록.
@Service
//스프링 4.3부터 단일 변수만 있을경우 생성자로 해결이 된다.
@AllArgsConstructor	
@Log4j
public class BoardServiceImpl implements BoardService {

//	@Setter(onMethod_=@Autowired)
	//@autowired는 setter위에다가 붙히는것이다. 
	//하지만 롬복으로 setter를 생성후 onMethod_에 @autowired를 붙히면서 같은 효과를 낸다.
	//더 알고 싶으면 내 매뉴얼 autowired 검색, 생성자 주입 부분 참고
	// 커멘트로 처리해 뒀고 이번엔 생성자에서 처리.
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public void register(BoardVO vo) {
		log.info(vo);
		mapper.insert(vo);
	}

	@Override
	public BoardVO get(Integer key) {
		return mapper.select(key);
	}

	@Override
	public int modify(BoardVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int remove(Integer key) {
		return mapper.delete(key);
	}

	@Override
	public List<BoardVO> listAll() {
		return mapper.selectAll();
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return mapper.selectPage(cri);
	}

	@Override
	public int getTotal() {
		return mapper.totalCount();
	}

	@Override
	public int getListCount(Criteria cri) {
		return mapper.selectPageCount(cri);
	}

}
