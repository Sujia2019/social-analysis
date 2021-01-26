package com.psx.social.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Verify {
    private static final Logger LOGGER = LoggerFactory.getLogger(Verify.class);

    public String sendCode(String phoneNumber) {
        int codeInt = (int) ((Math.random() * 9 + 1) * 100000);
        String i = String.valueOf(codeInt);

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAI4FyyLrtFdyZ181aSLvTN", "f3jxBf7CYjycLHreUpAFRFCDjIDnp4");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "灵魂决斗");
        request.putQueryParameter("TemplateCode", "SMS_189611057");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + i + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            LOGGER.info(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return i;
//        RedisUtil.insertCode(phoneNumber,i);
    }
//    public static void main(String[] args) {
//        sendCode("18539403150");
//    }

}
