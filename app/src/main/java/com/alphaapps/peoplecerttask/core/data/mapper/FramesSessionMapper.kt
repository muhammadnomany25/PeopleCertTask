package com.alphaapps.peoplecerttask.core.data.mapper

import com.alphaapps.peoplecerttask.core.data.source.local.entity.FrameSessionEntity
import com.nagwa.files.core.domain.FrameSessionModel

/**
 * @Author: Muhammad Noamany
 * @Email: muhammadnoamany@gmail.com
 */
class FramesSessionMapper : EntityMapper<FrameSessionEntity, FrameSessionModel> {
    override fun mapFromEntity(entity: FrameSessionEntity): FrameSessionModel {
        return FrameSessionModel(entity.id, entity.sessionName, entity.framesSent)
    }

    override fun mapToEntity(domainModel: FrameSessionModel): FrameSessionEntity {
        return FrameSessionEntity(
            sessionName = domainModel.name,
            framesSent = domainModel.framesSent
        )
    }

    override fun mapFromEntityList(entities: List<FrameSessionEntity>): List<FrameSessionModel> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapToEntityList(entities: List<FrameSessionModel>): List<FrameSessionEntity> {
        return entities.map { mapToEntity(it) }
    }

}