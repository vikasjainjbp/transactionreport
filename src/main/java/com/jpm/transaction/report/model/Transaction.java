package com.jpm.transaction.report.model;

import com.jpm.transaction.report.common.EntityType;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Transaction {

    private EntityType entityType;
    private String buySellFlag;
    private double agreedFx;
    private String currency;
    private Date instructionDate;
    private Date settlementDate;
    private int units;
    private double pricePerUnit;

    /**
     * The constructor
     *
     * @param entityType the enity type
     * @param buySellFlag the buy or sell flag
     * @param agreedFx the agreed fix
     * @param currency the currency
     * @param instructionDate the instruction date
     * @param settlementDate the settlement date
     * @param units the units
     * @param pricePerUnit the price per unit
     */
    public Transaction(final EntityType entityType, final String buySellFlag, final double agreedFx, final String currency, final Date instructionDate, final Date settlementDate, final int units, final double pricePerUnit) {
        this.entityType = entityType;
        this.buySellFlag = buySellFlag;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public String getEntityTypeString(){
        return entityType.name();
    }

    public void setEntityType(final EntityType entityType) {
        this.entityType = entityType;
    }

    public String getBuySellFlag() {
        return buySellFlag;
    }

    public void setBuySellFlag(final String buySellFlag) {
        this.buySellFlag = buySellFlag;
    }

    public double getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(final double agreedFx) {
        this.agreedFx = agreedFx;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public Date getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(final Date instructionDate) {
        this.instructionDate = instructionDate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(final Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(final int units) {
        this.units = units;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(final double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double calculateUsdAmountOfTrade() {
        return this.pricePerUnit * this.units * this.agreedFx;
    }

    private int getDayOfWeek(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get actual settlement date if weekend.
     *
     * @return the settlement date
     */
    public String getActualSettlementDate() {
        Date actualSettlementDate = this.settlementDate;
        final int dayIndex = getDayOfWeek(this.settlementDate);
        if ("AED".equals(this.currency) || "SAR".equals(this.currency)) {
            if (dayIndex > 5) {
                actualSettlementDate = addDays(actualSettlementDate, 8 - dayIndex);
            }
        }
        else {
            if (dayIndex > 6) {
                actualSettlementDate = addDays(actualSettlementDate, 2);
            }
            else if (dayIndex < 2) {
                actualSettlementDate = addDays(actualSettlementDate, 1);
            }
        }

        return actualSettlementDate.toString();
    }

    private Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    @Override
    public String toString() {
        return this.entityType.name();
    }
}
