package com.delivery.app.service.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@ApiModel(value = "[Response] old_destination, new_destination")
@NoArgsConstructor
public class UpdateDestinationResult {


    @JsonProperty("old_destination")
    private String oldDestination;

    @JsonProperty("new_destination")
    private String newDestination;

    @Builder
    public UpdateDestinationResult(String oldDestination, String newDestination) {
        this.oldDestination = oldDestination;
        this.newDestination = newDestination;
    }
}
