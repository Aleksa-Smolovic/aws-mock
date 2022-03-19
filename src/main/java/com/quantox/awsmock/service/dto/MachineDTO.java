package com.quantox.awsmock.service.dto;

import com.quantox.awsmock.domain.enumeration.MachineStatus;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class MachineDTO {

    private String uid;
    private MachineStatus status;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private boolean active;
    private Long createdById;
    private String createdByFirstName;
    private String createdByLastName;

}
