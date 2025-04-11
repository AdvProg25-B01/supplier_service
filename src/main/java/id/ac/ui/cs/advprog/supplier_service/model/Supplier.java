package id.ac.ui.cs.advprog.supplier_service.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

public class Supplier {
    private  UUID id;
    private String name;
    private String phoneNumber;
    private String address;
    private  Date createdAt;
    private Date updatedAt;

    public Supplier(UUID id, String name, String phoneNumber, String address, Date createdAt, Date updatedAt) {
    }

}
