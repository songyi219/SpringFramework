package mul.cam.a.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.BbsDao;
import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
import mul.cam.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	BbsDao dao;

	@Override
	public List<BbsDto> bbslist(BbsParam bbs) {
		return dao.bbslist(bbs);
	}

	@Override
	public int getAllBbs(BbsParam bbs) {
		return dao.getAllBbs(bbs);
	}

	@Override
	public boolean writeBbs(BbsDto dto) {	
		int n = dao.writeBbs(dto);
		return n>0?true:false;
	}

	@Override
	public BbsDto getBbs(int seq) {
		return dao.getBbs(seq);
	}

	@Override
	public boolean updateBbs(BbsDto dto) {
		int count = dao.updateBbs(dto);
		return count>0?true:false;
	}

	@Override
	public boolean deleteBbs(BbsDto dto) {
		int del = dao.deleteBbs(dto);
		return del>0?true:false;
	}

	@Override
	public boolean answerBbs(BbsDto dto) {
		dao.answerStepUpdate(dto);	//update는 없을수도 있음. 판단은 insert값으로만
		int n = dao.answerBbs(dto);	
		return n>0?true:false;
	}
	
	
	// 댓글

	@Override
	public boolean commentWrite(BbsComment bbs) {
		int n = dao.commentWrite(bbs);
		return n>0?true:false;
	}

	@Override
	public List<BbsComment> commentList(int seq) {
		return dao.commentList(seq);
	}

	@Override
	public int bbsreadcount(int seq) {
		return dao.bbsreadcount(seq);
		
	}



	
	
}
