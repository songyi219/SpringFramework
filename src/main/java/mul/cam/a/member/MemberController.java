package mul.cam.a.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mul.cam.a.dto.MemberDto;
import mul.cam.a.service.MemberService;

@Controller
public class MemberController {
	// service 접근(생성)
	
	@Autowired
	MemberService service;	// impl클래스 생성과 같음
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
	//	System.out.println("MemberController login " + new Date());
		
		return "login";		// login.jsp로 가라
		
	}
	@RequestMapping(value = "regi.do", method = RequestMethod.GET)
	public String regi() {
	//	System.out.println("MemberController regi " + new Date());
		return "regi";
	}
	
	@ResponseBody
	@RequestMapping(value = "idCheck.do", method = RequestMethod.POST)
	public String idcheck( String id ) {
	//	System.out.println("MemberController idcheck " + new Date());
		
		boolean isS = service.idCheck(id);
		
		if(isS == true) {	// id가 잇음
			return "YES";
		}
		return "NO";	// id가 없음
	}
	@RequestMapping(value = "regiAf.do", method = RequestMethod.POST)
		// return값 			들어오는값
	public String regiAf( Model model, MemberDto dto) {
	//	System.out.println("MemberController regiAf " + new Date());
		
		boolean isS = service.addmember(dto);
		String message = "";
		if(isS) {
			message = "MEMBER_ADD_YES";
		}else {
			message = "MEMBER_ADD_NO";
		}
		model.addAttribute("message", message);
		
		return "message";
	}
	
	@RequestMapping(value = "loginAf.do", method = RequestMethod.POST)
	public String login(HttpServletRequest req, Model model, MemberDto dto) {	// req접근 아무데서나 할 수있음
	//	System.out.println("MemberController loginAf " + new Date());
		
		MemberDto mem = service.login(dto);
		String msg = "";
		if(mem != null) {	// login 성공
			req.getSession().setAttribute("login", mem);	// session에 로그인정보 저장
			req.getSession().setMaxInactiveInterval(60 * 60 * 2);	// 2시간 저장			
			msg = "LOGIN_OK";			
		}else {				// login 실패
			msg = "LOGIN_FAIL";
		}
		model.addAttribute("login", msg);
		
		return "message";
	}
	@RequestMapping(value = "sessionOut.do", method = RequestMethod.GET)
	public String sessionOut(Model model) {
		String sessionOut = "logout";
		model.addAttribute("sessionOut", sessionOut);
		
		return "message";
	}
}









