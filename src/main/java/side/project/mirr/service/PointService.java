package side.project.mirr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import side.project.mirr.domain.Player;
import side.project.mirr.domain.Point;
import side.project.mirr.domain.Quarter;
import side.project.mirr.domain.eNum.PointType;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.PointDto;
import side.project.mirr.dto.request.PointRequest;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.repository.GameRepository;
import side.project.mirr.repository.PlayerRepository;
import side.project.mirr.repository.PointRepository;
import side.project.mirr.repository.QuarterRepository;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointService {

    private final QuarterRepository quarterRepository;
    private final PlayerRepository playerRepository;
    private final PointRepository pointRepository;

    public List<RankingResponse> getGoalRanking() {
        return playerRepository.findAll()
                .stream().map(player -> {
                    var count = pointRepository.countByPlayerIdAndType(player.getId(), PointType.GOAL);
                    return RankingResponse.from(PlayerDto.from(player), count);
                })
                .filter(response -> response.count() > 0)
                .sorted(Comparator.comparing(RankingResponse::count).reversed())
                .toList();
    }

    public List<RankingResponse> getAssistRanking() {
        return playerRepository.findAll()
                .stream().map(player -> {
                    var count = pointRepository.countByPlayerIdAndType(player.getId(), PointType.ASSIST);
                    return RankingResponse.from(PlayerDto.from(player), count);
                })
                .filter(response -> response.count() > 0)
                .sorted(Comparator.comparing(RankingResponse::count).reversed())
                .toList();
    }

    public List<PointDto> getPointByQuarter(Long quarterId) {

        return pointRepository.findAllByQuarterId(quarterId)
                .stream().map(PointDto :: from)
                .toList();
    }

    public void deleteById(Long pointId) {
        pointRepository.deleteById(pointId);
    }

    public void saveNewPoint(PointRequest pointRequest) {

        Quarter quarter = quarterRepository.findById(pointRequest.quarterId()).orElseThrow();
        Player player = playerRepository.findById(pointRequest.playerId()).orElseThrow();

        Point point = PointRequest.toEntity(quarter, player, pointRequest.type(), quarter.getGame().getMatchDay());

        pointRepository.save(point);
    }
}
