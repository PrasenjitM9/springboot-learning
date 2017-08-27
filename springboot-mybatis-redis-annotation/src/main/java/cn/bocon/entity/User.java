package cn.bocon.entity;

import java.io.Serializable;

/**
 * 用户实体类
 * 
 * Created by bysocket on 21/07/2017.
 */
public class User implements Serializable {

	private static final long serialVersionUID = 5866643072116119744L;

	/**
	 * 编号
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 出生时间
	 */
	private String birthday;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age
				+ ", birthday=" + birthday + '}';
	}
}