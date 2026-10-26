package ec.edu.espe.projectBookstore.model;

/**
 * @author Lino Diaz, Brain Training, @ESPE
 */
public class Address {

    private String mainStreet;
    private String secondaryStreet;
    private String sector;
    private String reference;

    public Address(String mainStreet, String secondaryStreet, String sector, String reference) {
        this.mainStreet = mainStreet;
        this.secondaryStreet = secondaryStreet;
        this.sector = sector;
        this.reference = reference;
    }

    public String getFormattedAddress() {
        return mainStreet + " and " + secondaryStreet + ", " + sector + " (" + reference + ")";
    }

    public void getCoordinates() {
        System.out.println("Fetching coordinates for address: " + getFormattedAddress());
    }

    /**
     * @return the mainStreet
     */
    public String getMainStreet() {
        return mainStreet;
    }

    /**
     * @param mainStreet the mainStreet to set
     */
    public void setMainStreet(String mainStreet) {
        this.mainStreet = mainStreet;
    }

    /**
     * @return the secondaryStreet
     */
    public String getSecondaryStreet() {
        return secondaryStreet;
    }

    /**
     * @param secondaryStreet the secondaryStreet to set
     */
    public void setSecondaryStreet(String secondaryStreet) {
        this.secondaryStreet = secondaryStreet;
    }

    /**
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Address{mainStreet=" + getMainStreet() + ", sector=" + getSector() + "}";
    }
}