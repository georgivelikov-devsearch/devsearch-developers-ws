package devsearch.developers.ws.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import devsearch.developers.ws.ui.model.response.ProjectResponse;

@FeignClient("projects-ws")
public interface ProjectsClient {

    @GetMapping("/projects/all/{username}")
    public ResponseEntity<List<ProjectResponse>> getProjectsForDeveloper(
	    @PathVariable(name = "username") String username);
}
