package main.java.budgetapp.breakdown;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.monthly.MonthlyBudget;

/**
 * Budget Calculations
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 06/11/2014
 * @project BudgetApp
 */
public class BudgetCalculation {

    public BudgetBreakdown processBudgetItemsCalculation(Budget budgetObject, BudgetBreakdown budgetBreakdown) {

        setBudgetType(budgetObject, budgetBreakdown);

        return budgetBreakdown;
    }

    private void setBudgetType(Budget budgetObject, BudgetBreakdown budgetBreakdown) {

        if(budgetObject instanceof AnnualBudget) {
            budgetBreakdown.setBudgetType("ANNUAL");
        }

        if(budgetObject instanceof MonthlyBudget) {
            budgetBreakdown.setBudgetType("MONTHLY");
        }

    }
}
