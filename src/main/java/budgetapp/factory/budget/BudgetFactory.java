package main.java.budgetapp.factory.budget;

import main.java.budgetapp.budget.Budget;

/**
 * Budget Factory Class.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public abstract class BudgetFactory {

    protected static final String ANNUAL_BUDGET = "ANNUAL";
    protected static final String MONTHLY_BUDGET = "MONTHLY";

    /**
     * create budget by the users budget_choice value.
     *
     * @param budget_choice
     * @return
     */
    public Budget requestBudgetByType(String budget_choice) throws Exception {
        return createBudget(budget_choice);
    }

    protected abstract Budget createBudget(String budget_choice) throws Exception;

}
