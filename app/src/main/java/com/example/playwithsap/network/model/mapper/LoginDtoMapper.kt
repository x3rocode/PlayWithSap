package com.example.playwithsap.network.model.mapper

import com.example.playwithsap.domain.model.Login
import com.example.playwithsap.domain.util.DomainMapper
import com.example.playwithsap.network.model.LoginDto

class LoginDtoMapper : DomainMapper<LoginDto, Login> {
    override fun mapToDomainModel(model: LoginDto): Login {
        return Login(
            model.login
        )
    }

    override fun mapFromDomainModel(domainModel: Login): LoginDto {
        return LoginDto(
            domainModel.login
        )
    }

    fun toDomainList(initial: List<LoginDto>): List<Login>{
        return initial.map { mapToDomainModel(it)}
    }

    fun fromDomainList(initial: List<Login>): List<LoginDto>{
        return initial.map { mapFromDomainModel(it) }
    }

}