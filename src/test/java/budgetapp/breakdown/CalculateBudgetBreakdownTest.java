package test.java.budgetapp.breakdown;

import main.java.budgetapp.breakdown.BudgetBreakdown;
import main.java.budgetapp.breakdown.CalculateBudgetBreakdown;
import main.java.budgetapp.budget.annual.AnnualBudget;
import main.java.budgetapp.budget.items.CoreBudgetItem;
import main.java.budgetapp.budget.items.SocialBudgetItem;
import main.java.budgetapp.budget.monthly.MonthlyBudget;
import main.java.budgetapp.exceptions.BudgetItemsMissingException;
import main.java.budgetapp.exceptions.SalaryNotFoundException;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Calculate Budget Breakdown Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/10/2014
 * @project BudgetApp
 */
public class CalculateBudgetBreakdownTest {

    private CalculateBudgetBreakdown calculateBudgetBreakdown = new CalculateBudgetBreakdown();

    // TODO - Start of Class
    // Ensure that the Budget object's data is complete and valid, throw exceptions if this is not the case.

    // Test that an object of BudgeBreakdown is returned from the method, with a number of calculations
    @Test
    public void isBudgetBreakdownNotNull() throws Exception {

        AnnualBudget annualBudget = createAnnualBudget("Annual Budget", new BigDecimal("20000"));
        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown breakdown = calculateBudgetBreakdown.calculateBreakdown(annualBudget);
        assertNotNull("BudgetBreakdown is not null", breakdown);

    }

    // Test that a Budget object (annual or monthly) is passed to the class CalculateBudgetBreakdown
    @Test
    public void isAnnualBudgetItemAcceptedInCalculateBudgetMethod() throws Exception {

        AnnualBudget annualBudget = createAnnualBudget("Annual Budget", new BigDecimal("20000"));
        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown breakdown = calculateBudgetBreakdown.calculateBreakdown(annualBudget);
        assertEquals("BudgetBreakdown budgetType equals ANNUAL", breakdown.getBudgetType(), "ANNUAL");
    }

    @Test
    public void isMonthlyBudgetItemAcceptedInCalculateBudgetMethod() throws Exception {

        MonthlyBudget monthlyBudget = createMonthlyBudget();
        monthlyBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        monthlyBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());


        BudgetBreakdown breakdown = calculateBudgetBreakdown.calculateBreakdown(monthlyBudget);
        assertEquals("BudgetBreakdown budgetType equals MONTHLY", breakdown.getBudgetType(), "MONTHLY");

    }

    /*
        Validate incoming budget item to ensure that the necessary data is contained within the object

        - Test that the the object contains a Salary value, throw SalaryNotFoundException.
        - Test that the list of CoreBudgetItems is not empty or null, throw BudgetItemsEmptyException.
        - Test that the list of SocialBudgetItems is not empty or null, throw BudgetItemsEmptyException.
    */

    @Test
    public void budgetDoesNotContainAValidSalaryValue() throws Exception {

        // salary value is null
        AnnualBudget annualBudget = createAnnualBudget("Annual Budget", null);

        try {
            calculateBudgetBreakdown.calculateBreakdown(annualBudget);
            fail("Budget does not contain a Salary value");
        } catch(SalaryNotFoundException e) {
            e.printStackTrace();
            assertEquals("Salary error message is correct", e.getMessage(), "Budget is missing a value for Salary.");
        }

    }

    @Test
    public void coreBudgetItemsListIsNullWithinBudgetObject() throws Exception {

        MonthlyBudget monthlyBudget = createMonthlyBudget();

        try {
            calculateBudgetBreakdown.calculateBreakdown(monthlyBudget);
            fail("Budget does not contain any coreBudgetItems.");
        } catch (BudgetItemsMissingException e) {
            assertEquals("Core Budget Items is null", e.getMessage(), "budgetItemList within budget is null");
        }

    }

    @Test
    public void socialBudgetItemsListIsNullWithinBudgetObject() throws Exception {

        MonthlyBudget monthlyBudget = createMonthlyBudget();
        monthlyBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());

        try {
            calculateBudgetBreakdown.calculateBreakdown(monthlyBudget);
            fail("Budget does not contain any socialBudgetItems.");
        } catch(BudgetItemsMissingException e) {
            assertEquals("Social Budget Items is null", e.getMessage(), "budgetItemList within budget is null");
        }

    }

     /*
        // TODO - Class behavior
        // A separate method is to be created to complete each calculation (action), ensure that these methods are hit if required.

        Loop through all coreBudgetItems and add each monetary amount to an overall total variable, called totalCoreBudgetItems

        Test 1
        - Test that totalCoreBudgetItems is not null and has a value, totalCoreBudgetItems must equal 0.

        Test 2
        - Mock BudgetCoreBudgetItems input.
        - Ensure that the result of totalCoreBudgetItems is 800.

        Repeat process for socialBudgetItems (change values)

     */

    @Test
    public void isTotalCoreBudgetNotNull() throws Exception {

        AnnualBudget annualBudget = createAnnualBudget("Annual Budget", new BigDecimal("50000"));
        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = calculateBudgetBreakdown.calculateBreakdown(annualBudget);

        assertNotNull("totalCoreBudget is not null.", budgetBreakdown.getTotalCoreBudget());
    }

    @Test
    public void totalCoreBudgetIsEqualToDefaultValueOfZero() throws Exception {

        AnnualBudget annualBudget = createAnnualBudget("Annual Budget", new BigDecimal("50000"));
        annualBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = calculateBudgetBreakdown.calculateBreakdown(annualBudget);

        assertEquals("totalCoreBudget is not equal to default value of ZERO.", budgetBreakdown.getTotalCoreBudget(),
                new BigDecimal(BigInteger.ZERO));

    }

    @Test
    public void totalCoreBudgetIsEqualTo800() throws Exception {

        AnnualBudget annualBudget = createAnnualBudget("Annual Budget", new BigDecimal("50000"));

        List<CoreBudgetItem> coreBudgetItemList = new ArrayList<CoreBudgetItem>();
        coreBudgetItemList.add(new CoreBudgetItem("Core Item #1", new BigDecimal("800")));

        annualBudget.setCoreBudgetItemList(coreBudgetItemList);
        annualBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = calculateBudgetBreakdown.calculateBreakdown(annualBudget);

        assertEquals("totalCoreBudget is not equal to 800.", budgetBreakdown.getTotalCoreBudget(),
                new BigDecimal("800"));

    }

    @Test
    public void isTotalSocialBudgetNotNull() throws Exception {

        MonthlyBudget monthlyBudget = createMonthlyBudget();
        monthlyBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        monthlyBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = calculateBudgetBreakdown.calculateBreakdown(monthlyBudget);

        assertNotNull("totalSocialBudget is not null", budgetBreakdown.getTotalSocialBudget());

    }

    @Test
    public void totalSocialBudgetIsEqualToDefaultValueOfZero() throws Exception {

        MonthlyBudget monthlyBudget = createMonthlyBudget();
        monthlyBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());
        monthlyBudget.setSocialBudgetItemList(new ArrayList<SocialBudgetItem>());

        BudgetBreakdown budgetBreakdown = calculateBudgetBreakdown.calculateBreakdown(monthlyBudget);

        assertEquals("totalSocialBudget is not equal to Zero.", budgetBreakdown.getTotalSocialBudget(),
                new BigDecimal(BigInteger.ZERO));

    }

    @Test
    public void totalSocialBudgetIsEqualTo500() throws Exception {

        MonthlyBudget monthlyBudget = createMonthlyBudget();
        monthlyBudget.setCoreBudgetItemList(new ArrayList<CoreBudgetItem>());

        List<SocialBudgetItem> socialBudgetItemList = new ArrayList<SocialBudgetItem>();
        socialBudgetItemList.add(new SocialBudgetItem(new Date(), "Social Item #1", new BigDecimal("250")));
        socialBudgetItemList.add(new SocialBudgetItem(new Date(), "Social Item #2", new BigDecimal("250")));

        monthlyBudget.setSocialBudgetItemList(socialBudgetItemList);

        BudgetBreakdown budgetBreakdown = calculateBudgetBreakdown.calculateBreakdown(monthlyBudget);

        assertEquals("totalSocialBudget is not equal to Zero.", budgetBreakdown.getTotalSocialBudget(),
                new BigDecimal("500"));

    }

    /*
        // TODO - End of Class
        // Ensure that the variables in BudgeBreakdown are not empty, or null after each calculation has been performed.

        - Test that BudgetBreakdown contains a value for 'typeOfBudget', result must not be empty or null.
        - Test that BudgetBreakdown contains a value for 'coreItemsTotal', result must not be empty or null.
        - Test that BudgetBreakdown contains a value for 'socialItemsTotal', result must not be empty or null.
        - Test that BudgetBreakdown contains a value for 'totalCoreBudgetItems', result must not be empty or null.
        - Test that BudgetBreakdown contains a value for 'totalMoneyAvailable', result must not be empty or null.
        - Test that BudgetBreakdown contains a value for 'totalMoneyAvailableWeekly', result must not be empty or null.
    */


//    @Test
//    public void isBudgetBreakdownContainSumOfCoreItemsValue() {
//
//        BudgetBreakdown breakdown = calculateBudgetBreakdown.calculateBreakdown();
//        assertNotNull("BudgetBreakdown contains SumOfCoreItems", breakdown.getCoreItemsTotal());
//
//    }


    // testing specific configuration methods.

    private AnnualBudget createAnnualBudget(String description, BigDecimal salary) {
        return new AnnualBudget(description, salary, new Date());
    }

    private MonthlyBudget createMonthlyBudget() {
        return new MonthlyBudget("Monthly Budget", new BigDecimal("10000"), new Date());
    }

}
