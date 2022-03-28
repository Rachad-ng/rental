package com.negra.location.repository;

import com.negra.location.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByRegistrationNumber(String registrationNumber);

    @Query("SELECT C FROM Car C ORDER BY C.id DESC ")
    Page<Car> findAllOrdredByIdDesc(PageRequest pageRequest);

    // Les critères du similarité sont la categorie, type de carburant et la page des prix (Ce traitement dépend du cahier de charge)
    @Query("SELECT C FROM Car C WHERE C.category.id = :idCategory AND C.fuel.id = :idFuel AND C.pricePerDay >= :minPrice AND C.pricePerDay <= :maxPrice AND C.id <> :id ORDER BY C.id DESC")
    Page<Car> findSimilarCars(@Param("idCategory") long idCategory, @Param("idFuel") long idFuel, @Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice, @Param("id") long id, Pageable pageable);

    // Select les voitures du luxe
    @Query("SELECT C FROM Car C ORDER BY C.pricePerDay")
    Page<Car> findHotOffers(PageRequest pageRequest);

    // Select les nouvelles annonces
    @Query("SELECT C FROM Car C ORDER BY C.id DESC")
    Page<Car> findNewCars(PageRequest pageRequest);
}