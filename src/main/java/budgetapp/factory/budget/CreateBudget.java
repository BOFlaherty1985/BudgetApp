package main.java.budgetapp.factory.budget;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.monthly.MonthlyBudget;
import main.java.budgetapp.exceptions.InvalidBudgetTypeException;

import java.math.BigDecimal;
import java.math.BigInteger;
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
    protected Budget createBudget(String budget_choice) throws Exception {

        validateBudgetChoice(budget_choice);

        return (budget_choice.equals(ANNUAL_BUDGET)) ?
                new AnnualBudget("Annual budget", new BigDecimal(BigInteger.ZERO), new Date()) :
                new MonthlyBudget("Monthly budget", new BigDecimal(BigInteger.ZERO), new Date());
    }

    /**
     * validate user requested budget type
     *
     * @param budget_choice
     * @throws InvalidBudgetTypeException
     */
    private void validateBudgetChoice(String budget_choice) throws InvalidBudgetTypeException {
        if(budget_choice != ANNUAL_BUDGET && budget_choice != MONTHLY_BUDGET) {
            throw new InvalidBudgetTypeException("Invalid Budget Type.");
        }
    }

}
