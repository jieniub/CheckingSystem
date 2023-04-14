package cn.simrobot.controller;

import cn.simrobot.service.CheckService;
import cn.simrobot.utils.R;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping("/check")
public class CheckingController {

    @Resource
    private CheckService checkService;

    @PostMapping("/checkdir")
    public R checkDir(@RequestBody File file , HttpServletResponse response){
        boolean b = checkService.checkDir(file,response);
        return b ? R.ok("请求成功") : R.error("系统内部错误");
    }


}
