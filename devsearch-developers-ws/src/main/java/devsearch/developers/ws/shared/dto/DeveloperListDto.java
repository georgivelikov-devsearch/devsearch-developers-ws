package devsearch.developers.ws.shared.dto;

import java.util.Collection;

public class DeveloperListDto {

    private int totalPages;
    private Collection<DeveloperDto> profiles;

    public int getTotalPages() {
	return totalPages;
    }

    public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
    }

    public Collection<DeveloperDto> getProfiles() {
	return profiles;
    }

    public void setProfiles(Collection<DeveloperDto> profiles) {
	this.profiles = profiles;
    }
}
