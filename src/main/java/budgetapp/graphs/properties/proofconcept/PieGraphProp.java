package main.java.budgetapp.graphs.properties.proofconcept;

import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.math.BigDecimal;
import java.util.List;

/**
 * PROOF OF CONCEPT
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/11/2014
 * @project BudgetApp
 */
public class PieGraphProp extends AbstractProp implements GraphProp {

    protected PieGraphProp(String title, String sizeOfGraph, boolean isLegendRequired) {
        super(title, sizeOfGraph, isLegendRequired);
    }

    @Override
    public <T extends AbstractDataset> T buildDataset(List<BigDecimal> categories) {

        // build dataset from input values

        DefaultPieDataset pieChartDS = new DefaultPieDataset();
        pieChartDS.setValue("Test", new Double(50));
        pieChartDS.setValue("CAT", new Double(60));

        return (T) pieChartDS;
    }

}
