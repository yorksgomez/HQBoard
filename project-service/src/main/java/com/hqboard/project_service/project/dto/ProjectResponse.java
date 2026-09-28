package com.hqboard.project_service.project.dto;

import java.time.LocalDateTime;

import com.hqboard.project_service.project.entity.ProjectStatus;

public record ProjectResponse(

        Long id,

        String name,

        String description,

        ProjectStatus status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}