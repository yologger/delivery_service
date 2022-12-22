package com.delivery.app.controller.account;

import com.delivery.app.common.ErrorResponse;
import com.delivery.app.service.account.AccountAlreadyExistException;
import com.delivery.app.service.account.AccountService;
import com.delivery.app.service.account.JoinData;
import com.delivery.app.service.account.JoinResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = "계정 API")
public class AccountController {

    final private AccountService accountService;

    @ApiOperation(value = "회원 가입", notes = "계정 id, 이름, 비밀번호로 회원 가입하기")
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원 가입 성공"),
            @ApiResponse(code = 400, message = "중복된 계정 id"),
            @ApiResponse(code = 400, message = "잘못된 account_id"),
            @ApiResponse(code = 400, message = "잘못된 name"),
            @ApiResponse(code = 400, message = "잘못된 password")
    })
    @PostMapping(value = "/account", consumes = "application/json", produces = "application/json")
    public ResponseEntity<JoinResult> join(@Valid @RequestBody JoinRequest request) throws AccountAlreadyExistException {
        JoinData joinData = JoinData.builder()
                .accountId(request.getAccountId())
                .name(request.getName())
                .password(request.getPassword())
                .build();

        return new ResponseEntity<>(accountService.join(joinData), HttpStatus.CREATED);
    }

    @ExceptionHandler(value = AccountAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleAccountAlreadyExistException(AccountAlreadyExistException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(AccountErrorCode.ACCOUNT_ALREADY_EXIST.getCode())
                .message(AccountErrorCode.ACCOUNT_ALREADY_EXIST.getMessage())
                .status(AccountErrorCode.ACCOUNT_ALREADY_EXIST.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(AccountErrorCode.ACCOUNT_ALREADY_EXIST.getStatus()));
    }
}
