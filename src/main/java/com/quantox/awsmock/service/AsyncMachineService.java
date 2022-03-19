package com.quantox.awsmock.service;

import com.quantox.awsmock.domain.Machine;
import com.quantox.awsmock.domain.enumeration.MachineStatus;
import com.quantox.awsmock.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AsyncMachineService {

    private final MachineRepository machineRepository;

    @Async
    public CompletableFuture<Void> resolveMachineStatusAndUnlock(Machine machine, MachineStatus status, long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        machine.setStatus(status);
        machine.setLocked(false);
        machineRepository.save(machine);
        return CompletableFuture.completedFuture(null);
    }

}
