package bean;

import java.sql.Timestamp;

import utils.Def;
import utils.StringUtils;

public class Player extends Pojo{
	private String name;
	private String ip;
	private String email;
	private String phone;
	private String unionid;
	private String password;
	private Timestamp createTime;
	private String model;
	private String mark;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getPassword() {
		return password;
	}
	public String realPassword() {
		return StringUtils.decrypt(this.password, Def.LOGIN_PWD_KEY);
	}
	public void putPassword(String password) {
		this.password = StringUtils.encrypt(password, Def.LOGIN_PWD_KEY);
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
}
