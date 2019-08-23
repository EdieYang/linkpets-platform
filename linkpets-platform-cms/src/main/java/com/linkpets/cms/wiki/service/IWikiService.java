package com.linkpets.cms.wiki.service;

import com.linkpets.core.model.SdArticle;
import com.linkpets.core.model.SdCatalog;
import com.linkpets.core.model.SdCatalogList;

import java.util.List;

public interface IWikiService {

    List<SdCatalog> getCatalogs(String type);

    SdArticle getArticleDetail(String catalogListId,String catalogId,String type);

    List<SdCatalogList> searchArticle(String search,String type);

    void addArticleReadNum(SdCatalogList catalogList);

    void addArticleLikeNum(SdCatalogList catalogList);

    SdCatalogList getCatalogList(String catalogListId,String catalogId,String type);

    void uptArticle(SdArticle sdArticle);
}
