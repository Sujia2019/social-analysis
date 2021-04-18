package com.psx.social.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alinlp.model.v20200629.GetPosChEcomRequest;
import com.aliyuncs.alinlp.model.v20200629.GetPosChEcomResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NLPUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(NLPUtil.class);

    public static String getData(String message) throws ClientException {
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                "cn-hangzhou",
                "LTAI5t7qHF3TxkNuZVg7cuey",
                "Ec3vczPwYuZL8mOytuvsFJwxAD70e0");
        IAcsClient client = new DefaultAcsClient(defaultProfile);
        GetPosChEcomRequest request = new GetPosChEcomRequest();
        request.setSysEndpoint("alinlp.cn-hangzhou.aliyuncs.com");
        request.setActionName("GetSaChGeneral");
        request.setServiceCode("alinlp");
        request.setText(message);
        request.setTokenizerId("MAINSE");
        long start = System.currentTimeMillis();
        GetPosChEcomResponse response = client.getAcsResponse(request);
        LOGGER.info(response.getRequestId() + "\n" + response.getData() + "\n" + "cost:" + (System.currentTimeMillis() - start));
        return response.getData();
    }
}
