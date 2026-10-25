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

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getVehiclePlate() { return vehiclePlate; }
    public void setVehiclePlate(String vehiclePlate) { this.vehiclePlate = vehiclePlate; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Address getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(Address currentLocation) { this.currentLocation = currentLocation; }

    @Override
    public String toString() {
        return "DeliveryDriver{id=" + getId() + ", name=" + getName() + ", vehiclePlate=" + getVehiclePlate() + "}";
    }
}