package com.cloudminds.cms.entity.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @Author: zhouchong
 * Created by 76409 on 16:07 2018/11/29.
 * @Description:
 */
@Document
@Data
public class User implements UserDetails {
	private String _id;

	private String userName;

	private Integer groupId;

	private String email;

	private Date registerTime;

	private Date lastLoginTime;

	private Long loginCount = 0L;

	private Integer status = 1;

	private boolean isAdmin = false;

	@JsonIgnore
	private String passWord;
	//roles
	private Collection<? extends GrantedAuthority> authorities;
	//菜单
	private Collection menus;
	//权限
	private Collection<String> permissions;

	private boolean accountNonExpired;

	private boolean accountNonLocked;

	private boolean credentialsNonExpired;

	private boolean enabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.passWord;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
}
