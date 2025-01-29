package side.project.mirr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import side.project.mirr.domain.Game;
import side.project.mirr.domain.Quarter;
import side.project.mirr.dto.QuarterDto;
import side.project.mirr.dto.request.QuarterRequest;
import side.project.mirr.repository.GameRepository;
import side.project.mirr.repository.QuarterRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuarterService {

    private final QuarterRepository quarterRepository;
    private final GameRepository gameRepository;

    public void deleteQuarter(Long quarterId) {
        quarterRepository.deleteById(quarterId);
    }

    public List<QuarterDto> findQuarter(Long gameId) {
        return quarterRepository.findAllByGameId(gameId)
                .stream().map(QuarterDto::from)
                .toList();
    }

    public QuarterDto findQuarterById(Long quarterId) {
        return QuarterDto.from(quarterRepository.findById(quarterId).orElseThrow());
    }

    public Long updateQuarter(QuarterDto quarterDto) {
        Quarter quarter = quarterRepository.findById(quarterDto.id()).orElseThrow();

        quarter.setQuarterNum(quarterDto.quarterNum());
        quarter.setOpponent(quarterDto.opponent());
        quarter.setPoint(quarterDto.point());
        quarter.setLosePoint(quarterDto.losePoint());

        quarterRepository.save(quarter);

        return quarter.getGame().getId();
    }

    public void saveQuarter(QuarterRequest quarterDto) {

        Game game = gameRepository.findById(quarterDto.gameId()).orElseThrow();

        Quarter quarter = QuarterRequest.toEntity(game, quarterDto);
        quarterRepository.save(quarter);
    }
}
