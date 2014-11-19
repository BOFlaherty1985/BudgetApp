package main.java.budgetapp.factory.graph;

import main.java.budgetapp.exceptions.GraphPropertiesNullException;
import main.java.budgetapp.graphs.implementation.BudgetCategoryGraph;
import main.java.budgetapp.graphs.implementation.BudgetGraph;
import main.java.budgetapp.graphs.implementation.BudgetPieGraph;
import main.java.budgetapp.graphs.properties.GraphProperties;

/**
 * Graph Factory
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 19/11/2014
 * @project BudgetApp
 */
public class GraphFactory {

    public static final char PIE_GRAPH = 'p';
    public static final char LINE_GRAPH = 'l';
    public static final char BAR_GRAPH = 'b';

    public BudgetGraph buildGraph(GraphProperties graphProperties) throws Exception {

        validate(graphProperties);

        return determineTypeOfGraph(graphProperties);
    }

    private void validate(GraphProperties graphProperties) throws GraphPropertiesNullException {
        if(graphProperties == null) {
            throw new GraphPropertiesNullException("GraphProperties must not be null.");
        }
    }

    private BudgetGraph determineTypeOfGraph(GraphProperties graphProperties) {

        BudgetGraph graph = null;

        switch (graphProperties.getTypeOfGraph()) {
            case PIE_GRAPH:
                graph = new BudgetPieGraph();
            break;
            case LINE_GRAPH:
                graph = new BudgetCategoryGraph();
                break;
            case BAR_GRAPH:
                graph = new BudgetCategoryGraph();
        }

        return graph;
    }

}