package com.example.demo.IRepository;

import com.example.demo.Model.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPitchRepository extends JpaRepository<Pitch, Long> {
}
