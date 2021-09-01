package com.resume.init.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileAttach {

    private String id;
    private String info_id;
    private String filename;
    private String fileoriginname;
    private String fileurl;
    
}
