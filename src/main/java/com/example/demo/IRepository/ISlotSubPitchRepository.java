package com.example.demo.IRepository;


import com.example.demo.Model.SlotSubPitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISlotSubPitchRepository extends JpaRepository<SlotSubPitch, Long> {
}
