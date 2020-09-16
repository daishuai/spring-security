package com.daishuai.web.controller;

import com.daishuai.dto.FileInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Description: 文件控制器
 * @Author: daishuai
 * @CreateDate: 2018/12/17 20:38
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping
    public FileInfo upload(MultipartFile file) {
        System.out.println(file.getName());
        //TODO 把文件上传到FastDFS分布式文件系统
        FileInfo fileInfo = new FileInfo();
        fileInfo.setPath("");
        return fileInfo;
    }

    /**
     * 文件下载
     * @param id
     * @param request
     * @param response
     */
    @GetMapping
    @RequestMapping("/{id}")
    public void download(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        try(OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("application/x-download");
        } catch (Exception e) {
        }
    }
}
