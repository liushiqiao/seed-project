package com.xh.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName: UserAddReqVO
 * TODO:类文件简单描述
 * @Author: lsq
 * @CreateDate: 2019/9/22 16:46
 * @UpdateUser: lsq
 * @UpdateDate: 2019/9/22 16:46
 * @Version: 0.0.1
 */
@Data
public class UserAddReqVO {
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "账号不能为空")
    private String username;
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    @ApiModelProperty(value = "手机号码")
    private String phone;
    @ApiModelProperty(value = "创建来源(1.web 2.android 3.ios )")
    private Integer createWhere;
    @ApiModelProperty(value = "所属机构")
    @NotBlank(message = "所属机构不能为空")
    private String deptId;
    @ApiModelProperty(value = "性别(1.男 2.女)")
    private String sex;
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "所拥有的角色")
    private List<String> roleIds;
}
