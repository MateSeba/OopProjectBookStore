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

    public String getMainStreet() { return mainStreet; }
    public void setMainStreet(String mainStreet) { this.mainStreet = mainStreet; }

    public String getSecondaryStreet() { return secondaryStreet; }
    public void setSecondaryStreet(String secondaryStreet) { this.secondaryStreet = secondaryStreet; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    @Override
    public String toString() {
        return "Address{mainStreet=" + getMainStreet() + ", sector=" + getSector() + "}";
    }
}