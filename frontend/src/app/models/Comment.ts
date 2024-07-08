export class Comment {
  text: string;
  insertTime: string;
  commentBy: string;
  rating: string;

  constructor(
    text: string,
    insertTime: string,
    commentBy: string,
    rating: string
  ) {
    this.text = text;
    this.insertTime = insertTime;
    this.commentBy = commentBy;
    this.rating = rating;
  }
}
