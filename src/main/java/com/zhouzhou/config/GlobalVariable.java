package com.zhouzhou.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GlobalVariable {

    private String server;

    private static GlobalVariable globalVariable = new GlobalVariable();

    private GlobalVariable(){}

    public static GlobalVariable getInstance(){
        return globalVariable;
    }
}
