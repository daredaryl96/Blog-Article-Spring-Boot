package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
