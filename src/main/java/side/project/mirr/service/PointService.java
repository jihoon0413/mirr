package side.project.mirr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import side.project.mirr.domain.eNum.PointType;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.repository.PlayerRepository;
import side.project.mirr.repository.PointRepository;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointService {

    private final PlayerRepository playerRepository;
    private final PointRepository pointRepository;

    public List<RankingResponse> getGoalRanking() {
        return playerRepository.findAll()
                .stream().map(player -> {
                    var count = pointRepository.countByPlayerIdAndType(player.getId(), PointType.GOAL);
                    return RankingResponse.from(PlayerDto.from(player), count);
                })
                .sorted(Comparator.comparing(RankingResponse::count).reversed())
                .toList();
    }

    public List<RankingResponse> getAssistRanking() {
        return playerRepository.findAll()
                .stream().map(player -> {
                    var count = pointRepository.countByPlayerIdAndType(player.getId(), PointType.ASSIST);
                    return RankingResponse.from(PlayerDto.from(player), count);
                })
                .sorted(Comparator.comparing(RankingResponse::count).reversed())
                .toList();
    }
}
