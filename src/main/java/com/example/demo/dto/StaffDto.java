package com.example.demo.dto;

public class StaffDto {

    private Long staffId;
    private String firstName;
    private String lastName;

    public StaffDto() {
        // Empty constructor
    }

    public StaffDto(Long staffId, String firstName, String lastName) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "StaffDto [staffId=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
