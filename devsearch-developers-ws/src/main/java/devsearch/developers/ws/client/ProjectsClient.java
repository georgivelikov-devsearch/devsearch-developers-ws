package devsearch.developers.ws.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import devsearch.developers.ws.ui.model.response.ImageResponse;

@FeignClient("projects-ws")
public interface ProjectsClient {

    @PostMapping("/projects/all/{developerId}")
    public ResponseEntity<ImageResponse> getProjectsForDeveloper(
	    @PathVariable(name = "developerId") String developerId);
}
