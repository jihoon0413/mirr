package side.project.mirr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import side.project.mirr.domain.Player;
import side.project.mirr.domain.Point;
import side.project.mirr.domain.Quarter;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.PointDto;
import side.project.mirr.dto.request.PointRequest;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.repository.PlayerRepository;
import side.project.mirr.repository.PointRepository;
import side.project.mirr.repository.QuarterRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointService {

    private final QuarterRepository quarterRepository;
    private final PlayerRepository playerRepository;
    private final PointRepository pointRepository;

    //TODO: 날짜 별로 조회
    public Page<RankingResponse> getGoalRanking(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Object[]> result = pointRepository.countGoal(pageable);

        List<RankingResponse> goalCount = result.stream().map(a -> {
            Player player = (Player) a[0];
            Long count = (Long) a[1];
            return RankingResponse.from(PlayerDto.from(player), count);
        }).toList();

        return new PageImpl<>(
                goalCount,
                pageable,
                result.getTotalElements()
        );

    }

    public Page<RankingResponse> getAssistRanking(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Object[]> result = pointRepository.countAssist(pageable);

        List<RankingResponse> assistCount = result.stream().map(a -> {
            Player player = (Player) a[0];
            Long count = (Long) a[1];
            return RankingResponse.from(PlayerDto.from(player), count);
        }).toList();

        return new PageImpl<>(
                assistCount,
                pageable,
                result.getTotalElements()
        );

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
