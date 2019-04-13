package com.rayanen.bankAccount.model.dao;

import com.rayanen.bankAccount.dto.LegalPersonDto;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LegalPersonDao extends JpaRepository<LegalPerson, Integer> {

    @Query("select c from LegalPerson c where c.name Like %:name% and  c.companyCode =:code")
    List<LegalPersonDto> findByNameAndCompanyCode(@Param("name") String name, @Param("code") String code);

    LegalPersonDto findByCompanyCode( String companyCode);

    boolean existsByCompanyCode(String companyCode);

}
