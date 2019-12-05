package com.haiyoung.tinycode.config;

import com.haiyoung.tinycode.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


@Slf4j
@Configuration
@EnableAsync(proxyTargetClass = true)
public class ThreadExecutorConfig implements AsyncConfigurer {

    @Value("${threadpool.corePoolSize:10}")
    private int corePoolSize;
    @Value("${threadpool.maxPoolSize:200}")
    private int maxPoolSize;
    @Value("${threadpool.queueCapacity:10}")
    private int queueCapacity;
    @Value("${threadpool.keepAliveSeconds:5}")
    private int keepAliveSeconds;
    @Value("${threadpool.awaitTerminationSeconds:30}")
    private int awaitTerminationSeconds;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("com.oyo.hotel-krypton-thread-" + executor);
        executor.initialize();
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("com.oyo.hotel-krypton-task-thread-" + executor);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, params) -> {
            if (throwable instanceof BusinessException) {
                log.warn("Exception message - " + throwable.getMessage());
                log.warn("Method name - " + method.getName());
                for (Object param : params) {
                    log.warn("Parameter value - " + param);
                }
            } else {
                log.error("Method name - " + method.getName());
                for (Object param : params) {
                    log.error("Parameter value - " + param);
                }
                log.error("Exception message: {}" + throwable);
            }
        };
    }

}
