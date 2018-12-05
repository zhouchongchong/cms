package com.cloudminds.cms.entity.mongo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author: zhouchong
 * Created by 76409 on 16:07 2018/11/29.
 * @Description:
 */
@Document
@Data
public class User {
	private String _id;

	private String userName;

	private Integer groupId;

	private String email;

	private Date registerTime;

	private Date lastLoginTime;

	private Long loginCount = 0L;

	private Integer status = 1;

	private boolean isAdmin = false;

	private String passWord ;
}
