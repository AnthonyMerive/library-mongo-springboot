package co.com.sofka.apicrud.repositories;

import co.com.sofka.apicrud.models.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IResourceDAO extends MongoRepository<Resource, Serializable> {
}
