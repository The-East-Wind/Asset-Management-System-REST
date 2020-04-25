package com.cg.assetmanagementsystem.services;

import com.cg.assetmanagementsystem.daos.AssetDAO;
import com.cg.assetmanagementsystem.entities.Asset;
import com.cg.assetmanagementsystem.exceptions.AssetNotFoundException;
import com.cg.assetmanagementsystem.exceptions.DeleteAllottedAssetException;
import com.cg.assetmanagementsystem.exceptions.ReportGenerationException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
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
	public ByteArrayInputStream generateAssetReport() throws ReportGenerationException{
		Iterable<Asset> allAssets = assetDAO.findAll();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Asset Details");
		String headers[] = new String[]{"Id","Name","Description","Category","Availability","Allotted To"};
		Row row = sheet.createRow(0);
		int cellnum=0;
		for(String header: headers){
			Cell cell = row.createCell(cellnum++);
			cell.setCellValue(header);
		}
		int rownum = 1;
		List<String[]> assetData = new ArrayList<>();
		allAssets.forEach(asset-> assetData.add(new String[]{
				asset.getAssetId().toString(),
				asset.getAssetName(),
				asset.getAssetDescription(),
				asset.getAssetCategory(),
				asset.getAvailability(),
				asset.getAllottedTo()!= null?asset.getAllottedTo().getEmployeeId().toString():""
		}));
		for(String[] asset:assetData){
			cellnum=0;
			row = sheet.createRow(rownum++);
			for(String data:asset){
				Cell cell = row.createCell(cellnum++);
				cell.setCellValue(data);
			}
		}
		ByteArrayOutputStream bos;
		byte[] byteArrayData;
		try {
			bos = new ByteArrayOutputStream();
			workbook.write(bos);
			byteArrayData = bos.toByteArray();
			bos.close();
		}
		catch(IOException exception){
			throw new ReportGenerationException("An error occurred when generating Asset Report",exception);
		}
		return new ByteArrayInputStream(byteArrayData);
	}
}
