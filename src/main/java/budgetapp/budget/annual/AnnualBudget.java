package main.java.budgetapp.budget.annual;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.form.BudgetFormData;
import main.java.budgetapp.budget.items.CoreBudgetItem;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Annual Budget Object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public class AnnualBudget extends Budget {

    public static final BigDecimal TWELVE_MONTHS = new BigDecimal((12));

    public AnnualBudget(String description, BigDecimal salary, Date submittedOn) {
        super(description, salary, submittedOn);
    }

    @Override
    public void buildBudget(BudgetFormData budgetFormData) throws Exception {

        validate(budgetFormData);

        // is this needed? already set in constructor method? Or does the initial constructor simply initialise
        // the object?
        setSalary(multiplyMonetaryValueByTwelve(budgetFormData.getSalary())); // overrides default salary value
        setSubmittedOn(budgetFormData.getSubmittedOn()); // this is not required, already set in constructor

        // TODO - Generic method to loop through each value of the given List<?> and multiply the value by 12
        processCoreBudgetItems(budgetFormData);

        setCoreBudgetItemList(budgetFormData.getCoreBudgetItemsList());

    }

    private void validate(BudgetFormData budgetFormData) throws Exception {

        if(budgetFormData == null) {
            throw new Exception("BudgetFormData is null.");
        } else {

            if(budgetFormData.getSalary() == null || budgetFormData.getSubmittedOn() == null) {
                throw new Exception();
            }

            // TODO - use budget items exception
            if(budgetFormData.getCoreBudgetItemsList() == null) {
                throw new Exception();
            }

        }

    }

    public BigDecimal multiplyMonetaryValueByTwelve(BigDecimal monetaryValue) {
        return monetaryValue.multiply(TWELVE_MONTHS);
    }

    private void processCoreBudgetItems(BudgetFormData budgetFormData) throws Exception {
        int index = 0;

        for(CoreBudgetItem item : budgetFormData.getCoreBudgetItemsList()) {

            if(item.getItemMonetaryAmount() != null) {
                BigDecimal monetaryAmount = multiplyMonetaryValueByTwelve(item.getItemMonetaryAmount());
                budgetFormData.getCoreBudgetItemsList().get(index).setItemMonetaryAmount(monetaryAmount);
            } else {
                throw new Exception("CoreBudgetItem MonetaryAmount is null.");
            }

            index++;
        }

    }

}
