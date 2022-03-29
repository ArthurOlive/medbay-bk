package com.br.main.controllers.dtos;

public enum RoleEnum {
    MEDICO("Medico"),
    ATENDENTE("Atendente"),
    PACIENTE("Paciente");

    private String role;
    private RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    } 
}
