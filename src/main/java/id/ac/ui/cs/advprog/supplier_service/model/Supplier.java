package id.ac.ui.cs.advprog.supplier_service.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Supplier {
    private UUID id;
    private String name;
    private String phoneNumber;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    @Builder
    public Supplier(UUID id, String name, String phoneNumber, String address, Date createdAt, Date updatedAt) {
        this.createdAt = createdAt != null ? createdAt : new Date();
        this.updatedAt = updatedAt != null ? updatedAt : new Date();

        setId(id);
        setName(name);
        setPhoneNo(phoneNumber);
        setAddress(address);
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

    public void setId(UUID id) {
        if (id == null) {
            id = UUID.randomUUID();
        }
        this.id = id;
        this.updatedAt = new Date();
    }

    public void setCreatedAt(Date createdAt) {
        if (createdAt == null) {
            createdAt = new Date();
        }
        this.createdAt = createdAt;
    }


}
