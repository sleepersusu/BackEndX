package com.example.bistro.employee;




import com.example.bistro.jobTiltle.JobTitle;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter

@Entity
@Table(name = "Employee")
public class Employee {


	
	
	

    //PK
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private Integer ID;

    //欄位
        //員工資訊
            private String employeeAccount ;
            private String employeePassword ;
            private String employeeName;
            private String employeeGender ;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @Temporal(TemporalType.TIMESTAMP)
            private Date   employeeBorn  ;

            private String employeeTel  ;
            private Integer employeeSeniority ;
            private Integer employeeSalary ;
            private String employeeStatus  ;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @Temporal(TemporalType.TIMESTAMP)
            private Date createdAt  ;

    //FK
        //多對一：多個員工可以來自同個職位
            @ManyToOne(fetch = FetchType.LAZY)
            @JoinColumn(name = "jobtitleId")
            private JobTitle jobTitle;  // 職位


    public  Employee(){};




}
