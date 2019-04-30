package com.rayanen.bankAccount.serviceController;

import com.rayanen.bankAccount.model.entity.LegalPerson;

import java.util.List;

public interface LegalPersonService {

     void saveLegal(LegalPerson legalPerson) throws Exception;

     void updateLegal( LegalPerson legalPerson) throws Exception;

     LegalPerson findByCompanyCode( String companyCode) throws Exception;

     List<LegalPerson> findLegalAll(LegalPerson legalPerson) throws Exception;


}
