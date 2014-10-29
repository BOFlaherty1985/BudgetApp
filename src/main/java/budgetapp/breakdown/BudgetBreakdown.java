package main.java.budgetapp.breakdown;

import java.math.BigDecimal;

/**
 * Budget Breakdown
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/10/2014
 * @project BudgetApp
 */
public class BudgetBreakdown {

    private String budgetType;
    private BigDecimal totalCoreBudget;
    private BigDecimal totalSocialBudget;

    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
    }

    public BigDecimal getTotalCoreBudget() {
        return totalCoreBudget;
    }

    public void setTotalCoreBudget(BigDecimal totalCoreBudget) {
        this.totalCoreBudget = totalCoreBudget;
    }

    public BigDecimal getTotalSocialBudget() {
        return totalSocialBudget;
    }

    public void setTotalSocialBudget(BigDecimal totalSocialBudget) {
        this.totalSocialBudget = totalSocialBudget;
    }
}
