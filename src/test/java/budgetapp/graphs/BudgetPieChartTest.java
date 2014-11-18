package test.java.budgetapp.graphs;

import main.java.budgetapp.exceptions.GraphPropertiesNullException;
import main.java.budgetapp.graphs.BudgetPieChart;
import main.java.budgetapp.graphs.properties.GraphProperties;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Pie Chart Object Test Case
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/11/2014
 * @project BudgetApp
 */
public class BudgetPieChartTest {

    private BudgetPieChart pieChart = new BudgetPieChart();

    private GraphProperties props = new GraphProperties();

    // Validate GraphProperties (throw exceptions) - graphProperties.validate()
    // Use the object GraphProperties to build the Pie Chart
    //     - graphProperties.getTitle() - verify this has been called
    //     - graphProperties.getLegend() - verify this has been called
    //     - graphProperties.getDataSet() - verify this has been called
    //     - graphProperties.getGraphSize() - verify this has been called.
    // ChartFactory.createPieChart(
    // call method SaveChartAsJPEG() passing in the correct parameters

    /*
        GraphProperties behavior
          -  Set Title
          -  Set X & Y Axis (if applicable)
          -  Determine the size of the image (small, medium or large) (Abstract class method?, BudgetGraph)
          -  Determine whether or not to create a legend  (true/false)
          -  Create the dataset (using BudgetBreakdown Object)
          -  Convert values required values from BigDecimal to Double for the Dataset
    */
    @Before
    public void setUp() {
        props.setTypeOfGraph('p');
    }

    @After
    public void tearDown() {
        props = null;
    }

    @Test
    public void checkResultOfGenerateGraphIsNotNull() throws Exception {
        Boolean result = pieChart.generateGraph(new GraphProperties());
        assertNotNull("Result is equal to null. Must return either true or false.", result);
    }

    @Test
    public void whenSuccessfulCreationOfAGraphResultMustEqualTrue() throws Exception {

        Boolean result = pieChart.generateGraph(props);
        assertEquals("Result is equal to true when a graph has been successfully created.", new Boolean(true), result);

    }

    @Test
    public void whenFailureOfCreationOfAGraphResultMustEqualFalse() throws Exception {

        props.setTypeOfGraph(' ');

        Boolean result = pieChart.generateGraph(props);
        assertEquals("Result is equal to false when a graph has been successfully created.", new Boolean(false), result);

    }

    @Test
    public void throwExceptionWhenGraphPropertiesIsNull() throws Exception {

        try {
            pieChart.generateGraph(null);
            fail("GraphProperties must not be null.");
        } catch (GraphPropertiesNullException e) {
            System.out.println("GraphProperties is equal to null.");
        }

    }

    @Test
    public void whenGraphPropertiesExceptionThrownEnsureErrorMessageIsValid() throws Exception {

        try {
            pieChart.generateGraph(null);
            fail("GraphProperties must not be null.");
        } catch (GraphPropertiesNullException e) {
            assertEquals("GraphProperties is equal to null.", e.getMessage());
            Assert.assertEquals("GraphPropertiesNullException should be thrown.", "GraphPropertiesNullException",
                    e.getClass().getSimpleName());
        }

    }


}
