package side.project.mirr.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Mom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private Mom(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    public static Mom of(Game game, Player player) {
        return new Mom(game, player);
    }

}
