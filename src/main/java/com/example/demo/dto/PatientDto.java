package com.example.demo.dto;

public class PatientDto {

	private Long patientId;
    private String firstName;
    private String lastName;
    private String contactDetails;
    private String medicalHistory;
    private String insuranceDetails;
	
    public PatientDto(Long patientId, String firstName, String lastName, String contactDetails, String medicalHistory,
			String insuranceDetails) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactDetails = contactDetails;
		this.medicalHistory = medicalHistory;
		this.insuranceDetails = insuranceDetails;
	}

	public PatientDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getInsuranceDetails() {
		return insuranceDetails;
	}

	public void setInsuranceDetails(String insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}

	@Override
	public String toString() {
		return "PatientDto [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", contactDetails=" + contactDetails + ", medicalHistory=" + medicalHistory + ", insuranceDetails="
				+ insuranceDetails + "]";
	}
	
    
    
}
