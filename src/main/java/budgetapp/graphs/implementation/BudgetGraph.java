package main.java.budgetapp.graphs.implementation;

import main.java.budgetapp.graphs.properties.GraphProperties;

/**
 * Budget Graph
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 19/11/2014
 * @project BudgetApp
 */
public interface BudgetGraph {

    // TODO - abstract constructor?
    void initialise(GraphProperties graphProperties);

    // TODO - candidate to be moved into an abstract class?
    void isGraphLegendRequired(GraphProperties graphProperties);

    void createGraph(GraphProperties graphProperties);

}
