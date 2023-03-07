package mul.cam.a.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
import mul.cam.a.service.BbsService;

@Controller
public class BbsController {
	
	@Autowired
	BbsService service;
	
	@GetMapping(value = "bbslist.do")
	public String bbslist(BbsParam param, Model model) {
		
		// 글의 시작과 끝
		int pn = param.getPageNumber();	// 0 1 2 3 4
		int start = 1 + ( pn * 10);	// 1  11
		int end = ( pn + 1 ) * 10;	// 10 20
		
		param.setStart(start);
		param.setEnd(end);
		
		List<BbsDto> list = service.bbslist(param);
		int len = service.getAllBbs(param);
		
		int pageBbs = len / 10;	// 총 페이지 수   25 / 10 -> 2
		if((len % 10) > 0) {
			pageBbs = pageBbs + 1;
		}
		if(param.getChoice() == null || param.getChoice().equals("")			// 선택 x , 빈문자일때 다시 기본으로 셋팅해줌
			|| param.getSearch() == null || param.getSearch().equals("")) {
			param.setChoice("검색");
			param.setSearch("");
		}
		
		model.addAttribute("bbslist", list);	// 게시판 리스트
		model.addAttribute("pageBbs", pageBbs);	// 총 페이지 수
		model.addAttribute("pageNumber", param.getPageNumber()); // 현재 페이지
		model.addAttribute("choice", param.getChoice());	// 검색 카테고리
		model.addAttribute("search", param.getSearch());	// 검색어
		
		return "bbslist";
			
	}
	
	// 글작성하는 곳으로 보내주기
	@RequestMapping(value = "bbswrite.do", method = RequestMethod.GET)
	public String bbswrite() {
		return "bbswrite";
	}
	
	// 작성한 글 받아오기
	@RequestMapping(value = "bbswriteAf.do", method = RequestMethod.POST)
	public String bbswriteAf(BbsDto dto, Model model) {
		
		boolean isS = service.writeBbs(dto);
		
		String bbswrite = "";
		if(isS) {
			bbswrite = "BBS_ADD_OK";
		}else {
			bbswrite = "BBS_ADD_NO";
		}
		model.addAttribute("bbswrite", bbswrite);		
		return "message";
		// return "redirect:/bbslist.do";	controller에서 controller로 이동시 == sendRedirect
		// return "forward:/bbslist.do";	controller에서 controller로 이동시 ==  forward 짐을갖고 이동시
	}
	@GetMapping(value = "bbsdetail.do")
	public String bbsdetail(int seq, Model model) {
		int readcount = service.bbsreadcount(seq);	// 조회수 
		BbsDto dto = service.getBbs(seq);				
		model.addAttribute("bbsdto", dto);
		return "bbsdetail";
		
	}
	@GetMapping(value = "updatebbs.do")
	public String updateBbs(int seq, Model model) {	
		
		BbsDto dto = service.getBbs(seq);
		model.addAttribute("bbsdto", dto);
		return "bbsupdate";		
	}
	@PostMapping(value = "bbsupdateAf.do")
	public String bbsupdateAf(BbsDto dto, Model model) {
		
		boolean isS = service.updateBbs(dto);
		String update = "";
		if(isS) {
			update = "UPDATE_OK";
		}else {
			update = "UPDATE_FAIL";
		}
		model.addAttribute("update", update);
		return "message";		
		
	}
	@GetMapping(value = "deletebbs.do")
	public String deletebbs(BbsDto dto, Model model) {
		
		boolean isS = service.deleteBbs(dto);
				
		String delete = "";
		if(isS) {
			delete = "DELETE_OK";
		}else {
			delete = "DELETE_FAIL";
		}
		model.addAttribute("delete", delete);
		return "message";
	}
	@GetMapping(value = "answerbbs.do")
	public String answerbbs(int seq, Model model) {
		BbsDto dto = service.getBbs(seq);		
		model.addAttribute("bbsdto", dto);			
		return "answer";
	}
	@PostMapping(value = "answerAf.do")
	public String answerAf(int seq, BbsDto dto, Model model) {	// 들어오는 값. 부모글에대한 seq같이 날려주기. dto는 추가용
		
		dto.setSeq(seq);	// 부모글에 대한 seq하고 유저가 적어준 title content를 모아줌
		
		boolean isS = service.answerBbs(dto);
		String answer = "ANSWER_OK";
		if(isS == false) {
			answer = "ANSWER_NG";
		}
		model.addAttribute("answer", answer);
		
		return "message";
	
}
	// 댓글
	@PostMapping(value = "commentWriteAf.do")
	public String commentWriteAf(BbsComment bbs) {		//detail은 int seq를 같이 날려줘야함
		boolean isS = service.commentWrite(bbs);
		if(isS) {
			System.out.println("댓글작성에 성공했습니다");
		}else {
			System.out.println("댓글작성에 실패했습니다");
		}
		
		return "redirect:/bbsdetail.do?seq=" + bbs.getSeq();	//seq가지고 같이 보냄
		
		
	}
	
	// ajax
	@ResponseBody
	@GetMapping(value = "commentList.do")
	public List<BbsComment> commentList(int seq){
		List<BbsComment> list = service.commentList(seq);
		return list;
	}

}







