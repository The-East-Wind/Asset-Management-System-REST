package com.cg.assetmanagementsystem.daos;




import com.cg.assetmanagementsystem.entities.Credential;
import org.springframework.data.repository.CrudRepository;

public interface LoginDAO extends CrudRepository<Credential,String> {
}
