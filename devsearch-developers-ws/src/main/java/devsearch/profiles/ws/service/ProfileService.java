package devsearch.profiles.ws.service;

import java.util.List;

import devsearch.profiles.ws.exception.RestApiProfilesException;
import devsearch.profiles.ws.shared.dto.ProfileDto;
import devsearch.profiles.ws.shared.dto.ProfileListDto;

public interface ProfileService {

    public ProfileDto getProfileByProfileId(String profileId) throws RestApiProfilesException;

    public ProfileDto getProfileByUsername(String username) throws RestApiProfilesException;

    public ProfileDto createProfile(ProfileDto profileDto) throws RestApiProfilesException;

    public ProfileDto updateProfile(ProfileDto profileDto) throws RestApiProfilesException;

    public void deleteProfile(String profileId) throws RestApiProfilesException;

    public ProfileListDto getProfiles(int page, int limit, String searchText) throws RestApiProfilesException;

    public void initialSeed(List<ProfileDto> profilesDto) throws RestApiProfilesException;
}
