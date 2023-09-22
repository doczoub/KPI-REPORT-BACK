package com.zoubey.api_indicateur.Controller;

import com.zoubey.api_indicateur.Exception.ressourceNotFoundException;
import com.zoubey.api_indicateur.Model.Direction;
import com.zoubey.api_indicateur.Repositories.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/direction")
public class DirectionController {
    @Autowired
    DirectionRepository directionRepository;


    //list
    @GetMapping
    public List<Direction> getAllCalcul(){

        return directionRepository.findAll();
    }
//    @GetMapping("/userId/{id}")
//    public ResponseEntity<List<Calcul>> getByUserId(@PathVariable Long id){
//        List<Calcul> calcul = directionRepository
//                .findByUserId_Id(id);
//        return  ResponseEntity.ok(calcul);
//    }

//    @GetMapping("/Etat")
//    public ResponseEntity <List<Calcul>> getCalculEtat(@RequestParam String Etat){
//        return new  ResponseEntity<List<Calcul>>(calculRepository.findByEtat(Etat), HttpStatus.OK);
//    }


    //creation
    @PostMapping
    public Direction createCalcul(@RequestBody Direction direction){
        return directionRepository.save(direction);
    }

    //detail user
    @GetMapping("{id}")
    public ResponseEntity<Direction> getCalculId(@PathVariable Long id){
        Direction direction = directionRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
        return ResponseEntity.ok(direction);
    }

//    @PostMapping("/{userId}")
//    public ResponseEntity<Direction> saveCalculId(@PathVariable User userId, @RequestBody Calcul calculDetail){
//        directionRepository.findById(userId.getId())
//                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
//        directionRepository.save(calculDetail);
//        return ResponseEntity.ok(calculDetail);
//    }


    //update
    @PutMapping("{id}")
    public  ResponseEntity<Direction> updateDirection(@PathVariable Long id, @RequestBody Direction directionDetail){
        Direction updateDirection = directionRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("user n'existe pas"));
        updateDirection.setNom(directionDetail.getNom());

        directionRepository.save(updateDirection);
        return ResponseEntity.ok(updateDirection);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Direction> deleteCalcul(@PathVariable Long id){
        Direction direction = directionRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("l'utilisateur n'existe pas"));

        directionRepository.delete(direction);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
