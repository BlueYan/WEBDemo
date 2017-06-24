package com.mark.project.login_shopcart_checkcode.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2017/6/24.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Integer id;

	private String username;

	private String password;
}
