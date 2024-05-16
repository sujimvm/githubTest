package com.example.HRmarster.model;

import lombok.Data;

@Data
public class MemberDTO {
    private int member_key;
    private String member_id;
    private String member_pwd;
    private String member_name;
    private String member_gender;
    private String member_birth;
    private String member_addr;
    private String member_phone;
    private String member_email;
    private String member_image;
    private String member_dept;
    private String member_position;
    private String member_start_date;
    private String member_end_date;
    private String member_status;

    // 생성자, Getter 및 Setter 생략 (필요시 추가)
}
