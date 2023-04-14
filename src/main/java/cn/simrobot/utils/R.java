package cn.simrobot.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    private static Integer code;
    private String message;
    private static Map<String,Object> data;

    public R(Integer code){
        this.code = code;
    }

    public static R ok(){
        return new R(200);
    }
    public static R ok(String s){
        R r = new R(200);
        r.setMessage(s);
        return r;
    }

    public static R data(String s,Object o){
        R r = new R(200);
        data = new HashMap<>();
        data.put(s,o);
        r.data = data;
        return r;
    }


    public static R error(String msg){
        R r = new R(500);
        r.setMessage(msg);
        return r;
    }

}
