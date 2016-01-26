package javafxapplication1.src.main.java;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.apache.commons.csv.CSVFormat;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

public class FxmlWindowControl {

    private static final CSVFormat CSV_FORMAT = CSVFormat.RFC4180;

    private final ExecutorService executor = Executors.newCachedThreadPool();
    private static final FxmlWindowControl INSTANCE = new FxmlWindowControl();
    private static final Logger LOG = Logger.getLogger(FxmlWindowControl.class.getName());

    /**
     * 画面クローズ
     */
    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    /**
     * csv選択
     */
    @FXML
    private void csvChoose(ActionEvent event) throws Exception {
        System.out.println("test");
        Button button = (Button) event.getTarget();
        Window window = button.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Comma-Separated Values Files", "*.csv"));
        File selectedFile;
        selectedFile = fileChooser.showOpenDialog(window);
        MultiPart multipart = new FormDataMultiPart();
        multipart.bodyPart(new FileDataBodyPart("file", selectedFile, MediaType.APPLICATION_OCTET_STREAM_TYPE));

        Client client = ClientBuilder.newClient();
        client.register(MultiPartFeature.class);
        client.target("http://localhost:8080/api/csv/upload").request().post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA_TYPE));
    }
}
