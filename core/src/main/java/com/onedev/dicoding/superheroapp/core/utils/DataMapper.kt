package com.onedev.dicoding.superheroapp.core.utils

import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity
import com.onedev.dicoding.superheroapp.core.data.source.remote.response.HeroResults
import com.onedev.dicoding.superheroapp.core.domain.model.Hero

object DataMapper {
    fun mapResponsesToEntities(input: List<HeroResults>): List<HeroEntity> {
        val heroList = ArrayList<HeroEntity>()
        input.map {
            val heroes = HeroEntity(
                id = it.id,
                name = it.name,
                eyeColor = it.appearance.eyeColor ?: "",
                gender = it.appearance.gender ?: "",
                hairColor = it.appearance.hairColor ?: "",
                race = it.appearance.race ?: "",
                height = it.appearance.height[1],
                weight = it.appearance.weight[1],
                alignment = it.biography.alignment ?: "",
                alterEgos = it.biography.alterEgos ?: "",
                firstAppearance = it.biography.firstAppearance ?: "",
                fullName = it.biography.fullName ?: "",
                placeOfBirth = it.biography.placeOfBirth ?: "",
                publisher = it.biography.publisher ?: "",
                groupAffiliation = it.connections.groupAffiliation ?: "",
                relatives = it.connections.relatives ?: "",
                url = it.image.url ?: "",
                combat = (it.powerStats.combat ?: 0).toString(),
                durability = (it.powerStats.durability ?: 0).toString(),
                intelligence = (it.powerStats.intelligence ?: 0).toString(),
                power = (it.powerStats.power ?: 0).toString(),
                speed = (it.powerStats.speed ?: 0).toString(),
                strength = (it.powerStats.strength ?: 0).toString(),
                base = it.work.base ?: "",
                occupation = it.work.occupation ?: "",
                isFavorite = false
            )
            heroList.add(heroes)
        }
        return heroList
    }

    fun mapEntityToDomain(it: HeroEntity) = Hero(
        id = it.id,
        name = it.name,
        eyeColor = it.eyeColor,
        gender = it.gender,
        hairColor = it.hairColor,
        race = it.race,
        height = it.height,
        weight = it.weight,
        alignment = it.alignment,
        alterEgos = it.alterEgos,
        firstAppearance = it.firstAppearance,
        fullName = it.fullName,
        placeOfBirth = it.placeOfBirth,
        publisher = it.publisher,
        groupAffiliation = it.groupAffiliation,
        relatives = it.relatives,
        url = it.url,
        combat = it.combat,
        durability = it.durability,
        intelligence = it.intelligence,
        power = it.power,
        speed = it.speed,
        strength = it.strength,
        base = it.base,
        occupation = it.occupation,
        isFavorite = it.isFavorite
    )

    fun mapEntitiesToDomain(input: List<HeroEntity>): List<Hero> =
        input.map {
            Hero(
                id = it.id,
                name = it.name,
                eyeColor = it.eyeColor,
                gender = it.gender,
                hairColor = it.hairColor,
                race = it.race,
                height = it.height,
                weight = it.weight,
                alignment = it.alignment,
                alterEgos = it.alterEgos,
                firstAppearance = it.firstAppearance,
                fullName = it.fullName,
                placeOfBirth = it.placeOfBirth,
                publisher = it.publisher,
                groupAffiliation = it.groupAffiliation,
                relatives = it.relatives,
                url = it.url,
                combat = it.combat,
                durability = it.durability,
                intelligence = it.intelligence,
                power = it.power,
                speed = it.speed,
                strength = it.strength,
                base = it.base,
                occupation = it.occupation,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(it: Hero) = HeroEntity(
        id = it.id,
        name = it.name,
        eyeColor = it.eyeColor,
        gender = it.gender,
        hairColor = it.hairColor,
        race = it.race,
        height = it.height,
        weight = it.weight,
        alignment = it.alignment,
        alterEgos = it.alterEgos,
        firstAppearance = it.firstAppearance,
        fullName = it.fullName,
        placeOfBirth = it.placeOfBirth,
        publisher = it.publisher,
        groupAffiliation = it.groupAffiliation,
        relatives = it.relatives,
        url = it.url,
        combat = it.combat,
        durability = it.durability,
        intelligence = it.intelligence,
        power = it.power,
        speed = it.speed,
        strength = it.strength,
        base = it.base,
        occupation = it.occupation,
        isFavorite = it.isFavorite
    )
}