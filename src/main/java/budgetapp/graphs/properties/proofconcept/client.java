package main.java.budgetapp.graphs.properties.proofconcept;

import main.java.budgetapp.graphs.properties.GraphProperties;

import java.io.IOException;

/**
 * PROOF OF CONCEPT
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/11/2014
 * @project BudgetApp
 */
public class client {

    public static void main(String[] args) throws IOException {

//        PieGraphProp pieProps = new PieGraphProp();
//        DefaultPieDataset pieTest = pieProps.buildDataset(new GraphProperties());
//
//        CategoryGraphProp catProps = new CategoryGraphProp();
//        DefaultCategoryDataset catTest = catProps.buildDataset(new GraphProperties());

        GraphProperties properties = new GraphProperties();
        properties.setTypeOfGraph('p');

        BuildGraphProp buildGraphProp = new BuildGraphProp();
        buildGraphProp.buildGraph(properties);

    }

}
