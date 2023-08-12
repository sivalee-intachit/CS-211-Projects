/**
 * Child class represents a child filing for their taxes.
 * Is a child class which inherits the Person class.
 * @author Sivalee Intachit
 */
public class Child extends Person {
    private String school;
    private float tuition;
    
    /**
     * Constructor initializing a Child object's school, tution, and all fields declared in the parent class (Person).
     * @param name name of a Child object
     * @param birthday birthday of a Child object
     * @param ssn social security number of a Child object
     * @param grossIncome gross income of a Child object
     * @param school school of a Child object
     * @param tuition tuition of a Child object
     */
    public Child(String name, String birthday, String ssn, float grossIncome, String school, float tuition) {
        super.setName(name);
        super.setBirthday(birthday);
        super.setSSN(ssn);
        super.setGrossIncome(grossIncome);
        this.school = school;
        this.tuition = tuition;
    }
    /**
     * Overrides the parent toString method in Person class.
     * @return a string value which displays the name, ssn, birthday, and school of a Child object (censored)
     */
    @Override
    public String toString() {
        return super.getName() + " xxx-xx-" + super.getSSN().substring(7) + " " + super.getBirthday().substring(0,4) + "/**/** " + school;
    }

    public float getTuition() {
        return tuition;
    }
    public String getSchool() {
        return school;
    }
    
    /**
     * Overrides the parent deduction method in Person class. Calculates the amount of gross income that is exempted from taxation.
     * @return a float value representing the exemption
     */
    @Override
    public float deduction(Family family) {
        float exemptions = Taxation.getChildBaseExemption();
        float reduction = 0;
        // If the child's Family has more than 2 children, each child's exemption is reduced by 5% for each additional child
        if (family.getNumChildren() > 2) {
            reduction = (float) 0.05 * (family.getNumChildren() - 2);
        }
        // If the reduction calculated exceeds the max reduction percentage, set reduction equal to 50%
        if (reduction > 0.5) {
            reduction = (float) 0.5;
        }
        exemptions = exemptions - (exemptions * reduction);
        // If the exemption exceeds the Child's gross income, set the exemption equal to the gross income
        if (exemptions > getGrossIncome()) {
            return getGrossIncome();
        }
        return exemptions;
    }
} 
