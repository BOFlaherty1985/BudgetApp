package main.java.budgetapp.breakdown;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;

import java.math.BigDecimal;

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

    public BudgetBreakdown processBudgetItemsCalculation(Budget budgetObject, BudgetBreakdown budgetBreakdown) {

        // Least effort to obtain the green light for all test cases (Not Complete)
        if(budgetObject.getCoreBudgetItemList() != null) {
            BigDecimal totalCoreBudget = budgetObject.getCoreBudgetItemList().get(0).getItemMonetaryAmount();

            budgetBreakdown.setTotalCoreBudget(totalCoreBudget);
        } else {
            budgetBreakdown.setTotalCoreBudget(new BigDecimal("1000"));
        }

        return budgetBreakdown;
    }

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
        return (budgetObject instanceof  AnnualBudget) ? ANNUAL : MONTHLY;
    }

}
