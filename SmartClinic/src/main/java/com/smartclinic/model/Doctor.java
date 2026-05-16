package com.smartclinic.model;

public class Doctor {
    private int id;
    private String name;
    private String specialization;
    private String availableDays;
    private String email;

    public Doctor() {}

    public Doctor(int id, String name, String specialization, String availableDays, String email) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.availableDays = availableDays;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getAvailableDays() { return availableDays; }
    public void setAvailableDays(String availableDays) { this.availableDays = availableDays; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Doctor{id=" + id + ", name='" + name + "', specialization='" + specialization + "'}";
    }
}
