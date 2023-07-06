package com.yedam.app.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

// command
// Pojo : 상속 필요 없음

@Controller			// 객체 생성해서 컨테이너에 bean 등록하고 
					// Spring dispatcher servlet 에서 호출할 수 있도록 
					// 커맨드 타입으로 만들어 줌. 
					// @Component 상속 받음

public class EmpController {

	@Autowired EmpMapper empMapper;
	
	@GetMapping("empList")
	public String empList(Model model, @ModelAttribute("emp") EmpVO vo) {
		System.out.println(vo);
		// model.addAttribute("empVO", vo);
		model.addAttribute("empList", empMapper.selectList(vo));
		return "emp/empList"; // forward (페이지를 부름)
	}
	
	// 사원등록 페이지로 이동
	@GetMapping("empInsert")
	public String empInsertForm(Model model) {
		model.addAttribute("jobs", empMapper.selectJobs());
		model.addAttribute("depts", empMapper.selectDepts());
		return "emp/empInsert";
	}
	
	
	// 사원등록 처리
	@PostMapping("empInsert")
	public String empInsert(EmpVO vo) {
		empMapper.addEmp(vo);
		return "redirect:empList"; // redirect (값을 가지고 있는 매핑 url을 부름)
	}
	
	
	// 사원삭제 처리
	@GetMapping("empDelete")
	public String empDelete(@RequestParam int empId) { 			// requestParam은 생략가능
		empMapper.delEmp(empId);		// 삭제버튼에서 받는 값과 변수명은 같아야 함!
		return "redirect:empList";
	}
	
	
	// 사원 정보 수정페이지로 이동
	@GetMapping("empUpdate")
	public String empUpdateForm(Model model, int empId) {
		model.addAttribute("emp", empMapper.selectOne(empId));
		model.addAttribute("jobs", empMapper.selectJobs());
		model.addAttribute("depts", empMapper.selectDepts());
		return "emp/empUpdate";
	}
	
	
	// 사원 수정 처리
	@PostMapping("empUpdate")
	public String empUpdate(EmpVO vo) {
		empMapper.updateEmp(vo);
		return "redirect:empList"; // redirect (값을 가지고 있는 매핑 url을 부름)
	}
	
}