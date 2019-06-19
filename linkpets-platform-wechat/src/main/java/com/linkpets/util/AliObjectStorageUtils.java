package com.linkpets.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;

import java.io.InputStream;

/**
 * @author SteveYang
 * @date 2019/3/25
 */
public class AliObjectStorageUtils {


    private static final String endpoint="oss-cn-hangzhou.aliyuncs.com";

    private static final String accessKeyId="LTAIKclZeHcK1wEi";

    private static final String accessKeySecret="JptvG66RV6MWa5DPJFecDWxoMDQotb";

    private static final String bucketName="linkpet-image-bucket-1";

    private final static String portraitObjNamePrefix="images/";

    private final static String activityPicObjNamePrefix="ac_html/";

    private final static String onlineImagePrefix="https://melody.memorychilli.com/";

    public static void generateImage(String fileObjName,InputStream inputStream){

        // 创建OSSClient实例。
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try {

            client.putObject(bucketName,fileObjName,inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {

            if (client != null) {
                client.shutdown();
            }
        }
    }
}
