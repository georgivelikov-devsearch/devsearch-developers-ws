package devsearch.developers.ws.shared.dto;

import java.util.Collection;

public class DeveloperListDto {

    private int totalPages;
    private Collection<DeveloperDto> developers;

    public int getTotalPages() {
	return totalPages;
    }

    public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
    }

    public Collection<DeveloperDto> getDevelopers() {
	return developers;
    }

    public void setDevelopers(Collection<DeveloperDto> developers) {
	this.developers = developers;
    }
}
