package main.java.budgetapp.breakdown;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.items.BudgetItem;
import main.java.budgetapp.exceptions.BudgetItemsMissingException;
import main.java.budgetapp.exceptions.SalaryNotFoundException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Calculate Budget Breakdown
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/10/2014
 * @project BudgetApp
 */
public class CalculateBudgetBreakdown {

    private static final String ANNUAL_BUDGET = "ANNUAL";
    private static final String MONTHLY_BUDGET = "MONTHLY";

    private static final BigDecimal DIVISIBLE_BY_FOUR = new BigDecimal("4");

    public BudgetBreakdown calculateBreakdown(Budget budget) throws Exception {

        // validate the budget object
        validateBudget(budget);

        return buildBudgetBreakdownResult(budget);
    }

    private BudgetBreakdown buildBudgetBreakdownResult(Budget budget) {

        BudgetBreakdown breakdown = new BudgetBreakdown();

        // determine which type of budget object is being processed
        breakdown.setBudgetType(determineTypeOfBudget(budget));

        // budget item calculations
        processBudgetItemCalculations(budget, breakdown);

        // total available calculations
        processTotalAvailableCalculations(budget, breakdown);

        return breakdown;
    }

    /**
     * processes all budget item calculations and sets totals on the BudgetBreakdown object.
     *
     * @param budget
     * @param breakdown
     */
    private void processBudgetItemCalculations(Budget budget, BudgetBreakdown breakdown) {

        // calculate the sub total of core budget items
        breakdown.setTotalCoreBudget(calculateBudgetItemsSubTotal(budget.getCoreBudgetItemList()));

        // calculate the sub total of social budget items
        breakdown.setTotalSocialBudget(calculateBudgetItemsSubTotal(budget.getSocialBudgetItemList()));

        // calculate total value of all budget items
        breakdown.setTotalOfAllBudgetItems(calculateAllBudgetItemsTotal(breakdown));

    }

    /**
     * process all money available calculations and sets totals on the BudgetBreakdown object.
     *
     * @param budget
     * @param breakdown
     */
    private void processTotalAvailableCalculations(Budget budget, BudgetBreakdown breakdown) {

        // total money available calculation
        breakdown.setTotalMoneyAvailable(
                calculateMoneyAvailable(budget.getSalary(), breakdown.getTotalOfAllBudgetItems())
        );

        // total weekly available calculation
        breakdown.setTotalMoneyAvailableWeekly(
                breakdown.getTotalMoneyAvailable().divide(DIVISIBLE_BY_FOUR)
        );

    }

    /**
     * validates the budget object ensure it is valid and ready for processing.
     *
     * @param budget
     * @throws Exception
     */
    private void validateBudget(Budget budget) throws Exception {
        checkSalaryValue(budget);
        checkBudgetItemLists(budget);
    }

    /**
     * throws an exception if either CoreBudgetItemList or SocialBudgetItemList are null.
     *
     * @param budget
     * @throws BudgetItemsMissingException
     */
    private void checkBudgetItemLists(Budget budget) throws BudgetItemsMissingException {

        if(budget.getCoreBudgetItemList() == null || budget.getSocialBudgetItemList() == null) {
            throw new BudgetItemsMissingException("budgetItemList within budget is null");
        }

    }

    /**
     * throws an exception if the salary value is null on the given budget object.
     *
     * @param budget
     * @throws SalaryNotFoundException
     */
    private void checkSalaryValue(Budget budget) throws SalaryNotFoundException {

        if(budget.getSalary() == null) {
            throw new SalaryNotFoundException("Budget is missing a value for Salary.");
        }

    }

    /**
     * determines which type of budget (Annual or Monthly) is being processed
     *
     * @param budget
     * @return
     */
    private String determineTypeOfBudget(Budget budget) {
        return (budget instanceof AnnualBudget) ? ANNUAL_BUDGET : MONTHLY_BUDGET;
    }

    /**
     * calculates the total value of the a given budgetItemList.
     * @return
     */
    private BigDecimal calculateBudgetItemsSubTotal(List budgetItemsList) {

        BigDecimal subTotal = new BigDecimal(BigInteger.ZERO);

        for(Object budgetObject : budgetItemsList) {

            BudgetItem budgetItem = (BudgetItem) budgetObject;
            subTotal = subTotal.add(budgetItem.getItemMonetaryAmount());

        }

        return subTotal;
    }

    /**
     * calculates the total of all budget items (Core & Social)
     *
     * @param breakdown
     * @return
     */
    private BigDecimal calculateAllBudgetItemsTotal(BudgetBreakdown breakdown) {
        return breakdown.getTotalSocialBudget().add(breakdown.getTotalCoreBudget());
    }

    /**
     * calculate total money available, subtracting totalOfAllBudgetItems fro the given Salary.
     *
     * @param salary
     * @param totalOfAllBudgetItems
     * @return
     */
    private BigDecimal calculateMoneyAvailable(BigDecimal salary, BigDecimal totalOfAllBudgetItems) {
        return salary.subtract(totalOfAllBudgetItems);
    }

}
