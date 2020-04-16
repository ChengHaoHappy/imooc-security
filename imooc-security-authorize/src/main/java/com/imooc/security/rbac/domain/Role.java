/**
 * 
 */
package com.imooc.security.rbac.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

/**
 * 角色信息
 * 
 * @author zhailiang
 *
 */
@Data
@Entity
public class Role {
	
	/**
	 * 数据库表主键
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 审计日志，记录条目创建时间，自动赋值，不需要程序员手工赋值
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdTime;
	/**
	 * 角色名称
	 */
	@Column(length = 20, nullable = false)
	private String name;
	/**
	 * 角色拥有权限的资源集合
	 */
	@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
	private Set<RoleResource> resources  = new HashSet<>();
	/**
	 * 角色的用户集合
	 */
	@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
	private Set<RoleAdmin> admins = new HashSet<>();

	
}
