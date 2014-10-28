package main.java.budgetapp.breakdown;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.monthly.MonthlyBudget;

/**
 * Calculate Budget Breakdown
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/10/2014
 * @project BudgetApp
 */
public class CalculateBudgetBreakdown {

    public BudgetBreakdown calculateBreakdown(Budget budget) {

        BudgetBreakdown budgetBreakdown = new BudgetBreakdown();

        if(budget instanceof AnnualBudget) {
            budgetBreakdown.setBudgetType("ANNUAL");
        } else if (budget instanceof MonthlyBudget) {
            budgetBreakdown.setBudgetType("MONTHLY");
        }

        return budgetBreakdown;
    }
}
