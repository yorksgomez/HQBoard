package com.hqboard.project_service.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hqboard.project_service.project.entity.Project;
import com.hqboard.project_service.project.entity.ProjectStatus;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByStatus(ProjectStatus status);
}