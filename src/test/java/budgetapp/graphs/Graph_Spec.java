package test.java.budgetapp.graphs;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/11/2014
 * @project BudgetApp
 */
public class Graph_Spec {

    // Build a graph factory to retrieve and build a particular type of graph object (pie, bar, line)
    // Pass the budget breakdown object into the method to retrieve value.
    // Validate object, ensure values are valid (not null, empty etc).
    // Determine how Graph and X & Y Axis titles are created (new object containing values?)
    // Build data set for the given graph object
    // Determine size of graph (set size or dynamic, e.g. user can choose between small, medium and large)

    /*

    Factory Implementation

                    GraphFactory
                    - Graph generateGraph

                    BuildGraph
                    - generateGraph() implementation (determines which graph to create)
                    - validate input to generateGraph (typeOfGraph and BudgetBreakdown)

                    Graph (abstract)
                     createGraph()

       BarGraph       PieGraph      LineGraph
     CreateGraph()  createGraph()  createGraph()


     BuildDataSet
     - implement graph specific DataSet object (DefaultPieDataset & DefaultCategoryDataset)
     - validate size and values of the DataSet
     - Specify jpg file location (where should we save the image?), fileName & prefix (date related?)
     - ensure ChartUtilities.saveChartAsJPEG() is called.

     */

}
