package org.severle.core.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.severle.core.data.ControlPoint;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Log4j2
public class ControlPointManager {
    private List<ControlPoint> points;

    public boolean add(ControlPoint point) {
        return this.points.add(point);
    }
}
