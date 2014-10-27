package main.java.budgetapp.budget.items;

import java.math.BigDecimal;

/**
 * Budget Item Interface.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public interface BudgetItem {

    void buildBudgetItem();
    void setItemDescription(String description);
    void setItemMonetaryAmount(BigDecimal moneySpent);
    public String toString();

}
