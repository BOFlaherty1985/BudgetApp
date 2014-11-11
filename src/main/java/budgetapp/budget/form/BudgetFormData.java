package main.java.budgetapp.budget.form;

import java.math.BigDecimal;
import java.util.Date;

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
}
