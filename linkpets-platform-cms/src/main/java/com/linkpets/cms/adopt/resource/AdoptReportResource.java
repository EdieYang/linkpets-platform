package com.linkpets.cms.adopt.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IReportService;
import com.linkpets.core.model.CmsAdoptReport;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.UUIDUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author SteveYang
 * @date 2019/5/19
 */

@Api(value = "领养模块-举报接口",tags = "领养模块-举报接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/reports")
public class AdoptReportResource {

    @Resource
    private IReportService reportService;


    @PostMapping
    public PlatformResult crtReport(@RequestParam("reportType") String reportType,
                                    @RequestParam("petId") String petId,
                                    @RequestParam("userId") String userId) {
        CmsAdoptReport report = new CmsAdoptReport();
        report.setReportId(UUIDUtils.getId());
        report.setPetId(petId);
        report.setReportType(reportType);
        report.setReportUserId(userId);
        report.setCreateDate(new Date());
        reportService.crtReport(report);
        return PlatformResult.success();
    }

}
