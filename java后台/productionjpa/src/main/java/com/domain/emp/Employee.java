package com.domain.emp;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

//员工
@Data
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    private String employeeName;

    private String sex;

    //身份证
    private String idCode;

    private Date birthday;

    public void setBirthday(String birthday) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.birthday = sDateFormat.parse(birthday);
    }

    private Date joinDate;

    public void setJoinDate(String joinDate) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.joinDate = sDateFormat.parse(joinDate);
    }

    private String status;

    //学历
    private String education;

    //学位
    private String degree;

    //专业
    private String major;

    //毕业学校
    private String graduateSchool;

    //  教育形式
    private String educationForm;
    //部门ID
    @ManyToOne()
    @JoinColumn(name = "departmentId")
    private Department department;

}