/**
 * 
 * I declare that this code was written by me, xandr. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: xandra
 * Student ID: 22022591
 * Date created: 2024-Nov-03 10:59:49 pm 
 * 
 */
package fyp.admin;

/**
 * @author xandr
 *
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author xandr
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public MemberDetailsService memberDetailsService() {
		return new MemberDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(memberDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers("/rewards/add", "/rewards/edit/*", "/rewards/delete/*", "/rewards/save",
						"/activities/add", "/activities/edit/*", "/activities/delete/*", "/activities/save", "/members",
						"/members/add", "/members/edit/*", "/members/delete/*", "/members/save", "/login")
				.hasRole("ADMIN")
				.requestMatchers("/", "/leaderboard","/history", "/rewards",  "/redeem", "/activities", "/more", "/avatar", "/profile",
						"/guide", "/feedback", "/forget", "/images/*")
				.permitAll() // Home page is visible without logging in
				.requestMatchers("/bootstrap/*/*").permitAll() // for static resources, visible to all
				.requestMatchers("/images/**").permitAll() // for static resources, visible to all
				.requestMatchers("/uploads/**").permitAll()// for upload images, visible to all
				.requestMatchers("/avatar/**").permitAll()// for avatar images, visible to all
				.anyRequest().authenticated())// Any other requests not specified earlier ,
				.formLogin((login) -> login.loginPage("/login").permitAll().defaultSuccessUrl("/", true))
				// Goes to homepage upon login
				.logout((logout) -> logout.logoutSuccessUrl("/"))// Goes to homepage upon logout
				.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/403"));

		return http.build();
	}
}
