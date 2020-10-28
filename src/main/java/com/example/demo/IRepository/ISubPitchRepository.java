package com.example.demo.IRepository;


import com.example.demo.Model.SubPitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubPitchRepository extends JpaRepository<SubPitch, Long> {
}
