/**
 * 
 */
package com.imooc.security.rbac.dto;

import java.util.ArrayList;
import java.util.List;

import com.imooc.security.rbac.domain.ResourceType;
import lombok.Data;

/**
 * @author zhailiang
 *
 */
@Data
public class ResourceInfo {
	
	/**
	 * 资源ID
	 *
	 * @since 1.0.0
	 */
	private Long id;
	/**
	 * 
	 */
	private Long parentId;
	/**
	 * 资源名
	 *
	 * @since 1.0.0
	 */
	private String name;
	/**
	 * 资源链接
	 *
	 * @since 1.0.0
	 */
	private String link;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 资源类型
	 */
	private ResourceType type;
	/**
	 * 子节点
	 */
	private List<ResourceInfo> children = new ArrayList<>();

	
}
