package com.psx.social.util;

import com.psx.social.entity.Answer;
import com.psx.social.entity.Question;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadTxt {

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

    public static String sendResult(String scores) {
        String res = "";
        try {
            ClassPathResource classPathResource = new ClassPathResource("classpath:questions/Research_2_Res");
            InputStream in = classPathResource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            while ((str=br.readLine())!=null){
                if(str.equals(scores)){
                    res=br.readLine();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

}
