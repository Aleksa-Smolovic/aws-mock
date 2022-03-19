package com.quantox.awsmock.service;

import com.quantox.awsmock.domain.Machine;
import com.quantox.awsmock.domain.enumeration.MachineAction;
import com.quantox.awsmock.domain.enumeration.MachineStatus;
import com.quantox.awsmock.errors.BadActionException;
import com.quantox.awsmock.errors.EntityNotFoundException;
import com.quantox.awsmock.errors.ExceptionErrors;
import com.quantox.awsmock.repository.MachineRepository;
import com.quantox.awsmock.service.criteria.MachineCriteria;
import com.quantox.awsmock.service.dto.MachineDTO;
import com.quantox.awsmock.service.mapper.MachineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MachineService {

    private static final String ENTITY = "Machine";
    private final static Random random = new Random();

    private final MachineRepository machineRepository;
    private final MachineMapper machineMapper;
    private final UserService userService;
    private final AsyncMachineService asyncMachineService;

    public MachineDTO save(MachineDTO machineDTO) {
        Machine machine = machineMapper.toEntity(machineDTO);
        machine.setStatus(MachineStatus.STOPPED);
        machine.setCreatedBy(userService.getLoggedUser());
        machine.setUid(UUID.randomUUID().toString());
        machine.setLocked(false);
        machine = machineRepository.save(machine);
        return machineMapper.toDto(machine);
    }

    public void destroy(Long id) {
        Machine machine = getOneAvailable(id);
        if (!machine.getStatus().equals(MachineStatus.STOPPED))
            throw new BadActionException(ExceptionErrors.MACHINE_LOCKED);
        machine.setActive(false);
        machineRepository.save(machine);
    }

    private Machine getOne(Long id) {
        return machineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, ENTITY));
    }

    private Machine getOneAvailable(Long id) {
        Machine machine = getOne(id);
        if (machine.isLocked())
            throw new BadActionException(ExceptionErrors.MACHINE_LOCKED);
        return machine;
    }

    private void lockMachine(Machine machine) {
        machine.setLocked(true);
        machineRepository.save(machine);
    }

    public void changeMachineStatus(Long id, MachineAction machineAction) {
        Machine machine = getOneAvailable(id);
        switch (machineAction) {
            case START:
                startMachine(machine);
                break;
            case STOP:
                stopMachine(machine);
                break;
            case RESTART:
                restartMachine(machine);
                break;
        }
    }

    private void startMachine(Machine machine) {
        if (!machine.getStatus().equals(MachineStatus.STOPPED))
            throw new BadActionException(ExceptionErrors.INVALID_ACTION);
        lockMachine(machine);

        long sleepTime = TimeUnit.SECONDS.toMillis(getRandomNumber(10, 15));
        asyncMachineService.resolveMachineStatusAndUnlock(machine, MachineStatus.RUNNING, sleepTime);
    }

    private void stopMachine(Machine machine) {
        if (!machine.getStatus().equals(MachineStatus.RUNNING))
            throw new BadActionException(ExceptionErrors.INVALID_ACTION);
        lockMachine(machine);

        long sleepTime = TimeUnit.SECONDS.toMillis(getRandomNumber(5, 10));
        asyncMachineService.resolveMachineStatusAndUnlock(machine, MachineStatus.STOPPED, sleepTime);
    }

    private void restartMachine(Machine machine) {
        if (!machine.getStatus().equals(MachineStatus.RUNNING))
            throw new BadActionException(ExceptionErrors.INVALID_ACTION);
        lockMachine(machine);

        long secondsToSleep = getRandomNumber(5, 10);
        long sleepTimeHalf = TimeUnit.SECONDS.toMillis(secondsToSleep) / 2;
        CompletableFuture<Void> stoppedMachineAction = asyncMachineService.resolveMachineStatusAndUnlock(machine, MachineStatus.STOPPED, sleepTimeHalf);
        stoppedMachineAction.whenComplete(((aVoid, throwable) -> {
            asyncMachineService.resolveMachineStatusAndUnlock(machine, MachineStatus.RUNNING, sleepTimeHalf);
        }));
    }

   

    private int getRandomNumber(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public List<MachineDTO> getAllByCriteria(MachineCriteria machineCriteria, Pageable pageable) {
        return machineMapper.toDto(machineRepository.findAllByCriteria(machineCriteria.getName(),
                machineCriteria.getStatuses().stream().map(Enum::toString).collect(Collectors.toList()),
                machineCriteria.getDateFrom(), machineCriteria.getDateTo(), pageable));
    }

}
