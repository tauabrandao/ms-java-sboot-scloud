package com.tauabrandao.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tauabrandao.hrpayroll.entities.Payment;
import com.tauabrandao.hrpayroll.entities.Worker;
import com.tauabrandao.hrpayroll.feignclients.WorkerFeignClients;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClients workerFeignClient;

	public Payment getPayment(Long workerId, Integer days) {
		Worker worker = workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

}
