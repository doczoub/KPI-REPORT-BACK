package com.zoubey.api_indicateur.Controller;




import com.zoubey.api_indicateur.DTO.CalculDTO;
import com.zoubey.api_indicateur.DTO.CalculIndicateurDto;
import com.zoubey.api_indicateur.Model.Calcul;
import com.zoubey.api_indicateur.Model.Indicateur;
import com.zoubey.api_indicateur.Model.User;
import com.zoubey.api_indicateur.Repositories.CalculRepository;
import com.zoubey.api_indicateur.Exception.ressourceNotFoundException;
import com.zoubey.api_indicateur.Repositories.IndicateurRepository;
import com.zoubey.api_indicateur.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/calcul")
@Transactional
public class calculController {

    @Autowired
    CalculRepository calculRepository;

    @Autowired
    IndicateurRepository indicateurRepository;
    @Autowired
    public UserRepository userRepository;

    //list
    @GetMapping
    public List<Calcul> getAllCalcul(){

        return calculRepository.findAll();
    }
    @GetMapping("/userId/{id}")
    public ResponseEntity<List<Calcul>> getByUserId(@PathVariable Long id){
        List<Calcul> calcul = calculRepository
                .findByUserId_Id(id);
        return  ResponseEntity.ok(calcul);
    }
    @GetMapping("/indicateurId/{id}")
    public  ResponseEntity<List<Calcul>> getByIndicateurId(@PathVariable Long id){
        List<Calcul> calculs =calculRepository
                .findByIndicateur_Id(id);
        return ResponseEntity.ok(calculs);
    }

    @GetMapping("/Calculindicateur")
    public ResponseEntity<List<Object[]>> getCalculByIndicateur(){
        List<Object[]> calculs =calculRepository
                .getCalculs();
        return ResponseEntity.ok(calculs);
    }

    @GetMapping("/Etat")
    public ResponseEntity <List<Calcul>> getCalculEtat(@RequestParam String Etat){
        return new  ResponseEntity<List<Calcul>>(calculRepository.findByEtat(Etat),HttpStatus.OK);
    }


    @GetMapping("/AnneeCalcul")
    public ResponseEntity <List<Calcul>> getCalculAnnee(){
        return new ResponseEntity<>(calculRepository.findByannee(), HttpStatus.OK);
    }

    @GetMapping("/Anneeinfo")
    public ResponseEntity <List<CalculDTO>> getCalculAn(){
        return new ResponseEntity<>(calculRepository.getCalculsInfo(), HttpStatus.OK);
    }

    @GetMapping("/performance/{directionId}")
    public ResponseEntity< List<CalculIndicateurDto>> getCalculIndicateurDtoByDirectionId(@PathVariable Long directionId) {
        return new ResponseEntity<>(calculRepository.getCalculIndicateurDtoByDirectionId(directionId),HttpStatus.OK) ;
    }


    //creation
    @PostMapping
    public Calcul createCalcul(@RequestBody Calcul calcul){
        return calculRepository.save(calcul);
    }

    //detail user
    @GetMapping("{id}")
    public ResponseEntity<Calcul> getCalculId(@PathVariable Long id){
        Calcul calcul = calculRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
        return ResponseEntity.ok(calcul);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Calcul> saveCalculId(@PathVariable User userId, @RequestBody Calcul calculDetail){
        calculRepository.findById(userId.getId())
                .orElseThrow(()->new ressourceNotFoundException("utilisateur n'a pas ete trouver"));
                calculRepository.save(calculDetail);
        return ResponseEntity.ok(calculDetail);
    }


    //update
    @PutMapping("{id}")
    public  ResponseEntity<Calcul> updateCalcul(@PathVariable Long id, @RequestBody Calcul calculDetail){
        Calcul updateCalcul = calculRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("user n'existe pas"));
        updateCalcul.setPeriode(calculDetail.getPeriode());
        updateCalcul.setResultatObtenu(calculDetail.getResultatObtenu());
        updateCalcul.setEtat(calculDetail.getEtat());
        updateCalcul.setCause(calculDetail.getCause());
        updateCalcul.setTypeAction(calculDetail.getTypeAction());
        updateCalcul.setResponsableCalcul(calculDetail.getResponsableCalcul());
        updateCalcul.setResponsableAnalyse(calculDetail.getResponsableAnalyse());
        updateCalcul.setAnalyseCause(calculDetail.getAnalyseCause());
        updateCalcul.setCause(calculDetail.getCause());
        updateCalcul.setAnalyseCause(calculDetail.getAnalyseCause());
        updateCalcul.setUserId(calculDetail.getUserId());

        calculRepository.save(updateCalcul);
        return ResponseEntity.ok(updateCalcul);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Calcul> deleteCalcul(@PathVariable Long id){
        Calcul calcul = calculRepository
                .findById(id)
                .orElseThrow(()->new ressourceNotFoundException("l'utilisateur n'existe pas"));

        calculRepository.delete(calcul);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //sort-calcul par annee

//    @GetMapping("/sort-resultat")
//    public ResponseEntity<Map<Integer, List<String>>> sortResultatObtenuByAnneeCalcul() {
//        List<Calcul> calculs = calculRepository.findAll();
//        Map<Integer, List<String>> sortedResultatObtenuMap = new TreeMap<>();
//
//        for (Calcul calcul : calculs) {
//            Date anneeCalcul = calcul.getAnneeCalcul();
//            String resultatObtenu = calcul.getResultatObtenu();
//            String periode = calcul.getPeriode();
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(anneeCalcul);
//            int year = calendar.get(Calendar.YEAR);
//
//            List<String> resultatObtenuList = sortedResultatObtenuMap.getOrDefault(year, new ArrayList<>());
//            resultatObtenuList.add(resultatObtenu);
//            resultatObtenuList.add(periode);
//            sortedResultatObtenuMap.put(year, resultatObtenuList);
//        }
//
//        return new ResponseEntity<>(sortedResultatObtenuMap, HttpStatus.OK);
//    }




        @GetMapping("/by-an/{an}")
        public List<Calcul> getCalculsByYear(@PathVariable int an) {
            return calculRepository.findByAnneeCalcul(an);
        }

    @GetMapping("/by-year/{year}/user/{userId}")
    public List<Calcul> getCalculsByYearAndUser(@PathVariable int year, @PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        return calculRepository.findByAnneeCalculAndUserId(year, user);
    }

    @GetMapping("/by-year/{year}/indicateur/{indicateurId}")
    public List<Calcul> getCalculsByYearAndIndicateur(@PathVariable int year, @PathVariable Long indicateurId) {
        Indicateur indicateur = indicateurRepository.findById(indicateurId)
                .orElseThrow(() -> new IllegalArgumentException("Indicateur not found with ID: " + indicateurId));

        return calculRepository.findByAnneeCalculAndIndicateur(year, indicateur);
    }


    @GetMapping("/average-by-year")
        public Map<Integer, Double> getAverageCalculsByYear() {
            List<Calcul> allCalculs = calculRepository.findAll();
            Map<Integer, List<Calcul>> calculsByYear = new HashMap<>();
            for (Calcul calcul : allCalculs) {
                calculsByYear.computeIfAbsent(calcul.getAnneeCalcul(), k -> new ArrayList<>()).add(calcul);
            }

            Map<Integer, Double> averageCalculsByYear = new HashMap<>();
            for (Map.Entry<Integer, List<Calcul>> entry : calculsByYear.entrySet()) {
                int year = entry.getKey();
                List<Calcul> calculsOfYear = entry.getValue();

                double totalCalcul = 0;
                for (Calcul calcul : calculsOfYear) {
                    totalCalcul += Double.parseDouble(calcul.getResultatObtenu());
                }

                double averageCalcul = totalCalcul / calculsOfYear.size();
                averageCalculsByYear.put(year, averageCalcul);
            }

            return averageCalculsByYear;
        }

        // average of calcul per year for a specific indicateur

    @GetMapping("/average-by-year/indicateur/{indicateurId}")

    public Map<Integer, Double> getAverageCalculsByYearAndIndicateur(@PathVariable Long indicateurId) {
        Indicateur indicateur = indicateurRepository.findById(indicateurId)
                .orElseThrow(() -> new IllegalArgumentException("Indicateur not found with ID: " + indicateurId));

        List<Calcul> allCalculs = calculRepository.findByIndicateur(indicateur);
        Map<Integer, List<Calcul>> calculsByYear = new HashMap<>();
        for (Calcul calcul : allCalculs) {
            calculsByYear.computeIfAbsent(calcul.getAnneeCalcul(), k -> new ArrayList<>()).add(calcul);
        }

        Map<Integer, Double> averageCalculsByYear = new HashMap<>();
        for (Map.Entry<Integer, List<Calcul>> entry : calculsByYear.entrySet()) {
            int year = entry.getKey();
            List<Calcul> calculsOfYear = entry.getValue();

            double totalCalcul = 0;
            for (Calcul calcul : calculsOfYear) {
                totalCalcul += Double.parseDouble(calcul.getResultatObtenu());
            }

            double averageCalcul = totalCalcul / calculsOfYear.size();
            averageCalculsByYear.put(year, averageCalcul);
        }

        return averageCalculsByYear;
    }

}
