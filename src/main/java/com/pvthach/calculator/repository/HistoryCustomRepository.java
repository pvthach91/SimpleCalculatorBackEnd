package com.pvthach.calculator.repository;


import com.pvthach.calculator.model.History;

import java.util.List;


/**
 * Created by THACH-PC
 */
public interface HistoryCustomRepository {

    List<History> getHistoriesByUser(String username);
}