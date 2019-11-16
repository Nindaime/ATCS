
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
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
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

import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class TrafficApp extends Application {

    public static ArrayList<Car> cars = new ArrayList<>();
    public static ArrayList<TrafficLightGroup> trafficLightGroup = new ArrayList<>();
    private static final int TOTAL_NUMBER_OF_CARS = 3;
    private static final long ADD_CAR_WAIT_TIME = 3000;
    private static final long CHECK_SENSOR_AREA_TIME = 5000;
    private static final long CHECK_CAR_PROXIMITY_WAIT_TIME = 2000;

    private Timer timer;

    public final static String[][] POSSIBLE_ROUTES = {{"L2", "L6"}, {"L2", "L10"}, {"L2", "L14"},
    {"L5", "L10"}, {"L5", "L14"}, {"L5", "L1"}, {"L9", "L14"},
    {"L9", "L1"}, {"L9", "L6"}, {"L13", "L1"}, {"L13", "L6"}, {"L13", "L10"}};

    public static String[] pickRandomPath() {

        return POSSIBLE_ROUTES[(int) (Math.random() * 11)];

    }

    // method to automatically add car to the road and attaching paths to them
    public static void main(String args[]) {
        // create TrafficLightGroup which are five;

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        // Four Traffic lights in each group
        TrafficLightGroup group1 = new TrafficLightGroup(125, 194);
        TrafficLightGroup group2 = new TrafficLightGroup(272, 194);
        TrafficLightGroup group3 = new TrafficLightGroup(423, 194);
        TrafficLightGroup group4 = new TrafficLightGroup(272, 90);
        TrafficLightGroup group5 = new TrafficLightGroup(272, 300);

        trafficLightGroup.add(group1);
        trafficLightGroup.add(group2);
        trafficLightGroup.add(group3);
        trafficLightGroup.add(group4);
        trafficLightGroup.add(group5);
        
        
//        trafficLightGroup.forEach(e->{
//            e.getEast();
//        });

        timer = new Timer();

//        var source = "L13";
//        var destination = "L10";
        ImageView map = new ImageView(new Image(getClass().getResourceAsStream("img/road_v2.png")));
        map.setFitWidth(540);

        map.setPreserveRatio(true);

        root.getChildren().add(0, map);
        addTrafficLightToRoot(group1, root);
        addTrafficLightToRoot(group2, root);
        addTrafficLightToRoot(group3, root);
        addTrafficLightToRoot(group4, root);
        addTrafficLightToRoot(group5, root);

        primaryStage.setTitle("JavaFX PathTransition Test with SVG");
        primaryStage.setScene(new Scene(root, 671, 481));

        primaryStage.show();

        TimerTask addCarTask = new TimerTask() {
            int count = 0;

            @Override
            public void run() {
                if (cars.size() > 0) {
                    Car c = cars.get(0);
//                    System.out.println("this is the x value of the car" + c.getSize());
//                    System.out.println("this is the sensor area"+group1.getEast().getDimension());
                    
                }

                if (count < TOTAL_NUMBER_OF_CARS) {

                    count++;
//                    System.out.println("the count is " + count);
                    var pick = pickRandomPath();
                    var source = pick[0];
                    var destination = pick[1];
                    var c = DijkstraShortestPath.findPathBetween(CustomDirectedGraph.getDefaultEdges(), source, destination);

                    Car car = new Car(c);
                    cars.add(car);

                    Platform.runLater(() -> {
                        root.getChildren().add(car.getCar());
                        ArrayList<PathTransition> pTList = getAnimationPaths(car.getVertexList(), car.getCar());
                        playSequentialTransition(pTList, car);
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
                
                trafficLightGroup.forEach( group->{
                    try {
                        group.checkTrafficLight();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TrafficApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

            }
        };

        TimerTask checkCarProximityTask = new TimerTask() {
            int count = 0;

            @Override
            public void run() {

            }
        };
//        this.timer.schedule(checkSensorAreaTask, 0, CHECK_SENSOR_AREA_TIME);
        timer.scheduleAtFixedRate(addCarTask, 0, ADD_CAR_WAIT_TIME);
        timer.schedule(checkSensorAreaTask, 0, CHECK_SENSOR_AREA_TIME);

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
            PathTransition pT = new PathTransition(Duration.seconds(9), path);

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

