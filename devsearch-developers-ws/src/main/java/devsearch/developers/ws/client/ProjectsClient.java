package devsearch.developers.ws.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import devsearch.developers.ws.ui.model.response.ProjectResponse;
import feign.Headers;

@FeignClient("projects-ws")
public interface ProjectsClient {

    @GetMapping("/projects/all/{developerId}")
    @Headers("Content-Type: application/json")
    public ResponseEntity<List<ProjectResponse>> getProjectsForDeveloper(
	    @PathVariable(name = "developerId") String developerId,
	    @RequestHeader(value = "Authorization", required = true) String authorizationHeader);
}
