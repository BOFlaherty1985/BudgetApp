package test.java.budgetapp.breakdown;

import main.java.budgetapp.breakdown.BudgetBreakdown;
import main.java.budgetapp.breakdown.CalculateBudgetBreakdown;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.monthly.MonthlyBudget;
import org.junit.Test;

import java.util.Date;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Calculate Budget Breakdown Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/10/2014
 * @project BudgetApp
 */
public class CalculateBudgetBreakdownTest {

    // Ensure that the Budget object's data is complete and valid, throw exceptions if this is not the case.
    // A separate method is to be created to complete each calculation (action), ensure that these methods are hit if required/
    // Ensure that the variables in BudgeBreakdown are not empty, or null after each calculation has been performed.

    private CalculateBudgetBreakdown calculateBudgetBreakdown = new CalculateBudgetBreakdown();

    // Test that a Budget object (annual or monthly) is passed to the class CalculateBudgetBreakdown
    @Test
    public void isAnnualBudgetItemAcceptedInCalculateBudgetMethod() {

        AnnualBudget annualBudget = createAnnualBudget();

        BudgetBreakdown breakdown = calculateBudgetBreakdown.calculateBreakdown(annualBudget);
        assertEquals("BudgetBreakdown budgetType equals ANNUAL", breakdown.getBudgetType(), "ANNUAL");
    }

    @Test
    public void isMonthlyBudgetItemAcceptedInCalculateBudgetMethod() {

        MonthlyBudget monthlybudget = createMonthlyBudget();

        BudgetBreakdown breakdown = calculateBudgetBreakdown.calculateBreakdown(monthlybudget);
        assertEquals("BudgetBreakdown budgetType equals MONTHLY", breakdown.getBudgetType(), "MONTHLY");

    }

    // Test that an object of BudgeBreakdown is returned from the method, with a number of calculations
    @Test
    public void isBudgetBreakdownNotNull() {

        AnnualBudget annualBudget = createAnnualBudget();

        BudgetBreakdown breakdown = calculateBudgetBreakdown.calculateBreakdown(annualBudget);
        assertNotNull("BudgetBreakdown is not null", breakdown);

    }

    private AnnualBudget createAnnualBudget() {
        return new AnnualBudget("Annual Budget", new BigDecimal("10000"), new Date());
    }

    private MonthlyBudget createMonthlyBudget() {
        return new MonthlyBudget("Monthly Budget", new BigDecimal("10000"), new Date());
    }

//    @Test
//    public void isBudgetBreakdownContainSumOfCoreItemsValue() {
//
//        BudgetBreakdown breakdown = calculateBudgetBreakdown.calculateBreakdown();
//        assertNotNull("BudgetBreakdown contains SumOfCoreItems", breakdown.getCoreItemsTotal());
//
//    }

}
