package com.br.main.repositories;

import com.br.main.models.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    @Query("select c from Consultation c where c.doctor.id = (select d.id from Doctor d where d.user.id = :userId)")
    Page<Consultation> findAllByDoctor(Pageable pageable, @Param("userId") long userId);

    @Query("select c from Consultation c where c.patient.id = (select d.id from UserSystem d where d.id = :userId)")
    Page<Consultation> findAllByPaciente(Pageable pageable, @Param("userId") long userId);

}
