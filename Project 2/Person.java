/**
 * Person class represents a person filing for their taxes.
 * Serves as a parent class for the Adult and Child class.
 * @author Sivalee Intachit
 */
public class Person {
    private int id;
    private String name;
    private String birthday;
    private String ssn;
    private float grossIncome;
    /** 
     * uniqueID serves as a tracker to produce a unique ID for each Person object.
    */
    private static int uniqueId = 1;

    /**
     * Constructor initializing id to uniqueID.
     * Iterates uniqueID to produce a new unique ID for the next Person instance.
     */
    public Person() {
        id = uniqueId;
        uniqueId++;
    }
    
    /**
     * Setter method which assigns name to the Person object if given valid input.
     * @param name name of a Person object
     * @return a boolean value which represents whether the given name is valid or invalid
     */
    public boolean setName(String name) {
        boolean isValid = true;
        // Checks if name only includes characters a-z, A-Z, and space
        for (int i = 0; i < name.length(); i++) {
            if (Character.isLetter(name.charAt(i)) || Character.isWhitespace(name.charAt(i))) {
                continue;
            }
            else {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            this.name = name;
        }
        return isValid;
    }
    /**
     * Setter method which assigns birthday to a Person object if given valid input.
     * @param birthday birthday of a Person object
     * @return a boolean value which represents whether the given birthday is valid or invalid
     */
    public boolean setBirthday(String birthday) {
        boolean isValid = true;
        // Checks if given string value's length is valid
        if (birthday.length() != 10) {
            return false;
        }
        // Checks if /'s are correctly formatted to separate year, month, and day
        if (birthday.charAt(4) != '/' || birthday.charAt(7) != '/') {
            return false;
        }
        else {
            while (isValid) {
                for (int i = 0; i < 4; i++) { // Checks if year is valid
                    if (!Character.isDigit(birthday.charAt(i))) {
                        isValid = false;
                        break;
                    }
                }
                for (int i = 5; i < 7; i++) { // Checks if month is valid
                    if (!Character.isDigit(birthday.charAt(i))) {
                        isValid = false;
                        break;
                    }
                }
                for (int i = 8; i < 10; i++) { // Checks if day is valid
                    if (!Character.isDigit(birthday.charAt(i))) {
                        isValid = false;
                        break;
                    }
                }
                break;
            }
        }
        if (isValid) {
            this.birthday = birthday;
        }
        return isValid;
    }
    /**
     * Setter method which assigns ssn to a Person object if given valid input.
     * @param ssn social security number of a Person object
     * @return a boolean value which represents whether the given ssn is valid or invalid
     */
    public boolean setSSN(String ssn) {
        boolean isValid = true;
        // Checks if given string value's length is valid
        if (ssn.length() != 11) {
            return false;
        }
        // Checks if -'s are correctly formatted to separate each portion of SSN
        if (ssn.charAt(3) != '-' || ssn.charAt(6) != '-') {
            return false;
        }
        else {
            String subSSN1 = ssn.substring(0,3);
            String subSSN2 = ssn.substring(4,6);
            String subSSN3 = ssn.substring(8);
            while (isValid) {
                for (int i = 0; i < subSSN1.length(); i++) {
                    if (!Character.isDigit(subSSN1.charAt(i))) {
                        isValid = false;
                        break;
                    }
                }
                for (int i = 0; i < subSSN2.length(); i++) {
                    if (!Character.isDigit(subSSN2.charAt(i))) {
                        isValid = false;
                        break;
                    }
                }
                for (int i = 0; i < subSSN3.length(); i++) {
                    if (!Character.isDigit(subSSN3.charAt(i))) {
                        isValid = false;
                        break;
                    }
                }
                break;
            }
        }
        if (isValid) {
            this.ssn = ssn;
        }
        return isValid;
    }
    /**
     * Setter method which assigns grossIncome to a Person object if given valid input.
     * @param grossIncome gross income of a Person object
     * @return a boolean value which represents whether the given gross income is valid or invalid
     */
    public boolean setGrossIncome(float grossIncome) {
        if (grossIncome >= 0) {
            this.grossIncome = grossIncome;
            return true;
        }
        else {
            return false;
        }
    }
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getBirthday() {
        return birthday;
    }
    public String getSSN() {
        return ssn;
    }
    public float getGrossIncome() {
        return grossIncome;
    }

    /**
     * Creates a string displaying the information of the Person
     * @return a string value which displays the name, ssn, and birthday of a Person object (censored)
     */
    public String toString() {
        return name + " xxx-xx-" + ssn.substring(7) + " " + birthday.substring(0,4) + "/**/**";
    }
    /**
     * An empty placeholder to be overidden by child classes Adult and Child.
     * @param family the family which a Person object belongs to
     * @return a placeholder value
     */
    public float deduction(Family family) {
        return (float) 0.0;
    }
}
