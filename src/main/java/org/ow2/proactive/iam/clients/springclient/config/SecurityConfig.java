package org.ow2.proactive.iam.clients.springclient.config;

//import org.apache.catalina.servlet4preview.GenericFilter;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationProvider authenticationProvider;
    private AuthenticationEntryPoint authenticationEntryPoint;
    private SingleSignOutFilter singleSignOutFilter;
    private LogoutFilter logoutFilter;

    private static AuthenticationFilter authenticationFilter = new AuthenticationFilter();
    private static Cas30ProxyReceivingTicketValidationFilter casTicketValidationFilter = new Cas30ProxyReceivingTicketValidationFilter();
    private static HttpServletRequestWrapperFilter casWrapperFilter = new HttpServletRequestWrapperFilter();

    //private AuthenticationFilter authenticationFilter;

  /*  @Autowired
    public SecurityConfig(//AuthenticationEntryPoint eP, //CasAuthenticationProvider casAuthenticationProvider,
              //            LogoutFilter lF
           // , SingleSignOutFilter ssF//, AuthenticationFilter af
    ) {
        //this.authenticationProvider = casAuthenticationProvider;
       // this.authenticationEntryPoint = eP;
      //  this.logoutFilter = lF;
       // this.singleSignOutFilter = ssF;

       // this.authenticationFilter = af;
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        authenticationFilter.setCasServerLoginUrl("https://localhost:8443/cas/login");
        authenticationFilter.setServerName("https://localhost:9000/springclient");

        //authenticationFilter.setService("http://localhost:9000/login/cas");

        casTicketValidationFilter.setTicketValidator(new Cas30ServiceTicketValidator("https://localhost:8443/cas"));
        casTicketValidationFilter.setServerName("https://localhost:9000/springclient");
        casTicketValidationFilter.setUseSession(true);
        //casTicketValidationFilter.setService("http://localhost:9000/login/cas");



        http    //.addFilterAfter(casWrapperFilter,BasicAuthenticationFilter.class)
                //.authorizeRequests()
                //.regexMatchers("/secured.*", "/login")
                //.permitAll().and()
                //.authenticated()
                //.and()
                //.addFilterBefore(authenticationFilter, BasicAuthenticationFilter.class)
                .authorizeRequests()
                .regexMatchers("/")
                .permitAll()
                //.authenticated()
                //.and()
                //.httpBasic()
                //.and().addFilterBefore()
                //.authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .logout().logoutSuccessUrl("/logout");
            //    .and()
              //  .addFilterBefore(logoutFilter, LogoutFilter.class)
               // .addFilterBefore(singleSignOutFilter, CasAuthenticationFilter.class)
               // .addFilterBefore(authenticationFilter, BasicAuthenticationFilter.class)
               // .addFilterBefore(casTicketValidationFilter, BasicAuthenticationFilter.class);
                //.addFilterBefore(casWrapperFilter,BasicAuthenticationFilter.class)



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // bring he all the code
/*
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService("http://localhost:9000");
        serviceProperties.setSendRenew(false);

        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setServiceProperties(serviceProperties);
        provider.setTicketValidator(new Cas30ServiceTicketValidator(
                "https://localhost:8443/cas"));

        provider.setUserDetailsService(
                s -> new User("toto", "toto", true, true, true, true,
                        AuthorityUtils.createAuthorityList("lala")));

        provider.setKey("CAS_PROVIDER_LOCALHOST_9000");

        auth.authenticationProvider(provider);*/
    }

   /* @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter(ServiceProperties sP) throws Exception {
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setServiceProperties(sP);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }*/
}