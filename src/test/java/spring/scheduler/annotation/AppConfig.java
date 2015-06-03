package spring.scheduler.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 配置类
 * 	- 由AnnotationConfigApplicationContext读取配置参数
 */

@Component
@ComponentScan("spring.scheduler.annotation")
@EnableScheduling
@EnableAsync
public class AppConfig {

}
