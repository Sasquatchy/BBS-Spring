package org.zerock.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;


//크리테리아는 게시판 페이지의 위치와 보여주는 양을 운반하는 DTO
@Getter
@Setter
@ToString
@Log4j
public class Criteria {

	private Integer bno;
	private int page, amount;
	private String type;
	private String keyword;

	//문제가 생기든 cri를 새로 받든, 기본값을 생성자에서 정한다.
	public Criteria() {
		this.page = 1;
		this.amount = 10;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}
	
	//페이지가 범위 바깥 숫자가 들어가면 1로 다시 돌려 놓는 방식을 만든다.
	public void setPage(int page) {
		this.page = page <=0?1:page;
	}

	public void setAmount(int amount) {
		this.amount = amount<=10?10:amount;
	}
	
	public int getSkip() {
		return (this.page -1)*(this.amount);
	}
	
	public String getLink() {
	    UriComponentsBuilder builder = UriComponentsBuilder
	    					.fromPath("")
	    					.queryParam("page", page)
	    					.queryParam("amount", amount)
	    					.queryParam("type", type)
	    					.queryParam("keyword", keyword);
	    return builder.toUriString();

	}
	
	public Map<String, String> getMap(){

		//type이 비었을때
		if(type==null||type.trim().length()==0) {
			return null;
		}
		
		String[] arr = type.split("");
		
//		log.info(Arrays.toString(arr));	//[T, C, W]
		
		Map<String,String> map = new HashMap<String, String>();
		for (String typeWord : arr) {
			map.put(typeWord, keyword);
		}
//		log.info(map);
		return map;
	}
	
}
