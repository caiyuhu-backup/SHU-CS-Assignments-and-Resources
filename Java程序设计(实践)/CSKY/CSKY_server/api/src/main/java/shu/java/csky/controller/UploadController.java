package shu.java.csky.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import shu.java.csky.dao.MyFileMapper;
import shu.java.csky.entity.MyFile;
import shu.java.csky.utils.QiniuUtils;
import shu.java.csky.vo.ResultVO;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Resource
    private QiniuUtils qiniuUtils;
    @Resource
    private MyFileMapper fileMapper;

    @PostMapping("/image")
    public ResultVO upload(@RequestParam("image")MultipartFile file) {
        if(file == null) {
            return new ResultVO(400, "文件不能为空", null);
        }
        String fileName = UUID.randomUUID() + "." + StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        // 上传到七牛云
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload){
            return new ResultVO(200, "成功", QiniuUtils.url + fileName);
        }
        return new ResultVO(400, "失败", null);
    }

    @PostMapping("/uploadFile")
    public ResultVO uploadFile(@RequestParam("file")MultipartFile file) {
        if(file == null) {
            return new ResultVO(400, "文件不能为空", null);
        }
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        // 上传到七牛云
        boolean upload = qiniuUtils.upload(file, fileName);
        MyFile myFile = new MyFile();
        myFile.setUrl(QiniuUtils.url + fileName);
        myFile.setYear(getYear(originalFilename));
        fileMapper.insert(myFile);
        if (upload){
            return new ResultVO(200, "成功", QiniuUtils.url + fileName);
        }
        return new ResultVO(400, "失败", null);
    }

    private Integer getYear(String name) {
        Integer[] years = new Integer[100];
        for (int i = 0; i < 100; i++) {
            years[i] = 1980 + i;
        }
        for (int i = 0; i < 100; i++) {
            if (name.contains(years[i].toString())) {
                return years[i];
            }
        }
        return 0;
    }
}
