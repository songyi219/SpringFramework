package mul.cam.a.dao.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.BbsDao;
import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

@Repository
public class BbsDaoImpl implements BbsDao {
	
	@Autowired
	SqlSession session;
	
	String ns = "Bbs.";		// Bbs.xml namespace 설정

	@Override
	public List<BbsDto> bbslist(BbsParam bbs) {
		return session.selectList(ns + "bbslist", bbs);
	}

	@Override
	public int getAllBbs(BbsParam bbs) {
		return session.selectOne(ns + "getAllBbs", bbs);
	}

	@Override
	public int writeBbs(BbsDto dto) {
		return session.insert(ns + "writeBbs", dto);
	}

	@Override
	public BbsDto getBbs(int seq) {
		return session.selectOne(ns + "getBbs", seq);
	}

	@Override
	public int updateBbs(BbsDto dto) {
		
		return session.update(ns + "updateBbs", dto);
	}

	@Override
	public int deleteBbs(BbsDto dto) {
		
		return session.update(ns + "deleteBbs", dto);
	}

	@Override
	public int answerStepUpdate(BbsDto dto) {
		return session.update(ns + "answerStepUpdate", dto);
	}

	@Override
	public int answerBbs(BbsDto dto) {
		return session.insert(ns + "answerBbs", dto);
	}
	
	
	// 댓글

	@Override
	public int commentWrite(BbsComment bbs) {		
		return session.insert(ns + "commentWrite", bbs);
	}

	@Override
	public List<BbsComment> commentList(int seq) {
		return session.selectList(ns + "commentList", seq);
	}

	@Override
	public int bbsreadcount(int seq) {		
		return session.update(ns + "bbsreadcount", seq);
	}

	





}
