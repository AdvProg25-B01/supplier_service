package id.ac.ui.cs.advprog.supplier_service.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Supplier {
    private final UUID id;
    private String name;
    private String phoneNumber;
    private String address;
    private final Date createdAt;
    private Date updatedAt;

    @Builder
    public Supplier(UUID id, String name, String phoneNumber, String address, Date createdAt, Date updatedAt) {
        this.id = id;
        this.createdAt = createdAt != null ? createdAt : new Date();
        this.updatedAt = updatedAt != null ? updatedAt : new Date();

        setName(name);
        setPhoneNo(phoneNumber);
        setAddress(address);
    }

    public String getPhoneNo() {
        return this.phoneNumber;
    }

    public void setPhoneNo(String phoneNo) {
        if (phoneNo == null || phoneNo.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or blank");
        }
        this.phoneNumber = phoneNo;
        this.updatedAt = new Date();
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.name = name;
        this.updatedAt = new Date();
    }

    public void setAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
        this.address = address;
        this.updatedAt = new Date();
    }
}
