package cn.simrobot.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.File;

public interface CheckService {
    boolean checkDir(File file, HttpServletResponse response);
}
