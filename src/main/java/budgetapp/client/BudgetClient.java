package main.java.budgetapp.client;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.factory.BudgetFactory;
import main.java.budgetapp.factory.CreateBudget;

/**
 * Client for Budget Application.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public class BudgetClient {

    private static BudgetFactory budgetFactory = new CreateBudget();

    public static void main(String[] args) {

        Budget budget = budgetFactory.requestBudgetType("ANNUAL");
        budget.buildBudget();

    }

}
