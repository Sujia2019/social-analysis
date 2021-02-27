package com.psx.social.util;

import com.psx.social.entity.Answer;
import com.psx.social.entity.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadTxt {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadTxt.class);

    public static List<Question> ReadQuestions(String url){
        List<Question> questions = new ArrayList<>();
        try {
            ClassPathResource classPathResource = new ClassPathResource(url);
            InputStream in = classPathResource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String title;
            String str;
            int id=0;
            int answerId=0;
            while ((str=br.readLine())!=null){
                if(!str.contains("//")){
                    title=str;
                    Question q = new Question();
                    q.setTitle(title);
                    id=id+1;
                    answerId=0;
                    q.setQuestionID(id);
                    q.setAnswers(new ArrayList<>());
                    questions.add(q);
                }else if (str.contains("//")) {
                    Answer a = new Answer();
                    answerId++;
                    a.setAnswerID(answerId);
                    a.setContext(str.split("//")[0]);
                    a.setScore(Integer.parseInt(str.split("//")[1]));
                    Question q = questions.get(questions.size()-1);
                    List<Answer> list = q.getAnswers();
                    list.add(a);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public static String sendResult(String scores, String url) {
        String res = "";
        try {
            ClassPathResource classPathResource = new ClassPathResource("classpath:questions/" + url);
            InputStream in = classPathResource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            while ((str = br.readLine()) != null) {
                if (str.equals(scores)) {
                    res = br.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void writeQuestions(String url, List<Question> questionList) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(url));
            for (Question question : questionList) {
                out.write(question.getQuestionID() + "、" + question.getTitle() + "\n");
                for (Answer a : question.getAnswers()) {
                    out.write(a.getContext() + "//" + a.getScore() + "\n");
                }

            }
            LOGGER.info("问卷创建成功！");
            out.close();
        } catch (IOException e) {
            LOGGER.error("问卷创建失败\n{}", e.getMessage());
        }
    }

    public static void writeRes(String url, List<String> res) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(url));
            out.write("A\n");
            out.write(res.get(0) + "\n");
            out.write("B\n");
            out.write(res.get(1) + "\n");
            out.write("C\n");
            out.write(res.get(2) + "\n");
            out.write("D\n");
            out.write(res.get(3) + "\n");
            out.close();
            LOGGER.info("分析答案创建成功！");
        } catch (IOException e) {
            LOGGER.error("分析答案创建失败\n{}", e.getMessage());
        }
    }

}
