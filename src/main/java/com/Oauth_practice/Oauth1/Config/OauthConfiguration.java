package com.Oauth_practice.Oauth1.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OauthConfiguration {
		
		@Bean
		SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {
			
				return http.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(req->req.requestMatchers("/public","/logout").permitAll()
						.requestMatchers("/admin").authenticated()
						)
				.oauth2Login(oauth->oauth.loginPage("/oauth/authorization/github")
						.userInfoEndpoint(userinfo->userinfo.userService(oAuthService())))
				.logout(lg->lg.logoutUrl("/logout").logoutSuccessUrl("/public")
						.invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID").permitAll()).build();
				
		}
		
		@Bean
		 OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuthService(){
			return new DefaultOAuth2UserService();
		}
}
