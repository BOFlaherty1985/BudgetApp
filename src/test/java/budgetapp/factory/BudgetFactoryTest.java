package test.java.budgetapp.factory;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.monthly.MonthlyBudget;
import main.java.budgetapp.exceptions.InvalidBudgetTypeException;
import main.java.budgetapp.factory.BudgetFactory;
import main.java.budgetapp.factory.CreateBudget;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Budget Factory Method Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 29/10/2014
 * @project BudgetApp
 */
public class BudgetFactoryTest {

    private BudgetFactory budgetFactory = new CreateBudget();

    private static String ANNUAL_BUDGET = "ANNUAL";
    private static String MONTHLY_BUDGET = "MONTHLY";

    @Test
    public void testAnnualFactoryObjectIsCreated() throws Exception {

        Budget budgetObj = budgetFactory.requestBudgetByType(ANNUAL_BUDGET);
        assert(budgetObj instanceof AnnualBudget);

    }

    @Test
    public void testMonthlyFactoryObjectIsCreated() throws Exception {

        Budget budgetObj = budgetFactory.requestBudgetByType(MONTHLY_BUDGET);
        assert(budgetObj instanceof MonthlyBudget);

    }

    @Test
    public void assertAnnualBudgetObjectIsCreatedWithInitialSalaryValueOfZero() throws Exception {

        Budget annualBudget = budgetFactory.requestBudgetByType(ANNUAL_BUDGET);
        assertEquals(annualBudget.getSalary(), new BigDecimal(BigInteger.ZERO));

    }

    @Test
    public void assertMonthlyBudgetObjectIsCreatedWithInitialSalaryValueOfZero() throws Exception {

        Budget monthlyBudget = budgetFactory.requestBudgetByType(MONTHLY_BUDGET);
        assertEquals(monthlyBudget.getSalary(), new BigDecimal(BigInteger.ZERO));

    }

    @Test
    public void throwInvalidBudgetTypeExceptionWhenBudgetTypeIsInValidTestOne() throws Exception {

        try {
            budgetFactory.requestBudgetByType("INVALID");
            fail("Budget Type is not valid.");
        } catch (InvalidBudgetTypeException e) {
            assertEquals("Invalid Budget Type.", e.getMessage());
        }

    }

    @Test
    public void throwInvalidBudgetTypeExceptionWhenBudgetTypeIsInValidTestTwo() throws Exception {

        try {
            budgetFactory.requestBudgetByType("ERROR");
            fail("Budget Type is not valid.");
        } catch (InvalidBudgetTypeException e) {
            assertEquals("Invalid Budget Type.", e.getMessage());
        }

    }

    @Test
    public void throwInvalidBudgetTypeExceptionWhenBudgetTypeIsNotEqualToAnnualOrMonthlyBudget() throws Exception {

        try {
            budgetFactory.requestBudgetByType("TEST");
            fail("Budget Type is not valid.");
        } catch (InvalidBudgetTypeException e) {
            assertEquals("Invalid Budget Type.", e.getMessage());
        }

    }

}
