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

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.candidates.CandidateResponseDTO;
import com.webAppG9.backend.dto.jobpost.JobPostResponseDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterResponseDTO;
import com.webAppG9.backend.dto.skill.SkillCreateUpdateDTO;
import com.webAppG9.backend.repository.CandidatedRepository;
import com.webAppG9.backend.service.CandidatedService;
import com.webAppG9.backend.service.RecruiterService;

@RestController
@RequestMapping("/api/candidates")
public class CandidatedController {

    private final CandidatedService candidatedService;
    private final RecruiterService recruiterService;
    private final CandidatedRepository candidatedRepository;

    public CandidatedController(CandidatedService candidatedService, RecruiterService recruiterService,
            CandidatedRepository candidatedRepository) {
        this.candidatedService = candidatedService;
        this.recruiterService = recruiterService;
        this.candidatedRepository = candidatedRepository;
    }

    // Obtener datos del candidato por userId
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<CandidateResponseDTO>> getCandidateById(@PathVariable Integer id) {
        return ResponseEntity.ok(candidatedService.getCandidateById(id));
    }

    // Actualizar/ completar datos de registro datos del candidato
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> updateCandidate(
            @PathVariable Integer id,
            @RequestBody CandidateResponseDTO requestDTO) {
        String message = candidatedService.updateCandidate(id, requestDTO);
        return ResponseEntity.ok(ResponseDTO.ok(message));
    }

    // obtener todos los candidatos activos (Recruiter/Admin)
    @GetMapping("/active")
    public ResponseEntity<ResponseDTO<List<CandidateResponseDTO>>> getAllActiveCandidates() {
        List<CandidateResponseDTO> candidates = candidatedService.getAllActiveCandidates();
        return ResponseEntity.ok(ResponseDTO.ok(candidates));
    }

    // Buscar ofertas laborales similares a los skills
    @GetMapping("/{id}/matches")
    public ResponseEntity<ResponseDTO<List<JobPostResponseDTO>>> getMatchingJobPosts(
            @PathVariable Integer id) {
        Candidated candidate = candidatedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        List<JobPostResponseDTO> matches = candidatedService.getMatchingJobPosts(candidate);
        return ResponseEntity.ok(ResponseDTO.ok(matches));
    }

    // Endpoint para actualizar skills
    @PutMapping("/{candidateId}/skills")
    public ResponseEntity<ResponseDTO<String>> updateSkills(
            @PathVariable Integer candidateId,
            @RequestBody SkillCreateUpdateDTO request) {

        String updatedCandidate = candidatedService.updateSkillsByIds(candidateId, request.getSkills());
        return ResponseEntity.ok(ResponseDTO.ok(updatedCandidate));
    }

    // Candidate solicita ser Recruiter metodo que relaciona aAdmin
    @PostMapping("/{id}/request-recruiter")
    public ResponseEntity<ResponseDTO<String>> requestRecruiterUpgrade(
            @PathVariable Integer id,
            @RequestBody RecruiterResponseDTO request) {

        recruiterService.requestRecruiterUpgrade(id, request);
        return ResponseEntity.ok(
                new ResponseDTO<>("Solicitud enviada. Espera aprobación del administrador.", null));
    }

    // Bamnia el estado de disponible o pausado
    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<String>> updateStatus(@PathVariable Integer id) {

        String statusCandidate = candidatedService.updateCandidateStatus(id);
        return ResponseEntity.ok(ResponseDTO.ok(statusCandidate));

    }

}
