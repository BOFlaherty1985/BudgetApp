package main.java.budgetapp.graphs.properties.proofconcept;

import main.java.budgetapp.graphs.properties.GraphProperties;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * PROOF OF CONCEPT
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/11/2014
 * @project BudgetApp
 */
public class BuildGraphProp {

    public void buildGraph(GraphProperties prop) throws IOException {

        JFreeChart chart;

        if(prop.getTypeOfGraph() == 'p') {
            PieGraphProp pie = new PieGraphProp("Title Goes Here", "SML", true);

            DefaultPieDataset dataset = pie.buildDataset(new ArrayList<BigDecimal>());

            // create the graph
            chart = createPieChart(pie, dataset);

        } else {

            CategoryGraphProp cat = new CategoryGraphProp("Title Goes Here", "SML", true);
            cat.setXYAxisTitles();

            DefaultCategoryDataset dataset = cat.buildDataset(new ArrayList<BigDecimal>());

            if(prop.getTypeOfGraph() == 'b') {
                chart = createBarChart(cat, dataset);
            } else {
                chart = createLinechart(cat, dataset);
            }

        }

        File chartFile = new File("C://budgetGraph.jpg");
        ChartUtilities.saveChartAsJPEG(chartFile, chart, 300, 300);

    }

    private JFreeChart createPieChart(PieGraphProp pie, DefaultPieDataset dataset) {
        return ChartFactory.createPieChart(
                pie.getTitle(),  // chart title
                dataset,        // data
                pie.isLegendRequired(),   // include legend
                true,
                false);
    }

    private JFreeChart createBarChart(CategoryGraphProp cat, DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                cat.getTitle(),
                cat.getXAxis(), cat.getYAxis(), // x/y axis
                dataset, PlotOrientation.VERTICAL,
                cat.isLegendRequired(), true, false);
    }

    private JFreeChart createLinechart(CategoryGraphProp cat, DefaultCategoryDataset dataset) {
        return ChartFactory.createLineChart(
                cat.getTitle(),
                cat.getXAxis(), cat.getYAxis(), // x/y axis
                dataset, PlotOrientation.VERTICAL,
                cat.isLegendRequired(), true, false);
    }

}



