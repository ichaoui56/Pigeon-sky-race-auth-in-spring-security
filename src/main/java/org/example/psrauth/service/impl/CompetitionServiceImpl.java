package org.example.psrauth.service.impl;

import lombok.AllArgsConstructor;
import org.example.psrauth.dto.competition.RequestCompetitionDTO;
import org.example.psrauth.dto.competition.ResponseCompetitionDTO;
import org.example.psrauth.exception.NotFoundException;
import org.example.psrauth.mapper.CompetitionMapper;
import org.example.psrauth.model.RoleType;
import org.example.psrauth.model.entity.Competition;
import org.example.psrauth.model.entity.User;
import org.example.psrauth.repository.CompetitionRepository;
import org.example.psrauth.repository.UserRepository;
import org.example.psrauth.service.CompetitionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;
    private final CompetitionMapper competitionMapper;

    @Transactional
    @Override
    public ResponseCompetitionDTO addCompetition(RequestCompetitionDTO requestCompetitionDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userRepository.findByUsername(currentUser)
                .orElseThrow(() -> new NotFoundException("User not found with username: " + currentUser));

        if (!user.getRole().getRoleType().equals(RoleType.ROLE_ORGANIZER)) {
            throw new IllegalStateException("Only users with the ORGANIZER role can create competitions");
        }

        Competition competition = competitionMapper.toEntity(requestCompetitionDTO);
        competition.setUser(user);
        competition.setStatus(false);

        Competition savedCompetition = competitionRepository.save(competition);

        return competitionMapper.toDTO(savedCompetition);
    }

}
