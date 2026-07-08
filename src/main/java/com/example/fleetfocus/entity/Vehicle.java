    package com.example.fleetfocus.entity;

    import com.example.fleetfocus.enums.VehicleStatus;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.EnumType;
    import jakarta.persistence.Enumerated;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;

    @Entity
    @Table(name = "fleetfocus")
    public class Vehicle {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(name="17 Chars",unique = true,nullable = false,length = 17)
        private String vin;   
        @NotBlank(message = "License plate is required")
        
        @Column(name = "license plate", unique = true, nullable = false)
        private String licensePlate;
        
        @NotBlank(message = "Model is required")
        @Column(name ="Model" ,nullable = false)
        private String model;
       
        @Enumerated(EnumType.STRING)
        @NotNull(message = "Vehicle status is required")
        @Column(name = "Status",nullable = false)
        private VehicleStatus status;
        
        @Column(name = "current mileage")
        private Double currentMileage;
        public Vehicle(){

        }
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getVin() {
            return vin;
        }
        public void setVin(String vin) {
            this.vin = vin;
        }
        public String getLicensePlate() {
            return licensePlate;
        }
        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }
        public String getModel() {
            return model;
        }
        public void setModel(String model) {
            this.model = model;
        }
        public VehicleStatus getStatus() {
            return status;
        }
        public void setStatus(VehicleStatus status) {
            this.status = status;
        }
        public Double getCurrentMileage() {
            return currentMileage;
        }
        public void setCurrentMileage(Double currentMileage) {
            this.currentMileage = currentMileage;
        }


    }
