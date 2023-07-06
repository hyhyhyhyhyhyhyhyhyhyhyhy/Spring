package com.yedam.app;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class EmpMapperClient {

	@Autowired
	EmpMapper empMapper; // 인터페이스를 상속받은 구현클래스를 찾아서 가져오는 것(주입 받음)
	
	/*
	@Test
	public void getEmpInfo() {
		EmpVO findEmp = empMapper.selectOne(100);
		assertEquals(findEmp.getFirstName(), "Steven");
	} 
	*/
	
	@Test
	public void 건수() {
		int cnt = empMapper.selectCount(null);
	}
	
	@Test
	public void 전체조회() {
		EmpVO param = new EmpVO();
		param.setDepartmentId("50, 40");
//		param.setFirstName("e");
		param.setOrderColumn("department_id, first_name");
		List<EmpVO> list = empMapper.selectList(param);
		int cnt = empMapper.selectCount(param);
		for(EmpVO emp : list) {
			System.out.println(list);			
		}
//		assertEquals(list.get(0).getEmployeeId(), "100");
	}
	
	/* normal ver.
	@Test 
	public void 등록() {
 		String lastN = "Georgia";
		String email = "ginny@test.com";
		String jobId = "IT_PROG";
		EmpVO vo = new EmpVO();
		vo.setLastName(lastN);
		vo.setEmail(email);
		vo.setJobId(jobId);
		int check = empMapper.addEmp(vo);
		assertEquals(check, 1);
//		EmpVO findEmp = empMapper.selectOne(900);
//		assertEquals(findEmp.getEmployee_id(), "900");
	} 
	*/
	
	
	/* selectKey ver.
	@Test
	public void addFromServer() {
		EmpVO vo = new EmpVO();
		vo.setLastName("kim");
		vo.setEmail("seventeen@right.here");
		vo.setJobId("IT_PROG");
		empMapper.addEmp(vo);
		// 등록 후에 id를 사용하고자 할 경우, selectKey 이용
		System.out.println(vo.getEmployeeId());
	}
	*/
	
	
	@Test
	public void selectJobs() {
		List<Map<String, Object>> list = empMapper.selectJobs();
		assertNotNull(list);
	}
}