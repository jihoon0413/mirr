package side.project.mirr.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
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

}
