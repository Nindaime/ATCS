
import java.io.FileInputStream;
import java.io.IOException;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.parser.PathParser;
import org.apache.batik.util.XMLResourceDescriptor;

import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;
import org.jgrapht.traverse.*;

public class Traff extends Application {

    private static Path path;
    private static ImageView car;

    // method to automatically add car to the road and attaching paths to them
    public static void main(String args[]) {
        //create TrafficLightGroup which are five;
//        var t = new TrafficLightGroup(1,2);

        DefaultDirectedGraph<String, DefaultEdge> directedGraph
                = new DefaultDirectedGraph<>(DefaultEdge.class);
        directedGraph.addVertex("L4H");
        directedGraph.addVertex("L2");
        directedGraph.addVertex("L1");
        directedGraph.addVertex("L2toL4");
        directedGraph.addVertex("L4V");
        directedGraph.addVertex("L3toL1");
        directedGraph.addVertex("L3V");
        directedGraph.addVertex("L3H");
        directedGraph.addVertex("L2toL17");
        directedGraph.addVertex("L17");
        directedGraph.addVertex("L17toL19");
        directedGraph.addVertex("L19");
        directedGraph.addVertex("L19toL10");
        directedGraph.addVertex("L10");
        directedGraph.addVertex("L18toL1");
        directedGraph.addVertex("L18");
        directedGraph.addVertex("L20toL18");
        directedGraph.addVertex("L20");
        directedGraph.addVertex("L9toL20");
        directedGraph.addVertex("L9");
        directedGraph.addVertex("L7toL3");
        directedGraph.addVertex("L7H");
        directedGraph.addVertex("L7V");
        directedGraph.addVertex("L4toL8");
        directedGraph.addVertex("L8H");
        directedGraph.addVertex("L8V");
        directedGraph.addVertex("L9toL7");
        directedGraph.addVertex("L8toL10");
        directedGraph.addVertex("L23");
        directedGraph.addVertex("L24");
        directedGraph.addVertex("L24toL8");
        directedGraph.addVertex("L17toL24");
        directedGraph.addVertex("L5toL3");
        directedGraph.addVertex("L23toL19");
        directedGraph.addVertex("L5");
        directedGraph.addVertex("L6");
        directedGraph.addVertex("L4toL23");
        directedGraph.addVertex("L4toL6");
        directedGraph.addVertex("L7toL6");
        directedGraph.addVertex("L5toL8");
        directedGraph.addVertex("L5toL23");
        directedGraph.addVertex("L24toL6");
        directedGraph.addVertex("L23toL18");
        directedGraph.addVertex("L20toL24");
        directedGraph.addVertex("L19toL17");
        directedGraph.addVertex("L8toL20");
        directedGraph.addVertex("L12V");
        directedGraph.addVertex("L11V");
        directedGraph.addVertex("L11H");
        directedGraph.addVertex("L15H");
        directedGraph.addVertex("L14");
        directedGraph.addVertex("L13");
        directedGraph.addVertex("L15V");
        directedGraph.addVertex("L22");
        directedGraph.addVertex("L16H");
        directedGraph.addVertex("L12H");
        directedGraph.addVertex("L16H");
        directedGraph.addVertex("L21");
        directedGraph.addVertex("L21toL16");
        directedGraph.addVertex("L15toL22");
        directedGraph.addVertex("L21toL14");
        directedGraph.addVertex("L13toL22");
        directedGraph.addVertex("L12toL22");
        directedGraph.addVertex("L21toL11");
        directedGraph.addVertex("L15toL11");
        directedGraph.addVertex("L12toL16");
        directedGraph.addVertex("L22toL18");
        directedGraph.addVertex("L22toL19");
        directedGraph.addVertex("L17toL21");
        directedGraph.addVertex("L23toL21");
        directedGraph.addVertex("L22toL24");
        directedGraph.addVertex("L20toL21");
        directedGraph.addVertex("L11toL20");
        directedGraph.addVertex("L9toL12");
        directedGraph.addVertex("L19toL12");
        directedGraph.addVertex("L11toL10");
        directedGraph.addVertex("L13toL16");
        directedGraph.addVertex("L12toL14");
        directedGraph.addVertex("L15toL14");
        directedGraph.addVertex("L13toL11");
        directedGraph.addVertex("L23toL3");
        directedGraph.addVertex("L7toL23");
        directedGraph.addVertex("L18toL15");
        directedGraph.addVertex("L16toL17");
        directedGraph.addVertex("L3toL17");
        directedGraph.addVertex("L16toL1");
        directedGraph.addVertex("L18toL4");
        directedGraph.addVertex("L2toL15");
        directedGraph.addVertex("L8toL12");
        directedGraph.addVertex("L11toL7");
        directedGraph.addVertex("L16toL4");
        directedGraph.addVertex("L3toL15");

        directedGraph.addEdge("L2", "L2toL15");
        directedGraph.addEdge("L2", "L2toL4");
        directedGraph.addEdge("L2", "L2toL17");
        directedGraph.addEdge("L2toL15", "L15V");
        directedGraph.addEdge("L15V", "L15H");
        directedGraph.addEdge("L15H", "L15toL11");
        directedGraph.addEdge("L15toL11", "L11H");
        directedGraph.addEdge("L11H", "L11V");
        directedGraph.addEdge("L11V", "L11toL20");
        directedGraph.addEdge("L11V", "L11toL10");
        directedGraph.addEdge("L11V", "L11toL7");
        directedGraph.addEdge("L7V", "L7H");
//        directedGraph.addEdge("L7", "L7H");

        directedGraph.addEdge("L7H", "L7toL23");
        directedGraph.addEdge("L7H", "L7toL3");
        directedGraph.addEdge("L7toL3", "L3H");
        directedGraph.addEdge("L3H", "L3V");
        directedGraph.addEdge("L3V", "L3toL1");
        directedGraph.addEdge("L3toL1", "L1");

        directedGraph.addEdge("L7H", "L7toL6");
        directedGraph.addEdge("L15H", "L15toL14");
        directedGraph.addEdge("L15H", "L15toL22");
        directedGraph.addEdge("L5", "L5toL3");
        directedGraph.addEdge("L5toL3", "L3H");
        directedGraph.addEdge("L5", "L5toL8");
        directedGraph.addEdge("L5", "L5toL23");
        directedGraph.addEdge("L5toL23", "L23");
        directedGraph.addEdge("L23", "L23toL19");
        directedGraph.addEdge("L23", "L23toL18");
        directedGraph.addEdge("L23", "L23toL21");
        directedGraph.addEdge("L21", "L21toL16");
        directedGraph.addEdge("L21", "L21toL14");
        directedGraph.addEdge("L21", "L21toL11");
        directedGraph.addEdge("L21toL14", "L14");

        directedGraph.addEdge("L13", "L13toL11");
        directedGraph.addEdge("L13", "L13toL16");
        directedGraph.addEdge("L13", "L13toL22");
        directedGraph.addEdge("L13toL22", "L22");
        directedGraph.addEdge("L22", "L22toL18");
        directedGraph.addEdge("L22", "L22toL19");
        directedGraph.addEdge("L22", "L22toL24");

        //directedGraph.addEdge("", "");
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();

        getNextPath();
        root.getChildren().add(path);

        root.getChildren().add(car);
//        ImageView map = new ImageView(new Image(getClass().getResourceAsStream("img/road_v2.png")));
//        map.setFitWidth(540);
        car.setPreserveRatio(true);

//        root.getChildren().add(0, map);
        primaryStage.setTitle("JavaFX PathTransition Test with SVG");
        primaryStage.setScene(new Scene(root, 671, 481));
//        primaryStage.getScene().getStylesheets().add("C:\\Users\\PETER-PC\\Documents\\NetBeansProjects\\PCore_SVG_Test\\src\\pcore_svg_test\\assets\\style.css");
        primaryStage.show();

        playAnimation();
    }

    public void getNextPath() {
        PathParser parser = new PathParser();
//        SAXParser p = new SAXParser();
        JavaFXPathElementHandler handler = new JavaFXPathElementHandler("track");
        parser.setPathHandler(handler);
        System.out.println(" debug 1");
        String pathData = "";
        String transformMatrix = "";
        try {
            System.out.println(" debug 2");
            String xmlParser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(xmlParser);
            System.out.println(" debug 3");
            String uri = "FILE:\\Users\\User01\\Documents\\NetBeansProjects\\ATCS\\src\\img\\ATCS_DrivePath.svg";
            FileInputStream fI = new FileInputStream("C:\\Users\\User01\\Documents\\NetBeansProjects\\ATCS\\src\\img\\ATCS_DrivePath.svg");
            System.out.println(fI.available());
            SVGDocument doc = f.createSVGDocument(uri);
//            var amaobi = doc.getElementsByTagName("path");
//            
//
//            for (int i = 0; i < amaobi.getLength(); i++) {
//                var path = amaobi.item(i);
//                System.out.println("document in string form " +
//                        path.getAttributes().
//                                getNamedItem("id").
//                                getTextContent());
//            }

            Element element = doc.getElementById("L14");
            pathData = element.getAttributeNode("d").getValue();
            transformMatrix = element.getAttributeNode("transform").getValue();
//            System.out.println(pathData);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        parser.parse(pathData);

        path = handler.getPath();
        path.getElements().forEach(System.out::println);

        String tempTransformMatrix1 = transformMatrix.replace("matrix(", "");
        String tempTransformMatrix2 = tempTransformMatrix1.replace(")", "");

        var a = tempTransformMatrix2.split(",");
        path.getTransforms().add(Transform.affine(
                Double.parseDouble(a[0]),
                Double.parseDouble(a[1]),
                Double.parseDouble(a[2]),
                Double.parseDouble(a[3]),
                Double.parseDouble(a[4]),
                Double.parseDouble(a[5])
        ));
        path.setStroke(Color.BLACK);

        car = new ImageView(new Image(getClass().getResourceAsStream("img/red.png")));
        car.setFitHeight(20);

        car.setPreserveRatio(true);

    }

    public void playAnimation() {
        PathTransition pT = new PathTransition(Duration.seconds(5), path, car);

        pT.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//        pT.setCycleCount(2);
//        pT.setRate(-1);
        pT.play();

        pT.setOnFinished((e) -> {
              System.out.println("we have finished playing the animation");
        });

    }

}
