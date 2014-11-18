package main.java.budgetapp.graphs.properties.proofconcept;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;

import java.math.BigDecimal;
import java.util.List;

/**
 * PROOF OF CONCEPT
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/11/2014
 * @project BudgetApp
 */
public class CategoryGraphProp extends AbstractProp implements GraphProp {

    private String XAxis;
    private String YAxis;

    protected CategoryGraphProp(String title, String sizeOfGraph, boolean isLegendRequired) {
        super(title, sizeOfGraph, isLegendRequired);
    }

    @Override
    public <T extends AbstractDataset> T buildDataset(List<BigDecimal> categories) {

        // build dataset from input values

        return (T) new DefaultCategoryDataset();
    }

    public void setXYAxisTitles() {
        setXAxis("X Category");
        setYAxis("Y Category");
    }

    public String getXAxis() {
        return XAxis;
    }

    public void setXAxis(String XAxis) {
        this.XAxis = XAxis;
    }

    public String getYAxis() {
        return YAxis;
    }

    public void setYAxis(String YAxis) {
        this.YAxis = YAxis;
    }

}
