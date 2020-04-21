package com.cg.assetmanagementsystem.controller;

import com.cg.assetmanagementsystem.entities.Request;
import com.cg.assetmanagementsystem.exceptions.RequestNotFoundException;
import com.cg.assetmanagementsystem.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public List<Request> getPendingRequests(){
        return requestService.getPendingRequests();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            value="/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    @ResponseBody
    public Request getRequestWithId(@PathVariable("id") Integer requestId) throws RequestNotFoundException {
        return requestService.getRequestWithId(requestId);
    }
    @CrossOrigin("http://localhost:4200")
    @PostMapping(
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ResponseBody
    public Request addNewRequest(@RequestBody Request newRequest){
        return requestService.addNewRequest(newRequest);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(
            value = "/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ResponseBody
    public Request updateRequest(@PathVariable("id") Integer requestId, @RequestBody Request updatedRequest) throws RequestNotFoundException {
        return requestService.updateRequest(requestId,updatedRequest);
    }
}
