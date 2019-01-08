package com.pvthach.calculator.controller;

import com.pvthach.calculator.model.History;
import com.pvthach.calculator.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class HistoryController {

	@Autowired
	HistoryRepository historyRepository;
	
	@GetMapping("/api/histories")
	@PreAuthorize("hasRole('USER')")
	public List<History> getCategories() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return historyRepository.getHistoriesByUser(username);
	}
}