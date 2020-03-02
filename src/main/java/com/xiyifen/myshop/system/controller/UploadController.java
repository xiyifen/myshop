package com.xiyifen.myshop.system.controller;

import com.xiyifen.myshop.common.result.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@RestController
public class UploadController {

    @Value("${upload.imagePath}")
    private String path;

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
    @PostMapping("/import")
    public ResponseResult importData(MultipartFile file, HttpServletRequest request) throws IOException {
        // 生成日期
       String format = sdf.format(new Date());
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png")/* || suffix.equals(".gif")*/){
            fileName = UUID.randomUUID().toString()+suffix;
            // 文件保存策略为用日期保存路径
            File targetFile = new File(path+format, fileName);
            if(!targetFile.getParentFile().exists()){    //注意，判断父级路径是否存在
                targetFile.getParentFile().mkdirs();
            }
            long size = 0;
            //保存
            try {
                file.transferTo(targetFile);
                size = file.getSize();
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseResult().error(400,"上传失败！");
            }
            //项目url，http://localhost:8090
            String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            //文件获取路径
            fileUrl = fileUrl + request.getContextPath() + "/img" +format+ fileName;
            System.out.println(request.getContextPath());
            return new ResponseResult().success("fileUrl", fileUrl);
        }else{
            return new ResponseResult().error(400,"图片格式有误，请上传.jpg、.png、.jpeg格式的文件");
        }


    }


}
