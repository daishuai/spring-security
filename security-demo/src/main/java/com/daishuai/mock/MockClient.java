package com.daishuai.mock;


import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/8/31 19:18
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class MockClient {

    public static void main(String[] args) throws IOException {
        configureFor(9999);
        removeAllMappings();
        mock("/user/1", "user");
    }

    public static void mock(String url, String fileName) throws IOException {
        ClassPathResource source = new ClassPathResource("mock/" + fileName + ".txt");
        String content = StringUtils.join(FileUtils.readLines(source.getFile(), "UTF-8"), '\n');
        stubFor(get(urlEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }
}
