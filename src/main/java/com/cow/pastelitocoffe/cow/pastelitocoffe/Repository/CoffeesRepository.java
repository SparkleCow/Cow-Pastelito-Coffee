package com.cow.pastelitocoffe.cow.pastelitocoffe.Repository;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Coffees.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeesRepository extends JpaRepository<Coffee, Long> {
}
