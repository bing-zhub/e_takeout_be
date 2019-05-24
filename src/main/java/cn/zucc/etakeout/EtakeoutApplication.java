package cn.zucc.etakeout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EtakeoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtakeoutApplication.class, args);
	}

}
