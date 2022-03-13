package com.negra.location.service;

import com.negra.location.entity.*;
import com.negra.location.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private FraisService fraisService;

    @Autowired
    private EntretienService entretienService;

    @Autowired
    private ReservationService reservationService;

    public void createVoiture(Voiture voiture, Model model, Agent agent){
        model.addVoiture(voiture);
        agent.addVoiture(voiture);
        voitureRepository.save(voiture);
    }

    public void deleteVoiture(Voiture voiture){
        voiture.getModel().removeVoiture(voiture);
        voiture.getAgent().removeVoiture(voiture);

        // Suppression des frais associes
        Set<Frais> frais = voiture.getListFrais();
        for (Frais frai: frais)
            fraisService.deleteFrais(frai);

        // Supression des entretiens assicies
        Set<Entretien> entretiens = voiture.getEntretiens();
        for (Entretien entretien: entretiens)
            entretienService.deleteEntretien(entretien);

        // Suppression des reservation
        Set<Reservation> reservations = voiture.getReservations();
        for (Reservation reservation: reservations)
            reservationService.deleteReservation(reservation);

        voitureRepository.delete(voiture);
    }

}
