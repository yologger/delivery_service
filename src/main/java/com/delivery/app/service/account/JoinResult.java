package com.delivery.app.service.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@ApiModel(value = "[Response] 생성된 계정의 고유 id", description = "생성된 회원의 고유 id")
public class JoinResult {

    @JsonProperty("id")
    @ApiModelProperty(value = "생성된 계정의 고유 id", example = "1")
    private Long id;

    @Builder
    public JoinResult(Long id) {
        this.id = id;
    }
}
