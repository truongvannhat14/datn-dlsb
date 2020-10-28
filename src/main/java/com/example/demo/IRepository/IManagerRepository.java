package com.example.demo.IRepository;

import com.example.demo.Model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IManagerRepository extends JpaRepository<Manager, Long> {
}
