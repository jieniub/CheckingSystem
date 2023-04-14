package cn.simrobot.controller;

import cn.simrobot.service.CheckService;
import cn.simrobot.utils.R;
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
    public R checkDir(@RequestBody File file){
        boolean b = checkService.checkDir(file);
        return b ? R.ok(). : R.error("系统内部错误");
    }
}
