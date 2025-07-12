package com.example.demo.repo;




import com.example.demo.model.RoleEntity;



import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	RoleEntity findByRoleName(String roleName);
}
