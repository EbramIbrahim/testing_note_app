package com.example.testing.common.data.mapper

interface Mapper<Entity, Domain> {

     fun entityToDomain(entity: List<Entity>): List<Domain>

     fun domainToEntityForUpsert(domain: Domain): Entity

     fun domainToEntityForDelete(domain: Domain): Entity

}