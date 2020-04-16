package com.example.reliableevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class ReliableEventsApplication {

    public static void main(String[] args) {
        // close the application context to shut down the custom ExecutorService ATTENTION HERE
        // com o close ele somente roda o app runner
        // sem o close roda o app runner e dps starteia o servidor, man
//         SpringApplication.run(ReliableEventsApplication.class, args).close();
        SpringApplication.run(ReliableEventsApplication.class, args);
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("ReliableEvents-");
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
