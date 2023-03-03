package com.zhouzhou.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Data
@Component
public class GlobalVariable {

    private String server = "斗转星移";

    private List<Long> GroupIdList;

    private static GlobalVariable globalVariable = new GlobalVariable();


    private GlobalVariable(){}

    public static GlobalVariable getInstance(){
        return globalVariable;
    }
}
