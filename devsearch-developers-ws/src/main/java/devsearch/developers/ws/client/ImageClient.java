package devsearch.developers.ws.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import devsearch.developers.ws.ui.model.request.DeveloperImageRequest;
import devsearch.developers.ws.ui.model.response.ImageResponse;

@FeignClient("images-ws")
public interface ImageClient {

    @PostMapping("/images/developer")
    public ResponseEntity<ImageResponse> addDeveloperImage(@RequestBody DeveloperImageRequest imageRequest);
}
