package HealthyLife.SpringBootBuild.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import HealthyLife.SpringBootBuild.Model.UserEntity;
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	List<UserEntity> findByusername(String name);
}
