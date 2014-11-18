package main.java.budgetapp.graphs.properties.proofconcept;

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
public interface GraphProp {

    <T extends AbstractDataset> T buildDataset(List<BigDecimal> categories);

}
