package com.example.bistro.jobTiltle;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "JobTitle")
public class JobTitle {

    //PK
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer ID;  // 職位 ID

    //欄位
        private String titleName;

    public JobTitle() {};


}
