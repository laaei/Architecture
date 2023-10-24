import com.sun.applet2.AppletParameters;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    private Map<String, String> mainClassMap = new HashMap<>();
    private Map<String, Image> imageMap = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("体系结构选择器");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        ToggleGroup toggleGroup = new ToggleGroup();

        // 创建RadioButtons用于选择Main函数
        RadioButton main1Button = new RadioButton("主程序子程序");
        main1Button.setToggleGroup(toggleGroup);
        RadioButton main2Button = new RadioButton("管道过滤软件体系结构");
        main2Button.setToggleGroup(toggleGroup);
        RadioButton main3Button = new RadioButton("事件系统软件体系结构");
        main3Button.setToggleGroup(toggleGroup);
        RadioButton main4Button = new RadioButton("面向对象体系结构");
        main4Button.setToggleGroup(toggleGroup);

        Button executeButton = new Button("执行");
        ImageView imageView = new ImageView();
        TextArea textArea = new TextArea();

        root.getChildren().addAll(main1Button, main2Button, main3Button, main4Button, executeButton, imageView, textArea);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        mainClassMap.put("主程序子程序", "主程序子程序.Main");
        mainClassMap.put("面向对象体系结构", "面向对象体系结构.Main");
        mainClassMap.put("事件系统软件体系结构", "事件系统软件体系结构.Main");
        mainClassMap.put("管道过滤软件体系结构", "管道过滤软件体系结构.Main");

        imageMap.put("主程序子程序", new Image("img.png"));
        imageMap.put("管道过滤软件体系结构", new Image("img_3.png"));
        imageMap.put("事件系统软件体系结构", new Image("img_2.png"));
        imageMap.put("面向对象体系结构", new Image("img_1.png"));

        executeButton.setOnAction(e -> {
            RadioButton selectedButton = (RadioButton) toggleGroup.getSelectedToggle();
            if (selectedButton != null) {
                String selectedOption = selectedButton.getText();
                String mainClassName = mainClassMap.get(selectedOption);
                // 根据用户选择显示对应的图片
                Image selectedImage = imageMap.get(selectedOption);
                imageView.setImage(selectedImage);
                try {
                    Class<?> mainClass = Class.forName(mainClassName);
                    mainClass.getMethod("main", String[].class).invoke(null, (Object) new String[0]);

                    String textContent = new String(Files.readAllBytes(Paths.get("src\\output.txt")));
                    textArea.setText(textContent);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchMethodException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        primaryStage.show();
    }
}
