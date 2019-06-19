package com.linkpets.cms.aprilfool.resource;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.util.QrCodeGeneraterUtil;
import com.linkpets.util.UUIDUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author SteveYang
 * @date 2019/3/25
 */


@Api(value = "活动文件上传接口", tags = "活动文件上传接口")
@ResponseResult
@RestController
@RequestMapping("/activity/oss")
@Slf4j
public class CmsActivityOssResource {


    private static final String ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";

    private static final String ACCESSKEY = "LTAIKclZeHcK1wEi";

    private static final String ACCESS_SK = "JptvG66RV6MWa5DPJFecDWxoMDQotb";

    private static final String BUCKET_NAME = "linkpet-image-bucket-1";

    private final static String OSS_STORAGE_SLICE = "activity/aprilfool";

    private final static String URL_PREFIX = "https://melody.memorychilli.com/";


    @PostMapping("/image")
    @ApiOperation(value = "上传图片接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ossZone", value = "存储片区(此处填活动id)", required = true, dataType = "String", paramType = "query")
    })
    public JSONObject uploadImage(@ApiParam(name = "file", value = "上传的文件", required = true) MultipartFile file,
                                  @RequestParam("ossZone") String ossZone) throws IOException {
        JSONObject result = new JSONObject();
        String objName = "";
        objName += OSS_STORAGE_SLICE + "/" + ossZone + "/" + UUIDUtils.getUUID();


        //拼文件格式

        if (file == null || file.isEmpty()) {
            result.put("data", null);
            return result;
        }

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String fileObjName = objName + suffix;

        // 创建OSSClient实例。
        OSSClient client = new OSSClient(ENDPOINT, ACCESSKEY, ACCESS_SK);


        try {

            // 上传文件流。
            InputStream inputStream = file.getInputStream();

            client.putObject(BUCKET_NAME, fileObjName, inputStream);
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message: " + oe.getErrorCode());
            log.error("Error Code:       " + oe.getErrorCode());
            log.error("Request ID:      " + oe.getRequestId());
            log.error("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ce.getMessage());
        } finally {

            if (client != null) {
                client.shutdown();
            }

            result.put("data", URL_PREFIX + fileObjName);
            return result;
        }
    }


    @GetMapping("/image/poster")
    @ApiOperation(value = "获取海报图片接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String", paramType = "query")
    })
    public JSONObject getPosterImage(@RequestParam String userId){
        JSONObject result = new JSONObject();
        String objName = "";
        objName += OSS_STORAGE_SLICE + "/tempPost/" + UUIDUtils.getUUID();



        // 创建OSSClient实例。
        OSSClient client = new OSSClient(ENDPOINT, ACCESSKEY, ACCESS_SK);

        String fileName=UUIDUtils.getUUID()+".jpg";
        try {



            InputStream inputStream=QrCodeGeneraterUtil.generatePost(fileName,userId);

            client.putObject(BUCKET_NAME, objName+"/"+fileName, inputStream);
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message: " + oe.getErrorCode());
            log.error("Error Code:       " + oe.getErrorCode());
            log.error("Request ID:      " + oe.getRequestId());
            log.error("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ce.getMessage());
        } finally {

            if (client != null) {
                client.shutdown();
            }
            result.put("data", URL_PREFIX +objName+"/"+fileName);
            return result;
        }
    }

}
