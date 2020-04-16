package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardService extends GenericService<BoardVO, Integer> {

	//제네릭 이외에 추가되는 메소드들은 여기다가 추가
	
	public List<BoardVO> listAll();
	
}
