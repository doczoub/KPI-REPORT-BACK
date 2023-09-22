package com.zoubey.api_indicateur.Controller;

import com.zoubey.api_indicateur.Exception.ressourceNotFoundException;
import com.zoubey.api_indicateur.Model.PlanAction;
import com.zoubey.api_indicateur.Model.User;
import com.zoubey.api_indicateur.Repositories.PlanActionRepository;
import com.zoubey.api_indicateur.Repositories.PlanActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/planAction")
public class PlanActionController {
    @Autowired
    PlanActionRepository planActionRepository;


    //list
    @GetMapping
    public List<PlanAction> getAllCalcul(){

        return planActionRepository.findAll();
    }
    @GetMapping("/userId/{id}")
    public ResponseEntity<List<PlanAction>> getByUserId(@PathVariable Long id){
        List<PlanAction> planAction = planActionRepository
                .findByIdCalcul_Id(id);
        return  ResponseEntity.ok(planAction);
    }

//    @GetMapping("/Etat")
//    public ResponseEntity <List<Calcul>> getCalculEtat(@RequestParam String Etat){
//        return new  ResponseEntity<List<Calcul>>(calculRepository.findByEtat(Etat), HttpStatus.OK);
//    }


    //creation
    @PostMapping
    public PlanAction createCalcul(@RequestBody PlanAction service){
        return planActionRepository.save(service);
    }

    //detail user
    @GetMapping("{id}")
    public ResponseEntity<PlanAction> getCalculId(@PathVariable Long id){
        PlanAction service = planActionRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
        return ResponseEntity.ok(service);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PlanAction> savePlanActionId(@PathVariable User userId, @RequestBody PlanAction planActionDetail){
        planActionRepository.findById(userId.getId())
                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
        planActionRepository.save(planActionDetail);
        return ResponseEntity.ok(planActionDetail);
    }


    //update
    @PutMapping("{id}")
    public  ResponseEntity<PlanAction> updatePlanAction(@PathVariable Long id, @RequestBody PlanAction serviceDetail){
        PlanAction updatePlanAction = planActionRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("user n'existe pas"));
        updatePlanAction.setAction(serviceDetail.getAction());
        updatePlanAction.setResponsableAction(serviceDetail.getResponsableAction());
        updatePlanAction.setDateLivraisonPlanAction(serviceDetail.getDateLivraisonPlanAction());
        updatePlanAction.setEtat(serviceDetail.getEtat());
        updatePlanAction.setSuivi(serviceDetail.getSuivi());
        updatePlanAction.setIdCalcul(serviceDetail.getIdCalcul());
        updatePlanAction.setUserId(serviceDetail.getUserId());

        planActionRepository.save(updatePlanAction);
        return ResponseEntity.ok(updatePlanAction);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PlanAction> deleteCalcul(@PathVariable Long id) {
        PlanAction service = planActionRepository
                .findById(id)
                .orElseThrow(() -> new ressourceNotFoundException("l'utilisateur n'existe pas"));

        planActionRepository.delete(service);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
