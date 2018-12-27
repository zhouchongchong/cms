package com.cloudminds.cms.entity.mysql;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable, UserDetails {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.userName
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.email
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.registerTime
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    private Date registertime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.lastLoginTime
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    private Date lastlogintime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.loginCount
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    private Long logincount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.status
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.token
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.phone
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    private String phone;

    List<Role> roles;

    List<Permission> permissions;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.userName
     *
     * @return the value of user.userName
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    @Override
    public String getUsername() {
        return username;
    }

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.userName
     *
     * @param username the value for user.userName
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.email
     *
     * @return the value of user.email
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.email
     *
     * @param email the value for user.email
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.registerTime
     *
     * @return the value of user.registerTime
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public Date getRegistertime() {
        return registertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.registerTime
     *
     * @param registertime the value for user.registerTime
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.lastLoginTime
     *
     * @return the value of user.lastLoginTime
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public Date getLastlogintime() {
        return lastlogintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.lastLoginTime
     *
     * @param lastlogintime the value for user.lastLoginTime
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.loginCount
     *
     * @return the value of user.loginCount
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public Long getLogincount() {
        return logincount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.loginCount
     *
     * @param logincount the value for user.loginCount
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public void setLogincount(Long logincount) {
        this.logincount = logincount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.status
     *
     * @return the value of user.status
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.status
     *
     * @param status the value for user.status
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.token
     *
     * @return the value of user.token
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.token
     *
     * @param token the value for user.token
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.phone
     *
     * @return the value of user.phone
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.phone
     *
     * @param phone the value for user.phone
     *
     * @mbg.generated Wed Dec 26 17:22:33 CST 2018
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}