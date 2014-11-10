package test.java.budgetapp.breakdown;

import main.java.budgetapp.breakdown.BudgetBreakdown;
import main.java.budgetapp.breakdown.BudgetCalculation;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.items.CoreBudgetItem;
import main.java.budgetapp.budget.items.SocialBudgetItem;
import main.java.budgetapp.budget.monthly.MonthlyBudget;
import main.java.budgetapp.exceptions.BudgetItemsMissingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;

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
    private BudgetBreakdown breakdown = new BudgetBreakdown();

    private AnnualBudget annualBudget;
    private MonthlyBudget monthlyBudget;

    private BigDecimal salary = new BigDecimal("1200");

    @Before
    public void setUp() {
        this.annualBudget =  new AnnualBudget(
                "Annual Budget", new BigDecimal("25000"), new Date());

        this.monthlyBudget = new MonthlyBudget(
                "Monthly Budget", new BigDecimal("25000"), new Date());
    }


    @Test
    public void assertBudgetBreakdownIsNotNull() throws Exception {

        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget,breakdown);

        assertTrue(budgetBreakdown != null);

    }

    @Test
    public void assertBudgetBreakdownTypeOfBudgetIsNotNull() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetTypeDescription(annualBudget,breakdown);

        assert(budgetBreakdown.getBudgetType() != null);

    }

    @Test
    public void assertBudgetBreakdownTypeOfBudgetIsEqualToAnnual() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetTypeDescription(annualBudget,breakdown);

        assert(budgetBreakdown.getBudgetType().equals("ANNUAL"));

    }

    @Test
    public void assertBudgetBreakdownTypeOfBudgetIsEqualToMonthly() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetTypeDescription(monthlyBudget,
                new BudgetBreakdown());

        assert(budgetBreakdown.getBudgetType().equals("MONTHLY"));

    }

    @Test
    public void assertBudgetBreakdownTotalCoreBudgetValueIsNotNull() throws Exception {

        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);

        assert(budgetBreakdown.getTotalCoreBudget() != null);

    }

    @Test
    public void assertBudgetBreakdownTotalCoreBudgetValueIsEqualTo500() throws Exception {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem(anyString(), new BigDecimal("500")));

        annualBudget.setCoreBudgetItemList(coreBudgetItemList);
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);

        assert(budgetBreakdown.getTotalCoreBudget().equals(new BigDecimal("500")));

    }


    @Test
    public void assertBudgetBreakdownTotalCoreBudgetValueIsEqualTo750() throws Exception {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem(anyString(), new BigDecimal("750")));

        annualBudget.setCoreBudgetItemList(coreBudgetItemList);
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        assertEquals(1, coreBudgetItemList.size());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);

        assert(budgetBreakdown.getTotalCoreBudget().equals(new BigDecimal("750")));

    }

    @Test
    public void assertBudgetBreakdownTotalCoreBudgetValueIsEqualTo1000() throws Exception {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem(anyString(), new BigDecimal("750")));
        coreBudgetItemList.add(new CoreBudgetItem(anyString(), new BigDecimal("250")));

        annualBudget.setCoreBudgetItemList(coreBudgetItemList);
        assertEquals(2, coreBudgetItemList.size());

        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);

        assert(budgetBreakdown.getTotalCoreBudget().equals(new BigDecimal("1000")));
    }


    @Test
    public void assertBudgetBreakdownTotalCoreBudgetValueIsEqualTo1500() throws Exception {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem(anyString(), new BigDecimal("750")));
        coreBudgetItemList.add(new CoreBudgetItem(anyString(), new BigDecimal("250")));
        coreBudgetItemList.add(new CoreBudgetItem(anyString(), new BigDecimal("500")));

        annualBudget.setCoreBudgetItemList(coreBudgetItemList);
        assertEquals(3, coreBudgetItemList.size());

        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);

        assert(budgetBreakdown.getTotalCoreBudget().equals(new BigDecimal("1500")));
    }


    @Test
    public void assertBudgetBreakdownTotalCoreBudgetValueIsZeroAsDefault() throws Exception {

        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);

        assert(budgetBreakdown.getTotalCoreBudget().equals(new BigDecimal(BigInteger.ZERO)));

    }

    @Test
    public void assertBudgetBreakdownTotalCoreBudgetExceptionThrownWhenListIsNull() {

        try {
           budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
           fail("CoreBudgetItemList is null.");
        } catch(Exception e) {
            System.out.println("CoreBudgetItemList is null.");
        }

    }

    @Test
    public void assertCoreBudgetItemListIsNullExceptionMessageIsValid() {

        try {
            budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
            fail("CoreBudgetItemList is null.");
        } catch(Exception e) {
            assertEquals("BudgetItemList is null.", e.getMessage());
        }

    }


    @Test
    public void assertBudgetItemsMissingExceptionThrownWhenTotalCoreBudgetListIsNull() throws Exception {

        try {
            budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
            fail("CoreBudgetItemList is null.");
        } catch(BudgetItemsMissingException e) {
            assertEquals("BudgetItemList is null.", e.getMessage());
        }

    }


    @Test
    public void assertTotalSocialBudgetIsNotNull() throws Exception {

        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);

        assertTrue(budgetBreakdown.getTotalSocialBudget() != null);
    }

    @Test
    public void assertTotalSocialBudgetValueIsEqualTo500() throws Exception {

        List<SocialBudgetItem> socialBudgetItemList = new ArrayList<SocialBudgetItem>();
        socialBudgetItemList.add(new SocialBudgetItem(new Date(), anyString(), new BigDecimal("500")));

        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(socialBudgetItemList);

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);

        assertEquals(budgetBreakdown.getTotalSocialBudget(), new BigDecimal("500"));

    }

    @Test
    public void assertTotalSocialBudgetValueIsEqualTo700() throws Exception {

        List<SocialBudgetItem> socialBudgetItemList = new ArrayList<SocialBudgetItem>();
        socialBudgetItemList.add(new SocialBudgetItem(new Date(), anyString(), new BigDecimal("700")));

        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(socialBudgetItemList);

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);

        assertEquals(budgetBreakdown.getTotalSocialBudget(), new BigDecimal("700"));

    }

    @Test
    public void assertBudgetItemsMissingExceptionThrownWhenSocialBudgetItemsListIsNull() throws Exception {

        try {
            annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
            budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
            fail("SocialBudgetItemList is null");
        } catch(BudgetItemsMissingException e) {
            System.out.println("exception thrown");
        }

    }

    @Test
    public void assertBudgetItemsMissingExceptionMessageIsValid() throws Exception {

        try {
            annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
            budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
            fail("SocialBudgetItemList is null");
        } catch(BudgetItemsMissingException e) {
            Assert.assertEquals("BudgetItemList is null.", e.getMessage());
        }

    }

    @Test
    public void assertCoreBudgetItemListContainsNullInvalidObject() throws Exception {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem(anyString(), null));

        annualBudget.setCoreBudgetItemList(coreBudgetItemList);
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        assertEquals(1, coreBudgetItemList.size());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);

        assert(budgetBreakdown.getTotalCoreBudget().equals(new BigDecimal("0")));

    }

    @Test
    public void assertBudgetBreakdownTotalAllBudgetItemsIsNotNull() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.calculateTotalAllBudgetItems(breakdown);
        assertTrue(budgetBreakdown.getTotalOfAllBudgetItems() != null);

    }

    @Test
    public void assertBudgetBreakdownTotalMoneyAvailableIsGreaterThanCombinedTotalOfBudgetItemsTestOne() {

        breakdown.setTotalCoreBudget(new BigDecimal("100"));
        breakdown.setTotalSocialBudget(new BigDecimal("200"));

        BudgetBreakdown budgetBreakdown = budgetCalculation.calculateTotalAllBudgetItems(breakdown);
        assertTrue(budgetBreakdown.getTotalOfAllBudgetItems().intValue() > 100);
        assertTrue(budgetBreakdown.getTotalOfAllBudgetItems().intValue() > 200);
        assertEquals(budgetBreakdown.getTotalOfAllBudgetItems(), new BigDecimal("300"));
    }


    @Test
    public void assertBudgetBreakdownTotalBudgetOfAllItemsIsGreaterThanCombinedTotalOfBudgetItemsTestTwo() {

        breakdown.setTotalCoreBudget(new BigDecimal("500"));
        breakdown.setTotalSocialBudget(new BigDecimal("500"));

        BudgetBreakdown budgetBreakdown = budgetCalculation.calculateTotalAllBudgetItems(breakdown);
        assertTrue(budgetBreakdown.getTotalOfAllBudgetItems().intValue() > 500);
        assertEquals(budgetBreakdown.getTotalOfAllBudgetItems(), new BigDecimal("1000"));
    }

    @Test
    public void assertBudgetBreakdownTotalBudgetOfAllItemsIsEqualTo1000() {

        breakdown.setTotalCoreBudget(new BigDecimal("500"));
        breakdown.setTotalSocialBudget(new BigDecimal("500"));

        BudgetBreakdown budgetBreakdown = budgetCalculation.calculateTotalAllBudgetItems(breakdown);
        assertEquals(new BigDecimal("1000"), budgetBreakdown.getTotalOfAllBudgetItems());

    }

    @Test
    public void assertBudgetBreakdownTotalBudgetOfAllItemsIsEqualTo1200() {

        breakdown.setTotalCoreBudget(new BigDecimal("600"));
        breakdown.setTotalSocialBudget(new BigDecimal("600"));

        BudgetBreakdown budgetBreakdown = budgetCalculation.calculateTotalAllBudgetItems(breakdown);
        assertEquals(new BigDecimal("1200"), budgetBreakdown.getTotalOfAllBudgetItems());

    }


    @Test
    public void assertBudgetBreakdownTotalMoneyAvailableIsNotNull() {

        BigDecimal totalAllItems = new BigDecimal("1000");

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, totalAllItems);
        assertTrue(budgetBreakdown.getTotalMoneyAvailable() != null);

    }

    @Test
    public void assertBudgetBreakdownTotalMoneyAvailableIsEqualTo300() {

        BigDecimal totalAllItems = new BigDecimal("1000");

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, totalAllItems);
        assertEquals(new BigDecimal("200"), budgetBreakdown.getTotalMoneyAvailable());

    }

    @Test
    public void assertBudgetBreakdownTotalMoneyAvailableEntryValueIsDifferentToReturnedValue() {

        BigDecimal totalAllItems = new BigDecimal("1000");

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, totalAllItems);

        assertFalse(budgetBreakdown.getTotalMoneyAvailable().equals(salary));
        assertFalse(budgetBreakdown.getTotalMoneyAvailable().equals(totalAllItems));

    }

    @Test
    public void assertBudgetBreakdownTotalMoneyAvailableIsEqualToTotalAllItemsMinusSalary() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, new BigDecimal("800"));

        assertEquals(budgetBreakdown.getTotalMoneyAvailable(), new BigDecimal("400"));
    }

    @Test
    public void assertBudgetBreakDownTotalMoneyAvailableIsValidWhenSalaryInputIsNull() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                null, new BigDecimal("800"));

        assertTrue(budgetBreakdown.getTotalMoneyAvailable() != null);
    }

    @Test
    public void assertBudgetBreakDownTotalMoneyAvailableEqualsDefaultValueZeroWhenSalaryInputIsNull() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                null, new BigDecimal("800"));

        assertEquals(budgetBreakdown.getTotalMoneyAvailable(), new BigDecimal(BigInteger.ZERO));
    }

}
