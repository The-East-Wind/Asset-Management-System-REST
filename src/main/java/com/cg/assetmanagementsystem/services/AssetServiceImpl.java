package com.cg.assetmanagementsystem.services;

import com.cg.assetmanagementsystem.daos.AssetDAO;
import com.cg.assetmanagementsystem.entities.Asset;
import com.cg.assetmanagementsystem.exceptions.AssetNotFoundException;
import com.cg.assetmanagementsystem.exceptions.DeleteAllottedAssetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl implements AssetService {
	@Autowired
	private AssetDAO assetDAO;
	@Override
	public Asset addAsset(Asset newAsset) {
		return assetDAO.save(newAsset);
	}
	@Override
	public Asset deleteAsset(Integer assetId) throws AssetNotFoundException, DeleteAllottedAssetException {
		Optional<Asset> assetToBeDeleted = assetDAO.findById(assetId);
		if(!assetToBeDeleted.isPresent()||assetToBeDeleted.get().getAvailability().equals("Deleted"))
			throw new AssetNotFoundException("Not Asset Found with the assetID="+assetId+".");
		if(assetToBeDeleted.get().getAvailability().equals("Not Available"))
			throw new DeleteAllottedAssetException("Cannot Delete Asset.The Asset you are trying to delete is currently allotted to someone.");
		Asset toBeDeleted = assetToBeDeleted.get();
		toBeDeleted.setAvailability("Deleted");
		return assetDAO.save(toBeDeleted);
	}
	@Override
	public List<Asset> getAllAssets() {
		return ((List<Asset>)assetDAO.findAll()).stream().filter(asset->!asset.getAvailability().equals("Deleted")).collect(Collectors.toList());
	}

	@Override
	public Asset getAssetWithId(int assetId) throws AssetNotFoundException{
		Optional<Asset> assetWithId = assetDAO.findById(assetId);
		if(!assetWithId.isPresent() || assetWithId.get().getAvailability().equals("Deleted")){
			throw new AssetNotFoundException("No Asset found with assetId="+assetId+".");
		}
		return assetWithId.get();
	}
	@Override
	public Asset modifyAssetWithId(int assetID, Asset modifiedAsset) throws AssetNotFoundException {
		Asset originalAsset = getAssetWithId(assetID);
		modifiedAsset.setAssetId(originalAsset.getAssetId());
		return assetDAO.save(modifiedAsset);
	}

	@Override
	public boolean generateAssetReport() {
		return false;
	}
}
