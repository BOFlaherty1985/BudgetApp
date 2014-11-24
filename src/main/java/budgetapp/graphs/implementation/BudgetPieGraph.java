package main.java.budgetapp.graphs.implementation;

import main.java.budgetapp.exceptions.BudgetGraphIsInvalidException;
import main.java.budgetapp.exceptions.BudgetGraphNullException;
import main.java.budgetapp.graphs.properties.GraphProperties;
import main.java.budgetapp.graphs.properties.GraphSize;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.PieDataset;

import java.io.File;

/**
 * Pie Graph Implementation
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 19/11/2014
 * @project BudgetApp
 */
public class BudgetPieGraph implements BudgetGraph {

    private String title;
    private String requiredSize;
    private boolean legendRequired;
    private PieDataset dataset;

    @Override
    public void initialise(GraphProperties graphProperties) {
        this.title = graphProperties.getTitle();
        this.requiredSize = graphProperties.getRequiredSize();
    }

    @Override
    public void isGraphLegendRequired(GraphProperties graphProperties) {
        setLegendRequired(graphProperties.isLegendRequired());
    }

    @Override
    public <T extends AbstractDataset> void setDataset(T dataset) {
        this.dataset = (PieDataset) dataset;
    }

    @Override
    public <T> void createGraph(T budgetGraph) throws Exception {

        BudgetPieGraph pieGraph = (BudgetPieGraph) budgetGraph;

        if(budgetGraph == null) {
            throw new BudgetGraphNullException("BudgetGraph is null.");
        } else {
            pieGraph.validate(pieGraph);

            JFreeChart pieChart = createPieChart(pieGraph);

            ChartUtilities.saveChartAsJPEG(new File("C:\\budgetGraph.jpg"),
                    pieChart,
                    determineGraphWidth(pieGraph.getRequiredSize()),
                    determineGraphHeight(pieGraph.getRequiredSize()));
        }

    }

    private int determineGraphWidth(String requestedSize) {

        return (requestedSize == "SMALL") ? GraphSize.SMALL.getWidth() :
                (requestedSize == "MEDIUM") ? GraphSize.MEDIUM.getWidth() :
                        (requestedSize == "LARGE") ? GraphSize.LARGE.getWidth() : null;
    }

    private int determineGraphHeight(String requestedSize) {
        return (requestedSize == "SMALL") ? GraphSize.SMALL.getHeight() :
                (requestedSize == "MEDIUM") ? GraphSize.MEDIUM.getHeight() :
                        (requestedSize == "LARGE") ? GraphSize.LARGE.getHeight() : null;

    }

    private JFreeChart createPieChart(BudgetPieGraph pieGraph) {
        return ChartFactory.createPieChart(
                pieGraph.getTitle(),
                pieGraph.getDataset(),
                pieGraph.legendRequired,
                true,
                false);
    }

    public void validate(BudgetPieGraph budgetPieGraph) throws Exception {

        if(budgetPieGraph.getTitle() == null
                || budgetPieGraph.getRequiredSize() == null
                || budgetPieGraph.getDataset() == null)  {
            throw new BudgetGraphIsInvalidException();
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequiredSize() {
        return requiredSize;
    }

    public boolean isLegendRequired() {
        return legendRequired;
    }

    public void setLegendRequired(boolean legendRequired) {
        this.legendRequired = legendRequired;
    }

    @Override
    public String toString() {
        return "BudgetPieGraph{" +
                "title='" + title + '\'' +
                ", requiredSize='" + requiredSize + '\'' +
                ", legendRequired=" + legendRequired +
                ", dataset=" + dataset +
                '}';
    }

    public PieDataset getDataset() {
        return dataset;
    }

    public void setDataset(PieDataset dataset) {
        this.dataset = dataset;
    }

}
