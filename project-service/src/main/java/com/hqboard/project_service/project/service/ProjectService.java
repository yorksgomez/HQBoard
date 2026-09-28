package com.hqboard.project_service.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqboard.project_service.project.dto.CreateProjectRequest;
import com.hqboard.project_service.project.dto.ProjectResponse;
import com.hqboard.project_service.project.dto.UpdateProjectRequest;
import com.hqboard.project_service.project.entity.Project;
import com.hqboard.project_service.project.entity.ProjectStatus;
import com.hqboard.project_service.project.exception.ProjectNotFoundException;
import com.hqboard.project_service.project.mapper.ProjectMapper;
import com.hqboard.project_service.project.repository.ProjectRepository;

import java.util.List;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(
            ProjectRepository projectRepository,
            ProjectMapper projectMapper
    ) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectResponse createProject(CreateProjectRequest request) {

        Project project = projectMapper.toEntity(request);

        Project savedProject = projectRepository.save(project);

        return projectMapper.toResponse(savedProject);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjects() {

        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProjectResponse getProject(Long projectId) {

        Project project = findProjectById(projectId);

        return projectMapper.toResponse(project);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjectsByStatus(
            ProjectStatus status
    ) {

        return projectRepository.findByStatus(status)
                .stream()
                .map(projectMapper::toResponse)
                .toList();
    }

    public ProjectResponse updateProject(
            Long projectId,
            UpdateProjectRequest request
    ) {

        Project project = findProjectById(projectId);

        projectMapper.updateEntity(project, request);

        Project updatedProject = projectRepository.save(project);

        return projectMapper.toResponse(updatedProject);
    }

    public void deleteProject(Long projectId) {

        Project project = findProjectById(projectId);

        projectRepository.delete(project);
    }

    private Project findProjectById(Long projectId) {

        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
    }
}