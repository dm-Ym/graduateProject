package com.cuit.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @JsonIgnoreProperties 注解作用：禁止pwd属性序列化，因为要加密存储
 */

@JsonIgnoreProperties(value = "pwd",allowSetters = true)
public class ResidentUser implements Serializable {
    /**
     * 用户id，主键，自增
     */
    private int residentUserId;
    /**
     * 用户名， 不能为空
     */
    @NotEmpty(message = "用户名不能为空")
    private String fullName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 年龄
     */
    @Min(value = 0,message = "年龄不能小于零，请重新输入")
    @Max(value = 120,message = "年龄不能大于120岁，谢谢。若您真高龄120，请联系社区工作人员-13958296420")
    private String age;
    /**
     * 电话号码
     */
    @Size(min = 11,max = 11,message = "电话号码必须十一位数，请核对！")
    private String phoneNumber;
    /**
     * 密码，不能为空
     */
    @NotEmpty(message = "密码不能为空")
    private String pwd;
    /**
     * 社区名称
     */
    private String community;
    /**
     * 小区名称
     */
    private String communityName;
    /**
     * 详细地址
     */
    private String detailedAddress;
    /**
     * 填写时间，可看不可写
     */
    private String fillInTime;
    /**
     * 健康状态
     */
    private String healthStatus;

    public ResidentUser() {

    }

    public ResidentUser(int residentUserId, String fullName, String gender, String age, String phoneNumber, String community, String communityName, String detailedAddress, String fillInTime, String healthStatus, String pwd) {
        this.residentUserId = residentUserId;
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.community = community;
        this.communityName = communityName;
        this.detailedAddress = detailedAddress;
        this.fillInTime = fillInTime;
        this.healthStatus = healthStatus;
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getResidentUserId() {
        return residentUserId;
    }

    public void setResidentUserId(int residentUserId) {
        this.residentUserId = residentUserId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getFillInTime() {
        return fillInTime;
    }

    public void setFillInTime(String fillInTime) {
        this.fillInTime = fillInTime;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    @Override
    public String toString() {
        return "ResidentUser{" +
                "residentUserId=" + residentUserId +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", community='" + community + '\'' +
                ", communityName='" + communityName + '\'' +
                ", derailedAddress='" + detailedAddress + '\'' +
                ", fillInTime='" + fillInTime + '\'' +
                ", healthStatus='" + healthStatus + '\'' +
                ", pwd=" + pwd +
                '}';
    }
}
