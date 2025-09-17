package com.webAppG9.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.candidates.CandidateResponseDTO;
import com.webAppG9.backend.dto.candidates.CandidatedRequestDTO;
import com.webAppG9.backend.dto.jobapplication.ApplicationStatusDTO;
import com.webAppG9.backend.dto.jobpost.JobPostResponseDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterRequestDTO;
import com.webAppG9.backend.dto.skill.SkillRequestDTO;
import com.webAppG9.backend.service.CandidatedService;
import com.webAppG9.backend.service.JobPostService;
import com.webAppG9.backend.service.RecruiterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/candidates")
@Tag(name = "Candidatos", description = "APIs para gestión de candidatos y sus interacciones con trabajos")
public class CandidatedController {

    private final CandidatedService candidatedService;
    private final RecruiterService recruiterService;
    private final JobPostService jobPostService;

    public CandidatedController(CandidatedService candidatedService,
            RecruiterService recruiterService,
            JobPostService jobPostService) {
        this.candidatedService = candidatedService;
        this.recruiterService = recruiterService;
        this.jobPostService = jobPostService;
    }

    @Operation(summary = "Obtener un trabajo por ID", description = "Devuelve los detalles de una publicación de trabajo si está activa.")
    @GetMapping("/{jobPostId}")
    public ResponseEntity<ResponseDTO<JobPostResponseDTO>> getJobPostById(
            @Parameter(description = "ID de la publicación de trabajo", example = "10") @PathVariable Integer jobPostId) {
        JobPostResponseDTO jobPost = jobPostService.getJobPostById(jobPostId);
        return ResponseEntity.ok(ResponseDTO.ok(jobPost));
    }

    @Operation(summary = "Convertir usuario en candidato", description = "Registra un nuevo candidato a partir de la información enviada en el CV.")
    @PostMapping
    public ResponseEntity<ResponseDTO<CandidateResponseDTO>> createCandidateForUser(
            @RequestBody CandidatedRequestDTO candidateRequest) {
        CandidateResponseDTO saved = candidatedService.createCandidateForUser(candidateRequest);
        return ResponseEntity.ok(ResponseDTO.ok(saved));
    }

    @Operation(summary = "Obtener datos del candidato", description = "Recupera los datos del candidato asociado al usuario autenticado.")
    @GetMapping("/getCandidated")
    public ResponseEntity<ResponseDTO<CandidateResponseDTO>> getCandidateById() {
        return ResponseEntity.ok(candidatedService.getCandidateById());
    }

    @Operation(summary = "Actualizar datos del candidato", description = "Permite al candidato actualizar o completar sus datos de registro.")
    @PatchMapping
    public ResponseEntity<ResponseDTO<CandidateResponseDTO>> updateCandidate(
            @RequestBody CandidatedRequestDTO requestDTO) {
        CandidateResponseDTO message = candidatedService.updateCandidate(requestDTO);
        return ResponseEntity.ok(ResponseDTO.ok(message));
    }

    @Operation(summary = "Buscar trabajos según skills", description = "Devuelve las ofertas laborales que coinciden con los skills del candidato.")
    @GetMapping("/matches")
    public ResponseEntity<ResponseDTO<List<JobPostResponseDTO>>> getMatchingJobPosts() {
        List<JobPostResponseDTO> matches = candidatedService.getMatchingJobPosts();
        return ResponseEntity.ok(ResponseDTO.ok(matches));
    }

    @Operation(summary = "Actualizar skills del candidato", description = "Permite modificar los skills de un candidato a partir de una lista de IDs.")
    @PutMapping("/skills")
    public ResponseEntity<ResponseDTO<String>> updateSkills(
            @RequestBody SkillRequestDTO request) {
        String updatedCandidate = candidatedService.updateSkillsByIds(request.getSkills());
        return ResponseEntity.ok(ResponseDTO.ok(updatedCandidate));
    }

    @Operation(summary = "Solicitar ser Recruiter", description = "El candidato envía una solicitud para convertirse en Recruiter. La aprobación queda pendiente del administrador.")
    @PostMapping("/request-recruiter")
    public ResponseEntity<ResponseDTO<String>> requestRecruiterUpgrade(
            @RequestBody RecruiterRequestDTO request) {
        recruiterService.requestRecruiterUpgrade(request);
        return ResponseEntity.ok(
                new ResponseDTO<>("Solicitud enviada. Espera aprobación del administrador.", null));
    }

    @Operation(summary = "Actualizar estado del candidato", description = "Cambia el estado del candidato entre disponible o pausado.")
    @PutMapping("/status")
    public ResponseEntity<ResponseDTO<String>> updateStatus() {
        String statusCandidate = candidatedService.updateCandidateStatus();
        return ResponseEntity.ok(ResponseDTO.ok(statusCandidate));
    }

    @Operation(summary = "Ver estados de aplicaciones", description = "Obtiene los mensajes y estados de las aplicaciones del candidato según lo respondido por los reclutadores.")
    @GetMapping("/my-applications/status")
    public ResponseEntity<ResponseDTO<List<ApplicationStatusDTO>>> getApplicationStatuses() {
        List<ApplicationStatusDTO> statuses = candidatedService.getStatusMessagesForCandidate();
        return ResponseEntity.ok(ResponseDTO.ok(statuses));
    }
}
