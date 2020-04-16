package org.zerock.mapper;

import java.io.InputStream;
import java.util.stream.IntStream;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private ReplyService service;
	
	
	
	@Test
	public void testUpdate() {
		
		ReplyVO vo = new ReplyVO();
		vo.setRno(265);
		vo.setReply("수정된 결과");
		
		mapper.update(vo);

	}

	//트랜잭션 시험
	@Test
	public void testTx() {
		// 성공
		//service.addTest("hong gil dong");
		//실패
		service.addTest("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce luctus sapien id nisi porttitor amet.");

		//@Transactional은 안쓰면 하나만 들어가고 하나는 안들어간다.
		//트랜잭션이  데이터를 보호하는 샘이다.
	}
	
	
	@Test
	public void testInsert() {

		IntStream.rangeClosed(7203,  7205).forEach( i -> {
			
			ReplyVO vo = new ReplyVO();
			vo.setBno(7023);
			vo.setReply("테스트");
			vo.setReplyer("태스트 사람");
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testList() {
		mapper.list(7023).forEach(vo ->log.info(vo));
	}
}
