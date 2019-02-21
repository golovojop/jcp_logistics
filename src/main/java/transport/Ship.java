package transport;

import seaport.Dock;
import stages.Route;
import stages.Stage;

import java.util.List;

public class Ship extends Thread {
    private List<Dock> docks;
    private Capacity capacity;
    private Route route;
    private String name;

    public Ship(String name, Capacity capacity, List<Dock> docks, Route route) {
        this.name = name;
        this.docks = docks;
        this.capacity = capacity;
        this.route = route;
    }

    @Override
    public void run() {
        p(String.format("%s вышел из порта", name));

        boolean docksNotEmpty = true;

        while(docksNotEmpty) {

            // В сторону доков
            for(Stage s : route.getStages()){
                s.go(this);
            }

            // Инверсия маршрута
            route.routeBack();

            // В сторону доков
            for(Stage s : route.getStages()){
                s.go(this);
            }

            docksNotEmpty = false;
        }
    }

    /**
     * @return скорость
     */
    public int getSpeed() {
        return capacity.getSpeed();
    }

    /**
     *  Вывод в консоль
     */
    private void p(String s){
        System.out.println(s);
    }

}
