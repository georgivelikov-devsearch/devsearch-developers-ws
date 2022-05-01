package devsearch.developers.ws.ui.model.response;

public class CommentResponse {

    private String commentId;

    private String commentText;

    private String author;

    private String authorFullname;

    private String authorPictureUrl;

    private String publicKey;

    private String projectId;

    private int rating;

    public String getCommentId() {
	return commentId;
    }

    public void setCommentId(String commentId) {
	this.commentId = commentId;
    }

    public String getCommentText() {
	return commentText;
    }

    public void setCommentText(String commentText) {
	this.commentText = commentText;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getAuthorFullname() {
	return authorFullname;
    }

    public void setAuthorFullname(String authorFullname) {
	this.authorFullname = authorFullname;
    }

    public String getAuthorPictureUrl() {
	return authorPictureUrl;
    }

    public void setAuthorPictureUrl(String authorPictureUrl) {
	this.authorPictureUrl = authorPictureUrl;
    }

    public String getPublicKey() {
	return publicKey;
    }

    public void setPublicKey(String publicKey) {
	this.publicKey = publicKey;
    }

    public String getProjectId() {
	return projectId;
    }

    public void setProjectId(String projectId) {
	this.projectId = projectId;
    }

    public int getRating() {
	return rating;
    }

    public void setRating(int rating) {
	this.rating = rating;
    }

}
