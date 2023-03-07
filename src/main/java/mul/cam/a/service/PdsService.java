package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;

public interface PdsService {
	List<PdsDto> pdslist(PdsParam pbs);
	
	boolean uploadPds(PdsDto dto); 
	
	int downCount(int seq);
	
	PdsDto pdsDetail(int seq);
	
	boolean pdsUpdate(PdsDto dto);
	
	boolean pdsDelete(PdsDto dto);
}
