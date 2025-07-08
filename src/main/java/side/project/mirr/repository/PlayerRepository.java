package side.project.mirr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import side.project.mirr.domain.Player;
import side.project.mirr.dto.response.RankInfo;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query(value = "SELECT ranked_players.player_rank, ranked_players.goal_count " +
            "FROM (" +
            "    SELECT " +
            "        p.id AS id, " +
            "        RANK() OVER (ORDER BY COUNT(pt.id) DESC) AS player_rank, " +
            "        COUNT(pt.id) AS goal_count " +
            "    FROM player p " +
            "    LEFT JOIN point pt ON pt.player_id = p.id AND pt.type = 'GOAL' " +
            "    GROUP BY p.id" +
            ") AS ranked_players " +
            "WHERE ranked_players.id = :playerId",
            nativeQuery = true)
    RankInfo getGoalRankInfo(@Param("playerId") Long id);

    @Query(value = "SELECT ranked_players.player_rank, ranked_players.assist_count " +
            "FROM (" +
            "    SELECT " +
            "        p.id AS id, " +
            "        RANK() OVER (ORDER BY COUNT(pt.id) DESC) AS player_rank, " +
            "        COUNT(pt.id) AS assist_count " +
            "    FROM player p " +
            "    LEFT JOIN point pt ON pt.player_id = p.id AND pt.type = 'ASSIST' " +
            "    GROUP BY p.id" +
            ") AS ranked_players " +
            "WHERE ranked_players.id = :playerId",
            nativeQuery = true)
    RankInfo getAssistRankInfo(@Param("playerId") Long id);

    @Query(value = "SELECT ranked_players.player_rank, ranked_players.attend_count " +
            "FROM (" +
            "    SELECT " +
            "        p.id AS id, " +
            "        RANK() OVER (ORDER BY COUNT(a.id) DESC) AS player_rank, " +
            "        COUNT(a.id) AS attend_count " +
            "    FROM player p " +
            "    LEFT JOIN attend a ON a.player_id = p.id " +
            "    GROUP BY p.id" +
            ") AS ranked_players " +
            "WHERE ranked_players.id = :playerId",
            nativeQuery = true)
    RankInfo getAttendRankInfo(@Param("playerId") Long id);
}
