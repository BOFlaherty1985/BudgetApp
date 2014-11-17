package main.java.budgetapp.budget.annual;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.form.BudgetFormData;
import main.java.budgetapp.budget.items.BudgetItem;
import main.java.budgetapp.budget.items.CoreBudgetItem;
import main.java.budgetapp.budget.items.SocialBudgetItem;
import main.java.budgetapp.exceptions.BudgetItemsMissingException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

        // budget items
        List<CoreBudgetItem> coreBudgetItems = (List<CoreBudgetItem>) processBudgetItems(budgetFormData.getCoreBudgetItemsList());
        setCoreBudgetItemList(coreBudgetItems);

        List<SocialBudgetItem> socialBudgetItemList = (List<SocialBudgetItem>) processBudgetItems(budgetFormData.getSocialBudgetItemsList());
        setSocialBudgetItemList(socialBudgetItemList);

    }

    private void validate(BudgetFormData budgetFormData) throws Exception {

        if(budgetFormData == null) {
            throw new Exception("BudgetFormData is null.");
        } else {

            if(budgetFormData.getSalary() == null || budgetFormData.getSubmittedOn() == null) {
                throw new Exception();
            }

            if(budgetFormData.getCoreBudgetItemsList() == null || budgetFormData.getSocialBudgetItemsList() == null) {
                throw new BudgetItemsMissingException("BudgetItems");
            }

        }

    }

    public BigDecimal multiplyMonetaryValueByTwelve(BigDecimal monetaryValue) {
        return monetaryValue.multiply(TWELVE_MONTHS);
    }

    private List<? extends BudgetItem> processBudgetItems(List<? extends BudgetItem> budgetItemList) throws Exception {
        int index = 0;

        for(BudgetItem item : budgetItemList) {

            if(item.getItemMonetaryAmount() != null) {
                BigDecimal monetaryAmount = multiplyMonetaryValueByTwelve(item.getItemMonetaryAmount());
                budgetItemList.get(index).setItemMonetaryAmount(monetaryAmount);
            } else {
                throw new BudgetItemsMissingException("BudgetItem monetaryAmount is null.");
            }

            index++;
        }

        return budgetItemList;
    }

}