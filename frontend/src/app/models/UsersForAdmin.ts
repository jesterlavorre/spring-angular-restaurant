export class UsersForAdmin {
  id: string;
  username: string;
  passwordClear: string;
  password: string;
  accountTypeId: string;
  securityQ: string;
  securityA: string;
  firstName: string;
  lastName: string;
  sex: string;
  contactPhone: string;
  email: string;
  profilePicture: string;
  creditCardNo: string;
  statusId: string;
  insertTime: string;
  address: string;

  constructor(
    id: string,
    username: string,
    passwordClear: string,
    password: string,
    accountTypeId: string,
    securityQ: string,
    securityA: string,
    firstName: string,
    lastName: string,
    sex: string,
    contactPhone: string,
    email: string,
    profilePicture: string,
    creditCardNo: string,
    statusId: string,
    insertTime: string,
    address: string
  ) {
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
}
