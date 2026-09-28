package com.hqboard.project_service.project.mapper;

import org.springframework.stereotype.Component;

import com.hqboard.project_service.project.dto.CreateProjectRequest;
import com.hqboard.project_service.project.dto.ProjectResponse;
import com.hqboard.project_service.project.dto.UpdateProjectRequest;
import com.hqboard.project_service.project.entity.Project;

@Component
public class ProjectMapper {

    public Project toEntity(CreateProjectRequest request) {
        return new Project(
                request.name(),
                request.description(),
                request.status()
        );
    }

    public ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStatus(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }

    public void updateEntity(
            Project project,
            UpdateProjectRequest request
    ) {
        project.update(
                request.name(),
                request.description(),
                request.status()
        );
    }
}