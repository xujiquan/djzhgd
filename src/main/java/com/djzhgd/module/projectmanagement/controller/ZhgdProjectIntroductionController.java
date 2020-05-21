package com.djzhgd.module.projectmanagement.controller;


import com.djzhgd.common.utils.bean.BeanUtils;
import com.djzhgd.module.projectmanagement.domain.ZhgdProjectIntroduction;
import com.djzhgd.module.projectmanagement.service.ZhgdProjectIntroductionService;
import com.djzhgd.module.projectmanagement.vo.ZhgdProjectIntroductionVo;
import com.djzhgd.module.result.PageResult;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("demo/zhgdProjectIntroduction")
public class ZhgdProjectIntroductionController {

    @Autowired
    private ZhgdProjectIntroductionService zhgdProjectIntroductionService;

    /**
     * 查询zhgdProjectIntroduction模块的列表(分页)
     * @param pageResult
     * @return
     */
    @RequestMapping("/list")
    public PageResult<ZhgdProjectIntroductionVo> getZhgdProjectIntroductionList(@RequestBody PageResult<ZhgdProjectIntroductionVo> pageResult){
        pageResult = zhgdProjectIntroductionService.getZhgdProjectIntroductionList(pageResult);
        return  pageResult;
    }

    /**
     * 新增一条addZhgdProjectIntroduction信息
     * @param zhgdProjectIntroductionVo
     * @return
     */
    @RequestMapping("/add")
    public Result addZhgdProjectIntroduction(@RequestBody ZhgdProjectIntroductionVo zhgdProjectIntroductionVo) {
        Result result = zhgdProjectIntroductionService.addZhgdProjectIntroduction(zhgdProjectIntroductionVo);
        return result;
    }

    /**
     * 修改demo数据
     * @param zhgdProjectIntroduction
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public Result update(ZhgdProjectIntroduction zhgdProjectIntroduction) {

        Result result = new Result();

        int i = zhgdProjectIntroductionService.updateData(zhgdProjectIntroduction);

        result.setData(i);
        result.setCode(ResultEnum.RESULT_UPDATE_OK.getCode());
        result.setMsg(ResultEnum.RESULT_UPDATE_OK.getMsg());

        return result;
    }

    /**
     * 删除一条信息
     * @param id
     * @return
     */

    @PostMapping("delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id) {

        Result result = new Result();
        int i = zhgdProjectIntroductionService.delete(id);
        result.setData(i);
        result.setCode(ResultEnum.RESULT_DELETE_OK.getCode());
        result.setMsg(ResultEnum.RESULT_DELETE_OK.getMsg());

        return result;
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    @ResponseBody
    public Result detail(@PathVariable("id") Integer id) {

        Result result = new Result();
        ZhgdProjectIntroduction zhgdProjectIntroduction = zhgdProjectIntroductionService.detail(id);
        ZhgdProjectIntroductionVo zhgdProjectIntroductionVo = new ZhgdProjectIntroductionVo();
        BeanUtils.copyProperties(zhgdProjectIntroduction, zhgdProjectIntroductionVo);
        result.setData(zhgdProjectIntroductionVo);
        result.setCode(ResultEnum.RESULT_OK.getCode());
        result.setMsg(ResultEnum.RESULT_OK.getMsg());

        return result;
    }

}
