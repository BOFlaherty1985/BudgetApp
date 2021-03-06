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

    void setItemDescription(String description);
    BigDecimal getItemDescription();
    void setItemMonetaryAmount(BigDecimal moneySpent);
    BigDecimal getItemMonetaryAmount();
    public String toString();

}
