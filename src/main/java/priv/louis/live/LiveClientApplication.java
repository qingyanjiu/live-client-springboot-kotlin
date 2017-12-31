package priv.louis.live;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("priv.louis.live.mapper")
public class LiveClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveClientApplication.class, args);
	}
}
