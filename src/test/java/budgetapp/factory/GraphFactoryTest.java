package test.java.budgetapp.factory;

import main.java.budgetapp.factory.graph.GraphFactory;
import main.java.budgetapp.graphs.*;
import main.java.budgetapp.graphs.properties.GraphProperties;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Build Graph Factory Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/11/2014
 * @project BudgetApp
 */
public class GraphFactoryTest {

    private GraphFactory graphFactory = new GraphFactory();

    /*

     create Graph object
      - typeOfGraph
      - locationOfGraphImage
      - dataSet

     create a GraphProperties object
        - typeOfGraph
        - title
        - xAxisTitle
        - yAxisTitle
        - BudgetBreakdown object
      */

    private GraphProperties graphProperties = new GraphProperties();

    @Before
    public void setUp() {
        graphProperties.setTypeOfGraph(' ');
    }

    @Test
    public void assertFactoryMethodBuildGraphIsNeverNull() throws Exception {

        graphProperties.setTypeOfGraph('p');

        BudgetGraph graph = graphFactory.buildGraph(graphProperties);
        assertNotNull("BudgetGraph is not null.", graph);
    }

    @Test
    public void givenGraphPropertiesIsNullThrowException() {

        try {
            graphFactory.buildGraph(null);
            fail("Graph Properties is Null.");
        } catch (Exception e) {
            System.out.println("exception thrown");
        }

    }

    @Test
    public void givenGraphPropertiesTypeOfGraphIsEqualToPIEBuildPieGraph() throws Exception {

        graphProperties.setTypeOfGraph('p');

        BudgetGraph graph = graphFactory.buildGraph(graphProperties);
        assertTrue("Graph is instanceOf PieChart.", graph instanceof BudgetPieChart);
    }

    @Test
    public void givenGraphPropertiesTypeOfGraphIsEqualToBARBuildBARGraph() throws Exception {

        graphProperties.setTypeOfGraph('b');

        BudgetGraph graph = graphFactory.buildGraph(graphProperties);
        assertTrue("Graph is instanceOf PieChart.", graph instanceof BudgetBarChart);
    }

    @Test
    public void givenGraphPropertiesTypeOfGraphIsEqualToLINEBuildLineGraph() throws Exception {

        graphProperties.setTypeOfGraph('l');

        BudgetGraph graph = graphFactory.buildGraph(graphProperties);
        assertTrue("Graph is instanceOf LineChart.", graph instanceof BudgetLineChart);
    }

    @Test
    public void throwExceptionIfGraphPropertiesTypeOfGraphIsNull() {

        try {
            graphFactory.buildGraph(graphProperties);
            fail("TypeOfBudget in Graph Properties is null.");
        } catch (Exception e) {
            System.out.println("exception thrown");
        }

    }

}
