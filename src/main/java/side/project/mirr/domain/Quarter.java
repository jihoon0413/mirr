package side.project.mirr.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Quarter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quarterNum;
    private String opponent;
    private int point;
    private int losePoint;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;


}
