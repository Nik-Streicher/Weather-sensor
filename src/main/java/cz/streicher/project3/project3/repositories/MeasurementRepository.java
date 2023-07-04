package cz.streicher.project3.project3.repositories;

import cz.streicher.project3.project3.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    @Query("SELECT COUNT(m) FROM Measurement m WHERE m.raining = true")
    int countRainyDays();
}
