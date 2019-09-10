package com.portjs.base.controller;

import com.portjs.base.service.UploadService;
import com.portjs.base.util.ResponseMessage;
import com.portjs.base.util.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author gumingyang
 **/
@Controller
@RequestMapping("/uploadFile")
public class FileuploadController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    static final String TAG = "FileuploadController================>";
    @Autowired
    private UploadService upload;

    @RequestMapping("/insert-file")
    @ResponseBody
    public ResponseMessage insertDesign(@RequestBody MultipartFile file){
        logger.debug(TAG+file);
       String url = upload.uploadFlie(file).replace("#","%23");
//        replace("+", "%20").replace("(", "%28").
//               replace(")", "%29").replace(";", "%3B")
//               .replace("@", "%40").
//               replace("&", "%26").
//                       replace(",", "%2C").replace("$", "%24").
//                       replace("^", "%5E").
//                       replace("=", "%3D");

        if("1".equals(url)){
         return  new ResponseMessage(ResultCodeEnum.ERROR.getCode(),"上传失败","");
        }
        return new ResponseMessage(ResultCodeEnum.SUCCESS.getCode() , "上传成功",url);
    }
    //上传公共连接
    @RequestMapping(value = "/uploadCailiao", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage uploadSuperVision(MultipartFile[] files) {
        logger.debug(TAG + "uploadCailiao()files#################### "+files);
        StringBuffer file_path = new StringBuffer();
        try {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                file_path.append(upload.uploadFlie(file));
                logger.debug(TAG + "uploadCailiao()file_path#################### "+file_path);
                if (file_path.toString().equals("1")) {
                    return new ResponseMessage(ResultCodeEnum.ERROR, "上传失败");
                }
                file_path = file_path.append("^");
                logger.debug(TAG + "uploadCailiao()file_path2#################### "+file_path);
            }
            if (!StringUtils.isEmpty(file_path)) {
                return new ResponseMessage(ResultCodeEnum.SUCCESS, file_path);
            }
            return new ResponseMessage(ResultCodeEnum.ERROR, "上传失败");
        } catch (Exception e) {
            logger.debug(TAG + "uploadCailiao()error#################### ", e);
            return new ResponseMessage(ResultCodeEnum.ERROR, "上传失败");
        }
    }

    @RequestMapping("/down-file")
    @ResponseBody
    public void downDesign(HttpServletRequest request, HttpServletResponse response, String fileName, String attname)throws UnsupportedEncodingException {

        System.out.println(fileName+"-----------------------------------------------------------");
        upload.downloadFile(request,response,fileName);
    }
}
