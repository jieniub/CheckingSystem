package cn.simrobot.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    private static Integer code;
    private static String message;
    private static Map<String,Object> data;

    public static void ok(){
        code = 200;
    }
    public static void data(String s,Object o){
        data = new HashMap<>();
        data.put(s,o);
    }
    public static void error(String msg){
        code = 500;
        message = msg;
    }
}
