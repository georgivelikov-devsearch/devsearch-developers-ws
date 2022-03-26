package devsearch.developers.ws.ui.model.response;

import java.util.Collection;

public class DeveloperListResponse {

    private int totalPages;
    private Collection<DeveloperPublicResponse> developers;

    public int getTotalPages() {
	return totalPages;
    }

    public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
    }

    public Collection<DeveloperPublicResponse> getDevelopers() {
	return developers;
    }

    public void setDevelopers(Collection<DeveloperPublicResponse> developers) {
	this.developers = developers;
    }
}
