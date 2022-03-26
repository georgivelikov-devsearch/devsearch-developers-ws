package devsearch.developers.ws.service;

import java.util.List;

import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.shared.dto.DeveloperDto;
import devsearch.developers.ws.shared.dto.DeveloperListDto;

public interface DeveloperService {

    public DeveloperDto getDeveloperByDeveloperId(String developerId);

    public DeveloperDto getDeveloperByUsername(String username);

    public DeveloperDto createDeveloper(DeveloperDto developerDto) throws RestApiDevelopersException;

    public DeveloperDto updateDeveloper(DeveloperDto developerDto) throws RestApiDevelopersException;

    public void deleteDeveloper(String developerId) throws RestApiDevelopersException;

    public DeveloperListDto getDevelopers(int page, int limit, String searchText) throws RestApiDevelopersException;

    public void initialSeed(List<DeveloperDto> developerDto) throws RestApiDevelopersException;
}
