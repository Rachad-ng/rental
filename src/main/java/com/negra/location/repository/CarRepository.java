package com.negra.location.repository;

import com.negra.location.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByRegistrationNumber(String registrationNumber);

    @Query("SELECT C FROM Car C WHERE C.agent.id = :agentId ORDER BY C.id DESC")
    Page<Car> getAgentCars(@Param("agentId") Long agentId, Pageable pageable);

    @Query("SELECT C FROM Car C ORDER BY C.id DESC")
    Page<Car> findAllOrdredByIdDesc(PageRequest pageRequest);

    // Les critères du similarité sont la categorie, type de carburant et la plage des prix (Ce traitement dépend du cahier de charge)
    @Query("SELECT C FROM Car C WHERE C.category.id = :idCategory AND C.fuel.id = :idFuel AND C.pricePerDay >= :minPrice AND C.pricePerDay <= :maxPrice AND C.id <> :id ORDER BY C.id DESC")
    Page<Car> findSimilarCars(@Param("idCategory") long idCategory, @Param("idFuel") long idFuel, @Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice, @Param("id") long id, Pageable pageable);

    // Select les voitures plus visités
    @Query("SELECT C FROM Car C ORDER BY C.visitSet.size DESC")
    Page<Car> findMostPopular(PageRequest pageRequest);

    // Select les meilleures offres
    @Query("SELECT C FROM Car C ORDER BY (C.model.referencePrice - C.pricePerDay) DESC")
    Page<Car> findHotListingCars(PageRequest pageRequest);

    // Select les voitures non-réservées
    @Query("SELECT C.id FROM Car C WHERE C.bookingSet.size = 0")
    List<Long> getNotReservedCarIds();

    // Select Car without date constrainte
    @Query("SELECT C FROM Car C WHERE C.model.mark.libelle LIKE :markLibelle AND C.model.libelle LIKE :modelLibelle AND C.agent.address.town LIKE :town")
    List<Car> findCarsWithoutDateConstraint(@Param("markLibelle") String markLibelle, @Param("modelLibelle") String modelLibelle, @Param("town") String town);

}