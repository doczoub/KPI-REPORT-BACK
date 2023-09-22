package com.zoubey.api_indicateur.Controller;

import com.zoubey.api_indicateur.Exception.ressourceNotFoundException;
import com.zoubey.api_indicateur.Model.Calcul;
import com.zoubey.api_indicateur.Model.Indicateur;
import com.zoubey.api_indicateur.Repositories.IndicateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/indicateur")
public class IndicateurController {
    @Autowired
    IndicateurRepository indicateurRepository;


    //list
    @GetMapping
    public List<Indicateur> getAllCalcul(){

        return indicateurRepository.findAll();
    }
    @GetMapping("/userId/{id}")
    public ResponseEntity<List<Indicateur>> getByUserId(@PathVariable Long id){
        List<Indicateur> indicateurs = indicateurRepository
                .findByDirection_Id(id);
        return  ResponseEntity.ok(indicateurs);
    }

//    @GetMapping("/Etat")
//    public ResponseEntity <List<Calcul>> getCalculEtat(@RequestParam String Etat){
//        return new  ResponseEntity<List<Calcul>>(calculRepository.findByEtat(Etat), HttpStatus.OK);
//    }



    //creation
    @PostMapping
    public Indicateur createCalcul(@RequestBody Indicateur service){
        return indicateurRepository.save(service);
    }

    //detail user
    @GetMapping("{id}")
    public ResponseEntity<Indicateur> getCalculId(@PathVariable Long id){
        Indicateur service = indicateurRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
        return ResponseEntity.ok(service);
    }

//    @PostMapping("/{userId}")
//    public ResponseEntity<Indicateur> saveCalculId(@PathVariable User userId, @RequestBody Calcul calculDetail){
//        indicateurRepository.findById(userId.getId())
//                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
//        indicateurRepository.save(calculDetail);
//        return ResponseEntity.ok(calculDetail);
//    }


    //update
    @PutMapping("{id}")
    public  ResponseEntity<Indicateur> updateIndicateur(@PathVariable Long id, @RequestBody Indicateur serviceDetail){
        Indicateur updateIndicateur = indicateurRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("user n'existe pas"));
        updateIndicateur.setCodeMesure(serviceDetail.getCodeMesure());
        updateIndicateur.setNomMesure(serviceDetail.getNomMesure());
        updateIndicateur.setNorme(serviceDetail.getNorme());
        updateIndicateur.setTypeNorme(serviceDetail.getTypeNorme());

        indicateurRepository.save(updateIndicateur);
        return ResponseEntity.ok(updateIndicateur);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Indicateur> deleteCalcul(@PathVariable Long id) {
        Indicateur service = indicateurRepository
                .findById(id)
                .orElseThrow(() -> new ressourceNotFoundException("l'utilisateur n'existe pas"));

        indicateurRepository.delete(service);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
