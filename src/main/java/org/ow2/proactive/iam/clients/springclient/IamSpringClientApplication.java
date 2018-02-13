package org.ow2.proactive.iam.clients.springclient;

import org.jasig.cas.client.configuration.ConfigurationKey;
import org.jasig.cas.client.configuration.ConfigurationKeys;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.*;
import org.jasig.cas.client.authentication.AuthenticationFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpSessionEvent;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class IamSpringClientApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IamSpringClientApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(IamSpringClientApplication.class, args);
	}


	/**
	 *
	 *   SSO Config
	 *
	 */
	/*@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService("http://localhost:9000/login/cas");
		serviceProperties.setSendRenew(false);
		return serviceProperties;
	}

	@Bean
	@Primary
	public AuthenticationEntryPoint authenticationEntryPoint(
			ServiceProperties sP) {

		CasAuthenticationEntryPoint entryPoint
				= new CasAuthenticationEntryPoint();
		entryPoint.setLoginUrl("https://localhost:8443/cas/login");
		entryPoint.setServiceProperties(sP);
		return entryPoint;
	}

	@Bean
	public TicketValidator ticketValidator() {
		return new Cas30ServiceTicketValidator(
				"https://localhost:8443/cas");
	}*/

	/*@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {

		CasAuthenticationProvider provider = new CasAuthenticationProvider();
		provider.setServiceProperties(serviceProperties());
		provider.setTicketValidator(ticketValidator());

		//provider.setAuthenticationUserDetailsService();
		//provider.set
		//provider.setUserDetailsService(
		//		s -> new User("tobias", "tobias", true, true, true, true,
		//				AuthorityUtils.createAuthorityList("admin")));
		provider.setUserDetailsService(
				s -> new User("toto", "toto", true, true, true, true,
						AuthorityUtils.createAuthorityList("lala")));
		provider.setKey("CAS_PROVIDER_LOCALHOST_9000");
		return provider;
	}*/


	/**
	 *
	 *   Single Sign On Config  ======MY WAY=====
	 *
	 */
	/*@Bean
	public AuthenticationFilter authenticationFilter() {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		authenticationFilter.setCasServerLoginUrl("https://localhost:8443/cas/login");
		authenticationFilter.setServerName("http://localhost:9000");
		authenticationFilter.setService("SpringClient");

		return authenticationFilter;
	}*/

	/*@Bean
	public FilterRegistrationBean casAuthentication() {
		final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();

		AuthenticationFilter casAuthenticationFilter = new AuthenticationFilter();
		casAuthenticationFilter.setCasServerLoginUrl("https://localhost:8443/cas/login");
		casAuthenticationFilter.setServerName("http://localhost:9000");

		filterRegBean.setFilter(casAuthenticationFilter);
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setEnabled(Boolean.TRUE);
		filterRegBean.setName("casAuthenticationFilter");
		//filterRegBean.setAsyncSupported(Boolean.TRUE);
		return filterRegBean;
	}

	@Bean
	public FilterRegistrationBean casTicketValidation() {
		final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();


		Cas30ProxyReceivingTicketValidationFilter casTicketValidationFilter = new Cas30ProxyReceivingTicketValidationFilter();
		casTicketValidationFilter.setTicketValidator(new Cas30ServiceTicketValidator("https://localhost:8443/cas"));
		casTicketValidationFilter.setServerName("http://localhost:9000");

		filterRegBean.setFilter(casTicketValidationFilter);
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setEnabled(Boolean.TRUE);
		filterRegBean.setName("casTicketValidationFilter");
		//filterRegBean.setAsyncSupported(Boolean.TRUE);
		return filterRegBean;
	}

	@Bean
	public FilterRegistrationBean casWrapper() {
		final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();

		HttpServletRequestWrapperFilter casWrapperFilter = new HttpServletRequestWrapperFilter();

		filterRegBean.setFilter(casWrapperFilter);
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setEnabled(Boolean.TRUE);
		filterRegBean.setName("casWrapperFilter");
		filterRegBean.setAsyncSupported(Boolean.TRUE);
		return filterRegBean;
	}*/


	@Bean
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listener = new ServletListenerRegistrationBean<>();
		listener.setEnabled(true);
		listener.setListener(new SingleSignOutHttpSessionListener());
		listener.setOrder(1);
		return listener;
	}

	/**
	 *  The filter is used for single point logout ， Single point exit configuration ， Be sure to put something else on filter before
	 */
	/*@Bean
	public FilterRegistrationBean logOutFilter() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		LogoutFilter logoutFilter = new LogoutFilter("https://localhost:8443/cas" + "/logout?service=" + "http://localhost:9000",new SecurityContextLogoutHandler());
		logoutFilter.setFilterProcessesUrl("/singleLogout");
		filterRegistration.setFilter(logoutFilter);
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.addInitParameter("casServerUrlPrefix", "https://localhost:8443/cas");
		filterRegistration.addInitParameter("serverName", "http://localhost:9000");
		filterRegistration.setOrder(2);
		return filterRegistration;
	}*/

	/**
	 *  The filter is used for single point logout ， Single point exit configuration ， Be sure to put something else on filter before
	 */
	@Bean
	public FilterRegistrationBean singleSignOutFilter() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		filterRegistration.setFilter(new SingleSignOutFilter());
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.addInitParameter("casServerUrlPrefix", "https://localhost:8443/cas");
		filterRegistration.addInitParameter("serverName", "https://localhost:9000/springclient");
		filterRegistration.setOrder(2);
		return filterRegistration;
	}

	@Bean
	public FilterRegistrationBean authenticationFilter() {
		final FilterRegistrationBean filterRegistration = new FilterRegistrationBean();

		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		authenticationFilter.setCasServerLoginUrl("https://localhost:8443/cas/login");
		authenticationFilter.setServerName("https://localhost:9000/springclient");
		//authenticationFilter.setService("https://localhost:9000/springclient");

		filterRegistration.setFilter(authenticationFilter);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.addInitParameter("casServerLoginUrl", "https://localhost:8443/cas/login");
		filterRegistration.addInitParameter("serverName", "https://localhost:9000/springclient");
		//filterRegistration.addInitParameter("service", "https://localhost:9000/springclient");
		filterRegistration.setEnabled(true);
		filterRegistration.setOrder(3);
		//filterRegBean.setName("casAuthenticationFilter");
		//filterRegBean.setAsyncSupported(Boolean.TRUE);
		return filterRegistration;
	}

	@Bean
	public FilterRegistrationBean casTicketValidation() {
		final FilterRegistrationBean filterRegistration = new FilterRegistrationBean();

		Cas30ProxyReceivingTicketValidationFilter casTicketValidationFilter = new Cas30ProxyReceivingTicketValidationFilter();
		casTicketValidationFilter.setTicketValidator(new Cas30ServiceTicketValidator("https://localhost:8443/cas"));
		casTicketValidationFilter.setServerName("https://localhost:9000/springclient");
		//casTicketValidationFilter.setService("https://localhost:9000/springclient");

		filterRegistration.setFilter(casTicketValidationFilter);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.addInitParameter("casServerUrlPrefix", "https://localhost:8443/cas");
		filterRegistration.addInitParameter("serverName", "https://localhost:9000/springclient");
		//filterRegistration.addInitParameter("service", "https://localhost:9000/springclient");
		filterRegistration.setEnabled(Boolean.TRUE);
		filterRegistration.setOrder(4);
		//filterRegistration.setName("casTicketValidationFilter");
		//filterRegBean.setAsyncSupported(Boolean.TRUE);
		return filterRegistration;
	}

	@Bean
	public FilterRegistrationBean httpServletRequestWrapperFilter() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new HttpServletRequestWrapperFilter());
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.setOrder(5);
		return filterRegistration;
	}

	/**
	 *
	 *   Single Sign Out Config
	 *
	 */
	/*@Bean
	public SecurityContextLogoutHandler securityContextLogoutHandler() {
		return new SecurityContextLogoutHandler();
	}

	@Bean
	public LogoutFilter logoutFilter() {
		LogoutFilter logoutFilter = new LogoutFilter(
				"https://localhost:8443/cas/logout?service=" + "http://localhost:9000",
				securityContextLogoutHandler());
		logoutFilter.setFilterProcessesUrl("/singleLogout");
		return logoutFilter;
	}

	@Bean
	public SingleSignOutFilter singleSignOutFilter() {
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		singleSignOutFilter.setCasServerUrlPrefix("https://localhost:8443/cas");
		singleSignOutFilter.setIgnoreInitConfiguration(true);
		return singleSignOutFilter;
	}

	@EventListener
	public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(
			HttpSessionEvent event) {
		return new SingleSignOutHttpSessionListener();
	}*/
}
