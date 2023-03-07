package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.BbsParam;
import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;

public interface PdsDao {

	List<PdsDto> pdslist(PdsParam pbs);
	
	int uploadPds(PdsDto dto);
		
	int downCount(int seq);
	
	PdsDto pdsDetail(int seq);
	
	int pdsUpdate(PdsDto dto);
	
	int pdsDelete(PdsDto dto);
}
