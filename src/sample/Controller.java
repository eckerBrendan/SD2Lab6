/*
 * Course: CS-1021-051
 * Winter 2019
 * Lab 6 - Exceptions
 * Name: Brendan Ecker
 * Created: 01/17/19
 */
package sample;

import edu.msoe.se1021.Lab6.WebsiteTester;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Optional;

/**
 * This is the controller class for the Web Tester GUI.
 */
public class Controller {
    @FXML
    private TextField urlName;
    private WebsiteTester webTest = new WebsiteTester();
    @FXML
    private TextField timeout;
    @FXML
    private TextField size;
    @FXML
    private TextField downloadTime;
    @FXML
    private TextField host;
    @FXML
    private TextField port;
    @FXML
    private TextArea content;

    /**
     * The method that runs when the analyze
     * button is pressed or enter is pressed
     * when inside the URL text field.
     *
     * @param e An action that affects the button
     */
    @FXML
    public void analyze(ActionEvent e) {
        String url = urlName.getText();
        try {
            webTest.openURL(url);
            webTest.openConnection();
            webTest.downloadText();
            int size = webTest.getSize();
            this.size.setText(" " + size);
            downloadTime.setText(" " + webTest.getDownloadTime());
            host.setText("" + webTest.getHostname());
            port.setText("" + webTest.getPort());
            content.setText("" + webTest.getContent());

        } catch (MalformedURLException e1) {
            Alert malformedAlert = new Alert(Alert.AlertType.ERROR);
            malformedAlert.setTitle("Error Dialog");
            malformedAlert.setHeaderText("URL Error");
            malformedAlert.setContentText("The URL entered in the text box is invalid.");
            malformedAlert.showAndWait();
            urlName.setText("http://msoe.us/taylor/cs1021/Lab6");
        } catch (UnknownHostException e1) {
            Alert unknownHostAlert = new Alert(Alert.AlertType.ERROR);
            unknownHostAlert.setTitle("Error Dialog");
            unknownHostAlert.setHeaderText("Host Error");
            unknownHostAlert.setContentText("Error: Unable to reach the host " + url);
            unknownHostAlert.showAndWait();
            urlName.setText("http://msoe.us/taylor/cs1021/Lab6");
        } catch (SocketTimeoutException e1) {
            Alert timeoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
            timeoutAlert.setTitle("Timeout Dialog");
            timeoutAlert.setHeaderText("Wait longer?");
            timeoutAlert.setContentText("There has been a timeout reaching the site. Click OK to extend the timeout period?");
            Optional<ButtonType> result = timeoutAlert.showAndWait();
            if (result.get() == ButtonType.OK) {
                TextInputDialog timeoutSetter = new TextInputDialog("0");
                timeoutSetter.setTitle("Set timeout");
                timeoutSetter.setHeaderText("Set extended timeout");
                Optional<String> resultTimeout = timeoutSetter.showAndWait();
                if (resultTimeout.isPresent()) {
                    webTest.setTimeout(resultTimeout.get());
                    System.out.println(resultTimeout.get());
                }

            } else if (result.get() == ButtonType.CANCEL) {

            }
        } catch (IOException e1) {
            Alert iOExceptionAlert = new Alert(Alert.AlertType.ERROR);
            iOExceptionAlert.setTitle("Error Dialog");
            iOExceptionAlert.setHeaderText("File Error");
            iOExceptionAlert.setContentText("Error: File not found on the server, " + url);
            iOExceptionAlert.showAndWait();
        }
    }

    /**
     * The method that runs when the set button
     * is pressed or enter is pressed when inside
     * the timeout text field.
     *
     * @param e An action that effects the button.
     */
    public void setTimeout(ActionEvent e) {
        try {
            webTest.setTimeout(timeout.getText());
        } catch (NumberFormatException e1) {
            Alert numberFormatAlert = new Alert(Alert.AlertType.ERROR);
            numberFormatAlert.setTitle("Error Dialog");
            numberFormatAlert.setHeaderText("Invalid Timeout Error");
            numberFormatAlert.setContentText("Timeout must be greater than or equal to 0");
            numberFormatAlert.showAndWait();
        }
        timeout.setText(" ");
    }

    /**
     * This is the method that runs when the
     * enter key is pressed inside of the
     * URL text field that calls the earlier
     * method analyze.
     *
     * @param e A key event that only is accepted
     *          if it is the enter key.
     */
    public void enterAnalyze(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            ActionEvent e1 = new ActionEvent();
            analyze(e1);
        }
    }

    public void enterSet(KeyEvent e) {
        ActionEvent e1 = new ActionEvent();
        if (e.getCode() == KeyCode.ENTER) {
            setTimeout(e1);
        }
    }
}

