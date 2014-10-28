package main.java.budgetapp.breakdown;

/**
 * Budget Breakdown
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/10/2014
 * @project BudgetApp
 */
public class BudgetBreakdown {

    private String budgetType;
    private Object coreItemsTotal;

    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
    }

    public Object getCoreItemsTotal() {
        return coreItemsTotal;
    }

    public void setCoreItemsTotal(Object coreItemsTotal) {
        this.coreItemsTotal = coreItemsTotal;
    }
}
