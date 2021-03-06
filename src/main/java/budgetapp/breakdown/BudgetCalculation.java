package main.java.budgetapp.breakdown;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.items.BudgetItem;
import main.java.budgetapp.exceptions.BudgetItemsMissingException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
    private static final BigDecimal MONTHLY_DIVISIBLE_WEEKS = new BigDecimal("4");
    private static final BigDecimal ANNUAL_DIVISIBLE_WEEKS = new BigDecimal("52");

    /**
     * process budget type
     *
     * @param budget - object representing a budget
     * @param budgetBreakdown - representation of the breakdown of a budget object
     * @return - breakdown of values
     */
    public BudgetBreakdown processBudgetTypeDescription(Budget budget, BudgetBreakdown budgetBreakdown) {

        budgetBreakdown.setBudgetType(setBudgetType(budget));

        return budgetBreakdown;
    }

    /**
     * determines which type of budget (Annual or Monthly) is being processed.
     *
     * @param budget - object representing a budget
     * @return - breakdown of values
     */
    private String setBudgetType(Budget budget) {
        return (budget instanceof AnnualBudget) ? ANNUAL : MONTHLY;
    }

    /**
     * process Budget Item lists
     *
     * @param budget - object representing a budget
     * @param budgetBreakdown - representation of the breakdown of a budget object
     * @return - breakdown of values
     * @throws Exception
     */
    public BudgetBreakdown processBudgetItemsCalculation(Budget budget, BudgetBreakdown budgetBreakdown) throws Exception {

        // Least effort to obtain the green light for all test cases (Not Complete)
        BigDecimal totalCoreItems = processBudgetItemsList(budget, budget.getCoreBudgetItemList());
        budgetBreakdown.setTotalCoreBudget(totalCoreItems);

        BigDecimal totalSocialItems = processBudgetItemsList(budget, budget.getSocialBudgetItemList());
        budgetBreakdown.setTotalSocialBudget(totalSocialItems);

        return budgetBreakdown;
    }

    private BigDecimal processBudgetItemsList(Budget budget, List<? extends BudgetItem> budgetItems) throws BudgetItemsMissingException {

        if(budget.getCoreBudgetItemList() != null && budget.getSocialBudgetItemList() != null) {
            return calculateTotalBudgetItems(budgetItems);
        } else {
            throw new BudgetItemsMissingException("BudgetItemList is null.");
        }
    }

    private BigDecimal calculateTotalBudgetItems(List<? extends BudgetItem> budgetItems) {

        BigDecimal totalBudgetItem = new BigDecimal(BigInteger.ZERO);

        for(BudgetItem item : budgetItems) {
            totalBudgetItem = (item.getItemMonetaryAmount() != null) ? totalBudgetItem.add(item.getItemMonetaryAmount())
                    : totalBudgetItem.add(BigDecimal.ZERO);
        }

        return totalBudgetItem;
    }

    /**
     * calculates total of core and social budget items.
     *
     * @param breakdown - representation of the breakdown of a budget object
     * @return - breakdown of values
     */
    public BudgetBreakdown calculateTotalAllBudgetItems(BudgetBreakdown breakdown) {

        BigDecimal totalAllItems = new BigDecimal(BigInteger.ZERO);

        if(breakdown.getTotalCoreBudget() !=  null && breakdown.getTotalSocialBudget() != null) {
            totalAllItems = breakdown.getTotalCoreBudget().add(breakdown.getTotalSocialBudget());
        }

        breakdown.setTotalOfAllBudgetItems(totalAllItems);

        return breakdown;
    }

    /**
     * calculates money available and money available per week totals
     *
     * @param breakdown - representation of the breakdown of a budget object
     * @param salary - salary input as BigDecimal
     * @param totalAllItems = total of all budget items as BigDecimal
     * @return - breakdown of values
     */
    public BudgetBreakdown processTotalMoneyAvailableCalculation(BudgetBreakdown breakdown, BigDecimal salary,
                                                                 BigDecimal totalAllItems) {

        BigDecimal totalMoneyAvail = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalMoneyAvailPerWeek = new BigDecimal(BigInteger.ZERO);

        if(salary != null && totalAllItems != null) {
            // calculate totalMoneyAvail (salary - totalAllItems)
            totalMoneyAvail = salary.subtract(totalAllItems);

            // calculate totalMoneyAvailableWeekly
            totalMoneyAvailPerWeek = calculateAvailableWeeklyTotal(breakdown, totalMoneyAvail);
        }

        breakdown.setTotalMoneyAvailableWeekly(totalMoneyAvailPerWeek);
        breakdown.setTotalMoneyAvailable(totalMoneyAvail);

        return breakdown;
    }

    private BigDecimal calculateAvailableWeeklyTotal(BudgetBreakdown breakdown, BigDecimal totalMoneyAvail) {
        return (breakdown.getBudgetType().equals(ANNUAL)) ?
                totalMoneyAvail.divide((ANNUAL_DIVISIBLE_WEEKS), 2, RoundingMode.HALF_UP) :
                totalMoneyAvail.divide((MONTHLY_DIVISIBLE_WEEKS), 2, RoundingMode.HALF_UP);
    }

}