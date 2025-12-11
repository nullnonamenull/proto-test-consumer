package com.noname.prototestconsumer.controller;

import com.noname.proto.Important;
import com.noname.prototestconsumer.dto.ImportantDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private final RestClient restClient;

    public ConsumerController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/test")
    public ResponseEntity<ImportantDTO> test() {
        final var res = restClient
                .get()
                .uri("/test")
                .accept(MediaType.parseMediaType("application/x-protobuf"))
                .retrieve()
                .body(Important.ImportantDTO.class);

        return ResponseEntity.ok(new ImportantDTO(
                Objects.requireNonNull(res).getA(),
                res.getB())
        );
    }

}
