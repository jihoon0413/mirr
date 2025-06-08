package side.project.mirr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import side.project.mirr.domain.Player;
import side.project.mirr.domain.eNum.Position;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.response.PlayerDetailResponse;
import side.project.mirr.dto.response.RankInfo;
import side.project.mirr.repository.PlayerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public void savePlayer(PlayerDto playerDto) {
        Player player = Player.of(playerDto.name(), playerDto.pos(), playerDto.backNum());
        playerRepository.save(player);
    }

    public List<PlayerDto> findAll() {
        return playerRepository.findAll().stream().map(PlayerDto::from).toList();
    }

    public Page<PlayerDto> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable).map(PlayerDto::from);
    }

    public List<Position> getAllPos() {
        return Arrays.asList(Position.values());
    }

    public PlayerDetailResponse getPlayerDetail(Long playerId) {

        Player player = playerRepository.findById(playerId).orElseThrow();
        RankInfo scoreInfo = playerRepository.getGoalRankInfo(playerId);
        RankInfo assistInfo = playerRepository.getAssistRankInfo(playerId);
        RankInfo attendInfo = playerRepository.getAttendRankInfo(playerId);

        return PlayerDetailResponse.of(PlayerDto.from(player), scoreInfo, assistInfo, attendInfo);
    }

    public void modifyPlayer(PlayerDto playerDto) {
        Player player = playerRepository.findById(playerDto.id()).orElseThrow();

        player.setName(playerDto.name());
        player.setPos(playerDto.pos());
        player.setBackNum(playerDto.backNum());

        playerRepository.save(player);
    }

    public void deletePlayer(Long playerId) {
        playerRepository.deleteById(playerId);
    }
}
