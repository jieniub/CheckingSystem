package cn.simrobot.service.impl;

import cn.simrobot.service.CheckService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CheckServiceImpl implements CheckService {
    @Override
    public boolean checkDir(File file, HttpServletResponse response) {
        // TODO: 2023/4/14 调用jplag接口来生成结果文件

        // TODO: 2023/4/14 将结果文件通流的方式压缩
        File zip = zip(report);
        // TODO: 2023/4/14 将压缩后的文件加入响应中
        download(response,zip);

        return false;
    }
    // TODO: 2023/4/14 将结果文件通流的方式压缩
    private File zip(File file){
        download(zipfile);
    }
    // TODO: 2023/4/14 将压缩后的文件加入响应中
    private void download(HttpServletResponse response,File file){

    }
}
