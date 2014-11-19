package test.java.budgetapp.factory.graph;

import main.java.budgetapp.exceptions.GraphDatasetNullException;
import main.java.budgetapp.exceptions.GraphPropertiesNullException;
import main.java.budgetapp.factory.graph.BuildGraph;
import main.java.budgetapp.factory.graph.GraphFactory;
import main.java.budgetapp.graphs.dataset.BudgetCategoryDataset;
import main.java.budgetapp.graphs.dataset.BudgetPieDataset;
import main.java.budgetapp.graphs.implementation.BudgetCategoryGraph;
import main.java.budgetapp.graphs.implementation.BudgetPieGraph;
import main.java.budgetapp.graphs.properties.GraphProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Build Graph Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 19/11/2014
 * @project BudgetApp
 */
public class BuildGraphTest {

    private BuildGraph buildGraph = new BuildGraph();
    private GraphFactory graphFactory;
    private GraphProperties graphProperties;
    private BudgetPieGraph budgetPieGraph;
    private BudgetPieDataset pieDataset;
    private BudgetCategoryGraph budgetCategoryGraph;
    private BudgetCategoryDataset categoryDataset;

    @Before
    public void setUp() {
        graphFactory = Mockito.mock(GraphFactory.class);
        graphProperties = Mockito.mock(GraphProperties.class);
        budgetPieGraph = Mockito.mock(BudgetPieGraph.class);
        budgetCategoryGraph = Mockito.mock(BudgetCategoryGraph.class);
        pieDataset = Mockito.mock(BudgetPieDataset.class);
        categoryDataset = Mockito.mock(BudgetCategoryDataset.class);

        buildGraph.setGraphFactory(graphFactory);
        buildGraph.setPieDataset(pieDataset);
        buildGraph.setCategoryDataset(categoryDataset);
    }

    @After
    public void tearDown() {
        graphFactory = null;
        graphProperties = null;
        budgetPieGraph = null;
        budgetCategoryGraph = null;
    }

    @Test(expected = GraphPropertiesNullException.class)
    public void throwExceptionWhenGraphPropertiesIsNull() throws Exception {

       buildGraph.generateGraph(null);

    }

    @Test
    public void assertGenerateGraphReturnsABooleanReturnType() throws Exception {

        boolean result = buildGraph.generateGraph(graphProperties);
        assertEquals("Result is instanceOf Boolean", false, result);

    }

    @Test
    public void assertGenerateGraphReturnsTrueGraphPropertiesIsNotNull() throws Exception {

        when(graphFactory.buildGraph(graphProperties)).thenReturn(budgetPieGraph);

        boolean result = buildGraph.generateGraph(graphProperties);
        assertEquals("Default result is equal to false.", true, result);

    }

    @Test
    public void verifyGraphFactoryIsCalled() throws Exception {

        buildGraph.generateGraph(graphProperties);
        verify(graphFactory, times(1)).buildGraph(graphProperties);

    }

    @Test
    public void verifyPieGraphObjectInitialValuesSetupMethodIsCalled() throws Exception {

        when(graphFactory.buildGraph(graphProperties)).thenReturn(budgetPieGraph);

        buildGraph.generateGraph(graphProperties);
        verify(budgetPieGraph, times(1)).initialise(graphProperties);

    }

    @Test
    public void verifyPieGraphObjectInitialValuesSetupMethodIsNotTriggeredWhenObjectValueIsNull() throws Exception {

        when(graphFactory.buildGraph(graphProperties)).thenReturn(null);

        buildGraph.generateGraph(graphProperties);
        verify(budgetPieGraph, times(0)).initialise(graphProperties);

    }

    @Test(expected = GraphDatasetNullException.class)
    public void throwExceptionIfPieDatasetIsNull() throws Exception {

        buildGraph.setPieDataset(null);
        buildGraph.setCategoryDataset(categoryDataset);
        buildGraph.generateGraph(graphProperties);

    }

    @Test(expected = GraphDatasetNullException.class)
    public void throwExceptionIfCategoryDatasetIsNull() throws Exception {

        buildGraph.setPieDataset(pieDataset);
        buildGraph.setCategoryDataset(null);
        buildGraph.generateGraph(graphProperties);

    }

    @Test
    public void verifyPieDatasetNullExceptionMessageIsValid() throws Exception {

        buildGraph.setPieDataset(null);
        buildGraph.setCategoryDataset(categoryDataset);

        try {
            buildGraph.generateGraph(graphProperties);
            fail("GraphDataSet is null");
        } catch (Exception e) {
            assertEquals("Graph Dataset must not be null.", "Graph Dataset must not be null.", e.getMessage());
        }

    }

    @Test
    public void verifyCategoryDatasetNullExceptionMessageIsValid() throws Exception {

        buildGraph.setPieDataset(pieDataset);
        buildGraph.setCategoryDataset(null);

        try {
            buildGraph.generateGraph(graphProperties);
            fail("GraphDataSet is null");
        } catch (Exception e) {
            assertEquals("Graph Dataset must not be null.", "Graph Dataset must not be null.", e.getMessage());
        }

    }

    @Test
    public void verifyPieDatasetIsCalledWhenTypeOfGraphRequestedIsEqualToP() throws Exception {

        when(graphProperties.getTypeOfGraph()).thenReturn('p');
        when(graphFactory.buildGraph(graphProperties)).thenReturn(budgetPieGraph);

        buildGraph.generateGraph(graphProperties);
        verify(pieDataset, times(1)).buildDataset();

    }

    @Test
    public void verifyCategoryDatasetIsCalledWhenTypeOfGraphRequestedIsEqualToL() throws Exception {

        when(graphProperties.getTypeOfGraph()).thenReturn('l');
        when(graphFactory.buildGraph(graphProperties)).thenReturn(budgetCategoryGraph);

        buildGraph.generateGraph(graphProperties);
        verify(categoryDataset, times(1)).buildDataset();

    }

    @Test
    public void verifyCategoryDatasetIsCalledWhenTypeOfGraphRequestedIsEqualToB() throws Exception {

        when(graphProperties.getTypeOfGraph()).thenReturn('b');
        when(graphFactory.buildGraph(graphProperties)).thenReturn(budgetCategoryGraph);

        buildGraph.generateGraph(graphProperties);
        verify(categoryDataset, times(1)).buildDataset();

    }

    @Test
    public void verifyIsLegendRequiredMethodIsCalledOnBudgetPieGraphImplementation() throws Exception {

        when(graphFactory.buildGraph(graphProperties)).thenReturn(budgetPieGraph);

        buildGraph.generateGraph(graphProperties);
        verify(budgetPieGraph, times(1)).isGraphLegendRequired(graphProperties);

    }

    @Test
    public void verifyIsLegendRequiredMethodIsCalledOnBudgetCategoryGraphImplementation() throws Exception {

        when(graphFactory.buildGraph(graphProperties)).thenReturn(budgetCategoryGraph);

        buildGraph.generateGraph(graphProperties);
        verify(budgetCategoryGraph, times(1)).isGraphLegendRequired(graphProperties);

    }

    @Test
    public void verifyCreateGraphMethodIsCalledOnBudgetPieGraphImplementation() throws Exception {

        when(graphFactory.buildGraph(graphProperties)).thenReturn(budgetPieGraph);

        buildGraph.generateGraph(graphProperties);
        verify(budgetPieGraph, times(1)).createGraph(graphProperties);

    }

    @Test
    public void verifyCreateGraphMethodIsCalledOnBudgetCategoryGraphImplementation() throws Exception {

        when(graphFactory.buildGraph(graphProperties)).thenReturn(budgetCategoryGraph);

        buildGraph.generateGraph(graphProperties);
        verify(budgetCategoryGraph, times(1)).createGraph(graphProperties);

    }

}
