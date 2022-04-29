package devsearch.developers.ws.ui.model.response;

public class CommentResponse {

    private String commentId;

    private String commentText;

    private String developerId;

    private String publicKey;

    private String projectId;

    private boolean positiveFeedback;

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

    public String getDeveloperId() {
	return developerId;
    }

    public void setDeveloperId(String developerId) {
	this.developerId = developerId;
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

    public boolean isPositiveFeedback() {
	return positiveFeedback;
    }

    public void setPositiveFeedback(boolean positiveFeedback) {
	this.positiveFeedback = positiveFeedback;
    }
}
