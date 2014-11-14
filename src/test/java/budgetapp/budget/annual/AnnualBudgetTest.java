package test.java.budgetapp.budget.annual;

import main.java.budgetapp.budget.Budget;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.form.BudgetFormData;
import main.java.budgetapp.budget.items.CoreBudgetItem;
import main.java.budgetapp.budget.items.SocialBudgetItem;
import main.java.budgetapp.exceptions.BudgetItemsMissingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

    @Test
    public void ensureCoreBudgetItemsHasBeenCalledOnFormObject() throws Exception {
        budget.buildBudget(budgetFormData);
        verify(budgetFormData, times(2)).getCoreBudgetItemsList();
    }

    @Test
    public void exceptionThrownIfCoreBudgetItemListIsNullWithinFormData() throws Exception {

        when(budgetFormData.getCoreBudgetItemsList()).thenReturn(null);

        try {
            budget.buildBudget(budgetFormData);
            fail("CoreBudgetItemList is null.");
        } catch (Exception e) {
            System.out.println("CoreBudgetItemList is null.");
        }

    }

    /*
        - List<CoreBudgetItem> (loop through all items and multiply by 12, before setting Budget object value)
        - List<SocialBudgetItem> (loop through all items and multiply by 12, before setting Budget object value)

        - set value on the budgetFormData
        - assert that the same value within the budget object is different

    */

    // TODO - Test that a method is called to multiply given values in BudgetFormData by 12 (all values)
    // TODO - Ensure values are differ from FormObject to BudgetObject

    @Test
    public void ensureSalaryItemInBudgetContainsDifferentValueFormObjectTestOne() {

        when(budgetFormData.getSalary()).thenReturn(new BigDecimal("1000"));

        try {
            budget.buildBudget(budgetFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(budget.getSalary().intValue() != 1000);
    }

    @Test
    public void ensureSalaryItemInBudgetContainsDifferentValueFormObjectTestTwo() {

        when(budgetFormData.getSalary()).thenReturn(new BigDecimal("2500"));

        try {
            budget.buildBudget(budgetFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(budget.getSalary().intValue() != 2500);
    }

    @Test
    public void ensureSalaryValueWithinBudgetIs12TimesTheValueInFormObject() {

        when(budgetFormData.getSalary()).thenReturn(new BigDecimal("10"));

        try {
            budget.buildBudget(budgetFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(120, budget.getSalary().intValue());
    }

    @Test
    public void ensureCoreBudgetItemsInBudgetContainsDifferentFormObjectTestOne() {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem("Test Description", new BigDecimal("100")));

        when(budgetFormData.getCoreBudgetItemsList()).thenReturn(coreBudgetItemList);

        try {
            budget.buildBudget(budgetFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(budget.getCoreBudgetItemList().get(0).getItemMonetaryAmount().intValue() != 100);
    }

    @Test
    public void ensureCoreBudgetItemsInBudgetContainsDifferentFormObjectTestTwo() {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem("Test Description", new BigDecimal("200")));

        when(budgetFormData.getCoreBudgetItemsList()).thenReturn(coreBudgetItemList);

        try {
            budget.buildBudget(budgetFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(budget.getCoreBudgetItemList().get(0).getItemMonetaryAmount().intValue() != 200);

    }

    @Test
    public void ensureCoreBudgetItemValueWithinBudgetIs12TimesTheValueInFormObject() {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem("Test Description", new BigDecimal("100")));

        when(budgetFormData.getCoreBudgetItemsList()).thenReturn(coreBudgetItemList);

        try {
            budget.buildBudget(budgetFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(1200, budget.getCoreBudgetItemList().get(0).getItemMonetaryAmount().intValue());
    }

    @Test
    public void ensureCoreBudgetItemsInBudgetContainsDifferentValueFormObjectTestThree() {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem("Test Description", new BigDecimal("100")));
        coreBudgetItemList.add(new CoreBudgetItem("Test Description", new BigDecimal("200")));

        when(budgetFormData.getCoreBudgetItemsList()).thenReturn(coreBudgetItemList);

        try {
            budget.buildBudget(budgetFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(budget.getCoreBudgetItemList().get(0).getItemMonetaryAmount().intValue() != 100);
        assertTrue(budget.getCoreBudgetItemList().get(1).getItemMonetaryAmount().intValue() != 200);

    }


    @Test
    public void ensureMultipleCoreBudgetItemValueWithinBudgetIs12TimesTheValueInFormObject() {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem("Test Description", new BigDecimal("50")));
        coreBudgetItemList.add(new CoreBudgetItem("Test Description", new BigDecimal("100")));
        coreBudgetItemList.add(new CoreBudgetItem("Test Description", new BigDecimal("75")));

        when(budgetFormData.getCoreBudgetItemsList()).thenReturn(coreBudgetItemList);

        try {
            budget.buildBudget(budgetFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(600, budget.getCoreBudgetItemList().get(0).getItemMonetaryAmount().intValue());
        assertEquals(1200, budget.getCoreBudgetItemList().get(1).getItemMonetaryAmount().intValue());
        assertEquals(900, budget.getCoreBudgetItemList().get(2).getItemMonetaryAmount().intValue());
    }

    @Test
    public void ensureCoreBudgetItemWithNoMonetaryValueThrowsException() {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem("Test Description", null));

        when(budgetFormData.getCoreBudgetItemsList()).thenReturn(coreBudgetItemList);

        try {
            budget.buildBudget(budgetFormData);
            fail("BudgetItem monetaryAmount is null.");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "BudgetItem monetaryAmount is null.");
        }

    }

    @Test
    public void ensureBudgetObjectSocialBudgetItemsIsNotNull() throws Exception {
        budget.buildBudget(budgetFormData);
        assertTrue(budget.getSocialBudgetItemList() != null);
    }

    @Test
    public void ensureSocialBudgetItemsHasBeenCalledOnFormObject() throws Exception {
        budget.buildBudget(budgetFormData);
        verify(budgetFormData, times(2)).getSocialBudgetItemsList();
    }

    @Test
    public void exceptionThrownIfFormDataSocialBudgetItemsListIsNull() {

        when(budgetFormData.getSocialBudgetItemsList()).thenReturn(null);

        try {
            budget.buildBudget(budgetFormData);
            fail("SocialBudgetItemsList is null.");
        } catch (Exception e) {
            System.out.println("SocialBudgetItemsList is null.");
        }

    }

    @Test
    public void throwExceptionIfSocialBudgetItemsDoesNotContainAValidMonetaryAmountTestOne() throws Exception {

        List<SocialBudgetItem> socialBudgetItemsList = new ArrayList<SocialBudgetItem>();
        socialBudgetItemsList.add(new SocialBudgetItem(new Date(), "Test Description #1", null));

        when(budgetFormData.getSocialBudgetItemsList()).thenReturn(socialBudgetItemsList);

        try {
            budget.buildBudget(budgetFormData);
            fail("BudgetItem monetaryAmount is null.");
        } catch (BudgetItemsMissingException e) {
            assertEquals("BudgetItem monetaryAmount is null.", e.getMessage());
        }

    }

    @Test
    public void throwExceptionIfSocialBudgetItemsDoesNotContainAValidMonetaryAmountTestTwo() throws Exception {

        List<SocialBudgetItem> socialBudgetItemsList = new ArrayList<SocialBudgetItem>();
        socialBudgetItemsList.add(new SocialBudgetItem(new Date(), "Test Description #1", new BigDecimal("100")));
        socialBudgetItemsList.add(new SocialBudgetItem(new Date(), "Test Description #1", null));

        when(budgetFormData.getSocialBudgetItemsList()).thenReturn(socialBudgetItemsList);

        try {
            budget.buildBudget(budgetFormData);
            fail("BudgetItem monetaryAmount is null.");
        } catch (BudgetItemsMissingException e) {
            assertEquals("BudgetItem monetaryAmount is null.", e.getMessage());
        }

    }

    @Test
    public void ensureSocialBudgetItemsInBudgetContainsDifferentValueFormObjectTestOne() throws Exception {

        List<SocialBudgetItem> socialBudgetItemsList = new ArrayList<SocialBudgetItem>();
        socialBudgetItemsList.add(new SocialBudgetItem(new Date(), "Test Description #1", new BigDecimal("100")));

        when(budgetFormData.getSocialBudgetItemsList()).thenReturn(socialBudgetItemsList);

        budget.buildBudget(budgetFormData);
        assertTrue(budget.getSocialBudgetItemList().get(0).getItemMonetaryAmount().intValue() != 100);
    }


    @Test
    public void ensureSoreBudgetItemValueWithinBudgetIs12TimesTheValueInFormObjectTestOne() throws Exception {

        List<SocialBudgetItem> socialBudgetItemsList = new ArrayList<SocialBudgetItem>();
        socialBudgetItemsList.add(new SocialBudgetItem(new Date(), "Test Description #1", new BigDecimal("100")));

        when(budgetFormData.getSocialBudgetItemsList()).thenReturn(socialBudgetItemsList);

        budget.buildBudget(budgetFormData);
        assertEquals(1200, budget.getSocialBudgetItemList().get(0).getItemMonetaryAmount().intValue());

    }

    @Test
    public void ensureSoreBudgetItemValueWithinBudgetIs12TimesTheValueInFormObjectTestTwo() throws Exception {

        List<SocialBudgetItem> socialBudgetItemsList = new ArrayList<SocialBudgetItem>();
        socialBudgetItemsList.add(new SocialBudgetItem(new Date(), "Test Description #1", new BigDecimal("200")));

        when(budgetFormData.getSocialBudgetItemsList()).thenReturn(socialBudgetItemsList);

        budget.buildBudget(budgetFormData);
        assertEquals(2400, budget.getSocialBudgetItemList().get(0).getItemMonetaryAmount().intValue());

    }



}


