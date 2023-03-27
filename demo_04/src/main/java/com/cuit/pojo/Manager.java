package com.cuit.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 一个小区一张表，此处仅先用一个小区举例子. 后续将添加新增表操作
 */
@JsonIgnoreProperties(value = "pwd",allowSetters = true)
public class Manager {
    @Size(min = 11,max = 11,message = "电话号码必须十一位数，请核对！")
    private String manNumber;

    private String manName;

    @NotEmpty(message = "密码不能为空")
    private String pwd;

    public Manager() {
    }

    public Manager(String manNumber, String manName, String pwd) {
        this.manNumber = manNumber;
        this.manName = manName;
        this.pwd = pwd;
    }

    public String getManNumber() {
        return manNumber;
    }

    public void setManNumber(String manNumber) {
        this.manNumber = manNumber;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "manNumber='" + manNumber + '\'' +
                ", manName='" + manName + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
