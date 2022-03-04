package com.qf.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.alipay.api.AlipayConstants.*;

@Controller
public class TestPayController {

    @RequestMapping("/pay")
    @ResponseBody
    public String pay(HttpServletRequest httpRequest,
                      HttpServletResponse httpResponse) throws IOException {

        AlipayClient alipayClient =  new DefaultAlipayClient ( "https://openapi.alipaydev.com/gateway.do" , "2021000119620365", "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCPzM7i+AsKmIcYFp/VXIQD61jYLJiPXI6hHvPAsfRG8nJuHsaDv2Bcn/ZpJRXr3CQmasjh3RvlLa5KaUgeIIbg3WrByDoXu9HKxCQqhOZ4/dPf11RuqVkDHT1argex5xqZHLH1Ulq56U5ofspFy14HZjdy5q4xTU1ITbpshTcfqLE9tQs/7o/aNHF5/zKJRL42HxKU+SQFq/CTbEuH5phHbxSYA9Ks5NyaBPc5g1QZ4POwH4GJUzDwaflpA6/HmMHx7+TV15AcpKsgW2OlZh3N6D3ylA9C2GQurE/1hPbvwlAGyu5UPUv3ueHIk1ovzJf6YtH1borZQjYyGZFBTzNxAgMBAAECggEAOYZb6O4DNLzpphg+3lKBOTQnuWq1qPwEzq2cIyTNLKiGYEkNbtzbBpKzN0Kvr0fEal3jeH9ETnzksT3GrM6kRkYtE9IucxoehbfLI/tAW3xnCq+QRiiWxCkpRyJBT8i8mi2hfxrv0lljlWfLMPp25gzc96kSXASpetglUI5GC/KNWouIGPABTgM0jfgi/P+C3s9HW6CoqnufP+0ySLztI4B9YoOIafTAjHbZx08AEozI2PKrDoFKGAXYk2ZXdmDN7tuMOyvO23YwecjlrQsJQsJOED91AWBSF+zGbR0EQDwfByFWe7GUklNu1pyG7QUny2N0gFGPmT0mL/W/d1BIRQKBgQC/49XDvx1Wqz/K10rXkpC/w5M19icjDrLZUq1yJsKoRmRIO7fxEOEq1qaiFBkFbegzKDHhaR1HIN98yVTApUyC1oNssN3sAFPfJTRg+NV0x745ORSt5TCmD0YHkAgYWcMGFtwSQ2vqSd3hTAkp2D9gd6aLoexQ5VMAuP9qLvy5VwKBgQC/1+LdzlP/wd6lRs117q5y6slvo1JY+OJSf96hi7jUiCtL0Y0cIk5emvBcVNWn/lQEuF2bRWaak8Wnn/oiEbSALp3Hq6ifmMGFRoMqLrYTUHo3odKuHh+KaBlD+WEB4fjt8SPqL2FyW6uTzjAzyJikrxqGNXPVhnweVGq+9c/UdwKBgFMGfUNIuaT6S4u0Q0mqvL58HIHIk35zqaamQy2U37x3WIR4ckn3h7VFUqvmDj1vJHyd85cYJ/bSnmAbrHP+EXC65/prPFoUejWen18lyxXQJtoTfIMRG09+ZEp5Y0dF7z//PBEa4uJoO3xzrbNRQXq3ynO4B8uorZUudjS3R9nZAoGAXVdeI8uzJRqgCMyVRU83g+Rm1DGj7HRUMg3TNci0ynvijQZNYSXz+aXrUxFXz2A4Yv+NU/IFhSpnHjMBUjhJGaEU1cTYy6ZYak8vnIOTxXv/qzfKOTug3wfdWyxoHbFkG+mbLuOUxz6BEe5dl6R/WIl2H1y8mmTP2TX7piQV1esCgYAvfIJYxqvuNQnCXpiPZHHTSDE2DwztBcRXf72bmnn2pUtc/zKRlZHORpBLaI9aJ+3Hef019kEuPmp4FOee0Ph8j9tpBJZZ01QDsO3dBsoV0OBIc9RWiyrFxjsGIZkv8DhNSxHMpZHDvk7o7WIz7mqmsuS0U0Uta4Qvjx5lmGEZKQ==", "json", "utf-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlRKLkTKN/Wk6iHmoTh5/Oj3IP2++v7v52pVLe1lOW9KX+hZAJ+oME+2WovaXc11BOM17SNZusZp0SKK6EMZbBxUQlQPdRwSTxJUNWKvciYOe1M5rphVzsAqvaCbxrAaClZQ9Kl6IyYE35dJcdlUkOQ2hYzcWvErRmwCR9K1ySAQGH4xeC6nAqxTfcVF9cLbW2WHvWfvwr2mXmXIhU5vSvfkqrwogVWJCkz1rQL79vxH66zT3CVaconOx5cYgk6poaPfz7i/z78pxPjGBftN6ehliX4Pza9IiyVy/2yS5aXuOaYSJaaE22x113UaqGfw/546/B+XRBhMCqjQ4/SQ3gQIDAQAB", "");  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest =  new AlipayTradePagePayRequest (); //创建API对应的request
        alipayRequest.setReturnUrl( "http://domain.com/CallBack/return_url.jsp" );
        alipayRequest.setNotifyUrl( "http://domain.com/CallBack/notify_url.jsp" ); //在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\"20150320010101001\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":88.88,"  +
                "    \"subject\":\"Iphone6 16G\","  +
                "    \"body\":\"Iphone6 16G\","  +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
                "    \"extend_params\":{"  +
                "    \"sys_service_provider_id\":\"2088511833207846\""  +
                "    }" +
                "  }" ); //填充业务参数
        String form= "" ;
        try  {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType( "text/html;charset=utf-8");
        httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
        return form;

    }
}
