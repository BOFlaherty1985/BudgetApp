package test.java.budgetapp.graphs.properties;

import main.java.budgetapp.graphs.properties.BuildGraphProperties;
import main.java.budgetapp.graphs.properties.GraphProperties;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Build Graph Properties Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 18/11/2014
 * @project BudgetApp
 */
public class BuildGraphPropertiesTest {

    private BuildGraphProperties buildGraphProperties = new BuildGraphProperties();

    /*
     - buildGraphProperties(graphProperties)
        - check that graphProperties is not null
        - throw exception if null

        - determineXYAxisRequired(typeOfChart)
            - if pieChart - null
            - if Bar or Line - retrieve values from GraphProperties if they exist (can we incorporate any default?)

        - isGraphLegendRequired(boolean)

      */

    @Before
    public void setup() {}

    @After
    public void tearDown() {}

    @Test
    public void ensureBuildGraphPropertiesReturnIsNotNull() throws Exception {
        GraphProperties properties = buildGraphProperties.buildProperties(new GraphProperties());
        assertNotNull("GraphProperties must not be null.", properties);
    }

    @Test
    public void ensureGraphPropertiesInputIsNotNull() {

        try {
            GraphProperties properties = buildGraphProperties.buildProperties(null);
            fail("GraphProperties is null.");
        } catch (Exception e) {
            System.out.println("GraphPropertiesIsNull");
        }

    }

    @Test
    public void ensureGraphicPropertiesExceptionHasValidErrorMessage() {

        try {
            buildGraphProperties.buildProperties(null);
            fail("GraphProperties is null.");
        } catch (Exception e) {
            assertEquals("GraphicPropertiesNullException has been thrown.", "GraphPropertiesNullException", e.getClass().getSimpleName());
            assertEquals("GraphicPropertiesNullException error message is correct.", "GraphicProperties is null.", e.getMessage());
        }

    }


    /*
    - buildDataSet(typeOfchart)

    - Create new instance of Dataset based on typeOfChart (DefaultPieDataset / DefaultCategoryDataset)
    - test that the correct instance of Dataset has been assigned given the typeOfChart
    - test the size of returned Dataset
    - test that the input (BigDecimal) has been converted to a Double value (instanceOf?)
            - test that the newly converted Double values are correct
    - incorporate validation to cope with missing inputs when building the dataset.

    */

    @Test
    public void ensurePieDatasetIsCreatedWhenTypeOfChartIsEqualToP() throws Exception {

        GraphProperties props = new GraphProperties();
        props.setTypeOfGraph('p');

        GraphProperties result = buildGraphProperties.buildProperties(props);
        assertTrue("Dataset is not an instanceOf DefaultPieDataset", result.getDataSet() instanceof DefaultPieDataset);

    }

    @Test
    public void ensureCategoryDatasetIsCreatedWhenTypeOfChartIsEqualToL() throws Exception {

        GraphProperties props = new GraphProperties();
        props.setTypeOfGraph('b');

        GraphProperties result = buildGraphProperties.buildProperties(props);
        assertTrue("Dataset is not an instanceOf DefaultCategoryDataset", result.getDataSet() instanceof DefaultCategoryDataset);

    }

    @Test
    public void ensureCategoryDatasetIsCreatedWhenTypeOfChartIsEqualToB() throws Exception {

        GraphProperties props = new GraphProperties();
        props.setTypeOfGraph('l');

        GraphProperties result = buildGraphProperties.buildProperties(props);
        assertTrue("Dataset is not an instanceOf DefaultCategoryDataset", result.getDataSet() instanceof DefaultCategoryDataset);

    }

    @Test
    public void throwExceptionIfTypeOfChartInputIsInvalid() {


    }

}
