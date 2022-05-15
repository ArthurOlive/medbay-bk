package com.br.main.utils;

import com.br.main.controllers.dtos.DoctorDTO;
import com.br.main.controllers.dtos.DoctorInsertDTO;
import com.br.main.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoctorFactory {

    public static Doctor createDoctorForInsert() {

        DoctorInsertDTO doctor = new DoctorInsertDTO();

        doctor.setUsername("doutor01");
        doctor.setPassword("123456");
        doctor.setName("José Silva");
        doctor.setBirth(LocalDate.EPOCH);
        doctor.setDocument("056.252.020-10");
        doctor.setCrm("05625202010");
        doctor.setGender(GenderEnum.MALE);
        doctor.setAddress(createAddress());
        doctor.setMedaData(createMetaDataList());

        return DoctorInsertDTO.toDoctor(doctor);
    }

    public static Doctor createDoctor() {

        DoctorDTO doctor = new DoctorDTO();

        doctor.setId(1L);
        doctor.setUsername("paciente01");
        doctor.setName("José Silva");
        doctor.setBirth(LocalDate.EPOCH);
        doctor.setDocument("056.252.020-10");
        doctor.setCrm("05625202010");
        doctor.setGender(GenderEnum.MALE);
        doctor.setAddress(createAddress());
        doctor.setMedaData(createMetaDataList());

        return DoctorDTO.toDoctor(doctor);
    }

    private static Address createAddress() {

        Address address = new Address();

        address.setCep("21535-900");
        address.setCountry("Brasil");
        address.setUf("RJ");
        address.setCity("Rio de Janeiro");
        address.setStreet("Rodovia Presidente Dutra");
        address.setNumber("2660");
        address.setComplement("Pavuna");

        return address;
    }

    public static Role createDoctorRole() {

        Role doctorRole = new Role();

        doctorRole.setId(1L);
        doctorRole.setName("MEDICO");
        doctorRole.setPrivileges(Collections.emptyList());

        return doctorRole;
    }

    private static List<MetaData> createMetaDataList() {

        List<MetaData> metaDataList = new ArrayList<>();
        MetaData metaData1 = new MetaData();
        MetaData metaData2 = new MetaData();

        metaData1.setKey("Diabetes");
        metaData1.setValue("Tipo 1");

        metaData2.setKey("Sangue");
        metaData2.setValue("A+");

        metaDataList.add(metaData1);
        metaDataList.add(metaData2);

        return metaDataList;
    }
}
