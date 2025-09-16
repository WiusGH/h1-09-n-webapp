package com.webAppG9.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.candidates.CandidateResponseDTO;
import com.webAppG9.backend.dto.candidates.CandidatedRequestDTO;
import com.webAppG9.backend.dto.jobapplication.ApplicationStatusDTO;
import com.webAppG9.backend.dto.jobpost.JobPostResponseDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterRequestDTO;
import com.webAppG9.backend.dto.skill.SkillRequestDTO;
import com.webAppG9.backend.service.CandidatedService;
import com.webAppG9.backend.service.RecruiterService;

@RestController
@RequestMapping("/api/candidates")
public class CandidatedController {

    private final CandidatedService candidatedService;
    private final RecruiterService recruiterService;

    public CandidatedController(CandidatedService candidatedService, RecruiterService recruiterService) {
        this.candidatedService = candidatedService;
        this.recruiterService = recruiterService;
    }

    // Convertir en candidato mediante cv
    @PostMapping
    public ResponseEntity<ResponseDTO<CandidateResponseDTO>> createCandidateForUser(
            @RequestBody CandidatedRequestDTO candidateRequest) {

        CandidateResponseDTO saved = candidatedService.createCandidateForUser(candidateRequest);
        return ResponseEntity.ok(ResponseDTO.ok(saved));
    }

    // Obtener datos del candidato por userId
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<CandidateResponseDTO>> getCandidateById(@PathVariable Integer id) {
        return ResponseEntity.ok(candidatedService.getCandidateById(id));
    }

    // Actualizar/ completar datos de registro datos del candidato
    @PatchMapping
    public ResponseEntity<ResponseDTO<String>> updateCandidate(
            @RequestBody CandidatedRequestDTO requestDTO) {
        String message = candidatedService.updateCandidate(requestDTO);
        return ResponseEntity.ok(ResponseDTO.ok(message));
    }

    // Buscar ofertas laborales similares a los skills
    @GetMapping("/matches")
    public ResponseEntity<ResponseDTO<List<JobPostResponseDTO>>> getMatchingJobPosts() {
        List<JobPostResponseDTO> matches = candidatedService.getMatchingJobPosts();
        return ResponseEntity.ok(ResponseDTO.ok(matches));
    }

    // Endpoint para actualizar skills
    @PutMapping("/skills")
    public ResponseEntity<ResponseDTO<String>> updateSkills(
            @RequestBody SkillRequestDTO request) {

        String updatedCandidate = candidatedService.updateSkillsByIds(request.getSkills());
        return ResponseEntity.ok(ResponseDTO.ok(updatedCandidate));
    }

    // Candidate solicita ser Recruiter metodo que relaciona aAdmin
    @PostMapping("/{id}/request-recruiter")
    public ResponseEntity<ResponseDTO<String>> requestRecruiterUpgrade(
            @PathVariable Integer id,
            @RequestBody RecruiterRequestDTO request) {

        recruiterService.requestRecruiterUpgrade(id, request);
        return ResponseEntity.ok(
                new ResponseDTO<>("Solicitud enviada. Espera aprobación del administrador.", null));
    }

    // Camnia el estado de disponible o pausado
    @PutMapping("/status")
    public ResponseEntity<ResponseDTO<String>> updateStatus() {

        String statusCandidate = candidatedService.updateCandidateStatus();
        return ResponseEntity.ok(ResponseDTO.ok(statusCandidate));

    }

    // Buscar el msj y el status por parte de Recruiter
    @GetMapping("/my-applications/status")
    public ResponseEntity<ResponseDTO<List<ApplicationStatusDTO>>> getApplicationStatuses() {
        List<ApplicationStatusDTO> statuses = candidatedService.getStatusMessagesForCandidate();
        return ResponseEntity.ok(ResponseDTO.ok(statuses));
    }

}
