package com.example.HRmarster.model;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	MemberDTO doLogin(String user_id);
	List<MemberDTO> getMemberList();
}
