package co.com.sofka.apicrud.controllers;

import co.com.sofka.apicrud.models.Resource;
import co.com.sofka.apicrud.repositories.IResourceDAO;
import co.com.sofka.apicrud.services.ResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",
        methods = {RequestMethod.GET,
        RequestMethod.DELETE,
        RequestMethod.POST,
        RequestMethod.DELETE}
)

@RequestMapping("/api")
public class ResourceController {

    @Autowired
    private IResourceDAO repository;

    @Autowired
    private ResourceServices services;

    @GetMapping("/")
    public ResponseEntity<List<Resource>> getAll(){
        return new ResponseEntity<>(services.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getById(
            @PathVariable
                    String id
    ){
        return new ResponseEntity<>(services.getById(id), HttpStatus.OK);
    }

    @GetMapping("/available/{id}")
    public ResponseEntity<String> availability(@PathVariable("id") String id){
        if (Boolean.TRUE.equals(services.availability(id)))
            return new ResponseEntity<>("Resourse available", HttpStatus.OK);

        return new ResponseEntity<>("Resourse unavailable", HttpStatus.OK);
    }

    @GetMapping("/returnResource/{id}")
    public ResponseEntity<String> returnResource(@PathVariable("id") String id){
        if(services.returnResource(id))
            return new ResponseEntity<>("Successfull Returned", HttpStatus.OK);

        return new ResponseEntity<>("Successfull Returned", HttpStatus.CONFLICT);
    }

    @PostMapping("/resource")
    public ResponseEntity<Resource> create(
            @Validated
            @RequestBody
                    Resource resource){
        return new ResponseEntity<>(services.add(resource), HttpStatus.OK);
    }

    @PutMapping("/resource/{id}")
    public ResponseEntity<Resource> edit(
            @PathVariable
                    String id,
            @Validated
            @RequestBody
                    Resource request){
        return new ResponseEntity<>(services.update(request, id), HttpStatus.OK);
    }

    @DeleteMapping("/resource/{id}")
    public ResponseEntity<String> delete(
            @PathVariable
                    String id){

        services.delete(id);

        return new ResponseEntity<>("Resource whit ID: "+id+" Deleted", HttpStatus.OK);
    }

}
