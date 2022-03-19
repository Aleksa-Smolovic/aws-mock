package com.quantox.awsmock.repository;

import com.quantox.awsmock.domain.Machine;
import liquibase.pro.packaged.Q;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Long> {

    @Query(value = "select * from machine " +
            "where (?1 is null or uid = ?1) " +
            "and (?2 is null or status in ?2) " +
            "and (?3 is null or cast(created_at as DATE) >= ?3) " +
            "and (?4 is null or cast(created_ad as DATE) <= ?4)", nativeQuery = true)
    List<Machine> findAllByCriteria(String uid, List<String> status, LocalDate dateFrom, LocalDate dateTo, Pageable pageable);

}
