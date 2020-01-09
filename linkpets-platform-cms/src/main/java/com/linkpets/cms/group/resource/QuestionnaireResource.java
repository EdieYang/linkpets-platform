package com.linkpets.cms.group.resource;

import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.group.reqEntity.QuestionnaireReq;
import com.linkpets.cms.group.service.IQuestionnaireAnswerService;
import com.linkpets.cms.group.service.IQuestionnaireService;
import com.linkpets.core.model.CmsQuestionnaire;
import com.linkpets.core.model.CmsQuestionnaireAnswer;
import com.linkpets.core.respEntity.RespQuestionnaireAnswerInfo;
import com.linkpets.core.respEntity.RespQuestionnaireInfo;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author edie
 */
@Api(value = "圈子模块-问卷及答案接口", tags = "圈子模块-问卷及答案接口")
@ResponseResult
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireResource {

    @Resource
    private IQuestionnaireService questionnaireService;
    @Resource
    private IQuestionnaireAnswerService questionnaireAnswerService;

    @ApiOperation("分页查询问卷列表")
    @GetMapping("page")
    public PlatformResult getQuestionnairePage(@ApiParam(name = "questionnaireTitle", value = "根据问卷标题模糊查询")
                                               @RequestParam(value = "questionnaireTitle", required = false) String questionnaireTitle,
                                               @RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize) {
        PageInfo<CmsQuestionnaire> pageInfo = questionnaireService.getQuestionnairePage(questionnaireTitle, pageNum, pageSize);
        return PlatformResult.success(pageInfo);
    }

    @ApiOperation("查询问卷列表")
    @GetMapping("list")
    public PlatformResult getQuestionnaireList(@ApiParam(name = "questionnaireTitle", value = "根据问卷标题模糊查询")
                                               @RequestParam(value = "questionnaireTitle", required = false) String questionnaireTitle) {
        List<CmsQuestionnaire> questionnaireList = questionnaireService.getQuestionnaireList(questionnaireTitle);
        return PlatformResult.success(questionnaireList);
    }

    @ApiOperation("查询问卷详情")
    @GetMapping("")
    public PlatformResult getQuestionnaireInfo(@RequestParam(value = "questionnaireId") String questionnaireId) {
        RespQuestionnaireInfo questionnaireInfo = questionnaireService.getQuestionnaireInfo(questionnaireId);
        return PlatformResult.success(questionnaireInfo);
    }

    @ApiOperation("创建问卷")
    @PostMapping("")
    public PlatformResult crtQuestionnaire(@RequestBody QuestionnaireReq questionnaireReq) {
        String questionnaireId = questionnaireService.crtQuestionnaire(questionnaireReq.getQuestionnaire(), questionnaireReq.getQuestionnaireItemList());
        return PlatformResult.success(questionnaireId);
    }

    @ApiOperation("更新问卷")
    @PutMapping("")
    public PlatformResult putQuestionnaire(@RequestBody QuestionnaireReq questionnaireReq) {
        questionnaireService.uptQuestionnaire(questionnaireReq.getQuestionnaire(), questionnaireReq.getQuestionnaireItemList());
        return PlatformResult.success();
    }

    @ApiOperation("删除问卷")
    @DeleteMapping("")
    public PlatformResult delQuestionnaire(@RequestParam(value = "questionnaireId") String questionnaireId) {
        questionnaireService.delQuestionnaire(questionnaireId);
        return PlatformResult.success();
    }

    @ApiOperation("分页查询答案列表")
    @GetMapping("answerPage")
    public PlatformResult getQuestionnaireAnswerPage(@RequestParam(value = "questionnaireId") String questionnaireId,
                                                     @RequestParam(value = "activityId") String activityId,
                                                     @RequestParam("pageNum") Integer pageNum,
                                                     @RequestParam("pageSize") Integer pageSize) {
        PageInfo<RespQuestionnaireAnswerInfo> answerPage = questionnaireAnswerService.getQuestionnaireAnswerPage(questionnaireId, activityId, pageNum, pageSize);
        return PlatformResult.success(answerPage);
    }

    @ApiOperation("查询答案列表")
    @GetMapping("answerList")
    public PlatformResult getQuestionnaireAnswerList(@RequestParam(value = "questionnaireId", required = false) String questionnaireId,
                                                     @RequestParam(value = "activityId", required = false) String activityId,
                                                     @RequestParam(value = "userId", required = false) String userId) {
        List<RespQuestionnaireAnswerInfo> answerList = questionnaireAnswerService.getQuestionnaireAnswerList(questionnaireId, activityId, userId);
        return PlatformResult.success(answerList);
    }


    @ApiOperation("查询答案详情")
    @GetMapping("answer")
    public PlatformResult getQuestionnaireAnswer(@RequestParam(value = "answerId") String answerId) {
        RespQuestionnaireAnswerInfo answerInfo = questionnaireAnswerService.getQuestionnaireAnswerInfo(answerId);
        return PlatformResult.success(answerInfo);
    }


    @ApiOperation("上传问卷答案")
    @PostMapping("answer")
    public PlatformResult crtQuestionnaireAnswer(@RequestBody CmsQuestionnaireAnswer questionnaireAnswer) {
        String answerId = questionnaireAnswerService.crtAnswer(questionnaireAnswer);
        return PlatformResult.success(answerId);
    }
}
