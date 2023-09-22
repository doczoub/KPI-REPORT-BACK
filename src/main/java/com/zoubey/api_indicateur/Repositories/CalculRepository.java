package com.zoubey.api_indicateur.Repositories;

import com.zoubey.api_indicateur.DTO.CalculDTO;
import com.zoubey.api_indicateur.DTO.CalculIndicateurDto;
import com.zoubey.api_indicateur.Model.Calcul;
import com.zoubey.api_indicateur.Model.Indicateur;
import com.zoubey.api_indicateur.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface CalculRepository extends JpaRepository<Calcul, Long> {
    List<Calcul> findByUserId_Id(Long id);

    List<Calcul> findByIndicateur_Id(Long id);

    @Procedure(name = "GetCalculs")
    List<Object[]> getCalculs();



    List<Calcul> findByEtat(String etat);

    List<Calcul> findByAnneeCalcul(int anneeCalcul);

    List<Calcul> findByAnneeCalculAndUserId(int anneeCalcul, User user);


    List<Calcul> findByAnneeCalculAndIndicateur(int year, Indicateur indicateur);

    List<Calcul> findByIndicateur(Indicateur indicateur);


    @Query("Select c from Calcul c left Join c.indicateur i where c.anneeCalcul in (FUNCTION('YEAR', CURRENT_DATE)-1,FUNCTION('YEAR',CURRENT_DATE))")
    List<Calcul> findByannee();

//    @Query("SELECT c FROM Calcul c JOIN c.indicateur i WHERE c.anneeCalcul IN (FUNCTION('YEAR', CURRENT_DATE) - 1, FUNCTION('YEAR', CURRENT_DATE))")
//    List<Calcul> findByAn();

    @Query("SELECT new com.zoubey.api_indicateur.DTO.CalculDTO(c.anneeCalcul, c.periode, c.resultatObtenu, i.Norme) " +
            "FROM Calcul c " +
            "JOIN c.indicateur i " +
            "ORDER BY c.anneeCalcul ASC")
    List<CalculDTO> getCalculsInfo();

    @Query("SELECT NEW com.zoubey.api_indicateur.DTO.CalculIndicateurDto(i.NomMesure, i.Norme, i.periodicite, c.periode, c.resultatObtenu) " +
            "FROM Calcul c JOIN c.indicateur i " +
            "WHERE i.direction.id = :directionId AND c.anneeCalcul = YEAR(CURRENT_DATE)")
    List<CalculIndicateurDto> getCalculIndicateurDtoByDirectionId(Long directionId);

}