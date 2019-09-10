package com.portjs.base.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Calendar;

/**
 * Created by dengshuangzhen on 2018\11\29 0029
 */
@Slf4j
@Service
public class UploadService {
    static final String tag = "Upload===>";
    @Value("${upload-path}")
    private String path1;

    public String uploadFlie(MultipartFile file) {
         if (StringUtils.isEmpty(file)) {
            return "1";
        }
        try {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            ApplicationHome home = new ApplicationHome(this.getClass());
            File jarFile = home.getSource();
            File file1 = new File("");
//            String separtor = File.separator;
            String separtor = "/";

            String filePath1 = path1 + separtor + year +separtor + month + separtor;//服务器上存储的路径
//            String filePath1 = jarFile.getParentFile().toString()+file1.separator+year+file1.separator+month+file1.separator;//服务器上存储的路径
            String filePath2 = separtor + year + separtor + month +separtor;//入库的路径

           /* String filePath1 = jarFile.getParentFile().toString()+"\\files\\"+year+"\\"+month+"\\";
            String filePath2 = "\\files\\"+year+"\\"+month+"\\";//入库的路径*/
            String fileName = file.getOriginalFilename();

            fileName = System.currentTimeMillis() + "~" + fileName.replace("%", "x");//添加时间戳
            File fp = new File(filePath1);
            // 创建目录
            if (!fp.exists()) {
                fp.mkdirs();// 目录不存在的情况下，创建目录。
            }

            File dest = new File(filePath1 + fileName);
            if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                dest.getParentFile().mkdir();   //没有就创建
            }
            if (!dest.exists()) {
                dest.createNewFile();
            }

            file.transferTo(dest);
            String result =filePath1 + fileName;
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println(result.substring(result.indexOf(filePath2)));
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            return result.substring(result.indexOf(filePath2));
            //return dest.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            log.debug(tag + "Upload()error#################### ", e);
        }
        return "1";
    }

    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        if (fileName != null) {
            //设置文件路径
            File file = new File(path1 + fileName);
            if (file.exists()) {
                if ("FF".equals(getBrowser(request))) {
                    // 火狐浏览器 设置编码new String(realName.getBytes("GB2312"), "ISO-8859-1");
                    fileName = new String(fileName.getBytes("GB2312"), "ISO-8859-1");
                } else {
                    fileName = URLEncoder.encode(fileName, "UTF-8");//encode编码UTF-8 解决大多数中文乱码
                    fileName = fileName.replace("+", "%20");//encode后替换空格  解决空格问题
                }
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }




    private static String getBrowser(HttpServletRequest request) {
        String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (UserAgent != null) {
            if (UserAgent.indexOf("msie") != -1){
                return "IE";
            }
            if (UserAgent.indexOf("firefox") != -1){
                return "FF";
            }

            if (UserAgent.indexOf("safari") != -1){
                return "SF";
            }

        }
        return null;
    }


}
