
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.parser.PathParser;
import org.apache.batik.util.XMLResourceDescriptor;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class TrafficApp extends Application {

    public static ArrayList<Car> cars = new ArrayList<>();
    public static ArrayList<Car> cars2 = new ArrayList<>();
    public static ArrayList<IntelligentLightGroup> trafficLightGroup = new ArrayList<>();
    public static ArrayList<TimerLightGroup> trafficLightGroup2 = new ArrayList<>();

    private static final int TOTAL_NUMBER_OF_CARS = 6;
    private static final long ADD_CAR_WAIT_TIME = 3000;
    private static final long CHECK_SENSOR_AREA_TIME = 3000;
    private static final long CHECK_CAR_PROXIMITY_WAIT_TIME = 2000;
    private static final int SECOND_MAP_OFFSET = 800;

    private LineChart<String, Number> lineChart;
    private Timer timer;
    private Pane root;
    public final static String[][] POSSIBLE_ROUTES = {{"L2", "L6"}, {"L2", "L10"}, {"L2", "L14"},
    {"L5", "L10"}, {"L5", "L14"}, {"L5", "L1"}, {"L9", "L14"},
    {"L9", "L1"}, {"L9", "L6"}, {"L13", "L1"}, {"L13", "L6"}, {"L13", "L10"}};

    public static String[] pickRandomPath() {

        return POSSIBLE_ROUTES[(int) (Math.random() * 11)];

    }

    public long getAverageDelayTime(ArrayList<Car> cars) {
        long sum = 0;
        sum = cars.stream().map((car) -> car.getTotalDelayTime()).reduce(sum, (accumulator, _item) -> accumulator + _item);
        return (long) (sum / cars.size());
    }

    public long getAverageTravelTime(ArrayList<Car> cars) {
        long sum = 0;
        sum = cars.stream().map((car) -> car.getTotalPlayTime()).reduce(sum, (accumulator, _item) -> accumulator + _item);
        return (long) (sum / cars.size());
    }

    public void drawGraph() {
        lineChart.setTitle("Comparing adaptive and non adaptive graph system");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Adaptive");

        var averageDelayTime = getAverageDelayTime(cars);
        var averageTravelTime = getAverageTravelTime(cars);

        series1.getData().add(new XYChart.Data("Delay Time", averageDelayTime));
        series1.getData().add(new XYChart.Data("Total Time", averageTravelTime));

        var averageDelayTime2 = getAverageDelayTime(cars2);
        var averageTravelTime2 = getAverageTravelTime(cars2);

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Non Adaptive 2");
        series2.getData().add(new XYChart.Data("Delay Time", averageDelayTime2));
        series2.getData().add(new XYChart.Data("Total Time", averageTravelTime2));

        lineChart.getData().addAll(series1, series2);
    }

    // method to automatically add car to the road and attaching paths to them
    public static void main(String args[]) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {

        root = new Pane();
        // initialize line graph
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Traffic Light");
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setLayoutX(500);
        lineChart.setLayoutY(400);
        lineChart.setPrefSize(350, 350);

        timer = new Timer();

        ImageView map = new ImageView(new Image(getClass().getResourceAsStream("img/road_v2.png")));
        ImageView map2 = new ImageView(new Image(getClass().getResourceAsStream("img/road_v2.png")));
        Button graphButton = new Button("Show Graph");
        Button simulateAnimationButton = new Button("Simulate Animation");

        // for the intelligent group
        IntelligentLightGroup group1 = new IntelligentLightGroup(125, 194);
        IntelligentLightGroup group2 = new IntelligentLightGroup(272, 194);
        IntelligentLightGroup group3 = new IntelligentLightGroup(423, 194);
        IntelligentLightGroup group4 = new IntelligentLightGroup(272, 90);
        IntelligentLightGroup group5 = new IntelligentLightGroup(272, 300);

        // for the normal light group
        TimerLightGroup group6 = new TimerLightGroup(SECOND_MAP_OFFSET + 125, 194);
        TimerLightGroup group7 = new TimerLightGroup(SECOND_MAP_OFFSET + 272, 194);
        TimerLightGroup group8 = new TimerLightGroup(SECOND_MAP_OFFSET + 423, 194);
        TimerLightGroup group9 = new TimerLightGroup(SECOND_MAP_OFFSET + 272, 90);
        TimerLightGroup group10 = new TimerLightGroup(SECOND_MAP_OFFSET + 272, 300);

        trafficLightGroup.add(group1);
        trafficLightGroup.add(group2);
        trafficLightGroup.add(group3);
        trafficLightGroup.add(group4);
        trafficLightGroup.add(group5);

        trafficLightGroup2.add(group6);
        trafficLightGroup2.add(group7);
        trafficLightGroup2.add(group8);
        trafficLightGroup2.add(group9);
        trafficLightGroup2.add(group10);

        map.setFitWidth(540);
        map2.setFitWidth(540);

        map.setPreserveRatio(true);
        map2.setPreserveRatio(true);

        map2.setLayoutX(SECOND_MAP_OFFSET);

        graphButton.setLayoutX(620);
        graphButton.setLayoutY(200);

        simulateAnimationButton.setLayoutX(600);
        simulateAnimationButton.setLayoutY(100);
        graphButton.setOnAction(e -> {
            drawGraph();
            root.getChildren().add(4, lineChart);
        });

        simulateAnimationButton.setOnAction(e -> {
            drawGraph();
            cars.clear();
            cars2.clear();
            generateTrafficAnimation();
        });

        root.getChildren().add(0, map);
        root.getChildren().add(1, map2);
        root.getChildren().add(2, graphButton);
        root.getChildren().add(3, simulateAnimationButton);

        addTrafficLightToRoot(group1, root);
        addTrafficLightToRoot(group2, root);
        addTrafficLightToRoot(group3, root);
        addTrafficLightToRoot(group4, root);
        addTrafficLightToRoot(group5, root);
        addTrafficLightToRoot(group6, root);
        addTrafficLightToRoot(group7, root);
        addTrafficLightToRoot(group8, root);
        addTrafficLightToRoot(group9, root);
        addTrafficLightToRoot(group10, root);

        primaryStage.setTitle("JavaFX PathTransition Test with SVG");
        primaryStage.setScene(new Scene(root, 671, 481));

        primaryStage.show();
        
        generateTrafficAnimation();

    }

    public void generateTrafficAnimation() {
        
        
        TimerTask addCarTask = new TimerTask() {
            int count = 0;

            @Override
            public void run() {

                if (count < TOTAL_NUMBER_OF_CARS) {

                    count++;
//                    System.out.println("the count is " + count);
                    var pick = pickRandomPath();
                    var source = pick[0];
                    var destination = pick[1];
                    var c = DijkstraShortestPath.findPathBetween(CustomDirectedGraph.getDefaultEdges(), source, destination);

                    Car car = new Car(c);
                    Car car2 = new Car(c);
                    cars.add(car);
                    cars2.add(car2);

                    Platform.runLater(() -> {
                        root.getChildren().add(car.getCar());
                        root.getChildren().add(car2.getCar());
                        
                        ArrayList<PathTransition> pTList = getAnimationPaths(car.getVertexList(), car.getCar());
                        ArrayList<PathTransition> pTList2 = getAnimationPaths2(car.getVertexList(), car2.getCar());

                        playSequentialTransition(pTList, car);
                        playSequentialTransition(pTList2, car2);

                    });

                    return;
                }
                this.cancel();
            }
        };

        TimerTask checkSensorAreaTask = new TimerTask() {

            @Override
            public void run() {

                //                if (cars.size() == TOTAL_NUMBER_OF_CARS) {
                //                    this.cancel();
                //                }
                trafficLightGroup.forEach(group -> {
                    try {
                        group.checkTrafficLight();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TrafficApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

            }
        };

        TimerTask checkTimerTrafficTask = new TimerTask() {

            @Override
            public void run() {

                //                if (cars.size() == TOTAL_NUMBER_OF_CARS) {
                //                    this.cancel();
                //                }
                trafficLightGroup2.forEach(group -> {
                    try {
                        group.checkTrafficLight();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TrafficApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

            }
        };

        TimerTask checkCarProximityTask = new TimerTask() {

            @Override
            public void run() {
//                cars.stream().filter(car ->{ return car.isStopped();}).forEach(car->System.out.println("car is out"));
                if (cars.size() < 2) {
                    return;
                }
                cars.forEach((car) -> {
                    if (car.isStopped()) {
                        ArrayList<Car> remainingCars = new ArrayList<>();
                        remainingCars.addAll(cars);
                        remainingCars.remove(car);

                        // find a car that is close to it
                        remainingCars.forEach(c -> {
                            if (!c.isStopped() && c.getX() != 0) {
                                var gradient = Math.abs(((car.getWidth() / 2 + car.getX()) - (c.getWidth() / 2 + c.getX()))
                                        / ((car.getHeight() / 2 + car.getY()) - (c.getHeight() / 2 + c.getY())));

                                if (gradient < 5) {
                                    c.stop();
                                    System.out.print("gradient " + gradient);
                                    System.out.println("stop car");
                                }

                            }

                        });

                        // if any is found check if it has already stopped
                        // if no then stop the car
                    }
                });
            }
        };

        timer.scheduleAtFixedRate(addCarTask, 0, ADD_CAR_WAIT_TIME);
        timer.schedule(checkSensorAreaTask, 0, CHECK_SENSOR_AREA_TIME);
        timer.schedule(checkTimerTrafficTask, 0, CHECK_SENSOR_AREA_TIME);
        //        timer.schedule(checkCarProximityTask, 0, CHECK_CAR_PROXIMITY_WAIT_TIME);

    }

    private void addTrafficLightToRoot(TrafficLightGroup group, Pane root) {
        group.getTrafficLights().forEach((e) -> {
            root.getChildren().add(e.getTrafficLight());
            root.getChildren().add(e.getSensor());
        });
    }

    public Path getTransformMatrix(String route) {

        Path path;

        String pathData = "";
        String transformMatrix = "";
        try {

            String xmlParser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(xmlParser);

            String uri = "FILE:\\Users\\User01\\Documents\\NetBeansProjects\\ATCS\\src\\img\\ATCS_DrivePath.svg";
            FileInputStream fI = new FileInputStream(
                    "C:\\Users\\User01\\Documents\\NetBeansProjects\\ATCS\\src\\img\\ATCS_DrivePath.svg");

            SVGDocument doc = f.createSVGDocument(uri);
            Element element = doc.getElementById(route);
            pathData = element.getAttributeNode("d").getValue();
            transformMatrix = element.getAttributeNode("transform").getValue();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        String tempTransformMatrix = transformMatrix.replace("matrix(", "");
        String tempTransformMatrix2 = tempTransformMatrix.replace(")", "");

        PathParser parser = new PathParser();
        // SAXParser p = new SAXParser();
        JavaFXPathElementHandler handler = new JavaFXPathElementHandler("track");
        parser.setPathHandler(handler);
        parser.parse(pathData);

        path = handler.getPath();
        // path.getElements().forEach(System.out::println);

        var a = tempTransformMatrix2.split(",");

        path.getTransforms()
                .add(Transform.affine(Double.parseDouble(a[0]), Double.parseDouble(a[1]), Double.parseDouble(a[2]),
                        Double.parseDouble(a[3]), Double.parseDouble(a[4]), Double.parseDouble(a[5])));

        path.setStroke(Color.BLACK);

        return path;

    }

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        timer.cancel();

    }

    public ArrayList<PathTransition> getAnimationPaths(List vertex, Pane car) {

        // Get the differenft paths of the route and add to an arraylist
        ArrayList<PathTransition> pTList = new ArrayList<>();

        vertex.forEach((e) -> {

            Path path = getTransformMatrix(e.toString());
            PathTransition pT = new PathTransition(Duration.seconds(5), path);

//            car.layoutXProperty().bind(path.translateXProperty());
            pT.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pT.setRate(1);

            pTList.add(pT);
        });
        return pTList;
    }

    public ArrayList<PathTransition> getAnimationPaths2(List vertex, Pane car) {

        // Get the differenft paths of the route and add to an arraylist
        ArrayList<PathTransition> pTList = new ArrayList<>();

        vertex.forEach((e) -> {

            Path path = getTransformMatrix(e.toString());
            var translateX = path.getTranslateX();

            path.setTranslateX(translateX + SECOND_MAP_OFFSET);

            PathTransition pT = new PathTransition(Duration.seconds(5), path);

//            car.layoutXProperty().bind(path.translateXProperty());
            pT.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pT.setRate(1);

            pTList.add(pT);
        });
        return pTList;
    }

    // play the list on paths in a sequence
    public void playSequentialTransition(List<PathTransition> pT, Car car) {

        SequentialTransition seq = new SequentialTransition();
        int numOfTransition = pT.size();

        for (int i = 0; i < numOfTransition; i++) {

            seq.getChildren().add(pT.get(i));
        }
        seq.setCycleCount(1);

        car.setSequenceTransition(seq);

    }

}
