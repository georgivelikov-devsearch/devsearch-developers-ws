package devsearch.developers.ws.ui.model.request;

public class CommentRequest {

    private String projectId;

    private String commentText;

    private int rating;

    public String getProjectId() {
	return projectId;
    }

    public void setProjectId(String projectId) {
	this.projectId = projectId;
    }

    public String getCommentText() {
	return commentText;
    }

    public void setCommentText(String commentText) {
	this.commentText = commentText;
    }

    public int getRating() {
	return rating;
    }

    public void setRating(int rating) {
	this.rating = rating;
    }

}
