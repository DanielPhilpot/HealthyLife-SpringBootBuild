package HealthyLife.SpringBootBuild.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import HealthyLife.SpringBootBuild.Model.ScheduleItemEntity;

public interface ScheduleItemRepository extends JpaRepository<ScheduleItemEntity, Integer>{
	List<ScheduleItemEntity> findByusername(String name);
}

