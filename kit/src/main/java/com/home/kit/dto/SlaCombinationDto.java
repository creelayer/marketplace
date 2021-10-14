package com.home.kit.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.home.kit.annotation.Constraint;
import com.home.kit.constraint.SlaExistConstraint;
import com.home.kit.constraint.DeliveryExistConstraint;
import com.home.kit.constraint.PaymentExistConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
public class SlaCombinationDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @JsonAlias("sla_id")
    @NotNull
    @Constraint(value = SlaExistConstraint.class, message = "Sla doesn't exist")
    private Integer slaId;

    @JsonAlias("delivery_uid")
    @NotNull
    @Constraint(value = DeliveryExistConstraint.class, message = "Delivery doesn't exist")
    private UUID deliveryUid;

    @JsonAlias("payment_uid")
    @NotNull
    @NotEmpty
    @Constraint(value = PaymentExistConstraint.class, message = "One or all of payment doesn't exist")
    private UUID[] paymentUid;
}
