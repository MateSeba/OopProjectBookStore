package ec.edu.espe.projectBookstore.model;

/**
 * @author Lino Diaz, Brain Training, @ESPE
 */
public class DeliveryDriver {

    private String id;
    private String name;
    private String vehiclePlate;
    private String phone;
    private String status;
    private Address currentLocation;

    public DeliveryDriver(String id, String name, String vehiclePlate, String phone, String status, Address currentLocation) {
        this.id = id;
        this.name = name;
        this.vehiclePlate = vehiclePlate;
        this.phone = phone;
        this.status = status;
        this.currentLocation = currentLocation;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public float calculateCost() {
        return 5.0f;
    }

    public void seeLocation() {
        if (currentLocation != null) {
            System.out.println("Driver location: " + currentLocation.getFormattedAddress());
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the vehiclePlate
     */
    public String getVehiclePlate() {
        return vehiclePlate;
    }

    /**
     * @param vehiclePlate the vehiclePlate to set
     */
    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the currentLocation
     */
    public Address getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @param currentLocation the currentLocation to set
     */
    public void setCurrentLocation(Address currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public String toString() {
        return "DeliveryDriver{id=" + getId() + ", name=" + getName() + ", vehiclePlate=" + getVehiclePlate() + "}";
    }
}