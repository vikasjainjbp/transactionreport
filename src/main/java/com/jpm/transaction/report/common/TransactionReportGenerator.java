package com.jpm.transaction.report.common;

import com.jpm.transaction.report.model.Transaction;
import com.jpm.transaction.report.service.TransactionReportGenerateService;

import java.util.List;
import java.util.Map;

public class TransactionReportGenerator {

    private static final String HEADER_STRING = "\n%35s\n\n-------------------------------------------------------------------------------\n%20s %28s %10s\n-------------------------------------------------------------------------------\n";
    private static final String COLUMN_ROW_STRING = "%20s %20s %15s\n";
    private static final String FOOTER_STRING = "-------------------------------------------------------------------------------\n============================================================================================";
    private static final String AMOUNT_IN_USD = "Amount in USD";
    private static final String SETTLEMENT_DATE = "Settlement Date";
    private static final String EMPTY_STRING ="";


    /**
     * Generate transaction report
     *
     * @param transactions the list of transaction
     */
    public void generate(List<Transaction> transactions) {
        TransactionReportGenerateService transactionReportGenerateService = new TransactionReportGenerateService();
        printReport(transactionReportGenerateService.getTotalUsdAmountSettledIncomingOrOutGoingEveryDay(transactions, "S"),"Day wise incoming", SETTLEMENT_DATE, AMOUNT_IN_USD);
        printReport(transactionReportGenerateService.getTotalUsdAmountSettledIncomingOrOutGoingEveryDay(transactions, "B"),"Day wise outgoing", SETTLEMENT_DATE, AMOUNT_IN_USD);
        printReport(transactionReportGenerateService.getSortedEntitiesBaseOnBuyInstruction(transactions),"Sorted entities based on buy instruction", "Entity Name", AMOUNT_IN_USD,"Ranking");
    }

    private void printReport(final Map<String, Double> reportMap,final String... headers) {
        System.out.println(String.format(HEADER_STRING,headers[0],headers[1],headers[2],headers.length==4?headers[3]:EMPTY_STRING));
        int[] index = {0};
        reportMap.forEach((k, v) -> {
            System.out.println(String.format(COLUMN_ROW_STRING, k, v, headers.length==4?++index[0]:EMPTY_STRING));
        });
        System.out.println(FOOTER_STRING);
    }
}
