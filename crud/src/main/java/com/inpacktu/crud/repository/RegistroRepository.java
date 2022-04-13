package com.inpacktu.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inpacktu.crud.entity.Registro;

@Repository
public interface RegistroRepository extends MongoRepository<Registro,String>{

}
