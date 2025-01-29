package side.project.mirr.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

    @OneToMany(mappedBy = "quarter", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Point> points = new HashSet<>();


    private Quarter(Game game, int quarterNum, String opponent, int point, int losePoint) {
        this.game = game;
        this.quarterNum = quarterNum;
        this.opponent = opponent;
        this.point = point;
        this.losePoint = losePoint;
    }

    public static Quarter of(Game game, int quarterNum, String opponent, int point, int losePoint) {
        return new Quarter(game, quarterNum, opponent, point, losePoint);
    }

}
