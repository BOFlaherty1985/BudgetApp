package main.java.budgetapp.budget.monthly;

import main.java.budgetapp.budget.Budget;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Monthly Budget Object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public class MonthlyBudget extends Budget {

    // TODO - pass in the form object containing data to create the object.
    public MonthlyBudget(String description, BigDecimal salary, Date submittedOn) {
        super(description, salary, submittedOn);

    }

    @Override
    public void buildBudget() {
        // TODO - form object from the user to be passed into the buildBudget method to create the object.
        this.setTestValue("TESTING");
        this.setCoreBudgetItemList(null);
        this.setSocialBudgetItemList(null);
    }

}
