package com.lupart.technologies.TODO.repository;

import com.lupart.technologies.TODO.entity.Role;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
