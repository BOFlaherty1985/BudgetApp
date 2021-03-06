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
    public void setItemDescription(String description) {
        this.description = description;
    }

    @Override
    public BigDecimal getItemDescription() {
        return moneySpent;
    }

    @Override
    public void setItemMonetaryAmount(BigDecimal moneySpent) {
        this.moneySpent = moneySpent;
    }

    @Override
    public BigDecimal getItemMonetaryAmount() {
        return moneySpent;
    }

    @Override
    public String toString() {
        return "CoreBudgetItem{" +
                "description='" + description + '\'' +
                ", moneySpent=" + moneySpent +
                '}';
    }

}
