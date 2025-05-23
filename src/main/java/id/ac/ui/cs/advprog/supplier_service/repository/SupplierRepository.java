package id.ac.ui.cs.advprog.supplier_service.repository;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    
    // Custom query to find suppliers by name (case-insensitive partial match)
    @Query("SELECT s FROM Supplier s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Supplier> findByNameContainingIgnoreCase(@Param("name") String name);
}