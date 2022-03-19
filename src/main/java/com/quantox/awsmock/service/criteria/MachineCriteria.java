package com.quantox.awsmock.service.criteria;

import com.quantox.awsmock.domain.Machine;
import com.quantox.awsmock.domain.enumeration.MachineStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MachineCriteria {

    private String name;
    private List<MachineStatus> statuses;
    private LocalDate dateFrom;
    private LocalDate dateTo;

}
