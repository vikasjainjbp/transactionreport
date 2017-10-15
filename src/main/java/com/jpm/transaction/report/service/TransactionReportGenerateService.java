package com.jpm.transaction.report.service;

import com.jpm.transaction.report.model.Transaction;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionReportGenerateService {


    /**
     * Get total USD amount settled incoming or outgoing everyday
     *
     * @param transactions the list of transaction
     * @param flag the buy or sell flag
     * @return the map of settled amount based on date.
     */
    public Map<String, Double> getTotalUsdAmountSettledIncomingOrOutGoingEveryDay(final List<Transaction> transactions, final String flag) {

        return transactions.stream()
                .filter(transaction -> flag.equals(transaction.getBuySellFlag()))
                .collect(Collectors.groupingBy(Transaction::getActualSettlementDate, Collectors.summingDouble(Transaction::calculateUsdAmountOfTrade)));

    }


    /**
     * Get sorted entity based on buy instructions.
     *
     * @param transactions the list of transaction.
     * @return sorted map of entity type and their total outgoing USD amount.
     */
    public Map<String, Double> getSortedEntitiesBaseOnBuyInstruction(final List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> "B".equals(transaction.getBuySellFlag()))
                .collect(Collectors.groupingBy(Transaction::getEntityTypeString, Collectors.summingDouble(Transaction::calculateUsdAmountOfTrade)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

}
