package com.example.backend.models;

public class User {
    private String statusId;
    private String creditCardNo;
    private String profilePicture;
    private String email;
    private String contactPhone;
    private String sex;
    private String lastName;
    private String firstName;
    private String securityA;
    private String securityQ;
    private String accountTypeId;
    private String password;
    private String passwordClear;
    private String username;
    private String address;

    public User() {
    }

    public User(String statusId, String creditCardNo, String profilePicture, String email, String contactPhone,
            String sex, String lastName, String firstName, String securityA, String securityQ, String accountTypeId,
            String password, String passwordClear, String username, String address) {
        this.statusId = statusId;
        this.creditCardNo = creditCardNo;
        this.profilePicture = profilePicture;
        this.email = email;
        this.contactPhone = contactPhone;
        this.sex = sex;
        this.lastName = lastName;
        this.firstName = firstName;
        this.securityA = securityA;
        this.securityQ = securityQ;
        this.accountTypeId = accountTypeId;
        this.password = password;
        this.passwordClear = passwordClear;
        this.username = username;
        this.address = address;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecurityA() {
        return securityA;
    }

    public void setSecurityA(String securityA) {
        this.securityA = securityA;
    }

    public String getSecurityQ() {
        return securityQ;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public String getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(String accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordClear() {
        return passwordClear;
    }

    public void setPasswordClear(String passwordClear) {
        this.passwordClear = passwordClear;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
