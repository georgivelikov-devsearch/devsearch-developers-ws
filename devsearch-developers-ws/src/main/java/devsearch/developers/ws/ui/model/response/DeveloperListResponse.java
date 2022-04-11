package devsearch.developers.ws.ui.model.response;

import java.util.Collection;

public class DeveloperListResponse {

    private int totalPages;
    private Collection<DeveloperResponse> developers;

    public int getTotalPages() {
	return totalPages;
    }

    public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
    }

    public Collection<DeveloperResponse> getDevelopers() {
	return developers;
    }

    public void setDevelopers(Collection<DeveloperResponse> developers) {
	this.developers = developers;
    }
}
