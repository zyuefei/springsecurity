package com.portjs.base.exception;

import org.apache.commons.fileupload.FileUploadException;

/**
 * @author gumingyang
 * @description 上传文件名字校验
 **/
public class FileNameLengthLimitExceededException extends FileUploadException {
    private static final long serialVersionUID = 1L;
    private int length;
    private int maxLength;
    private String filename;

    public FileNameLengthLimitExceededException(String filename, int length, int maxLength)
    {
        super("上传文件名过长，最大上传" + maxLength+"个字符");
        this.length = length;
        this.maxLength = maxLength;
        this.filename = filename;
    }

    public String getFilename()
    {
        return filename;
    }

    public int getLength()
    {
        return length;
    }

    public int getMaxLength()
    {
        return maxLength;
    }
}
