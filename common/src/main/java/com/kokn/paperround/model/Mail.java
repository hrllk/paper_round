package com.kokn.paperround.model;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class Mail{

//    private file;

    private String from;
    private String to;
    private String subject;
    private String template;
    private File file;

}
