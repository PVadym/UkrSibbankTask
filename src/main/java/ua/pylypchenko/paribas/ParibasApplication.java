package ua.pylypchenko.paribas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.pylypchenko.paribas.saver.TransactionSaver;

@Slf4j
@SpringBootApplication
public class ParibasApplication implements CommandLineRunner{

	@Autowired
	private TransactionSaver transactionSaver;


	public static void main(String[] args) {
		SpringApplication.run(ParibasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(args.length == 0 || args.length > 1) {
			log.warn("There no or too many arguments!");
			return;
		}
		String fileName = args[0];
		transactionSaver.parseAndSave(fileName);
	}
}
