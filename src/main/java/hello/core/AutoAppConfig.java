package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 기존 AppConfig 등록을 막기 위함
@ComponentScan(

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =Configuration.class)
)
public class AutoAppConfig {

}
