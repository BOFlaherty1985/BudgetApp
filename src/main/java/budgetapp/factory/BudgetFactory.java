package main.java.budgetapp.factory;

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

    /**
     * Client request entry point
     */
    public Budget requestBudgetByType(String budget_choice) {
        return createBudget(budget_choice);
    }

    protected abstract Budget createBudget(String budget_choice);

}
