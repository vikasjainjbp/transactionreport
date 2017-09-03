package com.jpm.transaction.report.common;

import com.jpm.transaction.report.model.Transaction;
import com.jpm.transaction.report.service.TransactionReportGenerateService;

import java.util.List;
import java.util.Map;

public class TransactionReportGenerator {

    /**
     * Generate transaction report
     * @param transactions the list of transaction
     */
    public void generate(List<Transaction> transactions) {
        TransactionReportGenerateService transactionReportGenerateService = new TransactionReportGenerateService();

        System.out.println("Day wise incoming :" );
        printMap(transactionReportGenerateService.getTotalUsdAmountSettledIncomingOrOutGoingEveryDay(transactions,"S"));
        System.out.println("Day wise outgoing :");
        printMap(transactionReportGenerateService.getTotalUsdAmountSettledIncomingOrOutGoingEveryDay(transactions,"B"));
        System.out.println("Sorted entities based on buy instruction :");
        printMap(transactionReportGenerateService.getSortedEntitiesBaseOnBuyInstruction(transactions));
    }

    private void printMap(Map map){
       map.forEach((k,v) -> {
           System.out.println(k+"-->"+v);
       });
    }

}
