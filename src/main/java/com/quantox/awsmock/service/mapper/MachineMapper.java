package com.quantox.awsmock.service.mapper;

import com.quantox.awsmock.domain.Machine;
import com.quantox.awsmock.service.dto.MachineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface MachineMapper extends EntityMapper<MachineDTO, Machine> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.firstName", target = "createdByFirstName")
    @Mapping(source = "createdBy.lastName", target = "createdByLastName")
    MachineDTO toDto(Machine machine);

    @Mapping(source = "createdById", target = "createdBy")
    Machine toEntity(MachineDTO machineDTO);

    default Machine fromId(Long id) {
        if (id == null)
            return null;
        Machine machine = new Machine();
        machine.setId(id);
        return machine;
    }
}
