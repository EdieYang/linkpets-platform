package com.linkpets.cms.wiki.service.impl;

import com.linkpets.cms.wiki.service.IWikiService;
import com.linkpets.core.dao.SdArticleMapper;
import com.linkpets.core.dao.SdCatalogListMapper;
import com.linkpets.core.dao.SdCatalogMapper;
import com.linkpets.core.model.SdArticle;
import com.linkpets.core.model.SdCatalog;
import com.linkpets.core.model.SdCatalogList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WikiServiceImpl implements IWikiService {

    @Resource
    private SdCatalogMapper sdCatalogMapper;

    @Resource
    private SdCatalogListMapper sdCatalogListMapper;

    @Resource
    private SdArticleMapper sdArticleMapper;

    @Override
    public List<SdCatalog> getCatalogs(String type) {
        return sdCatalogMapper.getCatalogs(type);
    }

    @Override
    public SdArticle getArticleDetail(String catalogListId,String catalogId,String type) {
        return sdArticleMapper.selectByCatalogId(catalogListId,catalogId,type);
    }

    @Override
    public List<SdCatalogList> searchArticle(String search,String type) {
        return sdCatalogListMapper.searchArticle(search,type);
    }

    @Override
    public void addArticleReadNum(SdCatalogList catalogList) {
        sdCatalogListMapper.updateByPrimaryKeySelective(catalogList);
    }

    @Override
    public void addArticleLikeNum(SdCatalogList catalogList) {
        sdCatalogListMapper.updateByPrimaryKeySelective(catalogList);
    }

    @Override
    public SdCatalogList getCatalogList(String catalogListId,String catalogId,String type) {
        return sdCatalogListMapper.selectByCatalogListIdAndType(catalogListId,catalogId,type);
    }

    @Override
    public void uptArticle(SdArticle sdArticle) {
        sdArticleMapper.updateByPrimaryKeySelective(sdArticle);
    }
}
