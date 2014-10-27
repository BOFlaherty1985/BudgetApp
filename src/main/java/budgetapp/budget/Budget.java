package main.java.budgetapp.budget;

import java.util.Date;

import java.math.BigDecimal;

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

    public abstract void buildBudget();

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

    @Override
    public String toString() {
        return "Budget{" +
                "description='" + description + '\'' +
                ", salary=" + salary +
                ", submittedOn=" + submittedOn +
                '}';
    }
}
