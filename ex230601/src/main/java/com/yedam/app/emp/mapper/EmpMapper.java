package com.yedam.app.emp.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {

	public EmpVO selectOne(int empNo);
	public List<EmpVO> selectList(EmpVO vo);
	public int addEmp(EmpVO vo);
	public int selectCount(EmpVO vo);
	// 목록 구하는 거랑 건수 구하는 매개변수는 일치 시켜야 나중에 페이징 할 때 편함
	
	public List<Map<String, Object>> selectJobs();
	public List<Map<String, Object>> selectDepts();
	
	public int updateEmp(EmpVO vo); // 수정
	public int delEmp(int empNo); // 삭제
}