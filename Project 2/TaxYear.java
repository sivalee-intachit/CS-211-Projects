/**
 * TaxYear class stores the tax filing information for all families.
 * @author Sivalee Intachit
 */
public class TaxYear {
    private int max;
    private Family[] families = new Family[0];
    
    /**
     * Constructor initializing max.
     * @param max the max number of families that could be stored locally
     */
    public TaxYear(int max) {
        this.max = max;
    }

    public Family[] getFamiliesFiled() {
        return families;
    }

    /**
     * Files a family's tax return for the year and validates the submitted data.
     * Stores the family's data locally if valid.
     * @param family given family filing for a tax return
     * @return a boolean value representing whether the family's tax return information is valid
     */
    public boolean taxFiling(Family family) {
        // Checks if max number of returns have been reached; does not file family if max is met
        if (numberOfReturnsFiled() == max) {
            return false;
        }
        // Checks if number of adults is valid
        else if (family.getNumAdults() > 2 || family.getNumAdults() < 1) {
            return false;
        }
        else {
            int filingStatus = family.getFilingStatus();
            // Checks if filing status is valid
            if (family.getFilingStatus() < 1 || family.getFilingStatus() > 3) {
                return false;
            }
            // Checks if only one adult is in family if filing as Single or Married Separately
            else if ((filingStatus == 1 || filingStatus == 3) && family.getNumAdults() != 1) {
                return false;
            }
            // Checks if two adults are in family if filing as Married Jointly
            else if (filingStatus == 2 && family.getNumAdults() != 2) {
                return false;
            }
        }
        // Extend the families array and add new family if the max numer of families have not been met
        Family[] tempArray = new Family[families.length + 1];
        for (int i = 0; i < families.length; i++) {
            tempArray[i] = families[i];
        }
        tempArray[tempArray.length - 1] = family;
        families = tempArray;
        return true;
    }
    /**
     * Calculates the total tax withheld from all families' paychecks up to the moment of invokement.
     * @return a float value representing the total tax withheld
     */
    public float taxWithheld() {
        float withheldTax = 0;
        for (int i = 0; i < families.length; i++) {
            for (int j = 0; j < families[i].getMembers().length; j++) {
                if (families[i].getMembers()[j] instanceof Adult) {
                    withheldTax += ((Adult)families[i].getMembers()[j]).taxWithheld();
                }
            }
        }
        return withheldTax;
    }
    /**
     * Calculates the total tax that is owed by all families based on their taxable income.
     * @return a float value representing the tax owed
     */
    public float taxOwed() {
        float owed = 0;
        for (int i = 0; i < families.length; i++) {
            owed += families[i].totalCalculatedTax();
        }
        return owed;
    }
    /**
     * Calculates the total tax due/returned the moment of invokement.
     * @return a float value representing 
     */
    public float taxDue() {
        float due = 0;
        for (int i = 0; i < families.length; i++) {
            due += families[i].calculateTax();
        }
        return due;
    }
    /**
     * Calculates total tax credits all families were given.
     * @return
     */
    public float taxCredits() {
        float credits = 0;
        for (int i = 0; i < families.length; i++) {
            credits += families[i].taxCredit();
        }
        return credits;
    }

    public int numberOfReturnsFiled() {
        return families.length;
    }
    public int numberOfPersonsFiled() {
        int numPersons = 0;
        for (int i = 0; i < families.length; i++) {
            numPersons += families[i].getMembers().length;
        }
        return numPersons;
    }
    public Family getTaxReturn(int index) {
        return families[index];
    }
}

