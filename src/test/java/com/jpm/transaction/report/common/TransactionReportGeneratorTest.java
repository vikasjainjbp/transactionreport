package com.jpm.transaction.report.common;

import com.jpm.transaction.report.model.Transaction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionReportGeneratorTest {


    @Test
    public void testGeneratorForAllMondayToFridayWithAED_SAR() {
        new TransactionReportGenerator().generate(getListOfTransactions());
    }


    private List<Transaction> getListOfTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(EntityType.FOO, "B", 0.50, "AED", new Date("31 Aug 2017"), new Date("02 Sep 2017"), 200, 100.25));
        transactions.add(new Transaction(EntityType.BAR, "S", 0.22, "SGP", new Date("31 Aug 2017"), new Date("02 Sep 2017"), 450, 150.5));
        transactions.add(new Transaction(EntityType.FOO, "B", 0.17, "SGP", new Date("30 Aug 2017"), new Date("31 Aug 2017"), 150, 100.25));
        transactions.add(new Transaction(EntityType.JIG, "S", 0.10, "GBP", new Date("02 SEP 2017"), new Date("03 SEP 2017"), 200, 150.5));
        transactions.add(new Transaction(EntityType.JOE, "S", 0.22, "SGP", new Date("01 SEP 2017"), new Date("02 SEP 2017"), 200, 150.5));
        transactions.add(new Transaction(EntityType.BAR, "S", 0.22, "AER", new Date("02 SEP 2017"), new Date("04 SEP 2017"), 310, 150.5));
        transactions.add(new Transaction(EntityType.JIM, "B", 0.22, "SGP", new Date("04 SEP 2017"), new Date("05 SEP 2017"), 450, 150.5));
        transactions.add(new Transaction(EntityType.FOO, "S", 0.13, "AUD", new Date("01 SEP 2017"), new Date("02 SEP 2017"), 130, 150.5));
        transactions.add(new Transaction(EntityType.FOO, "B", 0.22, "SGP", new Date("07 SEP 2017"), new Date("09 SEP 2017"), 450, 150.5));
        transactions.add(new Transaction(EntityType.BAR, "S", 0.22, "SAR", new Date("05 SEP 2017"), new Date("07 SEP 2017"), 450, 150.5));
        return transactions;
    }
}
