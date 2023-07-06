package com.yedam.app.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@RestController
public class EmpRestController {
	@Autowired
	EmpMapper empMapper;
	
	// 전체조회
	@GetMapping("emps") // 자원을 의미
	public List<EmpVO> getEmpList(){
		EmpVO empVO = new EmpVO();
		return empMapper.selectList(empVO);
	}
	
	// 단건조회
	@GetMapping("emps/{employeeId}")	// 해당 위치의 값에 대해서는 경로가 아니라 데이터로 처리하겠다 라고 해주면 됨
	public EmpVO getEmpInfo(@PathVariable int employeeId) {		// PathVariable 
		return empMapper.selectOne(employeeId);
	}
	
	// 등록
	@PostMapping("emps")
	public EmpVO insertEmpInfo(@RequestBody EmpVO empVO) {
		empMapper.addEmp(empVO);
		return empVO;
	}
	
	// 수정
	@PutMapping("emps/{employeeId}")
	public EmpVO updateEmpInfo(@PathVariable String employeeId,	// 데이터 들어옴
								@RequestBody EmpVO empVO) {		// 수정하고자 하는 대상
		empVO.setEmployeeId(employeeId);
		empMapper.updateEmp(empVO);
		return empVO;
	}
	
	/*
	// 삭제 방법 1
	@DeleteMapping("emps/{employeeId}")
	public int deleteEmpInfo(@PathVariable int employeeId) {
		empMapper.delEmp(employeeId);
		return employeeId;
	}
	*/
	
	
	// 삭제 방법 2( 좀 더 확실한 결과를 얻을 수 있는 방법 )
	@DeleteMapping("emps/{employeeId}")
	public Map<String, Object> deleteEmpInfo(@PathVariable int employeeId) {
		boolean success = false;
		int result = empMapper.delEmp(employeeId);
		if(result > 0) {
			success = true;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", success);
		map.put("employeeId", employeeId);
		
		return map;
	}
	
	
	
}