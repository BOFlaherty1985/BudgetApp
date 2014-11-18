package main.java.budgetapp.factory.graph;

import main.java.budgetapp.graphs.*;
import main.java.budgetapp.graphs.properties.GraphProperties;

/**
 * Graph Factory
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/11/2014
 * @project BudgetApp
 */
public class GraphFactory {

    public BudgetGraph buildGraph(GraphProperties props) throws Exception {

        validate(props);

        return generateGraph(props);
    }

    private BudgetGraph generateGraph(GraphProperties props) {

        BudgetGraph budgetGraph = null;

        switch (props.getTypeOfGraph()) {
            case 'p':
                budgetGraph = new BudgetPieChart();
                break;
            case 'b':
                budgetGraph = new BudgetBarChart();
                break;
            case 'l':
                budgetGraph = new BudgetLineChart();
                break;
        }

        return budgetGraph;
    }

    private void validate(GraphProperties props) throws Exception {

        if(props == null) {
            throw new Exception();
        }

        if(Character.isWhitespace(props.getTypeOfGraph())) {
            throw new Exception();
        }

    }

}
