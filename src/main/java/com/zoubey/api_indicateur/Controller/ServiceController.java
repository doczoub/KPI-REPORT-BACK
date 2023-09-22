package com.zoubey.api_indicateur.Controller;

import com.zoubey.api_indicateur.Exception.ressourceNotFoundException;
import com.zoubey.api_indicateur.Model.Service;
import com.zoubey.api_indicateur.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/service")
public class ServiceController {
    @Autowired
    ServiceRepository serviceRepository;


    //list
    @GetMapping
    public List<Service> getAllCalcul(){

        return serviceRepository.findAll();
    }
//    @GetMapping("/userId/{id}")
//    public ResponseEntity<List<Calcul>> getByUserId(@PathVariable Long id){
//        List<Calcul> calcul = serviceRepository
//                .findByUserId_Id(id);
//        return  ResponseEntity.ok(calcul);
//    }

//    @GetMapping("/Etat")
//    public ResponseEntity <List<Calcul>> getCalculEtat(@RequestParam String Etat){
//        return new  ResponseEntity<List<Calcul>>(calculRepository.findByEtat(Etat), HttpStatus.OK);
//    }


    //creation
    @PostMapping
    public Service createCalcul(@RequestBody Service service){
        return serviceRepository.save(service);
    }

    //detail user
    @GetMapping("{id}")
    public ResponseEntity<Service> getCalculId(@PathVariable Long id){
        Service service = serviceRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
        return ResponseEntity.ok(service);
    }

//    @PostMapping("/{userId}")
//    public ResponseEntity<Service> saveCalculId(@PathVariable User userId, @RequestBody Calcul calculDetail){
//        serviceRepository.findById(userId.getId())
//                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
//        serviceRepository.save(calculDetail);
//        return ResponseEntity.ok(calculDetail);
//    }


    //update
    @PutMapping("{id}")
    public  ResponseEntity<Service> updateService(@PathVariable Long id, @RequestBody Service serviceDetail){
        Service updateService = serviceRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("user n'existe pas"));
        updateService.setServiceName(serviceDetail.getServiceName());

        serviceRepository.save(updateService);
        return ResponseEntity.ok(updateService);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Service> deleteCalcul(@PathVariable Long id){
        Service service = serviceRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("l'utilisateur n'existe pas"));

        serviceRepository.delete(service);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
