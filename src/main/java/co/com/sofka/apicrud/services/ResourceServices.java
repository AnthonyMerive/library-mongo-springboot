package co.com.sofka.apicrud.services;

import co.com.sofka.apicrud.models.Resource;
import co.com.sofka.apicrud.repositories.IResourceDAO;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceServices {

    @Autowired
    private IResourceDAO repository;

    public List<Resource> getAll(){
        return repository.findAll();
    }

    public Resource getById(String id) throws ResourceNotFoundException {

        return repository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No ID"));
    }

    public Resource add(Resource resource){
        return repository.insert(resource);
    }

    public Resource update(Resource request, String id) {

        Optional<Resource> resources = Optional.ofNullable(this.getById(id));

        Resource resource = resources.get();

        resource.setName(request.getName());
        resource.setisAvailable(request.getisAvailable());
        resource.setReturnDate(request.getReturnDate());
        resource.setType(request.getType());
        resource.setThematic(request.getThematic());

        return repository.save(resource);
    }

    public Boolean availability(String id) {
        try {
            return this.getById(id).getisAvailable();
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean returnResource(String id) {
        Optional<Resource> resources = Optional.ofNullable(this.getById(id));

        Resource resource = resources.get();

        if (resource.getId() != null) {
            resource.setisAvailable(true);
            this.update(resource, id);
            return this.availability(resource.getId());
        }
        return this.availability(resource.getId());

    }

    public void delete(String id){
        repository.deleteById(id);
    }
}
