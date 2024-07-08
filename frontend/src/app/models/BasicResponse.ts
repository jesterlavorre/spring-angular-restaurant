export class BasicResponse {
  status: string;
  statusMessage: string;

  constructor(status: string, statusMessage: string) {
    this.status = status;
    this.statusMessage = statusMessage;
  }
}
