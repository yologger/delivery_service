package com.delivery.app.controller.delivery;

import com.delivery.app.common.ErrorResponse;
import com.delivery.app.service.auth.AccountNotExistException;
import com.delivery.app.service.delivery.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Api(tags = "배달 API")
public class DeliveryController {

    final private DeliveryService deliveryService;

    @GetMapping("/deliveries")
    @PreAuthorize("hasAnyAuthority('USER')")
    @ApiOperation(value = "배달 내역 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 400, message = "존재하지 않는 계정"),
            @ApiResponse(code = 400, message = "잘못된 조회 기간")
    })
    public ResponseEntity<FindDeliveriesBetweenResult> getDeliveries(
            @Valid @NotNull @RequestParam(value = "account_id", required = true) Long accountId,
            @Valid @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "start_date", required = true) LocalDate startDate,
            @Valid @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "end_date", required = true) LocalDate endDate
    ) throws AccountNotExistException, InvalidPeriodException {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1L).atStartOfDay().minusNanos(1);
        return new ResponseEntity<>(deliveryService.findDeliveriesByPeriod(accountId, startDateTime, endDateTime), HttpStatus.OK);
    }

    @ExceptionHandler(value = AccountNotExistException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotExistException(AccountNotExistException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(DeliveryErrorCode.ACCOUNT_NOT_EXIST.getCode())
                .message(DeliveryErrorCode.ACCOUNT_NOT_EXIST.getMessage())
                .status(DeliveryErrorCode.ACCOUNT_NOT_EXIST.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(DeliveryErrorCode.ACCOUNT_NOT_EXIST.getStatus()));
    }

    @ExceptionHandler(value = InvalidPeriodException.class)
    public ResponseEntity<ErrorResponse> handleAInvalidPeriodException(InvalidPeriodException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(DeliveryErrorCode.INVALID_PERIOD.getCode())
                .message(DeliveryErrorCode.INVALID_PERIOD.getMessage())
                .status(DeliveryErrorCode.INVALID_PERIOD.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(DeliveryErrorCode.INVALID_PERIOD.getStatus()));
    }

    @PatchMapping("/delivery")
    @PreAuthorize("hasAnyAuthority('USER')")
    @ApiOperation(value = "목적지 변경")
    @ApiResponses({
            @ApiResponse(code = 200, message = "목적지 변경 성공"),
            @ApiResponse(code = 400, message = "존재하지 않는 배달"),
            @ApiResponse(code = 400, message = "배달 중"),
            @ApiResponse(code = 400, message = "배달 완료")
    })
    public ResponseEntity<UpdateDestinationResult> updateDestination(@Valid @RequestBody UpdateDestinationRequest request) throws DeliveryNotExistException, OnDeliveryException, DeliveryCompleteException {
        return new ResponseEntity<>(deliveryService.updateDestination(request.getDeliveryId(), request.getDestination()), HttpStatus.OK);
    }

    @ExceptionHandler(value = DeliveryNotExistException.class)
    public ResponseEntity<ErrorResponse> handleDeliveryNotExistException(DeliveryNotExistException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(DeliveryErrorCode.INVALID_PERIOD.getCode())
                .message(DeliveryErrorCode.INVALID_PERIOD.getMessage())
                .status(DeliveryErrorCode.INVALID_PERIOD.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(DeliveryErrorCode.INVALID_PERIOD.getStatus()));
    }

    @ExceptionHandler(value = OnDeliveryException.class)
    public ResponseEntity<ErrorResponse> handleOnDeliveryException(OnDeliveryException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(DeliveryErrorCode.ON_DELIVERY.getCode())
                .message(DeliveryErrorCode.ON_DELIVERY.getMessage())
                .status(DeliveryErrorCode.ON_DELIVERY.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(DeliveryErrorCode.ON_DELIVERY.getStatus()));
    }

    @ExceptionHandler(value = DeliveryCompleteException.class)
    public ResponseEntity<ErrorResponse> handleDeliveryCompleteException(DeliveryCompleteException e) {
        final ErrorResponse response = ErrorResponse.builder()
                .code(DeliveryErrorCode.DELIVERY_COMPLETE.getCode())
                .message(DeliveryErrorCode.DELIVERY_COMPLETE.getMessage())
                .status(DeliveryErrorCode.DELIVERY_COMPLETE.getStatus())
                .build();
        return new ResponseEntity(response, HttpStatus.valueOf(DeliveryErrorCode.DELIVERY_COMPLETE.getStatus()));
    }
}
