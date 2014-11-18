package main.java.budgetapp.factory.graph;

import main.java.budgetapp.graphs.BudgetGraph;
import main.java.budgetapp.graphs.properties.GraphProperties;

/**
 * Create a Graph
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/11/2014
 * @project BudgetApp
 */
public class BuildGraph {

    // injected graph factory
    private GraphFactory graphFactory;

    public BudgetGraph createGraph(GraphProperties props) throws Exception {
        return graphFactory.buildGraph(props);
    }

    public void setGraphFactory(GraphFactory graphFactory) {
        this.graphFactory = graphFactory;
    }

}
