package com.delivery.app.controller.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@ApiModel(value = "[Request] 회원 가입을 위한 계정 정보", description = "계정 id, 이름, 비밀번호")
public class JoinRequest {

    @ApiModelProperty(value = "계정 아이디", notes = "5자리 이상, 20자리 미만 문자열", example = "yologger1013")
    @NotBlank(message = "'account_id' must not empty.")
    @JsonProperty(value = "account_id")
    @Length(min=5, max=20, message = "'account_id' must be between 5 and 20 characters in length")
    private String accountId;

    @ApiModelProperty(value = "이름", notes = "5자리 이상, 20자리 미만 문자열", example = "yologger")
    @NotBlank(message = "'name' must not empty.")
    @JsonProperty(value = "name")
    @Length(min=5, max=20, message = "'name' must be between 5 and 20 characters in length")
    private String name;

    @ApiModelProperty(value = "비밀번호", notes = "영어 대문자, 영어 소문자, 숫자, 특수 문자 중 3종류 이상이며 12자리 이상의 문자열", example = "!!!Qwert12345")
    @NotBlank(message = "'password' field empty.")
    @JsonProperty(value = "password")
    @Pattern(
            // 영어 대문자, 영어 소문자, 숫자, 특수문자 중 세 개 이상 포함 && 12자리 이상, 20자리 이하
            regexp = "^((?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,20})|((?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,20})|((?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,20})|((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{12,20})$",
            message = "'password' must contains at least three of uppercase letter, lowercase letter, number, and special character. 'password' must be between 8 and 20 characters in length"
    )
    private String password;
}
