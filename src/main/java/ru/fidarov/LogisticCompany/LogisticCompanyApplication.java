package ru.fidarov.LogisticCompany;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.fidarov.LogisticCompany.service.OperationHistoryDataLoader;

@SpringBootApplication
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
