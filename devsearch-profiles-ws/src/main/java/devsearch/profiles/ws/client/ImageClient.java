package devsearch.profiles.ws.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import devsearch.profiles.ws.ui.model.request.ProfileImageRequest;
import devsearch.profiles.ws.ui.model.response.ImageResponse;

@FeignClient("images-ws")
public interface ImageClient {

    @PostMapping("/images/profile")
    public ResponseEntity<ImageResponse> addProfileImage(@RequestBody ProfileImageRequest imageRequest);
}
