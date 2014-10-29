package main.java.budgetapp.exceptions;

/**
 * Salary Not Found Exception
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 29/10/2014
 * @project BudgetApp
 */
public class SalaryNotFoundException extends Exception {

    public SalaryNotFoundException(String message) {
        super(message);
    }

}
