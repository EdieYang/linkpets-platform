package com.linkpets.cms.wiki.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.wiki.service.IWikiService;
import com.linkpets.core.model.SdArticle;
import com.linkpets.core.model.SdCatalog;
import com.linkpets.core.model.SdCatalogList;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "宠物百科接口", tags = "宠物百科接口")
@ResponseResult
@RestController
@RequestMapping("/wiki")
public class WikiResource {

    @Resource
    IWikiService wikiService;

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
    public PlatformResult getArticleDetail(@RequestParam("catalogId")String catalogId){
        SdArticle articleDetail=wikiService.getArticleDetail(catalogId);
        return PlatformResult.success(articleDetail);
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索相关百科信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "search", name = "search", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult searchArticle(@RequestParam("search")String search){
        List<SdCatalogList> catalogLists=wikiService.searchArticle(search);
        return PlatformResult.success(catalogLists);
    }


}
