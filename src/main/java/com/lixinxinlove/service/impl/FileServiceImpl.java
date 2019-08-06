package com.lixinxinlove.service.impl;

import com.lixinxinlove.service.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements IFileService {


    private Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path) {

        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;

        logger.info("{}----{}----{}",path,fileName,uploadFileName);


        File fileDir=new File(path);
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile=new File(path,uploadFileName);

        try {
            file.transferTo(targetFile);

            //TODO 上穿到图片服务器


            // TODO 删除upload下的文件



        } catch (IOException e) {
            logger.info("上传异常");
            return null;
        }

        return targetFile.getName();


    }


}
