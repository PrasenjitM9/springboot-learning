package cn.bocon.entity;

import java.io.Serializable;

/**
 * 用户实体类
 * 
 * Created by bysocket on 21/07/2017.
 */
public class User2 implements Serializable {

	private static final long serialVersionUID = 5866643072116119744L;

	/**
	 * 编号
	 */
	private Long id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "User2 [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}