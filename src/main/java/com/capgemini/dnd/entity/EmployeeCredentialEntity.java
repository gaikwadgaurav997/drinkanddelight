package com.capgemini.dnd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQuery(name="GetOneConfidentialDetail", query="FROM EmployeeCredentialEntity WHERE Username=:username") 
@Table (
name = "EmployeeCredentials", 
	uniqueConstraints = { 
		@UniqueConstraint(columnNames = "EmpId"),
		@UniqueConstraint(columnNames = "Username"), 
		@UniqueConstraint(columnNames = "Hash"),
		@UniqueConstraint(columnNames = "Salt") 
	}
)

public class EmployeeCredentialEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EmpId")
	private String empId;

	@Column(name = "Username")
	private String userName;

	@Column(name = "SecurityQuestion")
	private String securityQuestion;

	@Column(name = "SecurityAnswer")
	private String securityAnswer;

	@Column(name = "ActiveStatus")
	private boolean activeStatus;

	@Column(name = "Hash")
	private String hash;

	@Column(name = "Salt")
	private String salt;

	public EmployeeCredentialEntity() {
	}

	public EmployeeCredentialEntity(String userName, String securityQuestion, String securityAnswer,
			boolean activeStatus, String hash, String salt) {
		this.userName = userName;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.activeStatus = activeStatus;
		this.hash = hash;
		this.salt = salt;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "EmployeeCredentialEntity [empId=" + empId + ", userName=" + userName + ", securityQuestion="
				+ securityQuestion + ", securityAnswer=" + securityAnswer + ", activeStatus=" + activeStatus + ", hash="
				+ hash + ", salt=" + salt + "]";
	}
}
