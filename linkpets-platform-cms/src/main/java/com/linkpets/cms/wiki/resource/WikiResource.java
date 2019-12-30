package com.linkpets.cms.wiki.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.wiki.service.IWikiService;
import com.linkpets.core.model.SdArticle;
import com.linkpets.core.model.SdCatalog;
import com.linkpets.core.model.SdCatalogList;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(value = "宠物百科接口", tags = "宠物百科接口")
@ResponseResult
@RestController
@RequestMapping("/wiki")
@Slf4j
public class WikiResource {

    @Resource
    IWikiService wikiService;


    private static final String ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";

    private static final String ACCESSKEY = "LTAIKclZeHcK1wEi";

    private static final String ACCESS_SK = "JptvG66RV6MWa5DPJFecDWxoMDQotb";

    private static final String BUCKET_NAME = "linkpet-image-bucket-1";

    private final static String OSS_STORAGE_SLICE = "wiki";

    @GetMapping("/catalogs")
    @ApiOperation(value = "查询父目录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "wiki类型", name = "type", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult getCatalogs(@RequestParam("type")String type){
        List<SdCatalog> catalogs=wikiService.getCatalogs(type);
        return PlatformResult.success(catalogs);
    }

    @GetMapping("/article")
    @ApiOperation(value = "查询文章详情")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "父目录Id", name = "catalogId", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult getArticleDetail(@RequestParam(value = "catalogListId")String catalogListId,
                                           @RequestParam(value = "catalogId",required = false)String catalogId,
                                           @RequestParam("type")String type){
        SdArticle articleDetail=wikiService.getArticleDetail(catalogListId,catalogId,type);
        return PlatformResult.success(articleDetail);
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索相关百科信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "search", name = "search", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult searchArticle(@RequestParam("search")String search,
                                        @RequestParam("type")String type){
        List<SdCatalogList> catalogLists=wikiService.searchArticle(search,type);
        return PlatformResult.success(catalogLists);
    }


    @GetMapping("/articleReadNum")
    @ApiOperation(value = "增加文章阅读量")
    public PlatformResult addArticleReadNum(@RequestParam("catalogListId")String catalogListId,
                                            @RequestParam("type")String type,
                                            @RequestParam("catalogId")String catalogId){

        SdCatalogList catalogList=wikiService.getCatalogList(catalogListId,catalogId,type);
        int readNum=catalogList.getReadNum();
        catalogList.setReadNum(++readNum);
        wikiService.addArticleReadNum(catalogList);
        return PlatformResult.success();
    }

    @GetMapping("/articleLikeNum")
    @ApiOperation(value = "增加文章点赞数")
    public PlatformResult addArticleLikeNum(@RequestParam("catalogListId")String catalogListId,
                                            @RequestParam("type")String type,
                                            @RequestParam("catalogId")String catalogId){
        SdCatalogList catalogList=wikiService.getCatalogList(catalogListId,catalogId,type);
        int likeNum=catalogList.getLikeNum();
        catalogList.setLikeNum(++likeNum);
        wikiService.addArticleLikeNum(catalogList);
        return PlatformResult.success();
    }


    @PostMapping("/article")
    @ApiOperation(value = "修改文章")
    public PlatformResult modifyArticle(@RequestBody String content){
        JSONObject obj= JSON.parseObject(content);
        SdArticle sdArticle=new SdArticle();
        sdArticle.setId(obj.getString("id"));
        String transfer=obj.getString("content");

        sdArticle.setContent(obj.getString("content"));
        wikiService.uptArticle(sdArticle);
        return PlatformResult.success();
    }


    @RequestMapping("/uploadImg")
    public PlatformResult uploadImg(@RequestParam("img")MultipartFile file){
        System.out.println("上传图片是否为空："+file.isEmpty());
        String objName = "";
        objName += OSS_STORAGE_SLICE + "/hmts/" + UUIDUtils.getId();


        //拼文件格式

        if (file == null || file.isEmpty()) {
            return PlatformResult.failure("file is empty");
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
            return PlatformResult.success(fileObjName);
        }
    }



}
