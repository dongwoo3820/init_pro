package com.resume.init.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageAttach {

    private String id;
    private String info_id;
    private String imgname;
    private String imgoriginname;
    private String imgurl;
    
}
