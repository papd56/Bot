package com.ruoyi.common.utils.job;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XxlJobConfig {

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(" http://8.217.124.68:8080/xxl-job-admin");
        xxlJobSpringExecutor.setAppname("my-springboot-job-executor");
        xxlJobSpringExecutor.setIp(null); // 如果想手动设置IP可以在这里设置
        xxlJobSpringExecutor.setPort(8080);
        xxlJobSpringExecutor.setLogPath("/home/xxl-job/logs");
        xxlJobSpringExecutor.setLogRetentionDays(30);
        return xxlJobSpringExecutor;
    }
}
