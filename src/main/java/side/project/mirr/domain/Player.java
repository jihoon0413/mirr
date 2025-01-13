package side.project.mirr.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.project.mirr.domain.eNum.Position;

@Entity
@Getter
@Builder
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


    private Player(String name, Position pos, int backNum) {
        this.name = name;
        this.pos = pos;
        this.backNum = backNum;
    }

    public static Player of(String name, Position pos, int backNum) {
        return new Player(name, pos, backNum);
    }


}
