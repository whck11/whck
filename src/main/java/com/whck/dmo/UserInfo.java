package com.whck.dmo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author 马健原 2016-1-24
 *
 */
@Entity
@Table(name = "userinfo")
public class UserInfo implements Serializable {

	public UserInfo() {
		super();
	}

	public UserInfo(Integer id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

	private static final long serialVersionUID = 8883827772773239109L;
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username")
	private String userName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
