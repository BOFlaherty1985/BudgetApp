package main.java.budgetapp.budget.monthly;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.form.BudgetFormData;

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
    public Budget buildBudget(BudgetFormData budgetFormData) {
        // TODO - form object from the user to be passed into the buildBudget method to create the object.
        this.setCoreBudgetItemList(null);
        this.setSocialBudgetItemList(null);

        return null;
    }

}
