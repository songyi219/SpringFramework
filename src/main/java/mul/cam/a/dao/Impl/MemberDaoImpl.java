package mul.cam.a.dao.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.MemberDao;
import mul.cam.a.dto.MemberDto;

@Repository	// == 저장소
public class MemberDaoImpl implements MemberDao{
	// MyBatis 접근(생성)
	@Autowired	// 자동생성
	SqlSession session;			// 자동으로 sqlsession class 생성	언제든지 불러써야하기때문에 web.xml에 생성후 계속 불러씀
	
	String ns = "Member.";
	
	@Override
	public List<MemberDto> allMember() {
		
		
		return session.selectList(ns + "allMember");
	}

	@Override
	public int idCheck(String id) {
		return session.selectOne(ns +"idCheck", id);
	}

	@Override
	public int addmember(MemberDto dto) {				
		return session.insert(ns + "addmember", dto);
	}

	@Override
	public MemberDto login(MemberDto dto) {
		MemberDto mem = session.selectOne(ns + "login", dto);
		return mem;
	}

	



	

	
	
	

}
