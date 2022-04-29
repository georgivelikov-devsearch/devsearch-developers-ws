package devsearch.developers.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comments")
public class CommentEntity implements Serializable {

    private static final long serialVersionUID = 2068420395879867316L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, length = 30)
    private String commentId;

    @Column(nullable = false)
    @Size(min = 1, max = 500)
    private String commentText;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private DeveloperEntity developer;

    @Column(nullable = false, unique = true, length = 30)
    private String publicKey;

    @Column(nullable = false, length = 30)
    private String projectId;

    @Column
    private boolean positiveFeedback;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

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

    public DeveloperEntity getDeveloper() {
	return developer;
    }

    public void setDeveloper(DeveloperEntity developer) {
	this.developer = developer;
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
