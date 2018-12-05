package com.cloudminds.cms.vo;

import com.cloudminds.cms.constant.ConstantBean;
import lombok.*;

/**
 * @Author: zhouchong
 * Created by 76409 on 16:36 2018/11/29.
 * @Description:
 */
@Data
public class BaseResp {
	private String status = ConstantBean.RESPONSE_200;
	private String sys;
	private String message = "OK";
	private Object data;
}
