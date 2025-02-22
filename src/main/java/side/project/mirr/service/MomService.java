package side.project.mirr.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Comparator;
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

    public List<RankingResponse> getMomRanking() {
        return playerRepository.findAll()
                .stream().map(player -> {
                    var count = momRepository.countByPlayerId(player.getId());
                    return RankingResponse.from(PlayerDto.from(player), count);
                })
                .filter(response -> response.count() > 0)
                .sorted(Comparator.comparing(RankingResponse::count).reversed())
                .toList();
    }
}
