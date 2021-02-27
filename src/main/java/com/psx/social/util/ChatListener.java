package com.psx.social.util;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChatListener {

    public static List getLogsName() {
        String basePath = "/home/sujia/social-logs/";
        String[] list = new File(basePath).list();
        if (list != null)
            return Arrays.asList(list);
        return Collections.EMPTY_LIST;
    }

    public static String getLog(String fileName) {
        Resource classPathResource = new PathResource("/home/sujia/social-logs/" + fileName);
        InputStream in = null;
        try {
            in = classPathResource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder("");
            String indexLine = "";
            while ((indexLine = br.readLine()) != null) {
                str.append(indexLine);
            }
            return str.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "发生错误，无法获取日志信息";
    }

}
