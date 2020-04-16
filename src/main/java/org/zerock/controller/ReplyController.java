package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies/*")
@AllArgsConstructor
@Log4j
@CrossOrigin	//외부에서 ajax연결을 하는것을 허락.
public class ReplyController {

	private ReplyService service;
	
	@PutMapping(value = "/modify", consumes= "application/json;charset=UTF-8", produces= MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify( @RequestBody ReplyVO vo){
		service.modify(vo);
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Integer rno){
		log.info("...remove...rno: "+rno);
		service.remove(rno);
		return new ResponseEntity<>( "success",HttpStatus.OK);
	}
	
	@GetMapping("/{rno}")
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Integer rno){
		log.info("...select....rno: "+rno);
		return new ResponseEntity<ReplyVO>(service.get(rno), HttpStatus.OK);
	}
	
	
	@GetMapping("/{bno}/{page}")
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable(name="bno") Integer bno,@PathVariable(name="page") Integer page){
		return new ResponseEntity<>(service.getSimpleList(bno), HttpStatus.OK);
	}
	
	
	//json이 전달되기를 명시한다. consumes= 특정MIME타입만 먹는다.produces=특정MIME을 발싸
	@PostMapping(value = "/new", consumes= "application/json;charset=UTF-8", produces= MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		log.info(vo);
		service.register(vo);
		return new ResponseEntity<String>("success",HttpStatus.OK);
		//HttpStatus중 201 CREATED란 정보를 보낼수 있지만 IE에서 애러가 나서 OK로 보낸다.
	}
}
