package com.resume.init.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Prize {
    
    private String info_id;
    private String name;
    private String agency; 
    private String reg_date;
    private String evidence;
    private String id;
    private int totcnt;
}
