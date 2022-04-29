package com.br.main.utils;

import com.br.main.controllers.dtos.PatientDTO;
import com.br.main.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatientFactory {

    public static UserSystem createPatient() {

        PatientDTO patient = new PatientDTO();

        patient.setId(1L);
        patient.setUsername("paciente01");
        patient.setName("José Silva");
        patient.setBirth(LocalDate.EPOCH);
        patient.setDocument("056.252.020-10");
        patient.setGender(GenderEnum.MALE);
        patient.setAddress(createAddress());
        patient.setRole(createPatientRole());
        patient.setMedaData(createMetaDataList());

        return PatientDTO.toUserSystem(patient);
    }

    private static Address createAddress() {

        Address address = new Address();

        address.setId(1L);
        address.setCep("21535-900");
        address.setCountry("Brasil");
        address.setUf("RJ");
        address.setCity("Rio de Janeiro");
        address.setStreet("Rodovia Presidente Dutra");
        address.setNumber("2660");
        address.setComplement("Pavuna");

        return address;
    }

    private static Role createPatientRole() {

        Role patientRole = new Role();

        patientRole.setId(3L);
        patientRole.setName("PACIENTE");
        patientRole.setPrivileges(Collections.emptyList());

        return patientRole;
    }

    private static List<MetaData> createMetaDataList() {

        List<MetaData> metaDataList = new ArrayList<>();
        MetaData metaData1 = new MetaData();
        MetaData metaData2 = new MetaData();

        metaData1.setId(1L);
        metaData1.setKey("Diabetes");
        metaData1.setValue("Tipo 1");

        metaData2.setId(2L);
        metaData2.setKey("Sangue");
        metaData2.setValue("A+");

        metaDataList.add(metaData1);
        metaDataList.add(metaData2);

        return metaDataList;
    }
}
