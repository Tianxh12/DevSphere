package com.devsphere.utils;

import com.devsphere.properties.GiteeProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class GiteeUtil {

    @Autowired
    private GiteeProperties giteeProperties;

    private static final String UPLOAD_URL_TEMPLATE = "/{owner}/{repo}/contents/{path}";

    /**
     * 上传文件至Gitee
     * @param fileBytes 文件字节数组
     * @param fileName 文件名
     * @return 上传后的文件URL
     */
    public String upload(byte[] fileBytes, String fileName) {
        // 将文件内容转为Base64编码
        String base64File = Base64.getEncoder().encodeToString(fileBytes);

        // 构造上传路径
        String filePath = "images/" + fileName; // 上传到Gitee的images文件夹下

        // 构造提交的请求体
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("access_token", giteeProperties.getAccessToken());
        requestBody.put("content", base64File);
        requestBody.put("message", "Upload " + fileName);

        // 构造URL
        String url = UriComponentsBuilder.fromHttpUrl(giteeProperties.getApiUrl())
                .path(UPLOAD_URL_TEMPLATE)
                .buildAndExpand(giteeProperties.getRepoOwner(), giteeProperties.getRepoName(), filePath)
                .toUriString();

        // 发送POST请求上传文件
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // 文件上传成功，返回文件访问URL
            String uploadedUrl = String.format("https://gitee.com/%s/%s/raw/branch/master/%s",
                    giteeProperties.getRepoOwner(),
                    giteeProperties.getRepoName(),
                    filePath);
            log.info("File uploaded to: {}", uploadedUrl);
            return uploadedUrl;
        } else {
            log.error("File upload failed: {}", response.getBody());
            return null;
        }
    }
}
