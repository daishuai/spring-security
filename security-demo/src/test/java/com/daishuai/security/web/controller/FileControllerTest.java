package com.daishuai.security.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @Description: 文件控制器测试类
 * @Author: daishuai
 * @CreateDate: 2018/12/17 20:31
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void whenUploadSuccess() throws Exception {
        mockMvc.perform(fileUpload("/file")
        .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello world".getBytes("UTF-8"))))
                .andExpect(status().isOk());
    }
}
