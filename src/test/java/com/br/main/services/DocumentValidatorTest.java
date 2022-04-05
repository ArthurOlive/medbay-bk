package com.br.main.services;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class DocumentValidatorTest {
    
    @Test
    @DisplayName("CPF VALID")
    public void validateCpf1() throws Exception {

        //VALID TEST
        assertEquals(DocumentValidatator.isCPF("11204003416"), true);
    }

    @Test
    @DisplayName("CPF INVALID")
    public void validateCpf2() throws Exception {
        //INVALID TEST
        assertEquals(DocumentValidatator.isCPF("11204003413"), false);
    }

    @Test
    @DisplayName("CPF SIZE TEST")
    public void validateCpf3() throws Exception {
        //SIZE TESTE
        assertEquals(DocumentValidatator.isCPF("1120400341"), false);
    }
    
    @Test
    @DisplayName("CPF INVALID TEXT")
    public void validateCpf4() throws Exception {
        //SIZE TESTE
        assertEquals(DocumentValidatator.isCPF("1120400341/"), false);
    }

    @Test
    @DisplayName("CNPJ VALID")
    public void validateCnpj1() throws Exception {
        //SIZE TESTE
        assertEquals(DocumentValidatator.isCNPJ("33654284000199"), true);
    }

    @Test
    @DisplayName("CNPJ INVALID TEST")
    public void validateCnpj2() throws Exception {
        //SIZE TESTE
        assertEquals(DocumentValidatator.isCNPJ("33654184000199"), false);
    }

    @Test
    @DisplayName("CNPJ SIZE TEST")
    public void validateCnpj3() throws Exception {
        //SIZE TESTE
        assertEquals(DocumentValidatator.isCNPJ("3365418400019"), false);
    }

    @Test
    @DisplayName("CNPJ INVALID TEXT")
    public void validateCnpj4() throws Exception {
        //SIZE TESTE
        assertEquals(DocumentValidatator.isCNPJ("336541840001A"), false);
    }
}
