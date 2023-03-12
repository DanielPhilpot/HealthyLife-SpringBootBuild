package HealthyLife.SpringBootBuild.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import HealthyLife.SpringBootBuild.Model.MealEntity;

public interface MealRepository extends JpaRepository<MealEntity, Integer>{
	List<MealEntity> findByusername(String name);
}
