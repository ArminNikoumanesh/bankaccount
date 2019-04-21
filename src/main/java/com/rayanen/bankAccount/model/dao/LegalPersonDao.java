package com.rayanen.bankAccount.model.dao;

import com.rayanen.bankAccount.model.entity.LegalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LegalPersonDao extends JpaRepository<LegalPerson, Integer> {

    @Query("select c from LegalPerson c where c.name Like %:name% and  c.companyCode =:code")
    List<LegalPerson> findByNameAndCompanyCode(@Param("name") String name, @Param("code") String code);

    LegalPerson findByCompanyCode(String companyCode);

    boolean existsByCompanyCode(String companyCode);

}
