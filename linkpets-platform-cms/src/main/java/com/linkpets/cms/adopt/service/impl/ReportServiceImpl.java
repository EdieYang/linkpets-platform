package com.linkpets.cms.adopt.service.impl;

import com.linkpets.cms.adopt.service.IReportService;
import com.linkpets.core.dao.CmsAdoptReportMapper;
import com.linkpets.core.model.CmsAdoptReport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author SteveYang
 * @date 2019/5/19
 */
@Service
public class ReportServiceImpl implements IReportService {

    @Resource
    private CmsAdoptReportMapper reportMapper;

    @Override
    public void crtReport(CmsAdoptReport report) {
        reportMapper.insertSelective(report);
    }

}
