package com.resume.init.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Work {
    

    private String info_id;
    private String date;
    private String start_date;
    private String end_date;
    private String company_name;
    private String department;
    private String task;
    private String task_mc;
    private String spot;
    private String spot_mc;
    private String id;
    private int totcnt;
}
