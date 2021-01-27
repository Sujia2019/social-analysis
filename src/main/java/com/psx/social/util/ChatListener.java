package com.psx.social.util;

import com.psx.social.entity.Answer;
import com.psx.social.entity.Question;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ChatListener {

    public static List<String> getLogsName() {
        Resource[] resources = new Resource[0];
        try {
            resources = new PathMatchingResourcePatternResolver()
                    .getResources(ResourceUtils.CLASSPATH_URL_PREFIX + "logs/*");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> res = new ArrayList<>();
        for (Resource resource : resources) {
            res.add(resource.getFilename());
        }
        return res;
    }

    public static String getLog(String fileName) {
        ClassPathResource classPathResource = new ClassPathResource("logs/" + fileName);
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
