package com.yedam.app.dept.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.yedam.app.dept.service.DeptInfoVO;
import com.yedam.app.dept.service.DeptListVO;
import com.yedam.app.dept.service.DeptService;

@Controller
public class DeptInfoController {
	
	@Autowired
	DeptService deptService;
	
	// 경로 <-> 기능 (페이지=view part) 연결
	// 보통 method 단위로 등록
	// 경로 + Method => unique (경로가 같아도 method가 다르면 다르게 인식)
	// Thus, certain mappers are way more used than RequestMapping
	
	// 조회 (데이터, 페이지) => GET
	// 등록, 수정, 삭제 => POST
	
	
	// 전체 조회 - 페이지 (페이지와 함께 보여주기 때문에 컨트롤러 분리 x)
	@GetMapping("deptList") // 경로 슬래쉬(/) 생략 가능
	public String getDeptAllList(@RequestParam(required = false) String msg, Model model, HttpServletRequest request) {
		model.addAttribute("deptList", deptService.getAllDept()); // (key, data)
		
		System.out.println("redirect : " + msg);
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap != null) {
			System.out.println("department_id : " + flashMap.get("departmentId"));
		}
		
		return "dept/list";
	}
	
	// 단건 조회 - 페이지
	@GetMapping("deptInfo")
	public String getDeptInfo(DeptInfoVO deptVO, Model model) { 	// model 값을 담는 역할
		DeptInfoVO findDept = deptService.getDeptInfo(deptVO);
		model.addAttribute("deptInfo", findDept);
		return "dept/info";
	}
	
	// 등록 - 페이지 (사용자 입력용 빈페이지 필요) : GET
	@GetMapping("deptInsert")
	public String deptInsertForm() {
		return "dept/insert"; 	// 담아올 정보가 필요 없음 => 페이지만 호출
	}
	
	// 등록 - 기능 (입력 후 처리용 페이지 필요) : POST
	@PostMapping("deptInsert")
	public String deptInsert(DeptInfoVO deptVO, RedirectAttributes rtt) { // redirect 할 때 값을 유지시켜주기 위한 속성 (cuz model 은 한번 전송되면 데이터 깨짐)
		deptService.insertDeptInfo(deptVO);
		rtt.addFlashAttribute("departmentId", deptVO.getDepartmentId());
		rtt.addAttribute("msg", "test"); // ?msg=test 로 표기됨 (데이터를 많이 보내야 할 때, if not ↓ 를 주로 씀)
//		return "redirect:deptList?departmentId=" + deptVO.getDepartmentId(); GET 방식이라 보안에 취약)
		return "redirect:deptList";
	}
	
	// 수정 - 기능 : POST
	@PostMapping("deptUpdate") // @RequestBody : JSON 포맷을 사용하는 경우 
								// => content-type : 'application/json'
								// (데이터가 넘어올 때 스프링은 잭슨 라이브러리를 사용하고 있는데, 이건 JSON 데이터 형식으로 받는 애라 JSON 포맷 데이터에 RequestBody 사용 가능)
 	@ResponseBody
	public Map<String, Object> deptUpdate(@RequestBody List<DeptInfoVO> deptVO) { //  RedirectAttributes rtt 매개변수
		Map<String, Object> map = deptService.updateDeptList(deptVO);
//		rtt.addFlashAttribute("updateRes", map);
//		return "redirect:deptInfo?departmentId="+deptVO.get(0).getDepartmentId(); // 원래는 fetch 쓸 때 이렇게 쓰면 안됨. 추후 배울예정
		return deptService.updateDeptList(deptVO);
	}
	
	
	// 삭제 - 기능 : POST
	@PostMapping("deptDelete")
	public String deptDelete(DeptListVO list){
		int result = deptService.deleteDeptList(list.getDeptList());
		return "redirect:deptList?msg=" + result;
	}
	
	
	
}