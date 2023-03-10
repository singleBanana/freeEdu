package com.freeedu.userserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ResourceBundle;

@Configuration
public class MessageSourceConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {

        ResourceBundleMessageSource source = new ResourceBundleMessageSource(){
            @Override
            protected String getStringOrNull(ResourceBundle bundle, String key) {
                String st = super.getStringOrNull(bundle, key);
                if (st == null) {
                    return null;
                }
                return st.replaceAll("'", "''");
            }
        };

        source.setBasenames("i18n.error","i18n.validation");
        source.setDefaultEncoding("UTF-8");
        return source;

    }
}
