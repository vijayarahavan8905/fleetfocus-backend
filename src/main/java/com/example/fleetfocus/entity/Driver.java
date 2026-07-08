package com.example.fleetfocus.entity;

import com.example.fleetfocus.enums.DriverStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private SystemUser user;
    @Column(unique = true, nullable = false)
    private String licenseNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DriverStatus status;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public SystemUser getUser() {
        return user;
    }
    public void setUser(SystemUser user) {
        this.user = user;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    public DriverStatus getStatus() {
        return status;
    }
    public void setStatus(DriverStatus status) {
        this.status = status;
    }
    
}
