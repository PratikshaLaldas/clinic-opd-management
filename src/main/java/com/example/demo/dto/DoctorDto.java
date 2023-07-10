package com.example.demo.dto;

public class DoctorDto {
	
    private Long doctorId;
    private String firstName;
    private String lastName;
    private String contactDetails;
    private String specialization;
	
    public DoctorDto(Long doctorId, String firstName, String lastName, String contactDetails, String specialization) {
		super();
		this.doctorId = doctorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactDetails = contactDetails;
		this.specialization = specialization;
	}

	public DoctorDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
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

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	@Override
	public String toString() {
		return "DoctorDto [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", contactDetails=" + contactDetails + ", specialization=" + specialization + "]";
	}

   
}

