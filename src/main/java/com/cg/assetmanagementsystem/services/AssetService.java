package com.cg.assetmanagementsystem.services;

import com.cg.assetmanagementsystem.entities.Asset;
import com.cg.assetmanagementsystem.exceptions.AssetNotFoundException;
import com.cg.assetmanagementsystem.exceptions.DeleteAllottedAssetException;

import java.util.List;

public interface AssetService {
	Asset addAsset(Asset newAsset);
	Asset deleteAsset(Integer assetId) throws AssetNotFoundException, DeleteAllottedAssetException;
	List<Asset> getAllAssets();
    Asset getAssetWithId(int assetId) throws AssetNotFoundException;
	Asset modifyAssetWithId(int assetID, Asset modifiedAsset) throws AssetNotFoundException;
	boolean generateAssetReport();
}
