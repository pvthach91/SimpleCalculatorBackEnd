package com.pvthach.calculator.repository;

import com.pvthach.calculator.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by THACH-PC
 */

@Repository
public interface HistoryRepository extends JpaRepository<History, Long>, HistoryCustomRepository {
}