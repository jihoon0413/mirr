package side.project.mirr.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import side.project.mirr.domain.Game;
import side.project.mirr.domain.Mom;
import side.project.mirr.domain.Player;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.repository.GameRepository;
import side.project.mirr.repository.MomRepository;
import side.project.mirr.repository.PlayerRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MomService {

    private final MomRepository momRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public List<PlayerDto> findMomsByGameId(Long gameId) {
        return momRepository.findAllByGameId(gameId)
                .stream().map(mom -> PlayerDto.from(mom.getPlayer()))
                .toList();
    }

    @Transactional
    public void modify(MomRequest momRequest) {

        Long gameId = momRequest.gameId();
        List<Mom> moms = momRepository.findAllByGameId(gameId);

        for(Mom mom : moms) {
            if(!momRequest.playerId().contains(mom.getPlayer().getId())) {
                momRepository.deleteByGameIdAndPlayerId(gameId, mom.getPlayer().getId());
            }
        }

        for(Long id : momRequest.playerId()) {
            if(momRepository.findByGameIdAndPlayerId(gameId, id).isEmpty()) {
                Game game = gameRepository.findById(gameId).orElseThrow();
                Player player = playerRepository.findById(id).orElseThrow();
                Mom mom = Mom.of(game, player);
                momRepository.save(mom);
            }
        }

    }

    public Page<RankingResponse> getMomRanking(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Object[]> result = momRepository.countMom(pageable);

        List<RankingResponse> momCount = result.stream().map(a -> {
            Player player = (Player) a[0];
            Long count = (Long) a[1];
            return RankingResponse.from(PlayerDto.from(player), count);
        }).toList();

        return new PageImpl<>(
                momCount,
                pageable,
                result.getTotalElements()
        );
    }
}
