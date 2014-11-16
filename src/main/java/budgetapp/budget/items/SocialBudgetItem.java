package main.java.budgetapp.budget.items;

import java.util.Date;
import java.math.BigDecimal;

/**
 * Social Budget Item.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public class SocialBudgetItem implements BudgetItem {

    private Date dateOfEvent;
    private String description;
    private BigDecimal moneySpent;

    public SocialBudgetItem(Date dateOfEvent, String description, BigDecimal moneySpent) {
        this.dateOfEvent = dateOfEvent;
        this.description = description;
        this.moneySpent = moneySpent;
    }

    @Override
    public void setItemDescription(String description) {
        this.moneySpent = moneySpent;
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
        return "SocialBudgetItem{" +
                "dateOfEvent=" + dateOfEvent +
                ", description='" + description + '\'' +
                ", moneySpent=" + moneySpent +
                '}';
    }

}
