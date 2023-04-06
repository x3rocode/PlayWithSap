//package com.example.playwithsap.network.model.mapper
//
//import com.example.playwithsap.domain.model.Empl
//import com.example.playwithsap.domain.util.DomainMapper
//import com.example.playwithsap.network.model.EmplDto
//import dagger.Provides
//import javax.inject.Singleton
//
//
//class EmplDtoMapper: DomainMapper<EmplDto, Empl> {
//
//    override fun mapToDomainModel(model: EmplDto): Empl {
//        return Empl(
//            model.mandt,
//            model.sabun,
//            model.uuid,
//            model.regdate,
//            model.name,
//            model.age,
//            model.height,
//            model.email
//        )
//    }
//
//    override fun mapFromDomainModel(domainModel: Empl): EmplDto {
//        return EmplDto(
//            domainModel.mandt,
//            domainModel.sabun,
//            domainModel.uuid,
//            domainModel.regdate,
//            domainModel.name,
//            domainModel.age,
//            domainModel.height,
//            domainModel.email
//        )
//    }
//
//    fun toDomainList(initial: List<EmplDto>): List<Empl>{
//        return initial.map { mapToDomainModel(it)}
//    }
//
//    fun fromDomainList(initial: List<Empl>): List<EmplDto>{
//        return initial.map { mapFromDomainModel(it) }
//    }
//}
