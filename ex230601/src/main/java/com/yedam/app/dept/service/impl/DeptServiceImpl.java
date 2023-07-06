package com.yedam.app.dept.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptInfoMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptInfoVO;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired	// 필요한 필드만큼 개별적으로 Autowired 생성해야 함
	DeptInfoMapper deptMapper; // 필요하다면 모든 mapper를 호출할 수 있기 때문에, 그냥 mapper 라고 쓰지 말기
	
	@Override
	public List<DeptInfoVO> getAllDept() {
		return deptMapper.selectAllDept();
	}

	@Override
	public DeptInfoVO getDeptInfo(DeptInfoVO deptVO) {
		return deptMapper.selectOneDept(deptVO);
	}

	@Override
	public int insertDeptInfo(DeptInfoVO deptVO) {
		int result = deptMapper.insertDeptInfo(deptVO);
		System.out.println("id: " + deptVO.getDepartmentId());
		return result;
	}

	@Override
	public Map<String, Object> updateDeptList(List<DeptInfoVO> deptVO) {
		
		// 비즈니스로직 (다건 등록을 할 경우, 특히 모든 데이터가 업데이트 되지 않을 수 있으므로
		// 				아래와 같이 몇 건이 정상적으로 실행됐는지 실패한 업데이트 데이터는 어떤 건지 확인하는 알고리즘 작성 가능
		
		boolean result = false;
		List<Integer> successList = new ArrayList<>();
		int count = 0;
		
		for(DeptInfoVO deptInfo : deptVO) {
			int res = deptMapper.updateDeptInfo(deptInfo);
			if(res > 0) {
				// 정상적으로 수정이 되었을 경우
				count += 1;
				successList.add(deptInfo.getDepartmentId());
			}
		}
		
		if(count > 0 ) {
			result = true;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("success", count);
		map.put("deptList", successList);
		
		return map;
	}

	@Override
	public int deleteDeptList(List<DeptInfoVO> deptVO) {
		int count = 0;
		for(DeptInfoVO deptInfo : deptVO) {
			count += deptMapper.deleteDeptInfo(deptInfo.getDepartmentId());
		}
		return count;
	}

}