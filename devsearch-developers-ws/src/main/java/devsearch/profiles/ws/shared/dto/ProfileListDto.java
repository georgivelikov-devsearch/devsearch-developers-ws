package devsearch.profiles.ws.shared.dto;

import java.util.Collection;

public class ProfileListDto {

    private int totalPages;
    private Collection<ProfileDto> profiles;

    public int getTotalPages() {
	return totalPages;
    }

    public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
    }

    public Collection<ProfileDto> getProfiles() {
	return profiles;
    }

    public void setProfiles(Collection<ProfileDto> profiles) {
	this.profiles = profiles;
    }
}
