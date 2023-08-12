import java.util.Scanner;
import java.io.File;

/**
 * Taxation class holds the brackets, rates, and exemptions used to calculate tax.
 * @author Sivalee Intachit
 */
public class Taxation {
    private static float socialSecurityRate = (float)(12.4);
    private static float socialSecurityIncomeLimit = (float)(137700.0);
    private static float medicareRate = (float)(2.9);
    private static float adultBaseExemption = (float)(3000.0);
    private static float childBaseExemption = (float)(2000.0);
    private static float medianIncomePerCapita = (float)(31099.0);
    private static final float[][] incomeBrackets = {
            {10000, 40000, 80000, 160000}, // single
            {20000, 70000, 160000, 310000}, // married (jointly)
            {12000, 44000, 88000, 170000} // married (separately)
    };
    private static final float[][] taxRates = {
            {(float)(0.1), (float)(0.12), (float)(0.22), (float)(0.24), (float)(0.32)}, // single
            {(float)(0.1), (float)(0.12), (float)(0.23), (float)(0.25), (float)(0.33)}, // married (jointed)
            {(float)(0.1), (float)(0.12), (float)(0.24), (float)(0.26), (float)(0.35)} // married (separately)
    };

    public static float getSocialSecurityRate() {
        return socialSecurityRate;
    }
    public static float getSocialSecurityIncomeLimit() {
        return socialSecurityIncomeLimit;
    }
    public static float getMedicareRate() {
        return medicareRate;
    }
    public static float getAdultBaseExemption() {
        return adultBaseExemption;
    }
    public static float getChildBaseExemption() {
        return childBaseExemption;
    }
    public static float medianIncomePerCapita() {
        return medianIncomePerCapita;
    }

    /** 
     * Retrieves new values for medicareRate, medianIncomePerCapita, socialSecurityIncomeLimit, adultBaseExemption,
     * childBaseExemption, and socialSecurity rate from a given file. Re-initalizes values based on file's info.
     */
    public static void loadParameters(String filename) throws Exception {
        Scanner scnr = new Scanner(new File(filename));
        while (scnr.hasNextLine()) {
            String info = scnr.nextLine();
            if (info.contains("medicareRate")) {
                medicareRate = Float.valueOf(info.substring(13));
            }
            else if (info.contains("medianIncomePerCapita")) {
                medianIncomePerCapita = Float.valueOf(info.substring(22));
            }
            else if (info.contains("socialSecurityIncomeLimit")) {
                socialSecurityIncomeLimit = Float.valueOf(info.substring(26));
            }
            else if (info.contains("adultBaseExemption")) {
                adultBaseExemption = Float.valueOf(info.substring(19));
            }
            else if (info.contains("childBaseExemption")) {
                childBaseExemption = Float.valueOf(info.substring(19));
            }
            else if (info.contains("socialSecurityRate")) {
                socialSecurityRate = Float.valueOf(info.substring(19));
            }
        }
    }

    public static byte getNumTaxBrackets() {
        return (byte) taxRates[0].length;
    }

    /**
     * Retrieves a Family's max income tax bracket based on their taxable income.
     * @param family family filed
     * @return byte value representing the Family's max income tax bracket
     */
    public static byte maxIncomeTaxBracket(Family family) {
        int bracket = 1;
        // Single Family
        if (family.getFilingStatus() == 1) {
            if (family.getTaxableIncome() > incomeBrackets[0][3]) {
                return 5;
            }
            for (int i = 0; i < incomeBrackets[0].length; i++) {
                if (family.getTaxableIncome() <= incomeBrackets[0][i]) {
                    bracket = i+1;
                    break;
                }
            }
        }
        // Married (Jointly)
        else if (family.getFilingStatus() == 2) {
            if (family.getTaxableIncome() > incomeBrackets[1][3]) {
                return 5;
            }
            for (int i = 0; i < incomeBrackets[1].length; i++) {
                if (family.getTaxableIncome() <= incomeBrackets[1][i]) {
                    bracket = i+1;
                    break;
                }
            }
        }
        // Married (Separately)
        else if (family.getFilingStatus() == 3) {
            if (family.getTaxableIncome() > incomeBrackets[2][3]) {
                return 5;
            }
            for (int i = 0; i < incomeBrackets[2].length; i++) {
                if (family.getTaxableIncome() <= incomeBrackets[2][i]) {
                    bracket = i+1;
                    break;
                }
            }
        }
        return (byte) bracket;
    }
    /**
     * Calculates the portion of a family's taxable income that falls within bracket b.
     * @param family family filed
     * @param b given bracket
     * @return a float value representing the portion of a family's taxable income
     */
    public static float bracketIncome(Family family, byte b) {
        float incomeBracket = 0;
        // If given bracket 1 and taxable income is within the max value, return taxable income (avoids out of bounds exception)
        if (b == 1 && family.getTaxableIncome() <= incomeBrackets[family.getFilingStatus() - 1][0]) {
            incomeBracket = family.getTaxableIncome();
        }
        // If given bracket 1 and taxable income exceeds the max value, return the max value (avoids out of bounds exception)
        else if (b == 1 && family.getTaxableIncome() > incomeBrackets[family.getFilingStatus() - 1][0]) {
            incomeBracket = incomeBrackets[family.getFilingStatus() - 1][0];
        }
        // If given bracket 5 and taxable income exceeds $160000, subtract $160000 (avoids out of bounds exception)
        else if (b == 5 && family.getTaxableIncome() > incomeBrackets[family.getFilingStatus() - 1][3]) {
            incomeBracket = family.getTaxableIncome() - incomeBrackets[family.getFilingStatus() - 1][3];
        }
        else {
            // If taxable income exceeds the max value in bracket b, then subtract the max value from the previous bracket
            if (family.getTaxableIncome() > incomeBrackets[family.getFilingStatus() - 1][b-1]) {
                incomeBracket = incomeBrackets[family.getFilingStatus() - 1][b-1] - incomeBrackets[family.getFilingStatus() - 1][b-2];
            }
            // If taxable income falls within bracket b's range, then subtract the min value
            else if (family.getTaxableIncome() <= incomeBrackets[family.getFilingStatus() - 1][b-1]) {
                incomeBracket = family.getTaxableIncome() - incomeBrackets[family.getFilingStatus() - 1][b-2];
            }
        }
        return incomeBracket;
    }
    /**
     * Returns the tax rate that corresponds to bracket b and filing status f.
     * @param b given bracket
     * @param f given filing status
     * @return a float value representing the tax rate
     */
    public static float bracketTaxRate(byte b, byte f) {
        return taxRates[f-1][b-1];
    }
}
