package HealthyLife.SpringBootBuild.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import HealthyLife.SpringBootBuild.Model.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Integer>{
	List<EventEntity> findByusername(String name);
}
