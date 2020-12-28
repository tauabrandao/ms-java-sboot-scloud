package com.tauabrandao.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tauabrandao.hrpayroll.entities.Payment;
import com.tauabrandao.hrpayroll.entities.Worker;

@Service
public class PaymentService {

	@Value("${hr-worker.host}")
	private String HR_WORKER_HOST;

	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(Long workerId, Integer days) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", "" + workerId);

		Worker worker = restTemplate.getForObject(this.getCompleteUrl(), Worker.class, uriVariables);

		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

	private String getCompleteUrl() {
		return HR_WORKER_HOST + "/workers/{id}";
	}

}
