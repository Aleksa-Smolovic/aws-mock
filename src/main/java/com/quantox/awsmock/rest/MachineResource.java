package com.quantox.awsmock.rest;

import com.quantox.awsmock.domain.enumeration.MachineAction;
import com.quantox.awsmock.service.MachineService;
import com.quantox.awsmock.service.criteria.MachineCriteria;
import com.quantox.awsmock.service.dto.MachineDTO;
import liquibase.pro.packaged.G;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MachineResource {

    private final MachineService machineService;

    @PostMapping("/machines")
    public ResponseEntity<MachineDTO> createMachine(@RequestBody MachineDTO machineDTO) {
        return ResponseEntity.ok(machineService.save(machineDTO));
    }

    @DeleteMapping("/machines/{id}/destroy")
    public ResponseEntity<Void> destroyMachine(@PathVariable Long id) {
        machineService.destroy(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/machines/{id}/status")
    public ResponseEntity<Void> changeMachineStatus(@PathVariable Long id, @RequestParam MachineAction machineAction) {
        machineService.changeMachineStatus(id, machineAction);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/machines")
    public ResponseEntity<List<MachineDTO>> getMachines(@RequestBody MachineCriteria machineCriteria, Pageable pageable) {
        return ResponseEntity.ok(machineService.getAllByCriteria(machineCriteria, pageable));
    }

}
