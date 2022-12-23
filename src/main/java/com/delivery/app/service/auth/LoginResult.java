package com.delivery.app.service.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@ApiModel(value = "[Response] Access Token")
public class LoginResult {
    @JsonProperty("access_token")
    @ApiModelProperty(value = "발급된 access token")
    private String accessToken;

    @Builder
    public LoginResult(String accessToken) {
        this.accessToken = accessToken;
    }
}
