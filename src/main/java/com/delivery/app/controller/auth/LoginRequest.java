package com.delivery.app.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@ApiModel(value = "[Request] 로그인을 위한 계정 정보", description = "이메일, 비밀번호")
public class LoginRequest {

    @ApiModelProperty(value = "이메일", example = "yologger1013@gmail.com")
    @NotBlank(message = "'email' must not be empty.")
    @Email(message = "'email' must be in email format.")
    @JsonProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "비밀번호", notes = "영어 대문자, 영어 소문자, 숫자, 특수 문자 중 3종류 이상이며 12자리 이상의 문자열", example = "!!!Qwert12345")
    @NotBlank(message = "'password' field empty.")
    @JsonProperty(value = "password")
    @Pattern(
            // 영어 대문자, 영어 소문자, 숫자, 특수문자 중 세 개 이상 포함 && 12자리 이상, 20자리 이하
            regexp = "^((?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,20})|((?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,20})|((?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,20})|((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{12,20})$",
            message = "'password' must contains at least three of uppercase letter, lowercase letter, number, and special character. 'password' must be between 8 and 20 characters in length"
    )
    private String password;

    @Builder
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
