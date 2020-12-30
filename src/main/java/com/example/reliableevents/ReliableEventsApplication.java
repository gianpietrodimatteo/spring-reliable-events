package com.example.reliableevents;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.JavaVersion;
import org.springframework.boot.system.SystemProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ReliableEventsApplication {

    public static void main(String[] args) {
        // close the application context to shut down the custom ExecutorService ATTENTION HERE
        // com o close ele somente roda o app runner
        // sem o close roda o app runner e dps starteia o servidor, man
//         SpringApplication.run(ReliableEventsApplication.class, args).close();
        System.out.println("version: " + SpringVersion.getVersion());
        System.out.println("version: " + SystemProperties.get("java.version"));
        System.out.println("version: " + System.getProperty("java.version")); // System is springs, no extra import needed
        System.out.println("version: " + JavaVersion.getJavaVersion().toString());

        SpringApplication.run(ReliableEventsApplication.class, args);
    }

    @Bean
    public Executor taskExecutor(@Value("${executor.thread.amount}") int threadAmount, @Value("${executor.queue.capacity}") int queueCapacity) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadAmount);
        executor.setMaxPoolSize(threadAmount);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("REThread-");
        executor.initialize();
        return executor;
    }


//    private static final Logger log = LoggerFactory.getLogger(ReliableEventsApplication.class);
//    private final WalletDashboardUpdateService walletDashboardUpdateService;
//
//
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }
//
//    @Bean
//    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//        return args -> {
//
//            walletDashboardUpdateService.updateExpense()
////			Quote quote = restTemplate.getForObject(
////					"https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
////			log.info(quote.toString());
//            getMyQuote(restTemplate);
//            getMyQuote(restTemplate);
//            getMyQuote(restTemplate);
//        };
//    }

}
