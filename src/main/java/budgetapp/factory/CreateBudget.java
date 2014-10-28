package main.java.budgetapp.factory;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.monthly.MonthlyBudget;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Monthly Budget Implementation.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public class CreateBudget extends BudgetFactory {

    @Override
    protected Budget createBudget(String budget_choice) {
        // TODO - refactor budget object creation constructor input(s)
        return (budget_choice.equals(ANNUAL_BUDGET)) ?
                new AnnualBudget("annual budget", new BigDecimal("30000"), new Date()) :
                new MonthlyBudget("monthly budget", new BigDecimal("20000"), new Date());
    }

}
