package com.alphaapps.peoplecerttask.core.data.mapper

import com.alphaapps.peoplecerttask.core.data.source.local.entity.StreamSessionEntity
import com.nagwa.files.core.domain.StreamSessionModel

/**
 * @Author: Muhammad Noamany
 * @Email: muhammadnoamany@gmail.com
 */
class StreamSessionMapper : EntityMapper<StreamSessionEntity, StreamSessionModel> {
    override fun mapFromEntity(entity: StreamSessionEntity): StreamSessionModel {
        return StreamSessionModel(entity.id, entity.sessionName, entity.sentBytes)
    }

    override fun mapToEntity(domainModel: StreamSessionModel): StreamSessionEntity {
        return StreamSessionEntity(
            sessionName = domainModel.name,
            sentBytes = domainModel.sentBytes
        )
    }

    override fun mapFromEntityList(entities: List<StreamSessionEntity>): List<StreamSessionModel> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapToEntityList(entities: List<StreamSessionModel>): List<StreamSessionEntity> {
        return entities.map { mapToEntity(it) }
    }

}