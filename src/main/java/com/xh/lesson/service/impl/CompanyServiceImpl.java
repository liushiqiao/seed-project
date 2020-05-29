package com.xh.lesson.service.impl;

import com.xh.lesson.mapper.CompanyMapper;
import com.xh.lesson.entity.Company;
import com.xh.lesson.service.CompanyService;
import com.xh.lesson.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/05/26.
 */
@Service
@Transactional
public class CompanyServiceImpl extends AbstractService<Company> implements CompanyService {
    @Resource
    private CompanyMapper tCompanyMapper;

}
