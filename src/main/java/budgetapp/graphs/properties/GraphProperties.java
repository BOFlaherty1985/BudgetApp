package main.java.budgetapp.graphs.properties;

/**
 * Graph Properties
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 19/11/2014
 * @project BudgetApp
 */
public class GraphProperties {

    private char typeOfGraph;
    private String title;
    private String requiredSize;
    private boolean legendRequired;

    public void setTypeOfGraph(char typeOfGraph) {
        this.typeOfGraph = typeOfGraph;
    }

    public char getTypeOfGraph() {
        return typeOfGraph;
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

    public void setRequiredSize(String requiredSize) {
        this.requiredSize = requiredSize;
    }

    public boolean isLegendRequired() {
        return legendRequired;
    }

    public void setLegendRequired(boolean legendRequired) {
        this.legendRequired = legendRequired;
    }

}
