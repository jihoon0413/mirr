package side.project.mirr.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stadium;

    private LocalDateTime matchDay;

    @OneToMany(mappedBy = "game" , cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Quarter> quarters = new HashSet<>();

    @OneToMany(mappedBy = "game" , cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Mom> moms = new HashSet<>();

    @OneToMany(mappedBy = "game" , cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Attend> attends = new HashSet<>();

    private Game(String stadium, LocalDateTime matchDay) {
        this.stadium = stadium;
        this.matchDay = matchDay;
    }

    public static Game of(String stadium, LocalDateTime matchDay) {
        return new Game(stadium, matchDay);
    }

}
