/**
 * 
 */
package com.imooc.security.rbac.web.controller.support;

import lombok.Data;

/**
 * @author zhailiang
 *
 */
@Data
public class SimpleResponse {
	
	public SimpleResponse(Object content){
		this.content = content;
	}
	
	private Object content;

}
