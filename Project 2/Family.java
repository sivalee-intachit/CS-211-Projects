/**
 * Family class represents a family that filed for a tax return.
 * @author Sivalee Intachit
 */
public class Family {
    private byte numMembers;
    private byte filingStatus;
    private Person[] members;
    
    /**
     * Constructor initializing a Family object's numMembers, filingStatus, and members.
     * @param numMembers number of members in Family
     * @param filingStatus filing status of a Family
     */
    public Family(byte numMembers, byte filingStatus) {
        this.numMembers = numMembers;
        this.members = new Person[numMembers];
        this.filingStatus = filingStatus;
    }

    /**
     * Adds a family member to the members array.
     * @param member person in family
     */
    public void addMember(Person member) {
        // If members array is already filled, extend the array and modify it in place
        if (members[members.length - 1] != null) {
            Person[] tempArray = new Person[members.length + 1];
            for (int i = 0; i < members.length; i++) {
                tempArray[i] = members[i];
            }
            tempArray[tempArray.length - 1] = member;
            members = tempArray;
            numMembers++;
        }
        else {
            for (int i = 0; i < members.length; i++) {
                if (members[i] == null) {
                    members[i] = member;
                    break;
                }
            }
        }
    }
    
    public int getNumMembers() {
        return members.length;
    }
    public byte getNumAdults() {
        byte numAdults = 0;
        for (int i = 0; i < members.length; i++) {
            if (members[i] instanceof Adult) {
                numAdults++;
            }
        }
        return numAdults;
    }
    public byte getNumChildren() {
        byte numChildren = 0;
        for (byte i = 0; i < members.length; i++) {
            if (members[i] instanceof Child) {
                numChildren++;
            }
        }
        return numChildren;
    }
    public Person[] getMembers() {
        return members;
    }
    public byte getFilingStatus() {
        return filingStatus;
    }

    /**
     * Calculates the family's total taxable income based on each memeber's income and deductions.
     * @return a float value representing the family's taxable income
     */
    public float getTaxableIncome() {
        if(members == null){
            return 0;
        }
        Family family = new Family(numMembers, filingStatus);
        for (int i = 0; i < members.length; i++) {
            family.members[i] = members[i];
        }
        float taxableIncome = 0;
        for (int i = 0; i < members.length; i++) {
            if (members[i] instanceof Adult) {
                taxableIncome += ((Adult) members[i]).adjustedIncome() - ((Adult) members[i]).deduction(family);
            }
            else if (members[i] instanceof Child) {
                taxableIncome += ((Child) members[i]).getGrossIncome() - ((Child) members[i]).deduction(family);
            }
        }
        if (taxableIncome < 0) {
            return 0;
        }
        return taxableIncome;
    }
    /**
     * Calculates the amount of tax credit that a family is eligible to receive.
     * @return a float value representing the tax credit
     */
    public float taxCredit() {
        float credit = 0;
        // Family is only eligible to receive tax credit if their taxable income is in the low 50% of the medianIncomePerCapita
        // is equal to $30 per each whole thousand dollars of taxable income
        if (getTaxableIncome() <= (Taxation.medianIncomePerCapita() * 0.5)) {
            credit += 30 * (int)(getTaxableIncome()/1000);
            for (int i = 0; i < members.length; i++) {
                // Family is subject to additional credit for each child as long as it doesn't exceed $2000
                if (members[i] instanceof Child && credit < 2000) {
                    if (((Child) members[i]).getTuition() < 1000) {
                        credit += ((Child) members[i]).getTuition();
                    } else {
                        credit += 1000;
                    }
                    break;           
                }
            }
        }
        // If pre-credit tax is less than $2000, then set pre-credit tax as the max credit
        if (credit > getTaxableIncome()) {
            credit = getTaxableIncome();
        }
        // If a family files as Married Separately, each parent can only claim half of the tax credit
        if (filingStatus == 3) {
            credit /= 2;
        }
        return credit;
    }
    /**
     * Calculates the Family's total calculated tax excluding reductions.
     * @return a float value representing the Family's total calculated tax
     */
    public float totalCalculatedTax() {
        float tax = 0;
        Family family = new Family(numMembers, filingStatus);
        for (int i = 0; i < members.length; i++) {
            family.addMember(members[i]);
        }
        // Step 1
        family.getTaxableIncome();
        if (family.getTaxableIncome() == 0) {
            return 0;
        }
        // Step 2 & 3
        for (int i = 1; i <= Taxation.maxIncomeTaxBracket(family); i++) {
            tax += Taxation.bracketIncome(family, (byte) i) * Taxation.bracketTaxRate((byte) i, (byte) filingStatus);
        }
        return tax;
    }
    /**
     * Calculates the amount of tax that a Family either owes or is to be refunded with.
     * @return a float value representing the Family's tax
     */
    public float calculateTax() {
        // Step 1, 2 & 3
        float tax = totalCalculatedTax();
        // Step 4
        tax -= taxCredit();
        // Step 5
        for (int i = 0; i < members.length; i++) {
            if (members[i] instanceof Adult) {
                tax -= ((Adult)members[i]).taxWithheld();
            }
        }
        return tax;
    }
}
