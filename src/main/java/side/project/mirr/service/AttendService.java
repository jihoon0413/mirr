package side.project.mirr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import side.project.mirr.domain.Attend;
import side.project.mirr.domain.Game;
import side.project.mirr.domain.Player;
import side.project.mirr.dto.AttendDto;
import side.project.mirr.dto.PlayerDto;
import side.project.mirr.dto.request.MomRequest;
import side.project.mirr.dto.response.RankingResponse;
import side.project.mirr.repository.AttendRepository;
import side.project.mirr.repository.GameRepository;
import side.project.mirr.repository.PlayerRepository;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttendService {

    private final AttendRepository attendRepository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public List<AttendDto> findPlayerByGameId(Long gameId) {
        return attendRepository.findAllByGameId(gameId)
                .stream().map(AttendDto::from)
                .sorted(Comparator.comparing(attendDto -> attendDto.playerDto().name()))
                .toList();
    }

    public Page<RankingResponse> getAttendRanking(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Object[]> result = attendRepository.countAttend(pageable);

        List<RankingResponse> attendCount = result.stream().map(a -> {
            Player player = (Player) a[0];
            Long count = (Long) a[1];
            return RankingResponse.from(PlayerDto.from(player), count);
        }).toList();

        return new PageImpl<>(
                attendCount,
                pageable,
                result.getTotalElements()
        );
    }

    public void deleteAttend(Long attendId) {

        attendRepository.deleteById(attendId);
    }

    public void newAttends(MomRequest momRequest) {

        Game game = gameRepository.findById(momRequest.gameId()).orElseThrow();

        for(Long playerId : momRequest.playerId()) {

            Player player = playerRepository.findById(playerId).orElseThrow();
            Attend attend = Attend.of(player, game);

            attendRepository.save(attend);
        }
    }
}
