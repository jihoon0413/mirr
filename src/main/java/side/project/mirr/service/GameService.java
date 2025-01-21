package side.project.mirr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import side.project.mirr.domain.Game;
import side.project.mirr.dto.GameDto;
import side.project.mirr.repository.GameRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public List<GameDto> findMatchByMonth() {
        return gameRepository.findAll()
                .stream().map(GameDto :: from)
                .toList();
    }

}
