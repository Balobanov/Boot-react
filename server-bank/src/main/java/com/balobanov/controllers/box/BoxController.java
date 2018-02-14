package com.balobanov.controllers.box;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIConnectionListener;
import com.box.sdk.BoxAPIException;
import com.box.sdk.BoxFolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/box")
public class BoxController {

    @Value("${multipart.upload.files.location}")
    private String uploadFolder;

    @Value("${box.access_token}")
    private String access_token;

    @Value("${box.refresh_token}")
    private String refresh_token;

    @Value("${box.client.secret}")
    private String client_secret;

    @Value("${box.client.id}")
    private String client_id;

    @RequestMapping(value = "/file", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void uploadFile(@RequestBody MultipartFile file) throws IOException {
        BoxAPIConnection api = new BoxAPIConnection(client_id, client_secret, access_token, refresh_token);
        api.setAutoRefresh(true);

        api.addListener(new BoxAPIConnectionListener() {
            @Override
            public void onRefresh(BoxAPIConnection api) {
                System.out.println("Refreshed");
            }

            @Override
            public void onError(BoxAPIConnection api, BoxAPIException error) {
                System.out.println("Error");
            }
        });
        api.refresh();

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);

        try (ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes())) {
            rootFolder.uploadFile(stream, file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
