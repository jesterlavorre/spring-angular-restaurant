package com.example.backend.models;

public class UsersForAdmin {
    private String id;
    private String username;
    private String passwordClear;
    private String password;
    private String accountTypeId;
    private String securityQ;
    private String securityA;
    private String firstName;
    private String lastName;
    private String sex;
    private String contactPhone;
    private String email;
    private String profilePicture;
    private String creditCardNo;
    private String statusId;
    private String insertTime;
    private String address;

    public UsersForAdmin() {
    }

    public UsersForAdmin(String id, String username, String passwordClear, String password, String accountTypeId,
            String securityQ, String securityA, String firstName, String lastName, String sex, String contactPhone,
            String email, String profilePicture, String creditCardNo, String statusId, String insertTime,
            String address) {
        this.id = id;
        this.username = username;
        this.passwordClear = passwordClear;
        this.password = password;
        this.accountTypeId = accountTypeId;
        this.securityQ = securityQ;
        this.securityA = securityA;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.contactPhone = contactPhone;
        this.email = email;
        this.profilePicture = profilePicture;
        this.creditCardNo = creditCardNo;
        this.statusId = statusId;
        this.insertTime = insertTime;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordClear() {
        return passwordClear;
    }

    public void setPasswordClear(String passwordClear) {
        this.passwordClear = passwordClear;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(String accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getSecurityQ() {
        return securityQ;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public String getSecurityA() {
        return securityA;
    }

    public void setSecurityA(String securityA) {
        this.securityA = securityA;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
