package mul.cam.a.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;
							// 상속

import mul.cam.a.service.PdsService;
public class DownloadVIiew extends AbstractView{		// 추상뷰를 만들어서 정보를 줘서 다운로드
	
	@Autowired
	PdsService service;		// 어디에서든 접근할 수 있음. 싱글턴이기 때문
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadVIiew renderMergedOutputModel");
		
		File downloadFile = (File)model.get("downloadFile");	// 경로 + 파일명
		String filename = (String)model.get("filename");	// 원본 파일명
		int seq = (Integer)model.get("seq");
		
		response.setContentType(this.getContentType());		// 응답형의 컨텐츠타입으로 셋팅
		response.setContentLength((int)downloadFile.length());	// 길이값
		
		// 이 설정은 한글명 파일의 경우 적용된다.
		filename = URLEncoder.encode(filename, "utf-8");
		
		
		// 다운로드창에 실제로 나오는 것
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";"); // 원본파일명으로 바꿔줌
		response.setHeader("Content-Transfer-Encoding", "binary;");			// 2진수 형태
		response.setHeader("Content-Length", "" + downloadFile.length());	
		response.setHeader("Pragma", "no-cache;"); 	// 저장 잠깐하는것 안함.
		response.setHeader("Expires", "-1;");	// 기한 -1은 필요없음
		
		OutputStream os = response.getOutputStream();		// 생성
		FileInputStream fis = new FileInputStream(downloadFile);
		
		// 실제 데이터 기입하는 부분
		FileCopyUtils.copy(fis, os);
		
		// download count 증가
		
		int downcount = service.downCount(seq);
				
		
		
		if(fis != null) {
			fis.close();
		}
		
	}
	
	

}
