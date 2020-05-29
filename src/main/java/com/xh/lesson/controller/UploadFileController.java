package com.xh.lesson.controller;

import com.xh.lesson.entity.SysLog;
import com.xh.lesson.utils.DataResult;
import com.xh.lesson.utils.MinioUtil;
import com.xh.lesson.vo.req.SysLogPageReqVO;
import com.xh.lesson.vo.resp.PageVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/upload")
@Api(tags = "文件上传-文件上传管理")
@RestController
public class UploadFileController {

    @PostMapping("/test1")
    public DataResult<PageVO<SysLog>> pageInfo(@RequestParam(value = "file") MultipartFile file) {
        String fileUrl = MinioUtil.uploadFiles(file);
        return DataResult.success(fileUrl);
    }
}
