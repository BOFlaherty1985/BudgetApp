package main.java.budgetapp.budget;

import main.java.budgetapp.budget.form.BudgetFormData;
import main.java.budgetapp.budget.items.CoreBudgetItem;
import main.java.budgetapp.budget.items.SocialBudgetItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Budget Object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public abstract class Budget {

    protected String description;
    protected BigDecimal salary;
    protected Date submittedOn;
    protected List<CoreBudgetItem> coreBudgetItemList;
    protected List<SocialBudgetItem> socialBudgetItemList;

    public Budget(String description, BigDecimal salary, Date submittedOn) {
        setDescription(description);;
        setSalary(salary);
        setSubmittedOn(submittedOn);
    }

    public abstract void buildBudget(BudgetFormData budgetFormData) throws Exception;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public List<CoreBudgetItem> getCoreBudgetItemList() {
        return coreBudgetItemList;
    }

    public void setCoreBudgetItemList(List<CoreBudgetItem> coreBudgetItemList) {
        this.coreBudgetItemList = coreBudgetItemList;
    }

    public List<SocialBudgetItem> getSocialBudgetItemList() {
        return socialBudgetItemList;
    }

    public void setSocialBudgetItemList(List<SocialBudgetItem> socialBudgetItemList) {
        this.socialBudgetItemList = socialBudgetItemList;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "description='" + description + '\'' +
                ", salary=" + salary +
                ", submittedOn=" + submittedOn +
                ", coreBudgetItemList=" + coreBudgetItemList +
                ", socialBudgetItemList=" + socialBudgetItemList +
                '}';
    }

}
