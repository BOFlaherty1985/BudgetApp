package main.java.budgetapp.budget.annual;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.form.BudgetFormData;

import java.util.Date;
import java.math.BigDecimal;

/**
 * Annual Budget Object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public class AnnualBudget extends Budget {

    public AnnualBudget(String description, BigDecimal salary, Date submittedOn) {
        super(description, salary, submittedOn);
    }

    @Override
    public Budget buildBudget(BudgetFormData budgetFormData) throws Exception {

        validate(budgetFormData);

        // TODO - Should the AnnualBudget object multiply all input values by 12?
        // TODO - form object from the user to be passed into the buildBudget method to create the object.

        this.salary = budgetFormData.getSalary();
        this.submittedOn = budgetFormData.getSubmittedOn();

        return this;
    }

    private void validate(BudgetFormData budgetFormData) throws Exception {
        if(budgetFormData == null) throw new Exception("BudgetFormData is null.");
    }

    /*
        TODO - create a method to multiply the users values by twelve (annual total).
        Generic method to loop through each value of the given List<?> and multiply the value by twelve.
     */
}
