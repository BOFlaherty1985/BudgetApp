package test.java.budgetapp.breakdown;

import main.java.budgetapp.breakdown.BudgetBreakdown;
import main.java.budgetapp.breakdown.BudgetCalculation;
import main.java.budgetapp.breakdown.CalculateBudgetBreakdown;
import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.items.CoreBudgetItem;
import main.java.budgetapp.budget.items.SocialBudgetItem;
import main.java.budgetapp.budget.monthly.MonthlyBudget;
import main.java.budgetapp.exceptions.SalaryNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Calculate Budget Breakdown Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/10/2014
 * @project BudgetApp
 */
public class CalculateBudgetBreakdownTest {

    private CalculateBudgetBreakdown calculateBudgetBreakdown = new CalculateBudgetBreakdown();

    private BudgetCalculation budgetCalculation;
    private BudgetBreakdown budgetBreakdown;

    @Before
    public void setup() {
        this.budgetCalculation = Mockito.mock(BudgetCalculation.class);
        this.budgetBreakdown = Mockito.mock(BudgetBreakdown.class);

        calculateBudgetBreakdown.setBudgetCalculation(budgetCalculation);
    }

    @After
    public void tearDown() {
        budgetCalculation = null;
        budgetBreakdown = null;
    }

    @Test
    public void validateBudgetObjectAndThrowExceptionIfNull() {

        try {
            calculateBudgetBreakdown.calculateBreakdown(null, budgetBreakdown);
            fail("Budget Object Is Null.");
        } catch(Exception e) {
            assertEquals("Budget is null.", e.getMessage());
        }

    }

    @Test
    public void validateBudgetBreakdownThrowExceptionIfNull() {

        Budget budget = createAnnualBudget("Annual Budget", new BigDecimal("2000"));

        try {
            calculateBudgetBreakdown.calculateBreakdown(budget, null);
            fail("BudgetBreakdown Object Is Null.");
        } catch(Exception e) {
            assertEquals("BudgetBreakdown is null.", e.getMessage());
        }

    }

    @Test
    public void validateBudgetContainsSalaryValue() throws Exception {
        Budget budget = createAnnualBudget("Annual Budget", null);

        try {
            calculateBudgetBreakdown.calculateBreakdown(budget, budgetBreakdown);
            fail("Salary value is missing from budget.");
        } catch (SalaryNotFoundException e) {
            assertEquals("Salary value is missing from budget.", e.getMessage());
        }

    }

    @Test
    public void ensureBudgetCalculationTypeOfBudgetMethodIsTriggered() throws Exception {

        Budget budget = createAnnualBudget("Annual Budget", new BigDecimal("2000"));
        defaultBudgetItemLists(budget);

        calculateBudgetBreakdown.calculateBreakdown(budget, budgetBreakdown);
        verify(budgetCalculation, times(1)).processBudgetTypeDescription(budget, budgetBreakdown);
    }

    @Test
    public void assertBudgetTypeDescriptionIsEqualToAnnual() throws Exception {

        Budget budget = createAnnualBudget("Annual Budget", new BigDecimal("2000"));
        defaultBudgetItemLists(budget);

        when(budgetCalculation.processBudgetTypeDescription(budget, budgetBreakdown)).thenReturn(budgetBreakdown);
        when(budgetBreakdown.getBudgetType()).thenReturn("ANNUAL");

        BudgetBreakdown result = calculateBudgetBreakdown.calculateBreakdown(budget, budgetBreakdown);
        verify(budgetCalculation, times(1)).processBudgetTypeDescription(budget, budgetBreakdown);
        assertEquals("ANNUAL", result.getBudgetType());
        assertFalse(result.getBudgetType() == null);
    }

    @Test
    public void ensureBudgetCalculationBudgetItemsCalculationIsTriggered() throws Exception {

        Budget budget = createAnnualBudget("Annual Budget", new BigDecimal("2000"));
        defaultBudgetItemLists(budget);

        calculateBudgetBreakdown.calculateBreakdown(budget, budgetBreakdown);
        verify(budgetCalculation, times(1)).processBudgetItemsCalculation(budget, budgetBreakdown);
    }

    @Test
    public void assertBudgetCalculationCoreBudgetItemsTotalsIsNotNull() throws Exception {

        Budget budget = createAnnualBudget("Annual Budget", new BigDecimal("2000"));
        defaultBudgetItemLists(budget);

        when(budgetCalculation.processBudgetItemsCalculation(budget, budgetBreakdown)).thenReturn(budgetBreakdown);
        when(budgetBreakdown.getTotalCoreBudget()).thenReturn(new BigDecimal("750"));

        BudgetBreakdown result = calculateBudgetBreakdown.calculateBreakdown(budget, budgetBreakdown);
        assertNotNull(result.getTotalCoreBudget());
    }

    @Test
    public void assertBudgetCalculationSocialBudgetItemsTotalsIsNotNull() throws Exception {

        Budget budget = createAnnualBudget("Annual Budget", new BigDecimal("2000"));
        defaultBudgetItemLists(budget);

        when(budgetCalculation.processBudgetItemsCalculation(budget, budgetBreakdown)).thenReturn(budgetBreakdown);
        when(budgetBreakdown.getTotalSocialBudget()).thenReturn(new BigDecimal("400"));

        BudgetBreakdown result = calculateBudgetBreakdown.calculateBreakdown(budget, budgetBreakdown);
        assertNotNull(result.getTotalSocialBudget());
    }

    @Test
    public void ensureBudgetCalculationTotalAllBudgetItemsCalculationTriggered() throws Exception {

        Budget budget = createAnnualBudget("Annual Budget", new BigDecimal("2000"));
        defaultBudgetItemLists(budget);

        calculateBudgetBreakdown.calculateBreakdown(budget, budgetBreakdown);
        verify(budgetCalculation, times(1)).calculateTotalAllBudgetItems(budgetBreakdown);
    }

    @Test
    public void assertBudgetCalculationTotalAllBudgetItemsIsNotNull() throws Exception {

        Budget budget = createAnnualBudget("Annual Budget", new BigDecimal("2000"));
        defaultBudgetItemLists(budget);

        when(budgetCalculation.calculateTotalAllBudgetItems(budgetBreakdown)).thenReturn(budgetBreakdown);
        when(budgetBreakdown.getTotalOfAllBudgetItems()).thenReturn(new BigDecimal("1000"));

        BudgetBreakdown result = calculateBudgetBreakdown.calculateBreakdown(budget, budgetBreakdown);
        assertNotNull(result.getTotalOfAllBudgetItems());
    }

    @Test
    public void ensureBudgetCalculationTotalMoneyAvailableCalculationIsTriggered() throws Exception {

        Budget budget = createAnnualBudget("Annual Budget", new BigDecimal("2000"));
        defaultBudgetItemLists(budget);

        when(budgetBreakdown.getTotalOfAllBudgetItems()).thenReturn(new BigDecimal("1000"));

        calculateBudgetBreakdown.calculateBreakdown(budget, budgetBreakdown);
        verify(budgetCalculation, times(1)).processTotalMoneyAvailableCalculation(budgetBreakdown, budget.getSalary(),
               budgetBreakdown.getTotalOfAllBudgetItems());
    }

    @Test
    public void assertBudgetCalculationTotalMoneyAvailableIsNotNull() {

        Budget budget = createAnnualBudget("Annual Budget", new BigDecimal("2000"));
        defaultBudgetItemLists(budget);

    }

    // testing specific configuration methods.
    private AnnualBudget createAnnualBudget(String description, BigDecimal salary) {
        return new AnnualBudget(description, salary, new Date());
    }

    private MonthlyBudget createMonthlyBudget() {
        return new MonthlyBudget("Monthly Budget", new BigDecimal("10000"), new Date());
    }

    private void defaultBudgetItemLists(Budget budget) {
        budget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        budget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());
    }

}
