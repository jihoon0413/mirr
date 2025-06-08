package side.project.mirr.domain;

import jakarta.persistence.*;
import lombok.*;
import side.project.mirr.domain.eNum.Position;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Position pos;

    private int backNum;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Point> points = new HashSet<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Attend> attends = new HashSet<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Mom> moms = new HashSet<>();


    private Player(String name, Position pos, int backNum) {
        this.name = name;
        this.pos = pos;
        this.backNum = backNum;
    }

    public static Player of(String name, Position pos, int backNum) {
        return new Player(name, pos, backNum);
    }


}
