package com.br.main.utils;

import com.br.main.controllers.dtos.RoleEnum;
import com.br.main.controllers.dtos.UserRegisterDTO;

public class Factory {

    public static UserRegisterDTO createUserRegisterDTO() {

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setName("Antonio");
        userRegisterDTO.setDocument("document");
        userRegisterDTO.setUsername("antonio1234");
        userRegisterDTO.setPassword("senhavalida");
        userRegisterDTO.setRole(RoleEnum.MEDICO);

        return userRegisterDTO;
    }
}
