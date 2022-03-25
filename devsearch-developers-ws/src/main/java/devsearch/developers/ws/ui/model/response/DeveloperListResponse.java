package devsearch.developers.ws.ui.model.response;

import java.util.Collection;

public class DeveloperListResponse {

    private int totalPages;
    private Collection<DeveloperPublicResponse> profiles;

    public int getTotalPages() {
	return totalPages;
    }

    public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
    }

    public Collection<DeveloperPublicResponse> getProfiles() {
	return profiles;
    }

    public void setProfiles(Collection<DeveloperPublicResponse> profiles) {
	this.profiles = profiles;
    }
}
