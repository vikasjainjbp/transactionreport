package com.jpm.transaction.report.service;

import com.jpm.transaction.report.common.EntityType;
import com.jpm.transaction.report.model.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TransactionReportGenerateServiceTest {

    @Test
    public void testGetTotalUsdAmountSettledIncoming_OutGoing_OnSaturday(){
        TransactionReportGenerateService transactionReportGenerateService = new TransactionReportGenerateService();
        final Date settledDate = new Date("02 Sep 2017");

        Map<String,Double> inMap = transactionReportGenerateService.getTotalUsdAmountSettledIncomingOrOutGoingEveryDay(getListOfTransactions(settledDate),"S");
        Map<String,Double> outMap = transactionReportGenerateService.getTotalUsdAmountSettledIncomingOrOutGoingEveryDay(getListOfTransactions(settledDate),"B");
        final String settledDateForAED = new Date("03 Sep 2017").toString();
        final String settledDateForSGP = new Date("04 Sep 2017").toString();
        Assert.assertTrue(inMap.get(settledDateForAED) > 0.0);
        Assert.assertTrue(inMap.get(settledDateForSGP) > 0.0);
        Assert.assertTrue(outMap.get(settledDateForAED) > 0.0);
        Assert.assertTrue(outMap.get(settledDateForSGP) > 0.0);
    }

    @Test
    public void testGetTotalUsdAmountSettledIncoming_OutGoing_OnFriday(){
        TransactionReportGenerateService transactionReportGenerateService = new TransactionReportGenerateService();
        final Date settledDate = new Date("01 Sep 2017");

        Map<String,Double> inMap = transactionReportGenerateService.getTotalUsdAmountSettledIncomingOrOutGoingEveryDay(getListOfTransactions(settledDate),"S");
        Map<String,Double> outMap = transactionReportGenerateService.getTotalUsdAmountSettledIncomingOrOutGoingEveryDay(getListOfTransactions(settledDate),"S");
        final String settledDateForAED = new Date("03 Sep 2017").toString();
        Assert.assertTrue(inMap.get(settledDateForAED) > 0.0);
        Assert.assertTrue(inMap.get(settledDate.toString()) > 0.0);
        Assert.assertTrue(outMap.get(settledDateForAED) > 0.0);
        Assert.assertTrue(outMap.get(settledDate.toString()) > 0.0);
    }

    @Test
    public void testGetTotalUsdAmountSettledIncoming_OutGoing_OnThursday(){
        TransactionReportGenerateService transactionReportGenerateService = new TransactionReportGenerateService();
        final Date settledDate = new Date("31 Aug 2017");

        Map<String,Double> inMap = transactionReportGenerateService.getTotalUsdAmountSettledIncomingOrOutGoingEveryDay(getListOfTransactions(settledDate),"S");
        Map<String,Double> outMap = transactionReportGenerateService.getTotalUsdAmountSettledIncomingOrOutGoingEveryDay(getListOfTransactions(settledDate),"S");
        Assert.assertTrue(inMap.size()==1);
        Assert.assertTrue(inMap.get(settledDate.toString()) > 0.0);
        Assert.assertTrue(outMap.size()==1);
        Assert.assertTrue(outMap.get(settledDate.toString()) > 0.0);
    }

    @Test
    public void testGetSortedEntities(){
        TransactionReportGenerateService transactionReportGenerateService = new TransactionReportGenerateService();
        Map<String,Double> map = transactionReportGenerateService.getSortedEntitiesBaseOnBuyInstruction(getListOfTransactions());
        Assert.assertTrue(map.size()==3);
        Map.Entry<String,Double> entry=map.entrySet().iterator().next();
        Assert.assertEquals(EntityType.FOO.name(),entry.getKey());
    }

    private List<Transaction> getListOfTransactions(Date settledDate) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(EntityType.FOO, "S", 0.50, "AED", new Date("31 Aug 2017"), settledDate, 200, 100.25));
        transactions.add(new Transaction(EntityType.BAR, "S", 0.22, "SGP", new Date("31 Aug 2017"), settledDate, 450, 150.5));
        transactions.add(new Transaction(EntityType.FOO, "B", 0.50, "AED", new Date("31 Aug 2017"), settledDate, 200, 100.25));
        transactions.add(new Transaction(EntityType.BAR, "B", 0.22, "SGP", new Date("31 Aug 2017"), settledDate, 450, 150.5));

        return transactions;
    }

    private List<Transaction> getListOfTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(EntityType.FOO, "B", 0.50, "AED", new Date("31 Aug 2017"), new Date("02 Sep 2017"), 200, 100.25));
        transactions.add(new Transaction(EntityType.BAR, "S", 0.22, "SGP", new Date("31 Aug 2017"), new Date("02 Sep 2017"), 450, 150.5));
        transactions.add(new Transaction(EntityType.FOO, "B", 0.17, "SGP", new Date("30 Aug 2017"), new Date("31 Aug 2017"), 150, 100.25));
        transactions.add(new Transaction(EntityType.JIG, "S", 0.10, "GBP", new Date("02 SEP 2017"), new Date("03 SEP 2017"), 200, 150.5));
        transactions.add(new Transaction(EntityType.JOE, "B", 0.22, "SGP", new Date("01 SEP 2017"), new Date("02 SEP 2017"), 200, 150.5));
        transactions.add(new Transaction(EntityType.BAR, "S", 0.22, "AER", new Date("02 SEP 2017"), new Date("04 SEP 2017"), 310, 150.5));
        transactions.add(new Transaction(EntityType.JIM, "B", 0.22, "SGP", new Date("04 SEP 2017"), new Date("05 SEP 2017"), 450, 150.5));
        transactions.add(new Transaction(EntityType.FOO, "B", 0.13, "AUD", new Date("01 SEP 2017"), new Date("02 SEP 2017"), 130, 150.5));
        transactions.add(new Transaction(EntityType.FOO, "B", 0.22, "SGP", new Date("07 SEP 2017"), new Date("09 SEP 2017"), 450, 150.5));
        transactions.add(new Transaction(EntityType.BAR, "S", 0.22, "SAR", new Date("05 SEP 2017"), new Date("07 SEP 2017"), 450, 150.5));
        return transactions;
    }

}
