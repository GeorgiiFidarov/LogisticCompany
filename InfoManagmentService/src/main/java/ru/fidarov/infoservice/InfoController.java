package ru.fidarov.infoservice;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/info")
public class InfoController {

    private final InfoService infoService;

    @GetMapping("getAll/")
    public ResponseEntity<List<InfoResponse>> getAll() {
        log.info("getting all info");
        List<InfoResponse> infoResponses = infoService.getAllInfo();
        return new ResponseEntity<>(infoResponses, HttpStatus.OK);
    }
}
