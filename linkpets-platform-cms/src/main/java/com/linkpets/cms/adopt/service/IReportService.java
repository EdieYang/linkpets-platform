package com.linkpets.cms.adopt.service;

import com.linkpets.core.model.CmsAdoptReport;

/**
 * @author Edie
 * @date 2019/5/19
 */
public interface IReportService {

    /**
     * 新增举报信息
     *
     * @param report
     */
    void crtReport(CmsAdoptReport report);
}
