package main.java.budgetapp.breakdown;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.items.BudgetItem;
import main.java.budgetapp.exceptions.BudgetItemsMissingException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Budget Calculations
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 06/11/2014
 * @project BudgetApp
 */
public class BudgetCalculation {

    private static final String ANNUAL = "ANNUAL";
    private static final String MONTHLY = "MONTHLY";
    public static final BigDecimal NUMBER_OF_WEEKS = new BigDecimal("4");

    /**
     * process budget type
     *
     * @param budgetObject
     * @param budgetBreakdown
     * @return
     */
    public BudgetBreakdown processBudgetTypeDescription(Budget budgetObject, BudgetBreakdown budgetBreakdown) {

        budgetBreakdown.setBudgetType(setBudgetType(budgetObject, budgetBreakdown));

        return budgetBreakdown;
    }

    /**
     * determines which type of budget (Annual or Monthly) is being processed.
     *
     * @param budgetObject
     * @param budgetBreakdown
     * @return
     */
    private String setBudgetType(Budget budgetObject, BudgetBreakdown budgetBreakdown) {
        return (budgetObject instanceof AnnualBudget) ? ANNUAL : MONTHLY;
    }


    /**
     * process Budget Item lists
     *
     * @param budgetObject
     * @param budgetBreakdown
     * @return
     * @throws Exception
     */
    public BudgetBreakdown processBudgetItemsCalculation(Budget budgetObject, BudgetBreakdown budgetBreakdown) throws Exception {

        // Least effort to obtain the green light for all test cases (Not Complete)
        BigDecimal totalCoreItems = processBudgetItemsList(budgetObject, budgetObject.getCoreBudgetItemList());
        budgetBreakdown.setTotalCoreBudget(totalCoreItems);

        BigDecimal totalSocialItems = processBudgetItemsList(budgetObject, budgetObject.getSocialBudgetItemList());
        budgetBreakdown.setTotalSocialBudget(totalSocialItems);

        return budgetBreakdown;
    }

    private BigDecimal processBudgetItemsList(Budget budgetObject, List budgetItems) throws BudgetItemsMissingException {

        if(budgetObject.getCoreBudgetItemList() != null && budgetObject.getSocialBudgetItemList() != null) {
            return calculateTotalBudgetItems(budgetItems);
        } else {
            throw new BudgetItemsMissingException("BudgetItemList is null.");
        }
    }

    private BigDecimal calculateTotalBudgetItems(List budgetItems) {

        BigDecimal totalBudgetItem = new BigDecimal(BigInteger.ZERO);

        for(Object obj : budgetItems) {
            BudgetItem item = (BudgetItem) obj;

            totalBudgetItem = (item.getItemMonetaryAmount() != null) ? totalBudgetItem.add(item.getItemMonetaryAmount())
                    : totalBudgetItem.add(BigDecimal.ZERO);
        }

        return totalBudgetItem;
    }

    /**
     * calculates total of core and social budget items.
     *
     * @param breakdown
     * @return
     */
    public BudgetBreakdown calculateTotalAllBudgetItems(BudgetBreakdown breakdown) {

        BigDecimal totalAllItems = new BigDecimal(BigInteger.ZERO);

        if(breakdown.getTotalCoreBudget() !=  null && breakdown.getTotalSocialBudget() != null) {
            totalAllItems = breakdown.getTotalCoreBudget().add(breakdown.getTotalSocialBudget());
        };

        breakdown.setTotalOfAllBudgetItems(totalAllItems);

        return breakdown;
    }

    /**
     * calculates money available and money available per week totals
     *
     * @param breakdown
     * @param salary
     * @param totalAllItems
     * @return
     */
    public BudgetBreakdown processTotalMoneyAvailableCalculation(BudgetBreakdown breakdown, BigDecimal salary,
                                                                 BigDecimal totalAllItems) {

        BigDecimal totalMoneyAvail = new BigDecimal(BigInteger.ZERO);

        if(salary != null && totalAllItems != null) {
            // calculate totalMoneyAvail (salary - totalAllItems)
            totalMoneyAvail = salary.subtract(totalAllItems);

            // divide totalMoneyAvail by 4 (weeks)
            breakdown.setTotalMoneyAvailableWeekly(totalMoneyAvail.divide(NUMBER_OF_WEEKS));
        }

        breakdown.setTotalMoneyAvailable(totalMoneyAvail);

        return breakdown;
    }

}
