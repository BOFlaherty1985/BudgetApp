package main.java.budgetapp.client;

import main.java.budgetapp.breakdown.BudgetBreakdown;
import main.java.budgetapp.breakdown.BudgetCalculation;
import main.java.budgetapp.breakdown.CalculateBudgetBreakdown;
import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.form.BudgetFormData;
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

    public static void main(String[] args) throws Exception {

        BudgetFormData formData = new BudgetFormData();

        Budget budget = budgetFactory.requestBudgetByType("MONTHLY");
        budget.buildBudget(formData);

        // call buildBudget() method
        budget.buildBudget(formData);
        System.out.println(budget.toString());

        CalculateBudgetBreakdown breakdown = new CalculateBudgetBreakdown();
        breakdown.setBudgetCalculation(new BudgetCalculation());

        try {
            breakdown.calculateBreakdown(budget, new BudgetBreakdown());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}