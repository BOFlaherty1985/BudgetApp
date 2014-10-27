package main.java.budgetapp.factory;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.monthly.MonthlyBudget;

/**
 * Monthly Budget Implementation
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public class CreateBudget extends BudgetFactory {


    @Override
    protected Budget createBudget(String budget_choice) {

        Budget budget = (budget_choice.equals(ANNUAL_BUDGET)) ? new AnnualBudget() :
                new MonthlyBudget();

        return budget;
    }

}
