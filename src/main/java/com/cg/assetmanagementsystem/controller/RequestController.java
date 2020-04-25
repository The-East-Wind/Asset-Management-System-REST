package com.cg.assetmanagementsystem.controller;

import com.cg.assetmanagementsystem.entities.Request;
import com.cg.assetmanagementsystem.exceptions.ReportGenerationException;
import com.cg.assetmanagementsystem.exceptions.RequestNotFoundException;
import com.cg.assetmanagementsystem.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(value="/requests")
public class RequestController {
    @Autowired
    private RequestService requestService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public List<Request> getPendingRequests(){
        return requestService.getPendingRequests();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            value="/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Request getRequestWithId(@PathVariable("id") Integer requestId) throws RequestNotFoundException {
        return requestService.getRequestWithId(requestId);
    }
    @CrossOrigin("http://localhost:4200")
    @PostMapping(
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public Request addNewRequest(@RequestBody Request newRequest){
        return requestService.addNewRequest(newRequest);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(
            value = "/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public Request updateRequest(@PathVariable("id") Integer requestId, @RequestBody Request updatedRequest) throws RequestNotFoundException {
        return requestService.updateRequest(requestId,updatedRequest);
    }
    @CrossOrigin("http://localhost:4200")
    @RequestMapping(
            value = "/report"
    )
    public ResponseEntity<InputStreamResource> getRequestReport() throws ReportGenerationException {
        ByteArrayInputStream in = requestService.generateRequestReport();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment; filename=request_report.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}
