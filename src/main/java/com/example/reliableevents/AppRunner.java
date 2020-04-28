package com.example.reliableevents;

import com.example.reliableevents.operation.DashboardUpdateService;
import com.example.reliableevents.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class AppRunner implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final ExpenseService expenseService;
    private final DashboardUpdateService dashboardUpdateService;

    public AppRunner(ExpenseService expenseService, DashboardUpdateService dashboardUpdateService) {
        this.dashboardUpdateService = dashboardUpdateService;
        this.expenseService = expenseService;
    }

    @Override
    public void run(String... args) {
        // book =~ save
//        expenseService.book("Alice", "Bob", "Carol");
//        Assert.isTrue(expenseService.findAllBookings().size() == 3,
//                "First booking should work with no problem");
//        logger.info("Alice, Bob and Carol have been booked");
//        try {
//            expenseService.book("Chris", "Samuel");
//        } catch (RuntimeException e) {
//            logger.info("v--- The following exception is expect because 'Samuel' is too " +
//                    "big for the DB ---v");
//            logger.error(e.getMessage());
//        }
//
//        for (String person : expenseService.findAllBookings()) {
//            logger.info("So far, " + person + " is booked.");
//        }
//        logger.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, " +
//                "and Chris was rolled back in the same TX");
//        Assert.isTrue(expenseService.findAllBookings().size() == 3,
//                "'Samuel' should have triggered a rollback");
//
//        try {
//            expenseService.book("Buddy", null);
//        } catch (RuntimeException e) {
//            logger.info("v--- The following exception is expect because null is not " +
//                    "valid for the DB ---v");
//            logger.error(e.getMessage());
//        }
//
//        for (String person : expenseService.findAllBookings()) {
//            logger.info("So far, " + person + " is booked.");
//        }
//        logger.info("You shouldn't see Buddy or null. null violated DB constraints, and " +
//                "Buddy was rolled back in the same TX");
//        Assert.isTrue(expenseService.findAllBookings().size() == 3,
//                "'null' should have triggered a rollback");


        // Start the clock
//        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
//        CompletableFuture<ReString> page1 = walletDashboardUpdateService.updateExpense(new ExpenseUpdateEvent(new Expense("Johnny", new BigDecimal(1234))));
//        CompletableFuture<String> page2 = walletDashboardUpdateService.updateExpense(new ExpenseUpdateEvent(new Expense("Mary", new BigDecimal(4321))));
//        CompletableFuture<String> page3 = walletDashboardUpdateService.updateExpense(new ExpenseUpdateEvent(new Expense("Bobby", new BigDecimal(125896))));

        // Wait until they are all done
//        CompletableFuture.allOf(page1, page2, page3).join(); //(?)
//
//        // Print results, including elapsed time
//        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
//        logger.info("--> " + page1.get());
//        logger.info("--> " + page2.get());
//        logger.info("--> " + page3.get());
    }

}