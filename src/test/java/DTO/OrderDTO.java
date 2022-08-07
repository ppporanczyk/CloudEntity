package DTO;

public class OrderDTO {

    private long id;
    private long petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public long getId() {
        return id;
    }

    public OrderDTO setId(long id) {
        this.id = id;
        return this;
    }

    public long getPetId() {
        return petId;
    }

    public OrderDTO setPetId(long petId) {
        this.petId = petId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getShipDate() {
        return shipDate;
    }

    public OrderDTO setShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OrderDTO setStatus(String status) {
        this.status = status;
        return this;
    }

    public boolean isComplete() {
        return complete;
    }

    public OrderDTO setComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

}
