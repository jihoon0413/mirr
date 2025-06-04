package side.project.mirr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import side.project.mirr.domain.Game;
import side.project.mirr.dto.GameDto;
import side.project.mirr.repository.GameRepository;
import side.project.mirr.repository.QuarterRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final QuarterRepository quarterRepository;

    public GameDto findGame() {
        return GameDto.from(gameRepository.findById(1L).orElseThrow());
    }

    public List<GameDto> findMatchByMonth() {
        return gameRepository.findAll()
                .stream().map(GameDto :: from)
                .toList();
    }

    public void saveGame(GameDto gameDto) {
        Game game = Game.of(gameDto.stadium(), gameDto.matchDay());
        gameRepository.save(game);
    }



    public void deleteGame(Long gameId) {
        gameRepository.deleteById(gameId);
    }
}
