package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

public interface BbsService {

	List<BbsDto> bbslist(BbsParam bbs);
	
	int getAllBbs(BbsParam bbs);
	
	boolean writeBbs(BbsDto dto);
	
	BbsDto getBbs(int seq);
	
	boolean updateBbs(BbsDto dto);
	
	boolean deleteBbs(BbsDto dto);
	
	boolean answerBbs(BbsDto dto);	// dto 같기때문에 함수는 1개만 써도됨
	
	// 댓글
	
	boolean commentWrite(BbsComment bbs);
	
	List<BbsComment> commentList(int seq);
	
	int bbsreadcount(int seq);
	
}








