package mul.cam.a.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.PdsDao;
import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;
import mul.cam.a.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService {
	
	@Autowired
	PdsDao dao;

	@Override
	public List<PdsDto> pdslist(PdsParam pbs) {
		return dao.pdslist(pbs);
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		int count = dao.uploadPds(dto);
		return count>0?true:false;
	}

	@Override
	public int downCount(int seq) {
		return dao.downCount(seq);
		
	}

	@Override
	public PdsDto pdsDetail(int seq) {		
		return dao.pdsDetail(seq);
	}

	@Override
	public boolean pdsUpdate(PdsDto dto) {
		int count = dao.pdsUpdate(dto);
		return count>0?true:false;
	}

	@Override
	public boolean pdsDelete(PdsDto dto) {
		int count = dao.pdsDelete(dto);
		return count>0?true:false;
	}
	
	
}
