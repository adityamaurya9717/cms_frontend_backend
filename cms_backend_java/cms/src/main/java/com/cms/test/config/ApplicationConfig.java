package com.cms.test.config;

import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ApplicationConfig {


}
