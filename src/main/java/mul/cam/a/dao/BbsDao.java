package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

public interface BbsDao {

	List<BbsDto> bbslist(BbsParam bbs);
	
	// 글의 총수
	int getAllBbs(BbsParam bbs);
	
	int writeBbs(BbsDto dto);
	
	BbsDto getBbs(int seq);
	
	int updateBbs(BbsDto dto);
	
	int deleteBbs(BbsDto dto);
	
	// 따로따로만들고 service에서 묶어줌
	int answerStepUpdate(BbsDto dto);	
	int answerBbs(BbsDto dto);
	
	
	// 댓글
	int commentWrite(BbsComment bbs);
	
	List<BbsComment> commentList(int seq);	// 들어오는값 seq seq에 대한 댓글만 불러와야함
	
	
	// 조회수
	int bbsreadcount(int seq);
	
}







