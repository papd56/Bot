package com.ruoyi.common.utils.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class SampleJobHandler {

    @XxlJob("demoJobHandler")
    public void sampleJobHandler() throws Exception {
        System.out.println("XXL-JOB, Hello World.");
    }
}
