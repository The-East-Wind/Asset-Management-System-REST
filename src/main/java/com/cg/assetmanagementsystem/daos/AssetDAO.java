package com.cg.assetmanagementsystem.daos;

import com.cg.assetmanagementsystem.entities.Asset;
import org.springframework.data.repository.CrudRepository;

public interface AssetDAO extends CrudRepository<Asset,Integer> {

}
