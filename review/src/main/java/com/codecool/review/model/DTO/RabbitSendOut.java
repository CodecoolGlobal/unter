package com.codecool.review.model.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RabbitSendOut {
    public Long accId;
    public Double rating;

    @JsonCreator
    public RabbitSendOut(@JsonProperty("accId") Long accId,@JsonProperty("rating") Double rating) {
        this.accId = accId;
        this.rating = rating;
    }
}
