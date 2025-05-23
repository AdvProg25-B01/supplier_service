package id.ac.ui.cs.advprog.supplier_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "suppliers")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String phoneNumber;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // Custom constructor
    public Supplier(String name, String phoneNumber, String address) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    // For backwards compatibility
    public void setPhoneNo(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
    
    /**
     * Custom static method to initialize a builder with default values
     * for timestamps and UUID
     */
    public static SupplierBuilder builder() {
        return new SupplierBuilder()
                .id(UUID.randomUUID())
                .createdAt(new Date())
                .updatedAt(new Date());
    }
}