package com.pvthach.calculator.controller;

import com.pvthach.calculator.calculation.Calculator;
import com.pvthach.calculator.calculation.OneParamCalculation;
import com.pvthach.calculator.calculation.TwoParamCalculation;
import com.pvthach.calculator.message.response.EnumResponse;
import com.pvthach.calculator.model.History;
import com.pvthach.calculator.model.User;
import com.pvthach.calculator.repository.HistoryRepository;
import com.pvthach.calculator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CalculationController {

	@Autowired
	HistoryRepository historyRepository;

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/calculateOneParam")
	@PreAuthorize("hasRole('USER')")
	public double calculateOneParam(@RequestBody OneParamCalculation calculation) throws Exception {
		return this.calculate(calculation);
	}

	@PostMapping("/api/calculateTwoParam")
	@PreAuthorize("hasRole('USER')")
	public double calculateTwoParam(@RequestBody TwoParamCalculation calculation) throws Exception {
		return this.calculate(calculation);
	}


	private double calculate(Calculator calculator) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(EnumResponse.USERNAME_NOT_FOUND.getDescription()));
        double result = calculator.calculate();
		History history = new History(calculator.generateHistory(result), user);
		historyRepository.save(history);

		return result;
	}
}