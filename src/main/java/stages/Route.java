package stages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Route {

    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }

    public Route(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public Route(Collection<Stage> stages) {
        this.stages = new ArrayList<>(stages);
    }

    // Инверсия маршрута
    public void routeBack() {
        Collections.reverse(stages);
    }
}

