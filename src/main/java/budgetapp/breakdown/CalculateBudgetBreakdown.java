package main.java.budgetapp.breakdown;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.items.CoreBudgetItem;
import main.java.budgetapp.budget.items.SocialBudgetItem;
import main.java.budgetapp.exceptions.BudgetItemsMissingException;
import main.java.budgetapp.exceptions.SalaryNotFoundException;

import java.math.BigDecimal;
import java.math.BigInteger;

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

    public BudgetBreakdown calculateBreakdown(Budget budget) throws Exception {

        // validate the budget object
        validateBudget(budget);

        return buildBudgetBreakdownResult(budget);
    }

    private BudgetBreakdown buildBudgetBreakdownResult(Budget budget) {

        BudgetBreakdown breakdown = new BudgetBreakdown();

        // determine which type of budget object is being processed
        breakdown.setBudgetType(determineTypeOfBudget(budget));

        // calculate the sub total of core budget items
        breakdown.setTotalCoreBudget(calculateCoreItemsSubTotal(budget));

        // calculate the sub total of social budget items
        breakdown.setTotalSocialBudget(calculateSocialItemsSubTotal(budget));

        return breakdown;
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
     * calculates the total value of the CoreBudgetItemsList.
     *
     * @param budget
     * @return
     */
    private BigDecimal calculateCoreItemsSubTotal(Budget budget) {

        BigDecimal totalCoreBudgetItems = new BigDecimal(BigInteger.ZERO);

        for(CoreBudgetItem budgetItem : budget.getCoreBudgetItemList()) {
            totalCoreBudgetItems = totalCoreBudgetItems.add(budgetItem.getMoneySpent());
        }

        return totalCoreBudgetItems;
    }

    /**
     * calculates the total value of the SocialBudgetItemsList
     *
     * @param budget
     * @return
     */
    private BigDecimal calculateSocialItemsSubTotal(Budget budget) {

        BigDecimal totalSocialBudgetItems = new BigDecimal(BigInteger.ZERO);

        for(SocialBudgetItem socialBudgetItem : budget.getSocialBudgetItemList()) {
            totalSocialBudgetItems = totalSocialBudgetItems.add(socialBudgetItem.getMoneySpent());
        }

        return totalSocialBudgetItems;
    }

}
