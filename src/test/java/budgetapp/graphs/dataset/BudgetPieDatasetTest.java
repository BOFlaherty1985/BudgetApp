package test.java.budgetapp.graphs.dataset;

/**
 * Pie Dataset Implementation Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 19/11/2014
 * @project BudgetApp
 */
public class BudgetPieDatasetTest {

    /*
        buildDataset(List<GraphData> dataSetList)

        GraphData Object
            - String category
            - BigDecimal value

        1. loop through each GraphData object in dataSetList
        2. calculate totalValue from 'value' fields of each GraphData object
        3. divide original GraphData value by the totalValue field (provides % for Pie Chart)
        4. convert percentage value to Double
        5. create instance of PieDataSet
        6. call setValue method on dataset and pass in category and value (%)
        7. return dataset object

     */

}
