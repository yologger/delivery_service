package com.delivery.app.controller.auth;

import com.delivery.app.common.ErrorResponse;
import com.delivery.app.controller.account.AccountErrorCode;
import com.delivery.app.repository.account.Account;
import com.delivery.app.repository.account.AccountRepository;
import com.delivery.app.security.AuthErrorCode;
import com.delivery.app.security.TokenProvider;
import com.delivery.app.service.account.AccountAlreadyExistException;
import com.delivery.app.service.auth.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "인증 API")
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "로그인", notes = "계정 id, 비밀번호로 로그인 하기")
    @ApiResponses({
            @ApiResponse(code = 201, message = "로그인 성공"),
            @ApiResponse(code = 401, message = "존재하지 않는 계정 id"),
            @ApiResponse(code = 400, message = "잘못된 형식의 계정 id"),
            @ApiResponse(code = 400, message = "잘못된 형식의 password"),
            @ApiResponse(code = 401, message = "잘못된 password")
    })
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginResult> login(@Valid @RequestBody LoginRequest request) throws AccountNotExistException, InvalidPasswordException {
        LoginData data = LoginData.builder()
                .accountId(request.getAccountId())
                .password(request.getPassword())
                .build();
        return new ResponseEntity(authService.login(data), HttpStatus.CREATED);
    }

    @ExceptionHandler(value = AccountNotExistException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotExistException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(AuthErrorCode.ACCOUNT_NOT_EXIST.getCode())
                .message(AuthErrorCode.ACCOUNT_NOT_EXIST.getMessage())
                .status(AuthErrorCode.ACCOUNT_NOT_EXIST.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(AuthErrorCode.ACCOUNT_NOT_EXIST.getStatus()));
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(AuthErrorCode.INVALID_PASSWORD.getCode())
                .message(AuthErrorCode.INVALID_PASSWORD.getMessage())
                .status(AuthErrorCode.INVALID_PASSWORD.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(AuthErrorCode.INVALID_PASSWORD.getStatus()));
    }
}