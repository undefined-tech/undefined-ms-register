package br.com.undefined.msregister;

import br.com.undefined.msregister.config.ApplicationConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Log4j2
@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class UndefinedMsRegisterApplication {

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(UndefinedMsRegisterApplication.class);
        final Environment env = application.run(args).getEnvironment();

        log.info("\n----------------------------------------------------------" +
                "\n\t" + "Application '{}' is running! " +
                "Access URLs:\n\t " +
                "Local: \t{}://localhost:{}\n\t " +
                "External: \t{}://{}:{}\n\t " +
                "Profile: \t{}\n" +
                "----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            env.getProperty("server.protocol"),
            env.getProperty("server.port"),
            env.getProperty("server.protocol"),
            getHostAddress(),
            env.getProperty("server.port"),
            env.getActiveProfiles());
    }

    private static String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("Nao foi possivel obter HostAddress");
            return null;
        }
    }

}
