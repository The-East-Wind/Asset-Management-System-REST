package com.cg.assetmanagementsystem.controller;

import com.cg.assetmanagementsystem.entities.Asset;
import com.cg.assetmanagementsystem.entities.Credential;
import com.cg.assetmanagementsystem.entities.Employee;
import com.cg.assetmanagementsystem.entities.Request;
import com.cg.assetmanagementsystem.exceptions.*;
import com.cg.assetmanagementsystem.services.AssetService;
import com.cg.assetmanagementsystem.services.EmployeeService;
import com.cg.assetmanagementsystem.services.LoginService;
import com.cg.assetmanagementsystem.services.RequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssetManagementSystemRestController {
    @Autowired
    private AssetService assetService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private LoginService loginService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            value = "/assets",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    @ResponseBody
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            value = "/assets/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    @ResponseBody
    public Asset getAssetWithId(@PathVariable("id") Integer assetId) throws AssetNotFoundException {
        return assetService.getAssetWithId(assetId);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(
            value="/assets",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    @ResponseBody
    public Asset addNewAsset(@RequestBody Asset newAsset){
        return assetService.addAsset(newAsset);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(
            value = "/assets/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ResponseBody
    public Asset updateAsset(@PathVariable("id") Integer assetId, @RequestBody Asset modifiedAsset) throws AssetNotFoundException{
        return assetService.modifyAssetWithId(assetId,modifiedAsset);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(
            value = "/assets/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ResponseBody
    public Asset deleteAsset(@PathVariable("id") Integer assetId) throws AssetNotFoundException, DeleteAllottedAssetException {
        return assetService.deleteAsset(assetId);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            value="/employees/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    @ResponseBody
    public Employee getEmployeeWithId(@PathVariable("id") Integer employeeId) throws EmployeeNotFoundException{
        return employeeService.getEmployeeWithId(employeeId);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            value="/requests",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    @ResponseBody
    public List<Request> getPendingRequests(){
        return requestService.getPendingRequests();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            value="/requests/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    @ResponseBody
    public Request getRequestWithId(@PathVariable("id") Integer requestId) throws RequestNotFoundException {
        return requestService.getRequestWithId(requestId);
    }
    @CrossOrigin("http://localhost:4200")
    @PostMapping(
            value = "/requests",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ResponseBody
    public Request addNewRequest(@RequestBody Request newRequest){
        return requestService.addNewRequest(newRequest);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(
            value = "/requests/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ResponseBody
    public Request updateRequest(@PathVariable("id") Integer requestId, @RequestBody Request updatedRequest) throws RequestNotFoundException {
        return requestService.updateRequest(requestId,updatedRequest);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(
            value="/login",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ResponseBody
    public Employee authenticateUser(@RequestBody Credential enteredCredentials) throws LoginException,EmployeeNotFoundException {
        return loginService.authenticateUser(enteredCredentials);
    }
}
