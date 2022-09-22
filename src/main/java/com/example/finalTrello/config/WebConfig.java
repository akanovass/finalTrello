package com.example.finalTrello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF8");
        messageSource.setBasename("classpath:messages");
        return messageSource;
    }

    @Bean
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        resolver.setCookieMaxAge(3600*24*30);
        resolver.setCookieName("my_current_lang");
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeInterceptor(){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(localeInterceptor());
    }

}
