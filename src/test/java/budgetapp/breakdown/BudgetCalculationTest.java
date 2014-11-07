package test.java.budgetapp.breakdown;

import main.java.budgetapp.breakdown.BudgetBreakdown;
import main.java.budgetapp.breakdown.BudgetCalculation;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.monthly.MonthlyBudget;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

/**
 * Budget Calculation Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 07/11/2014
 * @project BudgetApp
 */
public class BudgetCalculationTest {

    // Todo - outline rules for calculations and ensure that the correct methods are being implemented and hit - times(1)  etc
    // TODO - Also validate that these objects are returning the correct return type and expected values.

    private BudgetCalculation budgetCalculation = new BudgetCalculation();

    private AnnualBudget annualBudget;
    private MonthlyBudget monthlyBudget;

    @Before
    public void setUp() {
        this.annualBudget =  new AnnualBudget(
                "Annual Budget", new BigDecimal("25000"), new Date());

        this.monthlyBudget = new MonthlyBudget(
                "Monthly Budget", new BigDecimal("25000"), new Date());
    }


    @Test
    public void assertBudgetBreakdownIsNotNull() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget,
                new BudgetBreakdown());

        assertTrue(budgetBreakdown != null);

    }

    @Test
    public void assertBudgetBreakdownTypeOfBudgetIsNotNull() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget,
                new BudgetBreakdown());


        assert(budgetBreakdown.getBudgetType() != null);

    }

    @Test
    public void assertBudgetBreakdownTypeOfBudgetIsEqualToAnnual() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget,
                new BudgetBreakdown());

        assert(budgetBreakdown.getBudgetType().equals("ANNUAL"));

    }

    @Test
    public void assertBudgetBreakdownTypeOfBudgetIsEqualToMonthly() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(monthlyBudget,
                new BudgetBreakdown());

        assert(budgetBreakdown.getBudgetType().equals("MONTHLY"));

    }

}
