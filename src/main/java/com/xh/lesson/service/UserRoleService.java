package com.xh.lesson.service;

import com.xh.lesson.vo.req.UserRoleOperationReqVO;

import java.util.List;

/**
 * @ClassName: UserRoleService
 * TODO:类文件简单描述
 * @Author: lsq
 * @CreateDate: 2019/9/19 11:39
 * @UpdateUser: lsq
 * @UpdateDate: 2019/9/19 11:39
 * @Version: 0.0.1
 */
public interface UserRoleService {

    int removeByRoleId(String roleId);

    List<String> getRoleIdsByUserId(String userId);


    void addUserRoleInfo(UserRoleOperationReqVO vo);

    int removeByUserId(String userId);


    List<String> getUserIdsByRoleIds(List<String> roleIds);

}
