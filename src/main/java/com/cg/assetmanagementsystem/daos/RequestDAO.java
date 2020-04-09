package com.cg.assetmanagementsystem.daos;

import com.cg.assetmanagementsystem.entities.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestDAO extends CrudRepository<Request,Integer> {

}
