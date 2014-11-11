package test.java.budgetapp.budget.annual;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.form.BudgetFormData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Annual Budget Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/10/2014
 * @project BudgetApp
 */
public class AnnualBudgetTest {

    private Budget budget;
    private BudgetFormData budgetFormData;

    @Before
    public void setUp() {
        budget = new AnnualBudget("Description", new BigDecimal("2000"), new Date());
        budgetFormData = Mockito.mock(BudgetFormData.class);

        when(budgetFormData.getSalary()).thenReturn(new BigDecimal("2000"));
        when(budgetFormData.getSubmittedOn()).thenReturn(new Date());
    }

    @After
    public void tearDown() {
        budget = null;
        budgetFormData = null;
    }

    @Test
    public void assertThaMethodReturnIsNotNull() throws Exception {
        budget.buildBudget(budgetFormData);
        assertNotNull(budget);
    }

    @Test
    public void assertThatBudgetFormDataIsNotNull() {

        try {
            budget.buildBudget(null);
            fail("BudgetFormData is null.");
        } catch (Exception e) {
            assertEquals("BudgetFormData is null.", e.getMessage());
        }

    }

    // TODO - Test that all methods within BudgetFormData are executed and valid values have been set. (not null and contain a value)
    @Test
    public void ensureSalaryHasBeenCalledOnFormObject() throws Exception {

        budget.buildBudget(budgetFormData);
        verify(budgetFormData, times(2)).getSalary();

    }

    @Test
    public void assertBudgetContainsSalaryValue() throws Exception {

        budget.buildBudget(budgetFormData);
        assertNotNull(budget.getSalary());
    }

    @Test
    public void exceptionThrownIfSalaryIsNullWithinFormData() {

        when(budgetFormData.getSalary()).thenReturn(null);

        try {
            budget.buildBudget(budgetFormData);
            fail("Salary is null.");
        } catch (Exception e) {
            System.out.println("error thrown");
        }

    }

    @Test
    public void ensureSubmittedOnHasBeenCalledOnFormObject() throws Exception {

        budget.buildBudget(budgetFormData);
        verify(budgetFormData, times(2)).getSubmittedOn();

    }

    @Test
    public void assertBudgetContainsSubmittedOnValue() throws Exception {

        budget.buildBudget(budgetFormData);
        assertNotNull(budget.getSubmittedOn());

    }

    @Test
    public void exceptionThrownIfSubmittedOnIsNullWithinFormData() {

        when(budgetFormData.getSubmittedOn()).thenReturn(null);

        try {
            budget.buildBudget(budgetFormData);
            fail("SubmittedOn is null.");
        } catch (Exception e) {
            System.out.println("SubmittedOn is null");
        }

    }

    /*
        - List<CoreBudgetItem> (loop through all items and multiply by 12, before setting Budget object value)
        - List<SocialBudgetItem> (loop through all items and multiply by 12, before setting Budget object value)
     */

    // TODO - Test that a method is called to multiply given values in BudgetFormData by 12 (all values)
    // TODO - Ensure values are differ from FormObject to BudgetObject

}


