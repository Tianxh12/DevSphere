package com.devsphere.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "devsphere.gitee")
@Data
public class GiteeProperties {

    private String accessToken;
    private String repoOwner;
    private String repoName;
    private String apiUrl;

}
