package main.java.budgetapp.budget.form;

import main.java.budgetapp.budget.items.CoreBudgetItem;
import main.java.budgetapp.budget.items.SocialBudgetItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/11/2014
 * @project BudgetApp
 */
public class BudgetFormData {

    private BigDecimal salary;
    private Date submittedOn;
    private List<CoreBudgetItem> coreBudgetItemsList;
    private List<SocialBudgetItem> socialBudgetItemsList;

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
    }

    public List<CoreBudgetItem> getCoreBudgetItemsList() {
        return coreBudgetItemsList;
    }

    public void setCoreBudgetItemsList(List<CoreBudgetItem> coreBudgetItemsList) {
        this.coreBudgetItemsList = coreBudgetItemsList;
    }

    public List<SocialBudgetItem> getSocialBudgetItemsList() {
        return socialBudgetItemsList;
    }

    public void setSocialBudgetItemsList(List<SocialBudgetItem> socialBudgetItemsList) {
        this.socialBudgetItemsList = socialBudgetItemsList;
    }
}
