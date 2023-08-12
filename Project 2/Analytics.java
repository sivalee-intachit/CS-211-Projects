/**
 * Analytics class calculates the statistics for all families' tax returns based on the given tax year.
 * @author Sivalee Intachit
 */
public class Analytics {
    private static float povertyThreshold = 27750;
    private TaxYear taxYear;

    /**
     * Constructor initializing taxYear.
     * @param taxYear given tax year
     */
    public Analytics(TaxYear taxYear) {
        this.taxYear = taxYear;
    }

    public void setPovertyThreshold(float threshold) {
        povertyThreshold = threshold;
    }
    /**
     * Calculates the average family income for the given year based on the families stored locally in TaxYear.
     * @return a float value representing the average family income
     */
    public float averageFamilyIncome() {
        Family[] families = taxYear.getFamiliesFiled();
        float sumIncome = 0;
        float averageIncome = 0;
        for (int i = 0; i < families.length; i++) {
            sumIncome += families[i].getTaxableIncome();
        }
        averageIncome = sumIncome / families.length;
        return averageIncome;
    }
    /**
     * Calculates the average person income for the given year.
     * @return a float value representing the average person income
     */
    public float averagePersonIncome() {
        Family[] families = taxYear.getFamiliesFiled();
        float sumIncome = 0;
        float averageIncome = 0;
        float numPersons = 0;
        for (int i = 0; i < families.length; i++) {
            sumIncome += families[i].getTaxableIncome();
            numPersons += families[i].getNumMembers();
        }
        averageIncome = sumIncome / numPersons;
        return averageIncome;
    }
    /**
     * Calculates the maximum family taxable income for the given year.
     * @return a float value representing the max family taxable income
     */
    public float maxFamilyIncome() {
        Family[] families = taxYear.getFamiliesFiled();
        float maxIncome = 0;
        for (int i = 0; i < families.length; i++) {
            if (families[i].getTaxableIncome() > maxIncome) {
                maxIncome = families[i].getTaxableIncome();
            }
        }
        return maxIncome;
    }
    /**
     * Calculates the maximum personal taxable income for the given year.
     * @return a float value representing the max person taxable income
     */
    public float maxPersonIncome() {
        Family[] families = taxYear.getFamiliesFiled();
        float maxIncome = 0;
        for (int i = 0; i < families.length; i++) {
            Person[] members = families[i].getMembers();
            for (int j = 0; j < members.length; j++) {
                // If the member is an adult, calculate their taxable income and compare it to the current maxIncome value
                if (members[j] instanceof Adult && ((Adult) members[j]).adjustedIncome() - ((Adult) members[j]).deduction(families[i]) > maxIncome) {
                    maxIncome = ((Adult) members[j]).adjustedIncome() - ((Adult) members[j]).deduction(families[i]);
                }
                // If the member is an child, calculate their taxable income and compare it to the current maxIncome value
                else if (members[j] instanceof Child && ((Child) members[j]).getGrossIncome() - ((Child) members[j]).deduction(families[i]) > maxIncome) {
                    maxIncome = ((Child) members[j]).getGrossIncome() - ((Child) members[j]).deduction(families[i]);
                }
            }
        }
        return maxIncome;
    }
    /**
     * Calculates the number of families with a taxable income below the poverty threshold.
     * @return an int value representing the number of families in poverty
     */
    public int familiesBelowPovertyLimit() {
        Family[] families = taxYear.getFamiliesFiled();
        int numFamilies = 0;
        for (int i = 0; i < families.length; i++) {
            if (families[i].getTaxableIncome() <= povertyThreshold) {
                numFamilies++;
            }
        }
        return numFamilies;
    }
    /**
     * Calculates the rank of a family's taxable income in a certain tax year.
     * @param family given family
     * @return an int value representing the rank of a family's taxable income
     */
    public int familyRank(Family family) {
        int rank = 1;
        Family[] families = taxYear.getFamiliesFiled();
        for (int i = 0; i < families.length; i++) {
            if (family.getTaxableIncome() < families[i].getTaxableIncome()) {
                rank++;
            }
        }
        return rank;
    }
}
