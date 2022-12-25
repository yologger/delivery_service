package com.delivery.app.controller.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@ApiModel(value = "[Request] 목적지 주소 변경을 위한 정보", description = "배달 id, 목적지 주소")
public class UpdateDestinationRequest {

    @ApiModelProperty(value = "배달 id")
    @JsonProperty(value = "delivery_id")
    @NotNull
    private Long deliveryId;

    @ApiModelProperty(value = "목적지 주소")
    @NotBlank(message = "'destination' must not be empty.")
    @JsonProperty(value = "destination")
    private String destination;
}
