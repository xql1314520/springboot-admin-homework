package com.qf.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.ITestOrConfiguration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by 54110 on 2021/4/29.
 */
public class UploadUtils {

    private String ak = "m7mFmCTX55PMNPkEBaOLFQMLxW4ZFfZwf2EBm1M2";

    private String sk="dn220PqIs813-IYrIZtzO_bGLXPo3QSVNep6wNty";

    private String buckName="learnjdbc";


    public String upload(MultipartFile file){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传

//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {

            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(file.getBytes());
            Auth auth = Auth.create(ak, sk);
            String upToken = auth.uploadToken(buckName);
            try {
                Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return "http://qrl20it35.hd-bkt.clouddn.com/"+putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
