package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberView;

/*
 * Controller : View를 통해서 요청한 기능을 담당
 *              해당 메서드로 전달 된 데이터들을 가공 처리(vo객체 담아주기)한 후 Dao메서드 호출 시 vo 객체를 전달해준다.
 *              Dao부터 반환받은 결과에 따라 사용자가 보게 될 화면을 지정해준다. 
 */

public class MemberController {
	
	public void insertMember(String userId, String userPwd, String userName, String gender, int age, String email, String phone,
			String address, String hobby) {
		
		// 1. 전달 된 데이터들을 Member객체에 담기 => 가공 처리
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		// 2. Dao의 insertMember 메서드 호출
		int result = new MemberDao().insertMember(m);
		
		// 3. result 결괏값에 따라서 사용자가 보게 될 화면 지정
		if(result > 0) { // 삽입 된 해의 개수가 1개 이상 -> 성공
			// 성공 메세지 출력
			System.out.println("회원가입 성공");
		}else { // 삽입 된 행의 개수가 0개 -> 실패
			// 실패 메세지 출력
			System.out.println("회원가입 실패");
		}
	}
	
	/**
	 * 사용자의 회원 전체 조회 요청을 처리해주는 메서드
	 */
	public void selectAll() {
		
		// 결괏값을 담을 변수
		// SELECT -> ResultSet -> ArrayList<Member>
		
//		ArrayList<Member> list = new ArrayList<>();
//		list = new MemberDao().selectAll();
		ArrayList<Member> list = new MemberDao().selectAll();
		
		// 조회 결과가 있는지 없는지 판단 후 사용자가 보게 될 view 화면 지정
		if(list.isEmpty()) { // true -> 텅 빈 리스트 반환 -> 조회 결과가 없다
			// 조회 결과가 없을 경우 보게 될 화면
			new MemberView().displayNodata("전체 조회 결과가 없습니다.");
		}else {
			// 조회 결과가 있을 경우
			new MemberView().displayList(list);
		}
	}
	
	/**
	 * 사용자의 아이디로 검색 요청을 하는 메서드
	 * @param userId : 사용자가 입력했던 검색하고자 하는 아이디
	 */
	public void selectByUserId(String userId) {
		
		// 결괏값을 담을 변수
		// SELECT -> ResultSet -> Member
		
		Member m = new MemberDao().selectByUserId(userId);
		
		// 조회 결과 검색된 데이터가 있는지 없는지 판단 후 사용자가 보게 될 화면을 지정
		if(m == null) { // 조회 결과가 없다.
			new MemberView().displayNodata(userId + "에 해당하는 검색 결과가 없습니다.");
		}else { // 조회 결과가 있다.
			System.out.println(m);
		}
	}
	
	public void selectByUserName(String keyword) {
		// 결괏값을 담을 변수
		// SELECT -> ResultSet -> ArrayList<Member>
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		// 조회 결과가 있는지 없는지 판단
		if(list.isEmpty()) { // 검색 결과가 없는 경우
			new MemberView().displayNodata(keyword + "에 대한 검색 결과가 없습니다.");
		}else {
			new MemberView().displayList(list);
		}
	}
	
	/**
	 * 사용자의 회원정보 변경 요청을 처리해주는 메서드
	 * @param userId : 변경하고자 하는 회원의 아이디
	 * @param newPwd : new~~ 변경할 정보들
	 * @param newEmail
	 * @param newPhone
	 * @param newAddress
	 */
	public void updateMember(String userId, String newPwd, String newEmail, String newPhone, String newAddress) {
		
		// vo 객체로 입력받은 값을 가공 처리 해주기
		Member m = new Member();
		
		m.setUserId(userId);
		m.setUserPwd(newPwd);
		m.setEmail(newEmail);
		m.setPhone(newPhone);
		m.setAddress(newAddress);
		
		// 가공한 vo 객체를 Dao단으로 넘기기
		int result = new MemberDao().updateMember(m);
		
		// 결과에 따른 화면 지정
		if(result > 0) {
			System.out.println("회원 정보 변경 성공");
		}else {
			System.out.println("회원 정보 변경 실패");
		}
	}
	
	/**
	 * 사용자가 회원 탈퇴 요청 시 처리해주는 메서드
	 * @param userId -> 사용자가 입력한 회원의 아이디 값
	 */
	public void deleteMember(String userId) {
		
		int result = new MemberDao().deleteMember(userId);
		
		if(result > 0) {
			System.out.println("회원 탈퇴 성공했습니다.");
		}else {
			System.out.println("회원 탈퇴 실패했습니다.");
		}
	}

}

