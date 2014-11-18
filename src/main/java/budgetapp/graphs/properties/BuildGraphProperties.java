package main.java.budgetapp.graphs.properties;

import main.java.budgetapp.exceptions.GraphPropertiesNullException;

/**
 * Build Properties for a Graph
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/11/2014
 * @project BudgetApp
 */
public class BuildGraphProperties {

    // set type of graph  (already setup in the client?)
    // set title (already setup in the client?)

    public GraphProperties buildProperties(GraphProperties graphProperties) throws Exception {

        if(graphProperties == null) {
            throw new GraphPropertiesNullException("GraphicProperties is null.");
        }

        // build dataset
        buildDataset(graphProperties);

        return graphProperties;
    }

    private void buildDataset(GraphProperties graphProperties) {


    }

    /*
        determine if X/Y axis is required (based on the type of chart requested) (condition statement)

        if(chartType ...) {
            X/Y required
        }
    */

    /*
     build the dataset and convert given values to double (conversion method)
        - for each value given..
            - convert the BigDecimal value to a double
            - add value to the Dataset object
     */

    /*
       determine if a legend is required (true/false)  (condition statement)

       if(legendRequired) {
            output legend based on user input
       }

    */

    // set size of graph (already setup in the client?)

    // return GraphProperties object

}
