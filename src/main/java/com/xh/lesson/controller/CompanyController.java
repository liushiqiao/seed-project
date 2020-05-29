package com.xh.lesson.controller;

import com.xh.lesson.entity.Company;
import com.xh.lesson.service.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xh.lesson.aop.annotation.LogAnnotation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.xh.lesson.utils.DataResult;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/05/26.
*/
@RestController
@RequestMapping("/company")
@Api(tags = "")
public class CompanyController {
    @Resource
    private CompanyService companyService;

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("company:add")
    @PostMapping("/add")
    public DataResult add(Company company) {
        companyService.save(company);
        return DataResult.success();
    }

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("company:delete")
    @PostMapping("/delete")
    public DataResult delete(@RequestParam Integer id) {
        companyService.deleteById(id);
        return DataResult.success();
    }
    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("company:update")
    @PostMapping("/update")
    public DataResult update(Company company) {
        companyService.update(company);
        return DataResult.success();
    }

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("company:detail")
    @PostMapping("/detail")
    public DataResult detail(@RequestParam Integer id) {
        Company company = companyService.findById(id);
        return DataResult.success(company);
    }

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("company:list")
    @PostMapping("/list")
    public DataResult list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Company> list = companyService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return DataResult.success(pageInfo);
    }
}
