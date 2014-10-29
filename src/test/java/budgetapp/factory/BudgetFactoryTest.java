package test.java.budgetapp.factory;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.monthly.MonthlyBudget;
import main.java.budgetapp.factory.BudgetFactory;
import main.java.budgetapp.factory.CreateBudget;
import org.junit.Test;

/**
 * Budget Factory Method Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 29/10/2014
 * @project BudgetApp
 */
public class BudgetFactoryTest {

    private BudgetFactory budgetFactory = new CreateBudget();

    @Test
    public void testAnnualFactoryObjectIsCreated() {

        Budget budgetObj = budgetFactory.requestBudgetByType("ANNUAL");
        assert(budgetObj instanceof AnnualBudget);

    }

    @Test
    public void testMonthlyFactoryObjectIsCreated() {

        Budget budgetObj = budgetFactory.requestBudgetByType("MONTHLY");
        assert(budgetObj instanceof MonthlyBudget);

    }


}
