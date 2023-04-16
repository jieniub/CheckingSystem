package cn.simrobot.service;

import de.jplag.options.LanguageOption;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;

public interface CheckService {
    boolean checkDir(File file, HttpServletResponse response, LanguageOption type);
}
