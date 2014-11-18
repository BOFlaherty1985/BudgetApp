package main.java.budgetapp.client;

import main.java.budgetapp.breakdown.BudgetBreakdown;
import main.java.budgetapp.breakdown.BudgetCalculation;
import main.java.budgetapp.breakdown.CalculateBudgetBreakdown;
import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.form.BudgetFormData;
import main.java.budgetapp.budget.items.CoreBudgetItem;
import main.java.budgetapp.budget.items.SocialBudgetItem;
import main.java.budgetapp.factory.budget.BudgetFactory;
import main.java.budgetapp.factory.budget.CreateBudget;
import main.java.budgetapp.factory.graph.GraphFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Client for Budget Application.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 27/10/2014
 * @project BudgetApp
 */
public class BudgetClient {

    private static BudgetFactory budgetFactory = new CreateBudget();
    private static GraphFactory graphFactory = new GraphFactory();

    public static void main(String[] args) throws Exception {

        BudgetFormData formData = new BudgetFormData();
        formData.setSubmittedOn(new Date());
        formData.setSalary(new BigDecimal("2000"));

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem("Rent", new BigDecimal("665")));
        coreBudgetItemList.add(new CoreBudgetItem("Council Tax", new BigDecimal("65")));
        // set core budget items
        formData.setCoreBudgetItemsList(coreBudgetItemList);

        List<SocialBudgetItem> socialBudgetItems = new ArrayList<SocialBudgetItem>();
        socialBudgetItems.add(new SocialBudgetItem(new Date(), "BOF Birthday", new BigDecimal("120")));
        socialBudgetItems.add(new SocialBudgetItem(new Date(), "Essex Reunion", new BigDecimal("120")));
        // set social budget items
        formData.setSocialBudgetItemsList(socialBudgetItems);

        Budget budget = budgetFactory.requestBudgetByType("ANNUAL");

        // call buildBudget() method
        try {
            budget.buildBudget(formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(budget.toString());

        CalculateBudgetBreakdown breakdown = new CalculateBudgetBreakdown();
        breakdown.setBudgetCalculation(new BudgetCalculation());

        BudgetBreakdown budgetBreakdown = null;

        try {
            budgetBreakdown = breakdown.calculateBreakdown(budget, new BudgetBreakdown());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(budgetBreakdown.toString());

    }

}