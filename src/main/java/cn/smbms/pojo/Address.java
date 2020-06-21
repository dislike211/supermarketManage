package cn.smbms.pojo;

import java.util.Date;

/**
 * @Author: 四两数字先生（公众号/CSDN）
 */
public class Address {
    private Integer id;//bigint(20) NOT NULL主键ID
    private String contact;//varchar(15) NULL联系人姓名
    private String addressDesc;//varchar(50) NULL收货地址明细
    private String postCode; //varchar(15) NULL邮编
    private String tel; //varchar(20) NULL联系人电话
    private Integer createdBy; //bigint(20) NULL创建者
    private Date creationDate;//datetime NULL创建时间
    private Integer modifyBy; //bigint(20) NULL修改者
    private Date modifyDate;//datetime NULL修改时间
    private Integer userId;//bigint(20) NULL用户ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
