export class User {
  statusId: string;
  creditCardNo: string;
  profilePicture: string;
  email: string;
  contactPhone: string;
  sex: string;
  lastName: string;
  firstName: string;
  securityA: string;
  securityQ: string;
  accountTypeId: string;
  password: string;
  passwordClear: string;
  username: string;
  address: string;

  constructor(
    statusId: string,
    creditCardNo: string,
    profilePicture: string,
    email: string,
    contactPhone: string,
    sex: string,
    lastName: string,
    firstName: string,
    securityA: string,
    securityQ: string,
    accountTypeId: string,
    password: string,
    passwordClear: string,
    username: string,
    address: string
  ) {
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
}
