package devsearch.profiles.ws.service;

import devsearch.profiles.ws.exception.RestApiProfilesException;
import devsearch.profiles.ws.shared.dto.ProfileDto;
import devsearch.profiles.ws.shared.dto.ProfileListDto;

public interface ProfileService {

    public ProfileDto getProfileByProfilePrivateId(String profilePrivateId) throws RestApiProfilesException;

    public ProfileDto getProfileByProfilePublicId(String profilePublicId) throws RestApiProfilesException;

    public ProfileDto getProfileByUserId(String userId) throws RestApiProfilesException;

    public ProfileDto createProfile(ProfileDto profileDto) throws RestApiProfilesException;

    public ProfileDto updateProfile(ProfileDto profileDto) throws RestApiProfilesException;

    public void deleteProfile(String profileId) throws RestApiProfilesException;

    public ProfileListDto getPublicProfiles(int page, int limit, String searchText) throws RestApiProfilesException;
}
