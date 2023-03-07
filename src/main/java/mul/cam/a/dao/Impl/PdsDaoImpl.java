package mul.cam.a.dao.Impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.PdsDao;
import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;

@Repository
public class PdsDaoImpl implements PdsDao {
	@Autowired
	SqlSessionTemplate session;	// SqlSession 대신 써도됨
	
	String ns = "Pds.";		// namespace

	@Override
	public List<PdsDto> pdslist(PdsParam pbs) {
		return session.selectList(ns + "pdslist", pbs);
	}

	@Override
	public int uploadPds(PdsDto dto) {
		return session.insert(ns + "uploadPds", dto);
	}

	@Override
	public int downCount(int seq) {
		return session.update(ns + "downCount", seq);
	}

	@Override
	public PdsDto pdsDetail(int seq) {
		return session.selectOne(ns + "pdsDetail", seq);
	}

	@Override
	public int pdsUpdate(PdsDto dto) {		
		return session.update(ns + "pdsUpdate", dto);
		
	}

	@Override
	public int pdsDelete(PdsDto dto) {
		return session.delete(ns + "pdsDelete", dto);
	}
	
}
