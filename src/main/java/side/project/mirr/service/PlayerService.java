package side.project.mirr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import side.project.mirr.domain.Player;
import side.project.mirr.domain.eNum.Position;
import side.project.mirr.repository.PlayerRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public void savePlayer() {

        Player player = Player.of("지훈", Position.CM,43);

        playerRepository.save(player);

    }



}
