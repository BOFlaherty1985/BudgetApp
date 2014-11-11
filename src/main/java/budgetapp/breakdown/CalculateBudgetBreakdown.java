package main.java.budgetapp.breakdown;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.exceptions.SalaryNotFoundException;

/**
 * Calculate Budget Breakdown
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/10/2014
 * @project BudgetApp
 */
public class CalculateBudgetBreakdown {

    private BudgetCalculation budgetCalculation;

    public BudgetBreakdown calculateBreakdown(Budget budget, BudgetBreakdown breakdown) throws Exception {

        // validate budget object is not null
        validate(budget, breakdown);

        return buildBudgetBreakdown(budget, breakdown);
    }

    private BudgetBreakdown buildBudgetBreakdown(Budget budget, BudgetBreakdown breakdown) throws Exception {

        // determine budget description
        budgetCalculation.processBudgetTypeDescription(budget, breakdown);

        // determine budget items totals
        budgetCalculation.processBudgetItemsCalculation(budget, breakdown);

        // determine overall total of budget items
        budgetCalculation.calculateTotalAllBudgetItems(breakdown);

        // determine money available
        budgetCalculation.processTotalMoneyAvailableCalculation(breakdown, budget.getSalary(),
                breakdown.getTotalOfAllBudgetItems());

        return breakdown;
    }

    private void validate(Budget budget, BudgetBreakdown budgetBreakdown) throws Exception {

        if(budgetCalculation == null) {
            throw new Exception("BudgetCalculation is null");
        }

        if(budget == null) {
            throw new Exception("Budget is null.");
        }

        if(budget.getSalary() == null) {
            throw new SalaryNotFoundException("Salary value is missing from budget.");
        }

        if(budgetBreakdown == null) {
            throw new Exception("BudgetBreakdown is null.");
        }

    }

    public void setBudgetCalculation(BudgetCalculation budgetCalculation) {
        this.budgetCalculation = budgetCalculation;
    }

}



