package com.hebust.thesismanage.controller;

//public class FileUploadController {
import com.hebust.thesismanage.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@CrossOrigin
@RestController
public class FileUploadController {

    @Value("${upload.path}") // 从配置文件中获取上传文件的路径
    private String uploadPath;

    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 生成UUID文件名
        UUID uuidFileName = UUID.nameUUIDFromBytes(fileName.getBytes());
        // 构建文件路径
//        String filePath = uploadPath + File.separator + fileName;
        String filePath = uploadPath + File.separator + uuidFileName.toString() + ".pdf";

        // 保存文件
        file.transferTo(new File(filePath));
        log.info("成功上传文件到服务器，路径为：{}", filePath);

        return Result.success(filePath);
    }
}
