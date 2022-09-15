package org.feng.config;

import lombok.AllArgsConstructor;
import org.feng.cilent.AsyncMailClient;
import org.feng.cilent.MailClient;
import org.feng.enums.Rejected;
import org.feng.properties.MailPoolProperties;
import org.feng.properties.MailProperties;
import org.feng.properties.MailTemplateProperties;
import org.feng.thymeleaf.ResourceTemplateResolver;
import org.feng.thymeleaf.ThymeleafBuild;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * emailClient自动化配置
 *
 * @author feng
 */
@EnableConfigurationProperties
@Configuration
@AllArgsConstructor
public class MailAutoConfiguration {
    /**
     * mail 配置参数
     */
    private final MailProperties mailProperties;
    /**
     * mail 线程池配置
     */
    private final MailPoolProperties mailPoolProperties;
    /**
     * spring ApplicationContext
     */
    private final ApplicationContext applicationContext;

    /**
     * 模板解析器自动配置
     */
    private final MailTemplateProperties mailTemplateProperties;


    /**
     * 构建 mail client
     *
     * @return MailClient
     */
    @Bean
    public MailClient emailClient() {
        return new MailClient(mailProperties);
    }

    /**
     * 构建异步mial client
     *
     * @return
     */
    @Bean
    public AsyncMailClient asyncMailClient() {
        return new AsyncMailClient();
    }

    /**
     * mail异步线程池自动配置
     *
     * @return Executor 线程池
     */
    @Bean({"mailAsyncExecutor"})
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(mailPoolProperties.getCorePool());
        executor.setMaxPoolSize(mailPoolProperties.getMaxPool());
        executor.setQueueCapacity(mailPoolProperties.getQueueCapacity());
        executor.setKeepAliveSeconds(mailPoolProperties.getKeepAlive());
        executor.setThreadNamePrefix(mailPoolProperties.getThreadNamePrefix());
        if (mailPoolProperties.getRejectedExecutionHandler() == Rejected.CallerRunsPolicy) {
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        } else if (mailPoolProperties.getRejectedExecutionHandler() == Rejected.AbortPolicy) {
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        } else if (mailPoolProperties.getRejectedExecutionHandler() == Rejected.DiscardOldestPolicy) {
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        } else {
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        }
        executor.initialize();
        return executor;
    }

    /**
     * 模板解析器 自动配置
     *
     * @return ResourceTemplateResolver 模板解析器
     */
    @Bean
    public ResourceTemplateResolver resourceTemplateResource() {
        ResourceTemplateResolver resourceTemplateResolver = new ResourceTemplateResolver();
        resourceTemplateResolver.setApplicationContext(this.applicationContext);
        resourceTemplateResolver.setPrefix(mailTemplateProperties.getPrefix());
        resourceTemplateResolver.setSuffix(mailTemplateProperties.getSuffix());
        resourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        resourceTemplateResolver.setCheckExistence(true);
        resourceTemplateResolver.setCharacterEncoding("UTF-8");
        return resourceTemplateResolver;
    }

    @Bean
    public ThymeleafBuild thymeleafBuild() {
        return new ThymeleafBuild();
    }

}
