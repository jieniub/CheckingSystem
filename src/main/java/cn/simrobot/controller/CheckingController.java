package cn.simrobot.controller;

import cn.simrobot.service.CheckService;
import cn.simrobot.utils.R;
import de.jplag.options.LanguageOption;
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

    @PostMapping("/checkdir/java")
    public R checkDirOfJava(@RequestBody File file , HttpServletResponse response){
        boolean b = checkService.checkDir(file,response, LanguageOption.JAVA);
        return b ? R.ok("请求成功") : R.error("系统内部错误");
    }
    @PostMapping("/checkdir/python")
    public R checkDirOfPython(@RequestBody File file , HttpServletResponse response){
        boolean b = checkService.checkDir(file,response, LanguageOption.PYTHON_3);
        return b ? R.ok("请求成功") : R.error("系统内部错误");
    }
    @PostMapping("/checkdir/cpp")
    public R checkOfCpp(@RequestBody File file , HttpServletResponse response){
        boolean b = checkService.checkDir(file,response, LanguageOption.C_CPP);
        return b ? R.ok("请求成功") : R.error("系统内部错误");
    }
    @PostMapping("/checkdir/csharp")
    public R checkOfCSharp(@RequestBody File file , HttpServletResponse response){
        boolean b = checkService.checkDir(file,response, LanguageOption.C_SHARP);
        return b ? R.ok("请求成功") : R.error("系统内部错误");
    }
    @PostMapping("/checkdir/char")
    public R checkOfChar(@RequestBody File file , HttpServletResponse response){
        boolean b = checkService.checkDir(file,response, LanguageOption.CHAR);
        return b ? R.ok("请求成功") : R.error("系统内部错误");
    }
    @PostMapping("/checkdir/text")
    public R checkOfText(@RequestBody File file , HttpServletResponse response){
        boolean b = checkService.checkDir(file,response, LanguageOption.TEXT);
        return b ? R.ok("请求成功") : R.error("系统内部错误");
    }

    @PostMapping("/checkdir/scheme")
    public R checkOfscheme(@RequestBody File file , HttpServletResponse response){
        boolean b = checkService.checkDir(file,response, LanguageOption.SCHEME);
        return b ? R.ok("请求成功") : R.error("系统内部错误");
    }


}
