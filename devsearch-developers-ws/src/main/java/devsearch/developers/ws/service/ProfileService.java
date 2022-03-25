package devsearch.developers.ws.service;

import java.util.List;

import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.shared.dto.ProfileDto;
import devsearch.developers.ws.shared.dto.ProfileListDto;

public interface ProfileService {

    public ProfileDto getProfileByProfileId(String profileId) throws RestApiDevelopersException;

    public ProfileDto getProfileByUsername(String username) throws RestApiDevelopersException;

    public ProfileDto createProfile(ProfileDto profileDto) throws RestApiDevelopersException;

    public ProfileDto updateProfile(ProfileDto profileDto) throws RestApiDevelopersException;

    public void deleteProfile(String profileId) throws RestApiDevelopersException;

    public ProfileListDto getProfiles(int page, int limit, String searchText) throws RestApiDevelopersException;

    public void initialSeed(List<ProfileDto> profilesDto) throws RestApiDevelopersException;
}
