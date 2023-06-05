package com.yintech.cloud.entity;

import com.yintech.cloud.annotation.DbFieldEncrypt;
import com.yintech.commons.annotation.FieldDesensitize;
import com.yintech.commons.constant.DesensitizeEnum;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_employee")
public class Employee implements Serializable {
    /**
     * 数据库自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户工号
     */
    @Column(name = "work_code")
    @FieldDesensitize(type = DesensitizeEnum.CARD)
    @DbFieldEncrypt
    private String workCode;

    /**
     * 用户手机号
     */
    @FieldDesensitize(type = DesensitizeEnum.MOBILE)
    @DbFieldEncrypt
    private String phone;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取数据库自增id
     *
     * @return id - 数据库自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置数据库自增id
     *
     * @param id 数据库自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户姓名
     *
     * @return user_name - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户工号
     *
     * @return work_code - 用户工号
     */
    public String getWorkCode() {
        return workCode;
    }

    /**
     * 设置用户工号
     *
     * @param workCode 用户工号
     */
    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    /**
     * 获取用户手机号
     *
     * @return phone - 用户手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置用户手机号
     *
     * @param phone 用户手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}