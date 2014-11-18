package main.java.budgetapp.graphs.properties;

/**
 * Graph Properties
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/11/2014
 * @project BudgetApp
 */
public class GraphProperties<T> {

    private char typeOfGraph;
    private T dataSet;

    public char getTypeOfGraph() {
        return typeOfGraph;
    }

    public void setTypeOfGraph(char typeOfGraph) {
        this.typeOfGraph = typeOfGraph;
    }

    @Override
    public String toString() {
        return "GraphProperties{" +
                "typeOfGraph='" + typeOfGraph + '\'' +
                '}';
    }

    public T getDataSet() {
        return dataSet;
    }

    public void setDataSet(T dataSet) {
        this.dataSet = dataSet;
    }
}
