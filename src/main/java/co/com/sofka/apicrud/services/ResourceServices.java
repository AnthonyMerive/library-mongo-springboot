package co.com.sofka.apicrud.services;

import co.com.sofka.apicrud.models.Resource;
import co.com.sofka.apicrud.repositories.IResourceDAO;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        resource.setAvailable(request.getAvailable());
        resource.setReturnDate(request.getReturnDate());
        resource.setType(request.getType());
        resource.setThematic(request.getThematic());

        return repository.save(resource);
    }

    public Resource updateName(String name, String id) {

        Optional<Resource> resources = Optional.ofNullable(this.getById(id));

        Resource resource = resources.get();

        resource.setName(name);

        return repository.save(resource);
    }

    public Resource updateReturnDate(LocalDate date, String id) {

        Optional<Resource> resources = Optional.ofNullable(this.getById(id));

        Resource resource = resources.get();

        resource.setReturnDate(date);

        return repository.save(resource);
    }

    public Resource updateType(String type, String id) {

        Optional<Resource> resources = Optional.ofNullable(this.getById(id));

        Resource resource = resources.get();

        resource.setType(type);

        return repository.save(resource);
    }

    public Resource updateThematic(String thematic, String id) {

        Optional<Resource> resources = Optional.ofNullable(this.getById(id));

        Resource resource = resources.get();

        resource.setThematic(thematic);

        return repository.save(resource);
    }

    public Boolean availability(String id) {
        try {
            return this.getById(id).getAvailable();
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean returnResource(String id) {
        Optional<Resource> resources = Optional.ofNullable(this.getById(id));

        Resource resource = resources.get();

        if (resource.getId() != null) {
            resource.setAvailable(true);
            this.update(resource, id);
            return this.availability(resource.getId());
        }
        return this.availability(resource.getId());

    }

    public void delete(String id){
        repository.deleteById(id);
    }
}
