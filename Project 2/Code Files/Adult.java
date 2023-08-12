/**
 * Adult class represents an adult filing for their taxes.
 * Is a child class which inherits the Person class.
 * @author Sivalee Intachit
 */
public class Adult extends Person {
    private String employer;
    
    /**
     * Constructor initializing an Adult object's employer and all fields declared in the parent class (Person)
     * @param name name of an Adult object
     * @param birthday birthday of an Adult object
     * @param ssn social security number of an Adult object
     * @param grossIncome gross income of an Adult object
     * @param employer employer
     */
    public Adult(String name, String birthday, String ssn, float grossIncome, String employer) {
        super.setName(name);
        super.setBirthday(birthday);
        super.setSSN(ssn);
        super.setGrossIncome(grossIncome);
        this.employer = employer;
    }

    /**
     * Overrides the parent toString method in Person class.
     * @return a string value which displays the name, ssn, birthday, and gross income of a Adult object (censored)
     */
    @Override
    public String toString() {
        return super.getName() + " xxx-xx-" + super.getSSN().substring(7) + " " + super.getBirthday().substring(0,4) + "/**/**" + " $" + String.format("%.2f", getGrossIncome());
    }
    /**
     * Calculates the adjusted income of an employed adult based on the social security income limit and the social security rate.
     * @return a float value representing the adjusted income of an employed adault
     */
    public float adjustedIncome() {
        // Special condition which doesn't subject gross income in excess of the social security limit to the social security rate.
        if (super.getGrossIncome() > Taxation.getSocialSecurityIncomeLimit()) {
            return super.getGrossIncome() - ((Taxation.getSocialSecurityIncomeLimit()*(Taxation.getSocialSecurityRate()/100))/2) - ((super.getGrossIncome()*(Taxation.getMedicareRate()/100))/2);
        }
        return super.getGrossIncome() - ((super.getGrossIncome()*(Taxation.getSocialSecurityRate()/100))/2) - ((super.getGrossIncome()*(Taxation.getMedicareRate()/100))/2);
    }
    /**
     * Calculates the tax that the Adult's employer withheld from their employer at a progressive rate.
     * @return a float value which represents the tax withheld
     */
    public float taxWithheld() {
        float withheldTax = 0;
        // Withold 10% of the first $50k of the adult's paycheck
        if (super.getGrossIncome() <= 50000) {
            withheldTax += super.getGrossIncome() * (float) 0.1;
        }
        // Withhold 15% of the next $100k of the adult's paycheck
        else if (super.getGrossIncome() <= 150000) {
            withheldTax += 5000 + (super.getGrossIncome() - 50000) * (float) 0.15;
        }
        // Withhold 20% of the adult's paycheck which exceeds $100k
        else if (super.getGrossIncome() > 150000) {
            withheldTax += 20000 + (super.getGrossIncome() - 150000) * (float) 0.2;
        }
        return withheldTax;
    }
    /**
     * Overrides the parent deduction method in Person class. Calculates the amount of adjusted income that is exempted from taxation. 
     * @return a float value representing the exemption
     */
    @Override
    public float deduction(Family family) {
        float exemptions = Taxation.getAdultBaseExemption();
        // If the adult files as a single parent, double the adult base exemption
        if (family.getFilingStatus() == 1 && family.getNumChildren() >= 1) {
            exemptions *= 2;
        }
        // If the adult's adjusted income exceeds $100000, reduce the exemption by 0.5% per $10000
        if (adjustedIncome() > 100000) {
            float excess = (float) Math.floor((adjustedIncome() - 100000) / 1000);
            float reduction = (float) 0.005 * excess;
            if (reduction > 0.3) {
                reduction = (float) 0.3;
            }
            exemptions -= (exemptions * reduction);
        }
        // If the exemption exceeds the adult's adjusted income, set exemption
        if (exemptions > adjustedIncome()) {
            exemptions = adjustedIncome();
        }
        return exemptions;
    }
    
    public String getEmployer() {
        return employer;
    }
}
