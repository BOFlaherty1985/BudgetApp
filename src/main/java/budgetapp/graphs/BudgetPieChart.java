package main.java.budgetapp.graphs;

import main.java.budgetapp.exceptions.GraphPropertiesNullException;
import main.java.budgetapp.graphs.properties.GraphProperties;

/**
 * Pie Chart Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/11/2014
 * @project BudgetApp
 */
public class BudgetPieChart extends BudgetGraph {

    public Boolean generateGraph(GraphProperties graphProperties) throws Exception {

        boolean result = false;

        if(graphProperties != null) {  // && graphProperties.validate()

            if(!Character.isWhitespace(graphProperties.getTypeOfGraph())) {
                result = true;
            }

        } else {
            throw new GraphPropertiesNullException("GraphProperties is equal to null.");
        }

        return result;
    }
}
