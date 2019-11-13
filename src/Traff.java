
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
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

public class Traff extends Application {
    
    private static final int TOTAL_NUMBER_OF_CARS = 20;

    private static ImageView car;

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

        var pick = pickRandomPath();
        var source = pick[0];
        var destination = pick[1];


//        var source = "L13";
//        var destination = "L10";

        var c = DijkstraShortestPath.findPathBetween(CustomDirectedGraph.getDefaultEdges(), source, destination);

        car = new ImageView(new Image(getClass().getResourceAsStream("img/red.png")));
        car.setFitHeight(20);

        car.setPreserveRatio(true);

        root.getChildren().add(car);
        ImageView map = new ImageView(new Image(getClass().getResourceAsStream("img/road_v2.png")));
        map.setFitWidth(540);

        map.setPreserveRatio(true);

        root.getChildren().add(0, map);
        primaryStage.setTitle("JavaFX PathTransition Test with SVG");
        primaryStage.setScene(new Scene(root, 671, 481));

        primaryStage.show();

        ArrayList<PathTransition> pTList = getAnimation(c.getVertexList());
        playSequentialTransition(pTList);

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

    public ArrayList<PathTransition> getAnimation(List vertex) {

        ArrayList<PathTransition> pTList = new ArrayList<>();

        vertex.forEach((e) -> {
            System.out.println(" the value of e is " + e.toString());
            Path path = getTransformMatrix(e.toString());
            PathTransition pT = new PathTransition(Duration.seconds(5), path, car);

            pT.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pT.setCycleCount(1);
            pT.setRate(1);
            pTList.add(pT);
        });
        return pTList;
    }

    public void playSequentialTransition(List<PathTransition> pT) {

        SequentialTransition seq = new SequentialTransition();
        int numOfTransition = pT.size();

        for (int i = 0; i < numOfTransition; i++) {

            seq.getChildren().add(pT.get(i));
        }
        seq.setCycleCount(Timeline.INDEFINITE);
        seq.play();
    }

}
