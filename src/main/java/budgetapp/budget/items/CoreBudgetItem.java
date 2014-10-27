package main.java.budgetapp.budget.items;

import java.math.BigDecimal;

/**
 * Core Budget Item.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public class CoreBudgetItem implements BudgetItem {

    private String description;
    private BigDecimal moneySpent;

    public CoreBudgetItem(String description, BigDecimal moneySpent) {
        this.description = description;
        this.moneySpent = moneySpent;
    }

    @Override
    public void buildBudgetItem() {
        // call CoreBudgetItemBuilder
    }

    public String toString() {
        return "";
    }

}
