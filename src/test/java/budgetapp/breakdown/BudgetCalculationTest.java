package test.java.budgetapp.breakdown;

import main.java.budgetapp.breakdown.BudgetBreakdown;
import main.java.budgetapp.breakdown.BudgetCalculation;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.items.CoreBudgetItem;
import main.java.budgetapp.budget.items.SocialBudgetItem;
import main.java.budgetapp.budget.monthly.MonthlyBudget;
import main.java.budgetapp.exceptions.BudgetItemsMissingException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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

    public static final String MONTHLY = "MONTHLY";
    public static final String ANNUAL = "ANNUAL";

    //  State-based Testing for BudgetCalculation
    private BudgetCalculation budgetCalculation = new BudgetCalculation();
    private BudgetBreakdown breakdown = new BudgetBreakdown();

    private AnnualBudget annualBudget;
    private MonthlyBudget monthlyBudget;

    private BigDecimal salary = new BigDecimal("1200");
    private BigDecimal totalAllBudgetItems = new BigDecimal(BigInteger.ZERO);

    private BigDecimal DIVISIBLE_BY_FOUR = new BigDecimal("4");
    private BigDecimal DIVISIBLE_BY_FIFTY_TWO = new BigDecimal("52");


    @Before
    public void setUp() {
        this.annualBudget =  new AnnualBudget(
                "Annual Budget", new BigDecimal("25000"), new Date());

        this.monthlyBudget = new MonthlyBudget(
                "Monthly Budget", new BigDecimal("25000"), new Date());
    }

    @After
    public void tearDown() {
        annualBudget = null;
        monthlyBudget = null;
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
        assertTrue("BudgetType is not equal to null.", budgetBreakdown.getBudgetType() != null);
    }

    @Test
    public void assertBudgetBreakdownTypeOfBudgetIsEqualToAnnual() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetTypeDescription(annualBudget,breakdown);
        assertEquals("BudgetType is equal to ANNUAL", budgetBreakdown.getBudgetType(), "ANNUAL");
    }

    @Test
    public void assertBudgetBreakdownTypeOfBudgetIsEqualToMonthly() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetTypeDescription(monthlyBudget,
                new BudgetBreakdown());

        assertEquals("BudgetType is equal to MONTHLY", budgetBreakdown.getBudgetType(), "MONTHLY");
    }

    @Test
    public void assertBudgetBreakdownTotalCoreBudgetValueIsNotNull() throws Exception {

        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
        assertTrue("TotalCoreBudget is not null.", budgetBreakdown.getTotalCoreBudget() != null);
    }

    @Test
    public void assertBudgetBreakdownTotalCoreBudgetValueIsEqualTo500() throws Exception {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem(anyString(), new BigDecimal("500")));

        annualBudget.setCoreBudgetItemList(coreBudgetItemList);
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
        assertEquals("TotalCoreBudget is equal to 500.",budgetBreakdown.getTotalCoreBudget(), new BigDecimal("500"));
    }

    @Test
    public void assertBudgetBreakdownTotalCoreBudgetValueIsEqualTo750() throws Exception {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem(anyString(), new BigDecimal("750")));

        annualBudget.setCoreBudgetItemList(coreBudgetItemList);
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        assertEquals(1, coreBudgetItemList.size());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
        assertEquals("TotalCoreBudget is equal to 750.", budgetBreakdown.getTotalCoreBudget(), new BigDecimal("750"));
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
        assertEquals("TotalCoreBudget is equal to 1000.", budgetBreakdown.getTotalCoreBudget(), new BigDecimal("1000"));
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
        assertEquals("TotalCoreBudget is equal to 1500.", budgetBreakdown.getTotalCoreBudget(), new BigDecimal("1500"));
    }

    @Test
    public void assertBudgetBreakdownTotalCoreBudgetValueIsZeroAsDefault() throws Exception {

        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
        assertEquals("TotalCoreBudget is equal to Zero.", budgetBreakdown.getTotalCoreBudget(),(new BigDecimal(BigInteger.ZERO)));
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
    public void assertBudgetItemsMissingExceptionThrownWhenTotalCoreBudgetListIsNull() throws Exception {

        try {
            budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
            fail("CoreBudgetItemList is null.");
        } catch(BudgetItemsMissingException e) {
            assertEquals("BudgetItemsMissingException error message is correct.", "BudgetItemList is null.", e.getMessage());
        }



    }

    @Test
    public void assertTotalSocialBudgetIsNotNull() throws Exception {

        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
        assertTrue("TotalSocialBudget is not null.", budgetBreakdown.getTotalSocialBudget() != null);
    }

    @Test
    public void assertTotalSocialBudgetValueIsEqualTo500() throws Exception {

        List<SocialBudgetItem> socialBudgetItemList = new ArrayList<SocialBudgetItem>();
        socialBudgetItemList.add(new SocialBudgetItem(new Date(), anyString(), new BigDecimal("500")));

        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(socialBudgetItemList);

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
        assertEquals("TotalSocialBudget is equal to 500.",budgetBreakdown.getTotalSocialBudget(), new BigDecimal("500"));
    }

    @Test
    public void assertTotalSocialBudgetValueIsEqualTo700() throws Exception {

        List<SocialBudgetItem> socialBudgetItemList = new ArrayList<SocialBudgetItem>();
        socialBudgetItemList.add(new SocialBudgetItem(new Date(), anyString(), new BigDecimal("700")));

        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(socialBudgetItemList);

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
        assertEquals("TotalSocialBudget is equal to 700.", budgetBreakdown.getTotalSocialBudget(), new BigDecimal("700"));
    }

    @Test
    public void assertBudgetItemsMissingExceptionThrownWhenSocialBudgetItemsListIsNull() throws Exception {

        try {
            annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
            budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
            fail("SocialBudgetItemList is null");
        } catch(BudgetItemsMissingException e) {
            assertEquals("BudgetItemsMissingException error message is correct.", "BudgetItemList is null.", e.getMessage());
        }

    }

    @Test
    public void assertBudgetItemsMissingExceptionMessageIsValid() throws Exception {

        try {
            annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
            budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
            fail("SocialBudgetItemList is null");
        } catch(BudgetItemsMissingException e) {
            assertEquals("BudgetItemsMissingException error message is correct.", "BudgetItemList is null.", e.getMessage());
        }

    }

    @Test
    public void assertTotalCoreBudgetIsZeroWhenCoreBudgetItemListContainsNullInvalidObject() throws Exception {

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem(anyString(), null));

        annualBudget.setCoreBudgetItemList(coreBudgetItemList);
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        assertEquals(1, coreBudgetItemList.size());

        BudgetBreakdown budgetBreakdown = budgetCalculation.processBudgetItemsCalculation(annualBudget, breakdown);
        assertEquals("TotalCoreBudget is equal to Zero.", budgetBreakdown.getTotalCoreBudget(), new BigDecimal("0"));
    }

    @Test
    public void assertBudgetBreakdownTotalAllBudgetItemsIsNotNull() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.calculateTotalAllBudgetItems(breakdown);
        assertTrue("TotalOfAllBudgetItems is not null.", budgetBreakdown.getTotalOfAllBudgetItems() != null);
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
        assertTrue("TotalOfAllBudgetItems is greater than 500.",budgetBreakdown.getTotalOfAllBudgetItems().intValue() > 500);
        assertEquals("TotalOfAllBudgetItems is equal to 1000.", new BigDecimal("1000"), budgetBreakdown.getTotalOfAllBudgetItems());
    }

    @Test
    public void assertBudgetBreakdownTotalBudgetOfAllItemsIsEqualTo1000() {

        breakdown.setTotalCoreBudget(new BigDecimal("500"));
        breakdown.setTotalSocialBudget(new BigDecimal("500"));

        BudgetBreakdown budgetBreakdown = budgetCalculation.calculateTotalAllBudgetItems(breakdown);
        assertEquals("TotalOfAllBudgetItems is equal to 1000", new BigDecimal("1000"), budgetBreakdown.getTotalOfAllBudgetItems());
    }

    @Test
    public void assertBudgetBreakdownTotalBudgetOfAllItemsIsEqualTo1200() {

        breakdown.setTotalCoreBudget(new BigDecimal("600"));
        breakdown.setTotalSocialBudget(new BigDecimal("600"));

        BudgetBreakdown budgetBreakdown = budgetCalculation.calculateTotalAllBudgetItems(breakdown);
        assertEquals("TotalOfAllBudgetItems is equal to 1200", new BigDecimal("1200"), budgetBreakdown.getTotalOfAllBudgetItems());
    }

    @Test
    public void assertBudgetBreakdownTotalMoneyAvailableIsNotNull() {

        breakdown.setBudgetType("MONTHLY");

        BigDecimal totalAllItems = new BigDecimal("1000");

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, totalAllItems);
        assertTrue("TotalMoneyAvailable is not null.", budgetBreakdown.getTotalMoneyAvailable() != null);

    }

    @Test
    public void assertBudgetBreakdownTotalMoneyAvailableIsEqualTo300() {

        breakdown.setBudgetType(MONTHLY);
        totalAllBudgetItems = new BigDecimal("1000");

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, totalAllBudgetItems);
        assertEquals("TotalMoneyAvailable is equal to 200.", new BigDecimal("200"), budgetBreakdown.getTotalMoneyAvailable());
    }

    @Test
    public void assertBudgetBreakdownTotalMoneyAvailableEntryValueIsDifferentToReturnedValue() {

        breakdown.setBudgetType(MONTHLY);
        totalAllBudgetItems = new BigDecimal("1000");

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, totalAllBudgetItems);

        assertFalse("TotalMoneyAvailable is not equal to Salary value.", budgetBreakdown.getTotalMoneyAvailable().equals(salary));
        assertFalse("TotalMoneyAvailable is not equal to TotalAllBudgetItems value.", budgetBreakdown.getTotalMoneyAvailable().equals(totalAllBudgetItems));
    }

    @Test
    public void assertBudgetBreakdownTotalMoneyAvailableIsEqualToTotalAllItemsMinusSalary() {

        breakdown.setBudgetType(MONTHLY);

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, new BigDecimal("800"));

        assertEquals("TotalMoneyAvailable is equal to expected value.", budgetBreakdown.getTotalMoneyAvailable(), new BigDecimal("400"));
    }

    @Test
    public void assertBudgetBreakDownTotalMoneyAvailableIsValidWhenSalaryInputIsNull() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                null, new BigDecimal("800"));

        assertTrue("TotalMoneyAvailable is not equal to null.", budgetBreakdown.getTotalMoneyAvailable() != null);
    }

    @Test
    public void assertBudgetBreakDownTotalMoneyAvailableEqualsDefaultValueZeroWhenSalaryInputIsNull() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                null, new BigDecimal("800"));

        assertEquals("TotalMoneyAvailable is equal to Zero.", budgetBreakdown.getTotalMoneyAvailable(), new BigDecimal(BigInteger.ZERO));
    }

    @Test
    public void assertBudgetTotalMoneyAvailIsDivisibleBy52IfBudgetIsOfTypeAnnualTestOne() {

        breakdown.setBudgetType(ANNUAL);

        salary = new BigDecimal("20000");
        totalAllBudgetItems = new BigDecimal("14000");

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, totalAllBudgetItems);

        System.out.println("TotalMoneyAvailableWeekly should equal 115.38.");

        assertThatWeeklyTotalIsEqualToBudgetBreakdownValue(calculateAnnualBudgetTotalWeeklyAvailable(salary, totalAllBudgetItems, DIVISIBLE_BY_FIFTY_TWO),
                budgetBreakdown.getTotalMoneyAvailableWeekly());
    }

    @Test
    public void assertBudgetTotalMoneyAvailIsDivisibleBy52IfBudgetIsOfTypeAnnualTestTwo() {

        breakdown.setBudgetType(ANNUAL);

        salary = new BigDecimal("25000");
        totalAllBudgetItems = new BigDecimal("12000");

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, totalAllBudgetItems);

        System.out.println("TotalMoneyAvailableWeekly should equal 250.00.");

        assertThatWeeklyTotalIsEqualToBudgetBreakdownValue(calculateAnnualBudgetTotalWeeklyAvailable(salary, totalAllBudgetItems, DIVISIBLE_BY_FIFTY_TWO),
                budgetBreakdown.getTotalMoneyAvailableWeekly());
    }

    @Test
    public void assertBudgetTotalMoneyAvailIsDivisibleBy52IfBudgetIsOfTypeAnnualTestThree() {

        breakdown.setBudgetType(ANNUAL);

        salary = new BigDecimal("32000");
        totalAllBudgetItems = new BigDecimal("15000");

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, totalAllBudgetItems);

        System.out.println("TotalMoneyAvailableWeekly should equal 326.92.");

        assertThatWeeklyTotalIsEqualToBudgetBreakdownValue(calculateAnnualBudgetTotalWeeklyAvailable(salary, totalAllBudgetItems, DIVISIBLE_BY_FIFTY_TWO),
                budgetBreakdown.getTotalMoneyAvailableWeekly());
    }

    @Test
    public void assertBudgetTotalMoneyAvailIsDivisibleBy4IfBudgetIsOfTypeMonthlyTestOne() {

        breakdown.setBudgetType(MONTHLY);

        salary = new BigDecimal("1800");
        totalAllBudgetItems = new BigDecimal("950");

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, totalAllBudgetItems);

        System.out.println("TotalMoneyAvailableWeekly should equal 212.50.");

        assertThatWeeklyTotalIsEqualToBudgetBreakdownValue(calculateAnnualBudgetTotalWeeklyAvailable(salary, totalAllBudgetItems, DIVISIBLE_BY_FOUR),
                budgetBreakdown.getTotalMoneyAvailableWeekly());

    }

    @Test
    public void assertBudgetTotalMoneyAvailIsDivisibleBy4IfBudgetIsOfTypeMonthlyTestTwo() {

        breakdown.setBudgetType(MONTHLY);

        salary = new BigDecimal("2500");
        totalAllBudgetItems = new BigDecimal("1480");

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                salary, totalAllBudgetItems);

        System.out.println("TotalMoneyAvailableWeekly should equal 255.00.");

        assertThatWeeklyTotalIsEqualToBudgetBreakdownValue(calculateAnnualBudgetTotalWeeklyAvailable(salary, totalAllBudgetItems, DIVISIBLE_BY_FOUR),
                budgetBreakdown.getTotalMoneyAvailableWeekly());

    }

    @Test
    public void assertTotalMoneyAvailableWeeklyIsNotEqualToNullWhenMethodIsCalledWithoutAValidSalaryInput() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                null, totalAllBudgetItems);

        assertTrue("TotalMoneyAvailableWeekly is not equal to null.", budgetBreakdown.getTotalMoneyAvailableWeekly() != null);
    }

    @Test
    public void assertTotalMoneyAvailableWeeklyIsEqualToZeroWhenMethodIsCalledWithoutAValidSalaryInput() {

        BudgetBreakdown budgetBreakdown = budgetCalculation.processTotalMoneyAvailableCalculation(breakdown,
                null, totalAllBudgetItems);

        Assert.assertEquals("TotalMoneyAvailableWeekly is equal to Zero.", new BigDecimal(BigInteger.ZERO), budgetBreakdown.getTotalMoneyAvailableWeekly());
    }

    private BigDecimal calculateAnnualBudgetTotalWeeklyAvailable(BigDecimal salary, BigDecimal totalBudgetItemSpend, BigDecimal divisibleBy) {
        return salary.subtract(totalBudgetItemSpend).divide(divisibleBy, 2, RoundingMode.HALF_UP);
    }

    private void assertThatWeeklyTotalIsEqualToBudgetBreakdownValue(BigDecimal expected, BigDecimal actual) {

        System.out.println("Expected Value: " + expected);
        System.out.println("Actual Value: " + actual);

        if(!actual.equals(expected)) {
            fail("TotalMoneyAvailableWeekly should be calculated dividing totalMoneyAvailable by 52 if budgetType is equal to Annual");
        } else {
            assertEquals("TotalMoneyAvailableWeekly is the expected value of " + expected, actual, expected);
        }

    }

}