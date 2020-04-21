package com.cg.assetmanagementsystem.controller;

import com.cg.assetmanagementsystem.entities.Asset;
import com.cg.assetmanagementsystem.exceptions.AssetNotFoundException;
import com.cg.assetmanagementsystem.exceptions.DeleteAllottedAssetException;
import com.cg.assetmanagementsystem.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/assets")
public class AssetController {
    @Autowired
    private AssetService assetService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            produces = "application/json",
            headers = "Accept=application/json"
    )
    @ResponseBody
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(
            value = "/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    @ResponseBody
    public Asset getAssetWithId(@PathVariable("id") Integer assetId) throws AssetNotFoundException {
        return assetService.getAssetWithId(assetId);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(
            produces = "application/json",
            headers = "Accept=application/json"
    )
    @ResponseBody
    public Asset addNewAsset(@RequestBody Asset newAsset){
        return assetService.addAsset(newAsset);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(
            value = "/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ResponseBody
    public Asset updateAsset(@PathVariable("id") Integer assetId, @RequestBody Asset modifiedAsset) throws AssetNotFoundException{
        return assetService.modifyAssetWithId(assetId,modifiedAsset);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(
            value = "/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ResponseBody
    public Asset deleteAsset(@PathVariable("id") Integer assetId) throws AssetNotFoundException, DeleteAllottedAssetException {
        return assetService.deleteAsset(assetId);
    }
}
