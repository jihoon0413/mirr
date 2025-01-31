package side.project.mirr.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.project.mirr.domain.eNum.PointType;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PointType type;

    private LocalDateTime dateInfo;

    @ManyToOne
    @JoinColumn(name = "quarter_id")
    private Quarter quarter;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private Point(Quarter quarter, Player player, PointType type, LocalDateTime dateInfo) {
        this.quarter = quarter;
        this.player = player;
        this.type = type;
        this.dateInfo = dateInfo;
    }

    public static Point of(Quarter quarter, Player player, PointType type, LocalDateTime dateInfo) {
        return new Point(quarter, player, type, dateInfo);
    }

}
