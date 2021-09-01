package com.resume.init.Mapper;

import java.util.List;

import com.resume.init.domain.Account;
import com.resume.init.domain.Authority;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
	
	Account readAccount(String id);
	
	List<String> readAuthorites(String id);
	
	void insertUser(Account account);
	
	void insertUserAuthority(Authority authority);
	
	List<Account> readAllUsers();
	
	/*
	 *  실패횟수 update
	 */
	void failCntUpdate(String id);
	/*
	 *  실패횟수, isEnabled 조회.
	 */
	Account getFailCnt(String id);
	
	/* 
	 * 계정 활성화 여부변경, 1이었으면 0으로 0이었으면 1로 바꾼다.
	 * 0은 false, 1은 true이다.
	*/
	void changeEnabled(String id);
	
	/*
	 * 실패횟수 초기화
	 */
	void resetFailCnt(String id);
}