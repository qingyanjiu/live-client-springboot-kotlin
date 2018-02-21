package priv.louis.live.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
open class WebAppConfig : WebMvcConfigurerAdapter() {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
    }
}