/**
 ***************************************************************
 * Copyright(C) 2017 AUXENTA INC.
 * All rights reserved.
 * <p>
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF AUXENTA INC.
 * <p>
 * This copy of the Source Code is intended for AUXENTA INC's
 * internal use only and is intended for view by persons duly
 * authorized by the management of AUXENTA INC.
 * No part of this file may be reproduced or distributed in any form or by any
 * means without the written approval of the Management of AUXENTA INC.
 * <p>
 ***************************************************************
 */
package com.pos.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <Brief description for the purpose of the class>
 *
 * Created by prem on Oct 9, 2017.
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	CustomUserDetailService userService;

	@Autowired
	JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().antMatchers("/client/code/*").permitAll()
				.antMatchers("/resetPassword/*").permitAll().antMatchers("/country/*").permitAll()
				.antMatchers("/amlCore/**").permitAll().antMatchers(HttpMethod.GET, "/user/getUserByUsername")
				.permitAll().antMatchers(HttpMethod.GET, "/user/checkUserName").permitAll()
				.antMatchers(HttpMethod.POST, "/Product/addProductDetails").permitAll()
				.antMatchers(HttpMethod.PUT, "/Product/updateProductDetails").permitAll()
				.antMatchers(HttpMethod.PUT, "/Product/updateInventoryProductDetails").permitAll()
				.antMatchers(HttpMethod.PUT, "/Product/updateProductDetailsWoocommerce").permitAll()
				.antMatchers(HttpMethod.POST, "/category/save").permitAll()
				.antMatchers(HttpMethod.POST, "/resetPassword/request").permitAll().anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
//				// We filter the api/login requests
//				.addFilterBefore(new JWTLoginFilterV1("/v1/login", authenticationManager()),
//						UsernamePasswordAuthenticationFilter.class)
//				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
//						UsernamePasswordAuthenticationFilter.class)

				// And filter other requests to check the presence of JWT in
				// header
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// Create a default account
//		// auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
//		auth.userDetailsService(userService);
//	}

	@Override
	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/country/getCountriesByClientId").antMatchers("/user/checkActiveUser")
//				.antMatchers("/v1/user/getByUserNamePublic").antMatchers("/user/updateUserAccountStatus")
//				.antMatchers("/email/receiveAll")
//				.antMatchers("/clientDetails").antMatchers("/reports/getCustomerDetailPDF")
//				.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
//						"/swagger-ui.html", "/webjars/**", "/swagger.json")
//				.antMatchers("/v1/acraCompanyInformation/viewFile");

		// web.ignoring().regexMatchers("/resetPassword/.*");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
