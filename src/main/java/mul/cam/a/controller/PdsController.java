package mul.cam.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;
import mul.cam.a.service.PdsService;
import mul.cam.a.util.PdsUtil;

@Controller
public class PdsController {
	
	@Autowired
	PdsService service;
	
	@RequestMapping(value = "pdslist.do", method = RequestMethod.GET)
	public String pdslist(Model model, PdsParam pbs) {
		List<PdsDto> list = service.pdslist(pbs);
		if(pbs.getChoice() == null || pbs.getChoice().equals("")			// 선택 x , 빈문자일때 다시 기본으로 셋팅해줌
				|| pbs.getSearch() == null || pbs.getSearch().equals("")) {
				pbs.setChoice("검색");
				pbs.setSearch("");
			}
			
			model.addAttribute("choice", pbs.getChoice());	// 검색 카테고리
			model.addAttribute("search", pbs.getSearch());	// 검색어		
			model.addAttribute("pdslist", list);
		
		return "pdslist";
	}
	
	@GetMapping(value = "pdswrite.do")
	public String pdswrite() {
		return "pdswrite";
	}
	
	@PostMapping(value = "pdsupload.do")	// 무조건 post로 받아줘야함
	public String pdsupload(PdsDto dto, 						// 실패했을때 다시 시도하는지
							@RequestParam(value = "fileload", required = false) // jsp에 input으로 넘겨준 name을 받아옴
							MultipartFile fileload,		// <- parameter타입 1개
							HttpServletRequest req) {	// upload 경로를 설정하기위해 추가
							// 파일경로를 얻어오기 위한것
		// filename 취득
		String filename = fileload.getOriginalFilename();	// 원본의 파일명 빈문자일때나 null일때는 안올린다
		
		dto.setFilename(filename);	// 원본 파일명(DB) 디비에 넣어줌
		
		// upload 경로 설정
		// server	- 서버를 계속 껏다켯다 하기때문에 지워질 가능성이 있음
		String fupload = req.getServletContext().getRealPath("/upload");	// 경로 설정
		
		// 폴더
	//	String fupload = "c:\\temp";	client에 올려놓고 불러오는 방법
		
		System.out.println("fupload: " + fupload);
		
		// 파일명을 충돌되지 않는 명칭(Date)으로 변경
		String newfilename = PdsUtil.getNewFileName(filename);
		
		dto.setNewfilename(newfilename);	// 변경된 파일명
		
		File file = new File(fupload + "/" + newfilename);	
		
		// 실제로 파일 생성 + 기입 = 업로드		try catch 해주기
		try {
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());		// file에 날라온 파일로드의 바이트를 넣기	

			// db에 저장
			service.uploadPds(dto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "redirect:/pdslist.do";		// controller - controller 이동
	}
	
	@PostMapping(value = "filedownLoad.do")
	public String filedownLoad(int seq, String filename, String newfilename, 
								Model model, HttpServletRequest req) {
		// 경로		
		// server	- 서버를 계속 껏다켯다 하기때문에 지워질 가능성이 있음
		String fupload = req.getServletContext().getRealPath("/upload");	// 경로 설정
				
		// 폴더
		//	String fupload = "c:\\temp";	client에 올려놓고 불러오는 방법
				
		// 다운로드 받은 파일		
		File downloadFile = new File(fupload + "/" + newfilename);	// 객체
		
		model.addAttribute("downloadFile", downloadFile);	// file 실제 업로드 되어있는 파일명 경로/3543543.txt
		model.addAttribute("filename", filename);		// string 원 파일명			  abc.txt
		model.addAttribute("seq", seq);					// int download 카운트를 증가시키기 위해 보내줌
		
		return "DownloadVIiew";			// file-context에 생성해 놓은 것
			
	}
	
	
	@PostMapping(value = "downcount.do") 
	public String downcount(int seq, Model
	 model) {
	return "pdslist"; 
	}
	 
	
	@GetMapping(value = "pdsdetail.do")
	public String pdsdetail(int seq, Model model) {
		PdsDto dto = service.pdsDetail(seq);	
		model.addAttribute("pdsdto", dto);		
		return "pdsdetail";
	}
	
	@GetMapping(value = "pdsupdate.do")
	public String pdsupdate(int seq, Model model) {
		PdsDto dto = service.pdsDetail(seq);
		
		// model.addAttribute("seq", seq);
		model.addAttribute("pdsdto", dto);	
		return "pdsupdate";
	}
	
	@PostMapping(value = "pdsupdateAf.do")
	public String pdsupdateAf(PdsDto dto, 						// 실패했을때 다시 시도하는지
								@RequestParam(value = "fileload", required = false) // jsp에 input으로 넘겨준 name을 받아옴
								MultipartFile fileload,		// <- parameter타입 1개
								HttpServletRequest req) {
		
		String originalFileName = fileload.getOriginalFilename();	// 원래 파일네임
		
		if(originalFileName != null && !originalFileName.equals("")) {	// 넘어온 파일이 있음. 파일이 변경됨.
			
			// 업로드 새로해주고 파일네임 새로 집어넣어 주기
			
			String newfilename = PdsUtil.getNewFileName(originalFileName);	// timename으로 변경해줌
			
			dto.setFilename(originalFileName);
			dto.setNewfilename(newfilename);
			
			String fupload = req.getServletContext().getRealPath("/upload");
			
			File file = new File(fupload + "/" + newfilename);
			
			try {
				// 새로운 파일로 업로드
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				
				// db갱신
				service.pdsUpdate(dto);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{ // 파일이 변경되지 않았음
			service.pdsUpdate(dto);
		}
		return "redirect:/pdsdetail.do?seq=" + dto.getSeq();
	}
	
		
		
		
		
	
	@GetMapping(value = "pdsdelete.do")
	public String pdsdelete(PdsDto dto, Model model) {
		boolean isS = service.pdsDelete(dto);
		String pdsdelete = "";
		if(isS) {
			pdsdelete = "PDS_DELETE_OK";
		}else {
			pdsdelete = "PDS_DELETE_FAIL";
		}
		model.addAttribute("pdsdelete", pdsdelete);
		return "message";
	}
	
	
}








