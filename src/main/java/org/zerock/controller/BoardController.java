package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	//객체는 BoardServiceImpl이지만 유연성을 가진 자바는 주입할때 바라보는 타입만 본다.
	//즉 움직이는건 BoardServiceImpl이지만 BoardService만 써줘도 된다.
	//그러면 알아서 Impl가 들어가진다. 
	private BoardService service;
	
	@PostMapping("/remove")
	public String remove(@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove-----");
		
		int count = service.remove(cri.getBno());
		
		rttr.addFlashAttribute("result", "success");
		
		return "redirect:/board/list"+cri.getLink();
	}
	@PostMapping("/modify")
	public String modify(BoardVO vo, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify-----");
		log.info("modify: "+vo.getBno());
		service.modify(vo);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/read"+cri.getLink()+"&bno="+cri.getBno();
	}
	
	
	//@ModelAttribute는 받은 파라미터를 바로 모델에 담아 보낸다.
	@GetMapping({"/read", "/modify"})
	public void read(@ModelAttribute("cri") Criteria cri, Model model) {
		model.addAttribute("vo",service.get(cri.getBno()));
	}
	
	
//	@GetMapping("/list")
//	public void listAll(Model model) {
//		
//		//페이지로 리스트 전달.
//		model.addAttribute("list", service.listAll());
//	}
	
	@GetMapping("/list")
	public void listPage(@ModelAttribute("cri") Criteria cri, Model model) {
		
		int totalBoard = service.getListCount(cri);
		
		//페이지로 리스트 전달.
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pm", new PageMaker(cri, totalBoard));
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("=-=-=-get register------");
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("cri") Criteria cri, BoardVO vo, RedirectAttributes rttr) {
		log.info("=-=-=-registering vo: "+vo);
		service.register(vo);
		return "redirect:/board/read"+cri.getLink();
	}
	
	
}
