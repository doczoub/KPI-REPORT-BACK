package com.zoubey.api_indicateur.Controller;

import com.zoubey.api_indicateur.Exception.ressourceNotFoundException;
import com.zoubey.api_indicateur.Model.Suivi;
import com.zoubey.api_indicateur.Repositories.SuiviRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/suivi")
public class SuiviController {
    @Autowired
    SuiviRepository suiviRepository;


    //list
    @GetMapping
    public List<Suivi> getAllCalcul(){

        return suiviRepository.findAll();
    }
    @GetMapping("/calculId/{id}")
    public ResponseEntity<List<Suivi>> getByUserId(@PathVariable Long id){
        List<Suivi> suivi = suiviRepository
                .findByIdCalcul_Id(id);
       return  ResponseEntity.ok(suivi);
  }

//    @GetMapping("/Etat")
//    public ResponseEntity <List<Calcul>> getCalculEtat(@RequestParam String Etat){
//        return new  ResponseEntity<List<Calcul>>(calculRepository.findByEtat(Etat), HttpStatus.OK);
//    }


    //creation
    @PostMapping
    public Suivi createSuivi(@RequestBody Suivi service){
        return suiviRepository.save(service);
    }

    //detail user
    @GetMapping("{id}")
    public ResponseEntity<Suivi> getCalculId(@PathVariable Long id){
        Suivi service = suiviRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
        return ResponseEntity.ok(service);
    }

//    @PostMapping("/{userId}")
//    public ResponseEntity<Suivi> saveCalculId(@PathVariable User userId, @RequestBody Calcul calculDetail){
//        suiviRepository.findById(userId.getId())
//                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
//        suiviRepository.save(calculDetail);
//        return ResponseEntity.ok(calculDetail);
//    }


    //update
    @PutMapping("{id}")
    public  ResponseEntity<Suivi> updateSuivi(@PathVariable Long id, @RequestBody Suivi serviceDetail){
        Suivi updateSuivi = suiviRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("user n'existe pas"));
        updateSuivi.setAction(serviceDetail.getAction());
        updateSuivi.setEfficacite(serviceDetail.getEfficacite());
        updateSuivi.setJustificationEfficacite(serviceDetail.getJustificationEfficacite());
        updateSuivi.setDateAction(serviceDetail.getDateAction());

        suiviRepository.save(updateSuivi);
        return ResponseEntity.ok(updateSuivi);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Suivi> deleteCalcul(@PathVariable Long id) {
        Suivi service = suiviRepository
                .findById(id)
                .orElseThrow(() -> new ressourceNotFoundException("l'utilisateur n'existe pas"));

        suiviRepository.delete(service);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
