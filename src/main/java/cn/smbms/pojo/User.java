package cn.smbms.pojo;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

	/*实体类*/
	public class User {
		private Integer id;//主键id  int--->  Integer
		private String userCode;//用户编码 varchar/char--->  String
		private  String userName;//用户名称
		private String userPassword;//用户密码
		private Integer gender;//用户性别

		//springmvc 框架无法自动绑定时间  <fmt:formatDate value='${}' type='date' pattern='yyyy-MM-dd'/>
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date birthday;//出生日期  datetime date --->Date
		private String phone;//电话
		private String address;//地址
		private Integer userRole;//用户角色
		private Integer createdBy;//创建人

		private Date creationDate;//创建时间

		private Integer modifyBy;//修改人


		private Date modifyDate;//修改时间

		private Integer age;//年龄

		private String idPicPath;//上传的证件照路径

	public String getIdPicPath() {
		return idPicPath;
	}

	public void setIdPicPath(String idPicPath) {
		this.idPicPath = idPicPath;
	}

	public Integer getAge() {
		Integer age1=null;
		//进行计算:通过出生日期
		Date birthday = this.getBirthday();
		if(birthday!=null) {
			Date now = new Date();
			long l = now.getTime() - birthday.getTime();
			//毫秒 秒 分 时 天 年
			age1 = (int) (l / 1000 / 60 / 60 / 24 / 365);
		}
		return age1;
	}

	//年龄不是通过设置的来的
/*	public void setAge(Integer age) {

		this.age = age;
	}*/


	//一对多的关系：   一个User嵌套好几个Address：User--》List<Address>
		private List<Address> addressList;

		public List<Address> getAddressList() {
			return addressList;
		}

		public void setAddressList(List<Address> addressList) {
			this.addressList = addressList;
		}

		//一对一的关系：  一个User里面嵌套一个role对象
		private Role role;

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		//添加新属性
		private  String userRoleName;

		public String getUserRoleName() {
			return userRoleName;
		}

		public void setUserRoleName(String userRoleName) {
			this.userRoleName = userRoleName;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getUserCode() {
			return userCode;
		}

		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserPassword() {
			return userPassword;
		}

		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}

		public Integer getGender() {
			return gender;
		}

		public void setGender(Integer gender) {
			this.gender = gender;
		}

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Integer getUserRole() {
			return userRole;
		}

		public void setUserRole(Integer userRole) {
			this.userRole = userRole;
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

	}



