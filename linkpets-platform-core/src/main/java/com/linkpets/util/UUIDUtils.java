package com.linkpets.util;

import java.util.UUID;

public class UUIDUtils {

    public static String  getId(){
        return UUID.randomUUID().toString().replace("-","");
    }


    public static void main(String[] args) {
        System.out.println(getId());
    }
}
