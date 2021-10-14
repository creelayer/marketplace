package com.home.kit.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.home.kit.annotation.Constraint;
import com.home.kit.constraint.MarketExistConstraint;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Setter
@Getter
public class SlaDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @JsonAlias("market_id")
    @NotNull
    @Min(value = 1)
    @Constraint(value = MarketExistConstraint.class, message = "Market doesn't exist")
    private UUID marketUid;

}
