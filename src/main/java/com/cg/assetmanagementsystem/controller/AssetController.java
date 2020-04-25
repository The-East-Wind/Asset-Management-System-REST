package com.cg.assetmanagementsystem.controller;

import com.cg.assetmanagementsystem.entities.Asset;
import com.cg.assetmanagementsystem.exceptions.AssetNotFoundException;
import com.cg.assetmanagementsystem.exceptions.DeleteAllottedAssetException;
import com.cg.assetmanagementsystem.exceptions.ReportGenerationException;
import com.cg.assetmanagementsystem.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(value="/assets")
@CrossOrigin("http://localhost:4200")
public class AssetController {
    @Autowired
    private AssetService assetService;
    @GetMapping(
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }
    @GetMapping(
            value = "/{id}",
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Asset getAssetWithId(@PathVariable("id") Integer assetId) throws AssetNotFoundException {
        return assetService.getAssetWithId(assetId);
    }
    @PostMapping(
            produces = "application/json",
            headers = "Accept=application/json"
    )
    public Asset addNewAsset(@RequestBody Asset newAsset){
        return assetService.addAsset(newAsset);
    }
    @PutMapping(
            value = "/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public Asset updateAsset(@PathVariable("id") Integer assetId, @RequestBody Asset modifiedAsset) throws AssetNotFoundException{
        return assetService.modifyAssetWithId(assetId,modifiedAsset);
    }
    @DeleteMapping(
            value = "/{id}",
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public Asset deleteAsset(@PathVariable("id") Integer assetId) throws AssetNotFoundException, DeleteAllottedAssetException {
        return assetService.deleteAsset(assetId);
    }
    @GetMapping(
            value = "/report"
    )
    public ResponseEntity<InputStreamResource> getAssetReport() throws ReportGenerationException {
        ByteArrayInputStream in = assetService.generateAssetReport();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment; filename=asset_report.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}
