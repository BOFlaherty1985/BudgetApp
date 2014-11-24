package main.java.budgetapp.factory.graph;

import main.java.budgetapp.exceptions.BudgetGraphNullException;
import main.java.budgetapp.exceptions.GraphDatasetNullException;
import main.java.budgetapp.exceptions.GraphPropertiesNullException;
import main.java.budgetapp.graphs.GraphData;
import main.java.budgetapp.graphs.dataset.BudgetCategoryDataset;
import main.java.budgetapp.graphs.dataset.BudgetPieDataset;
import main.java.budgetapp.graphs.implementation.BudgetGraph;
import main.java.budgetapp.graphs.properties.GraphProperties;
import org.jfree.data.general.AbstractDataset;

import java.util.List;

/**
 * Build Graph
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 19/11/2014
 * @project BudgetApp
 */
public class BuildGraph {

    public static final char PIE_GRAPH = 'p';
    public static final char LINE_GRAPH = 'l';
    public static final char BAR_GRAPH = 'b';

    private GraphFactory graphFactory;
    private BudgetPieDataset pieDataset;
    private BudgetCategoryDataset categoryDataset;

    /**
     * build an implementation of BudgetGraph
     *
     * @param graphProperties
     * @param graphDataList
     * @return
     * @throws Exception
     */
    public boolean generateGraph(GraphProperties graphProperties, List<GraphData> graphDataList) throws Exception {

        boolean result;

        // validate
        validate(graphProperties);

        // 1. use GraphFactory to build initial Graph object
        BudgetGraph budgetGraph = graphFactory.buildGraph(graphProperties);

        if(budgetGraph != null) {

            // 2. set initial values on the object
            budgetGraph.initialise(graphProperties);
            // 3. Based on the value of graphProperties.getTypeOfGraph decide which DataSource class to use
            // 4. verify that the call to the DataSource.buildDataSource has been made
            budgetGraph.setDataset(determineDataset(graphProperties, graphDataList));
            // 5. graphLegendRequired() has been called
            budgetGraph.isGraphLegendRequired(graphProperties);
            //  6. verify that CreateGraph has been called

            budgetGraph.createGraph(budgetGraph);

            // TODO - createGraph should return type of Boolean
            result = true;

        } else {
            return throwBudgetGraphNullException();
        }

        return result;
    }

    private void validate(GraphProperties graphProperties) throws GraphPropertiesNullException, GraphDatasetNullException {
        if(graphProperties == null) {
            throw new GraphPropertiesNullException("GraphProperties is null.");
        }

        if(pieDataset == null || categoryDataset == null) {
            throw new GraphDatasetNullException("Graph Dataset must not be null.");
        }
    }

    private boolean throwBudgetGraphNullException() {

        try {
            throw new BudgetGraphNullException("BudgetGraph is null.");
        } catch (BudgetGraphNullException e) {
            System.out.println("exception thrown");
        }

        return false;
    }

    private AbstractDataset determineDataset(GraphProperties graphProperties, List<GraphData> graphDataList) throws Exception {
        return (graphProperties.getTypeOfGraph() == PIE_GRAPH) ? pieDataset.buildDataset(graphDataList) :
                ((graphProperties.getTypeOfGraph() == LINE_GRAPH || graphProperties.getTypeOfGraph() == BAR_GRAPH) ?
                        categoryDataset.buildDataset(graphDataList) : null);
    }


    public void setGraphFactory(GraphFactory graphFactory) {
        this.graphFactory = graphFactory;
    }

    public void setPieDataset(BudgetPieDataset pieDataset) {
        this.pieDataset = pieDataset;
    }

    public void setCategoryDataset(BudgetCategoryDataset categoryDataset) {
        this.categoryDataset = categoryDataset;
    }

}