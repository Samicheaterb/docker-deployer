package com.samicheaterb.dockerdeployer;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/webhook")
public class WebHookController {

    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody DockerPayload payload) {
        try {
            String dockerImageName = payload.getRepository().getRepoName();
            // Pull the updated Docker image
            ProcessBuilder pullProcessBuilder = new ProcessBuilder("docker", "pull", dockerImageName);
            Process pullProcess = pullProcessBuilder.start();
            pullProcess.waitFor();

            // Stop the existing Docker container
            ProcessBuilder stopProcessBuilder = new ProcessBuilder("docker", "stop", dockerImageName);
            Process stopProcess = stopProcessBuilder.start();
            stopProcess.waitFor();

            // Start a new Docker container with the updated image
            ProcessBuilder startProcessBuilder = new ProcessBuilder("docker", "run", "-d", "--name", dockerImageName, dockerImageName);
            Process startProcess = startProcessBuilder.start();
            startProcess.waitFor();

            return ResponseEntity.ok().build();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to update Docker image");
        }
    }
}
