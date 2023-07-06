package com.yedam.app.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("aService")
public class AaaServiceImpl implements AaaService {

	@Autowired
	AaaMapper aaaMapper;
	
	@Transactional		// 내부에 존재하는 sql문들이 같이 동작할 수 있도록 제어 가능 (같은 트랜잭션으로 묶임)
	@Override
	public void insert() {
		aaaMapper.insert("201"); // 자동 parsing 돼서 데이터베이스에 들어감
		aaaMapper.insert("a901");
		// DB타입이 number여서 문자가 들어가는 순간 오류가 남.
		// @Transactional 은 sql문이 몇 개가 있든 메소드 내의 sql 문을 하나로 보고 전부 성공해야 성공으로 확인
		// 하나라도 오류가 나면 실행 오류뜨면서 전부 데이터 입력 x
	}

}