package devsearch.developers.ws.service;

import java.util.List;

import devsearch.common.exception.DevsearchApiException;
import devsearch.developers.ws.shared.dto.DeveloperDto;
import devsearch.developers.ws.shared.dto.DeveloperListDto;

public interface DeveloperService {

    public DeveloperDto getDeveloperByDeveloperId(String developerId);

    public DeveloperDto getDeveloperByUsername(String username);

    public DeveloperDto createDeveloper(DeveloperDto developerDto) throws DevsearchApiException;

    public DeveloperDto updateDeveloper(DeveloperDto developerDto) throws DevsearchApiException;

    public void deleteDeveloper(String developerId) throws DevsearchApiException;

    public DeveloperListDto getDevelopers(int page, int limit, String searchText) throws DevsearchApiException;

    public void initialSeed(List<DeveloperDto> developerDto) throws DevsearchApiException;
}
