package main.java.budgetapp.budget.annual;

import main.java.budgetapp.budget.Budget;

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

        // build the budget object
        buildBudget();
    }

    @Override
    public void buildBudget() {

        // TODO - Should the AnnualBudget object multiply all input values by 12?

        // TODO - form object from the user to be passed into the buildBudget method to create the object.
        this.description = "Annual Budget";
        this.salary = new BigDecimal("10000");
        this.submittedOn = new Date();

    }

    // TODO - create a method to multiply the users values by twelve (annual total)

}
