package com.apps.geonames.repository;

import com.apps.geonames.repository.model.Geoname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeonamesRepository extends JpaRepository<Geoname,Long> {
    List<Geoname> findByNameContainingIgnoreCase(String name);
}
