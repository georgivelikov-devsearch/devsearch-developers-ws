package devsearch.developers.ws.service;

import java.util.List;

import devsearch.common.exception.DevsearchApiException;
import devsearch.developers.ws.shared.dto.SkillDescriptionDto;

public interface SkillDescriptionService {

    public SkillDescriptionDto getSkillDescriptionBySkillDescriptionId(String skillDescriptionId);

    public SkillDescriptionDto createSkillDescription(SkillDescriptionDto skillDescriptionDto)
	    throws DevsearchApiException;

    public SkillDescriptionDto updateSkillDescription(SkillDescriptionDto skillDescriptionDto)
	    throws DevsearchApiException;

    public void deleteSkillDescription(String username, String skillDescriptionId) throws DevsearchApiException;

    public List<SkillDescriptionDto> updateSkillDescriptionOrder(String username, List<SkillDescriptionDto> tags)
	    throws DevsearchApiException;
}
