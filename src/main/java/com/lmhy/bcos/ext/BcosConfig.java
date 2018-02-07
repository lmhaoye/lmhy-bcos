package com.lmhy.bcos.ext;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bcos")
@Data
public class BcosConfig {
    private String url;
    private String wallet;
    private String pwd;
}
