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
import com.webAppG9.backend.dto.CandidateResponseDTO;
import com.webAppG9.backend.dto.JobPostResponseDTO;
import com.webAppG9.backend.dto.RecruiterResponseDTO;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.SkillUpdateRequestDTO;
import com.webAppG9.backend.repository.CandidatedRepository;
import com.webAppG9.backend.service.CandidatedService;
import com.webAppG9.backend.service.JobPostService;
import com.webAppG9.backend.service.RecruiterService;

@RestController
@RequestMapping("/api/candidates")
public class CandidatedController {

    private final CandidatedService candidatedService;
    private final RecruiterService recruiterService;
    private final CandidatedRepository candidatedRepository;
    private final JobPostService jobPostService;

    public CandidatedController(CandidatedService candidatedService, RecruiterService recruiterService,
            CandidatedRepository candidatedRepository, JobPostService jobPostService) {
        this.candidatedService = candidatedService;
        this.recruiterService = recruiterService;
        this.candidatedRepository = candidatedRepository;
        this.jobPostService = jobPostService;
    }

    // Obtener datos del candidato por userId
    @GetMapping("/me/{id}")
    public ResponseEntity<ResponseDTO<CandidateResponseDTO>> getCandidateById(@PathVariable Integer id) {
        CandidateResponseDTO candidateDTO = candidatedService.getCandidateById(id);
        return ResponseEntity.ok(new ResponseDTO<>(candidateDTO, null));
    }

    // Actualizar datos del candidato
    @PatchMapping("/me/{id}")
    public ResponseEntity<ResponseDTO<CandidateResponseDTO>> updateCandidate(
            @PathVariable Integer id,
            @RequestBody CandidateResponseDTO updateRequest) {
        CandidateResponseDTO updatedCandidate = candidatedService.updateCandidate(id, updateRequest);
        return ResponseEntity.ok(new ResponseDTO<>(updatedCandidate, null));
    }

    // obtener todos los candidatos activos (Recruiter/Admin)
    @GetMapping("/active")
    public ResponseEntity<ResponseDTO<List<CandidateResponseDTO>>> getAllActiveCandidates() {
        List<CandidateResponseDTO> candidates = candidatedService.getAllActiveCandidates();
        return ResponseEntity.ok(new ResponseDTO<>(candidates, null));
    }

    // Candidate solicita ser Recruiter
    @PostMapping("/{id}/request-recruiter")
    public ResponseEntity<ResponseDTO<String>> requestRecruiterUpgrade(
            @PathVariable Integer id,
            @RequestBody RecruiterResponseDTO request) {

        recruiterService.requestRecruiterUpgrade(id, request);
        return ResponseEntity.ok(
                new ResponseDTO<>("Solicitud enviada. Espera aprobación del administrador.", null));
    }

    @GetMapping("/{id}/matches")
    public ResponseEntity<ResponseDTO<List<JobPostResponseDTO>>> getMatchingJobPosts(
            @PathVariable Integer id) {
        Candidated candidate = candidatedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        List<JobPostResponseDTO> matches = candidatedService.getMatchingJobPosts(candidate);
        return ResponseEntity.ok(new ResponseDTO<>(matches, null));
    }

    // Endpoint para actualizar skills
    @PutMapping("/{candidateId}/skills")
    public ResponseEntity<ResponseDTO<CandidateResponseDTO>> updateSkills(Integer jobPostId,
            @PathVariable Integer candidateId,
            @RequestBody SkillUpdateRequestDTO request) {

        // Verificar que el JobPost existe
        jobPostService.getJobPostById(jobPostId);

        CandidateResponseDTO updatedCandidate = candidatedService.updateSkillsByIds(candidateId, request.getSkills());
        return ResponseEntity.ok(new ResponseDTO<>(updatedCandidate, null));
    }

}
