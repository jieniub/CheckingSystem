package cn.simrobot.service.impl;

import cn.simrobot.service.CheckService;
import de.jplag.JPlag;
import de.jplag.JPlagComparison;
import de.jplag.JPlagResult;
import de.jplag.options.JPlagOptions;
import de.jplag.options.LanguageOption;
import de.jplag.reporting.Report;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class CheckServiceImpl implements CheckService {
    @Override
    public boolean checkDir(File file, HttpServletResponse response, LanguageOption type) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dddd");
        String format = simpleDateFormat.format(date);
        String name = format +"/" + String.valueOf(UUID.randomUUID());//生成文件名

        // ----------------------- jpalg -----------------------
        JPlagOptions jPlagOptions = new JPlagOptions(file.getName(),type);
        jPlagOptions.setBaseCodeSubmissionName(null);

        try {
            JPlag jPlag = new JPlag(jPlagOptions);
            JPlagResult result = jPlag.run();

            List<JPlagComparison> comparisons = result.getComparisons();

            File outputDir = new File("cn/simrobot/result/"+name);//生成文件路径

            outputDir.createNewFile();
            Report report = new Report(outputDir, jPlagOptions);
            report.writeResult(result);
        // ------------------------- zip -------------------------
            String zipFileName = "cn/simrobot/result/zip/" + name + ".zip";//压缩文件路径
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName));

            zip(zipOutputStream,outputDir,"");
            zipOutputStream.close();

        //------------------------- download -------------------------

            // TODO: 2023/4/14 将压缩后的文件加入响应中
            download(response,new File(zipFileName));

        } catch (Exception e) {
            return false;
        }
        return false;
    }


    private void zip(ZipOutputStream zipOutputStream,File file, String base) throws Exception {
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (base.length() != 0){
                zipOutputStream.putNextEntry(new ZipEntry(base+"/"));
            }
            for (int i = 0;i<files.length;i++){
                zip(zipOutputStream,files[i],base + files[i])
            }
        }else{
            zipOutputStream.putNextEntry(new ZipEntry(base));
            FileInputStream fileInputStream = new FileInputStream(file);
            int b;
            System.out.println(base);
            while ((b = fileInputStream.read()) != -1)
                zipOutputStream.write(b);
            fileInputStream.close();
        }
    }

    // TODO: 2023/4/14 将压缩后的文件加入响应中
    private void download(HttpServletResponse response,File file) throws Exception{
        String name = file.getName();

        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream fis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();

        response.reset();

        response.setCharacterEncoding("UTF-8");

        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        outputStream.write(buffer);
        outputStream.flush();

    }
}
