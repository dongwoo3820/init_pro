package com.resume.init.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExt extends Personal_info {

    private int rnum;
    private int totcnt;
    private String workArea;
    private String area_id;
    private String career;
    private String area;
    private String qualifi;
    private String client_mc;
    private String task_mc;
    
    public int getRnum() {
        return rnum;
    }

    public void setRnum(int rnum) {
        this.rnum = rnum;
    }

    public int getTotcnt() {
        return totcnt;
    }
    
    public void setTotcnt(int totcnt) {
        this.totcnt = totcnt;
    }
}

