package org.zerock.mapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	//root-context.xml���ϸ� �д´�.
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper;
	
	
	
	@Test
	public void testMap() {
		String type ="TCW";
		String keyword="홍길동";
		
		//type이 비었을때
		if(type==null||type.trim().length()==0) {
			// return null;
		}
		
		String[] arr = type.split("");
		
		log.info(Arrays.toString(arr));	//[T, C, W]
		Map<String,String> map = new HashMap<String, String>();
		for (String typeWord : arr) {
			map.put(typeWord, keyword);
		}
		log.info(map);
	}
	
	
	@Test
	public void testSearch() {
		Map<String, String> map = new HashMap<>();
		map.put("T", "샘플");
		map.put("C", "샘플");
		map.put("W", "샘플");
		
		mapper.search(map);
	}
	
	@Test
	public void testPageMaker() {
		
		Criteria cri = new Criteria();
		cri.setPage(11);
		PageMaker pm = new PageMaker(cri, 201);
		log.info(pm);
	}
	
	@Test
	public void testPaging() {
		
		Criteria  cri = new Criteria();
		//2 page, 10
		cri.setPage(50);
		cri.setType("TCW");
		cri.setKeyword("user00");
		
		mapper.selectPage(cri).forEach(e -> log.info(e));
	}
	
	@Test
	public void testInsert() {
		
		BoardVO vo = new BoardVO();
		vo.setTitle("샘플 제목");
		vo.setContent("테스트 내용");
		vo.setWriter("김아무개");
		mapper.insert(vo);
	}
	
	@Test
	public void testSelect() {
		BoardVO vo = mapper.select(23);
		log.info(vo);
	}
	
	@Test
	public void testUpdate() {
		BoardVO vo = mapper.select(3);
		log.info(vo);
		vo.setContent("수정된 내용이다");
		vo.setTitle("수정한 제목이다");
		log.info("Update count: "+mapper.update(vo));
		
	}
	
	@Test
	public void testDelete() {
		int count = mapper.delete(34);
		log.info("delete count: "+count);
	}
}
