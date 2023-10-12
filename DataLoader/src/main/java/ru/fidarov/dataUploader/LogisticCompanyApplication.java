package ru.fidarov.dataUploader;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import ru.fidarov.dataUploader.service.OperationHistoryDataLoader;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
public class LogisticCompanyApplication {

	private final OperationHistoryDataLoader load;

	@Autowired
	public LogisticCompanyApplication(OperationHistoryDataLoader load) {
		this.load = load;
	}

	public static void main(String[] args) {
		SpringApplication.run(LogisticCompanyApplication.class, args);
	}

	@PostConstruct
	public void loadData() {
		load.loadDataFromCSV();
	}
}
