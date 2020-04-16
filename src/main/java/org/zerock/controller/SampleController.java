package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	
	//리다이렉트 대상
	@GetMapping("/ex6")
	public void ex6() {
		
	}
	
	
	//리다이렉트 사용
	@GetMapping("/ex5")
	public String ex5() {
		log.info("ex5-------------");
		return "redirect:/sample/ex6?result=SUCCESS";	//리다이랙트는 2번 호출된다.
		//위의 방식대로라면 페이지를 뒤로가기 또는 새로고침에 매번 반응하게 된다.
		//주의할점은 모델 데이터를 쓴다고 해도, 모델데이터가 유지가 되지 않는다. 
		//redirectAttribute란 애가 존재한다.
	}
	
	@GetMapping("/ex5A")
	public String ex5A(RedirectAttributes rttr) {
		log.info("ex5a-------------");
		//한번만 전달하는 파라미터를 쓸때 사용한다.
		rttr.addFlashAttribute("result","SUCCESS");
		return "redirect:/sample/ex6";
	}
	

	//Model을 사용해서 데이터를 보내본다.
	//1. 없는 컨트롤러 예제.
//	@GetMapping("/ex4")
//	public String ex4(SampleDTO dto, int page) {
//		
//		log.info("ex4-----------------");
//		log.info(dto);
//		log.info(page);
//		
//		//파라메터로 들어온 값들은 소문자로 들어오는 타입이름으로 들어와진다.
//		//하지만 서버로 page는 들어오지만 ex4.jsp에는 기본자료형이 자동으로 전달이 안된다.
//		return "/sample/ex4";
//	}
	
	//2. 기본자료형 보내보기 : ex4.jsp참조
	@GetMapping("/ex4")
	public String ex4(SampleDTO dto, @ModelAttribute("page") int page, Model model) {
		
		log.info("ex4-----------------");
		log.info(dto);
		log.info(page);

		model.addAttribute("result","success");
		
		//이제 page란 기본자료형 값은 모델을 통해서 전달이 된다.
		return "/sample/ex4";
	}
	//어노테이션으로 들어가면서 파라미터와 리턴타입이 자유로와졌다.
	//Model에 값을 지정할수 있다 addAttribute키, 값. 
	//request와 유사, 추가적으로 전달하는 데이터를 여기다가 넣는다.
	
	
	//initBinder를 이용하여 날짜를 가져오기
	//"이" 타입이 나온다면 바인더가 명시하는 타입으로 바꿔준다.
	//오래되고 어려운코드지만 아직도 쓰일수 있다.
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(df, false));
//	}
	
	@GetMapping("/ex3")
	public void ex3A(TodoDTO todo) {
		log.info(todo);
	}
	
	@GetMapping("/ex3A")
	public void ex3(TodoDTO todo) {
		log.info(todo);
	}
	@GetMapping("/ex1")
	public void ex1(SampleDTO dto) {
		log.info(dto);
		
	}
	
	//들어올때  파라미터 이름을 따로 설정할땐 RequestParam을 써준다. 그외에 이름이 같으면 알아서 url param에서 된다.
	@GetMapping("/ex2")
	public void ex2(@RequestParam("name") String name,@RequestParam("age") int age) {
		log.info(name);
		log.info(age);
		
	}
	
	
	@GetMapping("/ex2Arr")
	public void ex2(@RequestParam("ids") String[] ids) {

	}
	
//	@GetMapping("/ex2Bean")
//	@PostMapping("/ex2Bean")
	//위처럼 하거나, 아래처럼 일괄할수 있다.
	@RequestMapping(value = "/ex2Bean", method = { RequestMethod.GET, RequestMethod.POST})
	public void ex2Bean(SampleDTOList list) {

		log.info(list);
	}
	
	
}
