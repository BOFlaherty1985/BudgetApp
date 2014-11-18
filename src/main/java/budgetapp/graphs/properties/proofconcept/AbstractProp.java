package main.java.budgetapp.graphs.properties.proofconcept;

/**
 * PROOF OF CONCEPT
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/11/2014
 * @project BudgetApp
 */
public abstract class AbstractProp {

    private String title;
    private String sizeOfGraph;
    private boolean isLegendRequired;

    protected AbstractProp(String title, String sizeOfGraph, boolean isLegendRequired) {
        this.title = title;
        this.sizeOfGraph = sizeOfGraph;
        this.isLegendRequired = isLegendRequired;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSizeOfGraph() {
        return sizeOfGraph;
    }

    public void setSizeOfGraph(String sizeOfGraph) {
        this.sizeOfGraph = sizeOfGraph;
    }

    public boolean isLegendRequired() {
        return isLegendRequired;
    }

    public void setLegendRequired(boolean isLegendRequired) {
        this.isLegendRequired = isLegendRequired;
    }
}
