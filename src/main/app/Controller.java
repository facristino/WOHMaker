package app;

import com.sun.jdi.BooleanType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.util.converter.BooleanStringConverter;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.*;

public class Controller implements Initializable {

    List<String> locationsList = Arrays.asList("downtown", "apartment", "school", "hospital", "seaside", "forest", "mansion", "schoolhospital", "seasideforest", "village", "atorasu", "athyola","gozu","ithotu");
    List<String> checksList = Arrays.asList("story", "strength", "dexterity", "perception", "knowledge", "luck", "charisma","funds1","funds2");
    List<String> rewardsList = Arrays.asList("none", "experience", "stamina", "reason", "doom", "funds", "item", "injury", "curse", "spell", "ally");
    List<String> extraRewardsList = Arrays.asList("none", "experience", "stamina", "reason", "doom");
    List<String> visualEffectsList = Arrays.asList("none", "whiteflash", "bloodsplat");

    Image eventArt;

    List<ComboBox<String>> comboRewards = new ArrayList<>();
    List<ComboBox<String>> comboChecks = new ArrayList<>();
    List<ComboBox<String>> comboVisual = new ArrayList<>();
    List<ComboBox<String>> comboExtraRewards = new ArrayList<>();



    @FXML
    TextField textTitle;

    @FXML
    TextField textDesc;

    @FXML

    TextField txtContact;

    @FXML

    TextField txtAuthor;

    @FXML
    TextArea textFlav;

    @FXML
    ComboBox<String> cmbLocation;

    @FXML
    Label lblTooLong;

    @FXML
    Label lblImgWarn;

    @FXML
    ImageView imgArt;

    @FXML
    ComboBox<String> comboCheckA;

    @FXML
    ComboBox<String> comboCheckB;

    @FXML
    ComboBox<String> comboCheckC;

    @FXML
    ComboBox<String> cmbRewardsA;

    @FXML
    ComboBox<String> cmbRewardsAF;

    @FXML
    ComboBox<String> cmbExtraRewardsA;

    @FXML
    ComboBox<String> cmbExtraRewardsAF;

    @FXML
    ComboBox<String> cmbExtraRewardsB;

    @FXML
    ComboBox<String> cmbExtraRewardsBF;

    @FXML
    ComboBox<String> cmbExtraRewardsC;

    @FXML
    ComboBox<String> cmbExtraRewardsCF;

    @FXML
    ComboBox<String> cmbRewardsBF;

    @FXML
    ComboBox<String> cmbRewardsCF;

    @FXML
    ComboBox<String> cmbRewardsB;

    @FXML
    ComboBox<String> cmbRewardsC;

    @FXML
    ComboBox<String> cmbVisualA;

    @FXML
    ComboBox<String> cmbVisualB;

    @FXML
    ComboBox<String> cmbVisualC;

    @FXML
    ComboBox<String> cmbVisualAF;

    @FXML
    ComboBox<String> cmbVisualBF;

    @FXML
    ComboBox<String> cmbVisualCF;

    @FXML
    Button btnSaveUser;

    @FXML
    Button btnLoadPic;

    @FXML
    TextField txtPic;

    @FXML
    Button btnExit;

    @FXML
    Tab tabFailureA;

    @FXML
    Tab tabFailureB;

    @FXML
    CheckBox chkWavy;

    @FXML
    Tab tabFailureC;

    @FXML
    Slider sldWavy;

    @FXML
    CheckBox chkBigArt;

    @FXML
    Label lblWavyVal;

    @FXML
    ComboBox<String> cmbOptions;

    @FXML
    VBox optionA;

    @FXML
    VBox optionB;

    @FXML
    VBox optionC;

    @FXML
    Button btnLoadIto;

    @FXML
    TextArea helpArea;

    @FXML
    TextField txtOptionA;

    @FXML
    TextField txtOptionB;

    @FXML
    TextField txtOptionC;

    @FXML
    ImageView imgGuiArt;

    @FXML
    TextArea txtFailureA;

    @FXML
    TextArea txtFailureB;

    @FXML
    TextArea txtFailureC;

    @FXML
    TextArea txtSuccessA;

    @FXML
    TextArea txtSuccessB;

    @FXML
    TextArea txtSuccessC;

    @FXML
    TextField txtRewardA;

    @FXML
    TextField txtRewardAF;

    @FXML
    TextField txtRewardB;

    @FXML
    TextField txtRewardBF;

    @FXML
    TextField txtRewardC;

    @FXML
    TextField txtRewardCF;

    @FXML
    TextField txtExtraRewardA;

    @FXML
    TextField txtExtraRewardB;

    @FXML
    TextField txtExtraRewardC;

    @FXML
    TextField txtExtraRewardCF;

    @FXML
    TextField txtExtraRewardAF;

    @FXML
    TextField txtExtraRewardBF;

    @FXML
    Button btnSaveIto;

    @FXML
    ImageView btnHideHelp;

    Dialog imageViewDialog = new Dialog();

    String currentImage = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboRewards.addAll(Arrays.asList(cmbRewardsA,cmbRewardsAF,cmbRewardsB,cmbRewardsBF,cmbRewardsC,cmbRewardsCF));
        comboExtraRewards.addAll(Arrays.asList(cmbExtraRewardsA,cmbExtraRewardsAF,cmbExtraRewardsB,cmbExtraRewardsBF,cmbExtraRewardsC,cmbExtraRewardsCF));
        comboVisual.addAll(Arrays.asList(cmbVisualA,cmbVisualAF,cmbVisualB,cmbVisualBF,cmbVisualC,cmbVisualCF));
        comboChecks.addAll(Arrays.asList(comboCheckA,comboCheckB,comboCheckC));

        readPrefs();

        imgArt.sceneProperty().addListener(inv -> {
            imageViewDialog.initOwner(imgArt.getScene().getWindow());
            imageViewDialog.getDialogPane().getButtonTypes().setAll(ButtonType.CLOSE);
            imageViewDialog.initModality(Modality.NONE);
            imageViewDialog.setResizable(false);
            imageViewDialog.initStyle(StageStyle.UTILITY);
            imageViewDialog.getDialogPane().setStyle("-fx-background-color: black;");
        });

        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        implementHelpSystem();

       cmbOptions.getItems().addAll("1","2","3");

       imgArt.setOnMouseClicked(evt -> {
           if (!imageViewDialog.isShowing()) {
               imageViewDialog.setHeaderText(txtPic.getText());
               final ImageView imageView = new ImageView(imgArt.getImage());
               imageView.setFitWidth(800);
               imageView.setPreserveRatio(true);
               imageView.setSmooth(false);
               VBox content = new VBox(5);
               content.setAlignment(Pos.CENTER_RIGHT);
               content.getChildren().add(imageView);
               imageViewDialog.getDialogPane().setContent(content);
               imageViewDialog.show();
           } else {
               imageViewDialog.getDialogPane().requestFocus();
           }
       });

        btnSaveIto.setOnAction(evt -> {

            FileChooser fileChooser = null;
            File ito = null;

            try {
            fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.home"),"AppData", "Local", "wohgame").toFile());
            fileChooser.getExtensionFilters().setAll((new FileChooser.ExtensionFilter("ITO files","*.ito")));
            fileChooser.setTitle("Save inside 'Custom', 'sandbox' or 'test'!");
            ito = fileChooser.showSaveDialog(btnLoadPic.getScene().getWindow());

            } catch (Exception e) {
                fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());
                fileChooser.setTitle("WOH Folder couldn't be located, please browser manually.");
                 ito = fileChooser.showSaveDialog(btnLoadPic.getScene().getWindow());
            }
            if (!Objects.isNull(ito)) {
                try {
                    saveIto(ito);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnHideHelp.setOnMouseClicked(evt -> {
            helpArea.setVisible(!helpArea.isVisible());
                savePrefs("enableHelp",helpArea.isVisible());
        });

        btnLoadIto.setOnAction(evt -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());
            fileChooser.getExtensionFilters().setAll((new FileChooser.ExtensionFilter("ITO files","*.ito")));
            fileChooser.setTitle("Select custom event file");
            File ito = fileChooser.showOpenDialog(btnLoadPic.getScene().getWindow());
            if (ito!=null){
                loadIto(ito);
            }
        });

        //Listeners and handlers

        textFlav.lengthProperty().addListener((ob,old,newValue) -> lblTooLong.setVisible(newValue.intValue()>350));
        comboCheckA.getSelectionModel().selectedItemProperty().addListener((ob,old,newValue) -> tabFailureA.setDisable(newValue.equals("story")));
        comboCheckB.getSelectionModel().selectedItemProperty().addListener((ob,old,newValue) -> tabFailureB.setDisable(newValue.equals("story")));
        comboCheckC.getSelectionModel().selectedItemProperty().addListener((ob,old,newValue) -> tabFailureC.setDisable(newValue.equals("story")));
        cmbOptions.getSelectionModel().selectedItemProperty().addListener((ob,old,newValue) -> {
            optionB.setDisable(newValue.equals("1"));
            optionC.setDisable(newValue.equals("1") || newValue.equals("2"));
        });

        chkWavy.selectedProperty().addListener((ob,old,newValue) -> sldWavy.setDisable(!newValue));
        sldWavy.valueProperty().addListener((ob,old,newValue) -> lblWavyVal.setText(String.format("%.1f", newValue)));

        btnSaveUser.setOnAction(evt -> {
            final File author = Paths.get(System.getProperty("user.home"), "WOHMaker", "author.txt").toFile();
            if (author.exists()) author.delete();

                try (final BufferedWriter writer = new BufferedWriter(new FileWriter(author))) {
                    writer.write(txtAuthor.getText() + System.lineSeparator() + txtAuthor.getText());
                    btnSaveUser.setDisable(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });

        btnLoadPic.setOnAction(evt -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());
            fileChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Image files","*.png", "*.bmp","*.gif"));
            fileChooser.setTitle("Select custom event art");
            File img = fileChooser.showOpenDialog(btnLoadPic.getScene().getWindow());
            if (img!=null){
                txtPic.setText(img.getName());
                imgArt.setImage(new Image(img.toURI().toString()));
                eventArt = imgArt.getImage();
                doImageCalculations();
            }
        });

        btnExit.setOnAction(evt -> System.exit(0));
        txtAuthor.setOnKeyReleased(evt -> btnSaveUser.setDisable(false));
        txtContact.setOnKeyReleased(evt -> btnSaveUser.setDisable(false));
    }

    File prefs = Paths.get(System.getProperty("user.home"), "WOHMaker", "prefs.dat").toFile();

    private void savePrefs(String property, boolean state) {

        if (prefs.exists()) {
            try {
                List<String> strings = Files.readAllLines(prefs.toPath());
                for (int i = 0; i < strings.size(); i++) {
                    if (strings.get(i).contains(property+"="+!state)) {
                        strings.set(i, property+"="+state);
                        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(prefs))) {
                            strings.forEach(str -> {
                                try {
                                    writer.write(str+ System.lineSeparator());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                    }
                }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createPrefs();
            savePrefs(property,state);
        }
    }

    void createPrefs(){
        prefs = Paths.get(System.getProperty("user.home"), "WOHMaker", "prefs.dat").toFile();
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(prefs))) {
            writer.write("enableHelp=true"+System.lineSeparator() +
                     "enableArts=true"+System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readPrefs(){
        if (prefs.exists()) {
            try {
                List<String> strings = Files.readAllLines(prefs.toPath());
                strings.forEach(str -> {
                    if (str.equals("enableHelp=false")) helpArea.setVisible(false);
                    else if (str.equals("enableArts=false")) { imgGuiArt.setVisible(false);}
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createPrefs();
        }
    }

    /**
     * Ticks/unticks the big art check depending on image res and warns about resolution not corresponding to woh sizes.
     */

    void doImageCalculations(){

        if ((eventArt.getWidth()==195 && eventArt.getHeight()==164)){
            lblImgWarn.setVisible(false);
            chkBigArt.setIndeterminate(false);
            chkBigArt.setSelected(false);
        } else if ((eventArt.getWidth()==506 && eventArt.getHeight()==220)){
            lblImgWarn.setVisible(false);
            chkBigArt.setIndeterminate(false);
            chkBigArt.setSelected(true);
        } else {
            lblImgWarn.setVisible(true);
            chkBigArt.setSelected(false);
            chkBigArt.setIndeterminate(true);
            lblImgWarn.setText("Image resolution doesn't seem correct");
        }
    }

    // STRINGS

    private static final String REWARDINFO = "Items and spells can be specified in all caps (i.e: FIRE AXE). Leave blank for injuries,allies and curses. Use integers for the rest.";
    private static final String TESTINFO = "Player stat to be tested. 'Story' means automatic success. 'Funds1' and 'Funds2' succeed if player owns at least 1 or 2 funds, and removes them.";
    private static final String VISUALINFO = "Visual effect which will be triggered when this outcome is reached.";
    private static final String EXTRAREWARDSINFO = "Additional outcome granted to the player for passing the check A. Use integers.";

    /**
     * Listens to mouse hover to fill the help panel.
     */

    void implementHelpSystem(){

        textTitle.hoverProperty().addListener((ob,old,newValue) -> {if (newValue) helpArea.setText("Shown on the upper-left part of the screen and mod menu.");});
        textDesc.hoverProperty().addListener(inv -> helpArea.setText("Shown only in the mod menu. Around 25 chars maximum!"));
        textFlav.hoverProperty().addListener(inv -> helpArea.setText("The text shown to player when facing the event. Around 350 chars max."));
        txtAuthor.hoverProperty().addListener(inv -> helpArea.setText("Shown in the lower-left part of the screen, and mod menu."));
        txtContact.hoverProperty().addListener(inv -> helpArea.setText("NOT shown in-game. A way for the dev to contact you shall it be necessary. @ is implied to be a discord name."));
        txtSuccessA.hoverProperty().addListener(inv -> helpArea.setText("Text shown if passing the check for option A, or in 'Story' type checks"));
        txtSuccessB.hoverProperty().addListener(inv -> helpArea.setText("Text shown if passing the check for option B, or in 'Story' type checks"));
        txtSuccessC.hoverProperty().addListener(inv -> helpArea.setText("Text shown if passing the check for option C, or in 'Story' type checks"));
        txtFailureA.hoverProperty().addListener(inv -> helpArea.setText("Text shown if failing the check for option A."));
        txtFailureB.hoverProperty().addListener(inv -> helpArea.setText("Text shown if failing the check for option B."));
        txtFailureC.hoverProperty().addListener(inv -> helpArea.setText("Text shown if failing the check for option C."));
        cmbOptions.hoverProperty().addListener(inv -> helpArea.setText("Number of choices presented to the player in this event."));
        cmbLocation.hoverProperty().addListener(inv -> helpArea.setText("The event will be added to this area's deck alone. God-dependant locations are global, tho."));
        cmbVisualA.hoverProperty().addListener(inv -> helpArea.setText("The event will be added to this area's deck alone. God-dependant locations are global, tho."));
        chkWavy.hoverProperty().addListener(inv -> helpArea.setText("If enabled, the art will undulate at the given speed."));
        sldWavy.hoverProperty().addListener(inv -> helpArea.setText("The higher, the quickers the wavy animation will be"));
        chkBigArt.hoverProperty().addListener(inv -> helpArea.setText("Big arts take the whole event screen. Will be selected automatically when loading a pic."));
        btnLoadIto.hoverProperty().addListener(inv -> helpArea.setText("Loads an already created event and parses its contents.Wrong semantics will likely result in error."));
        btnSaveUser.hoverProperty().addListener(inv -> helpArea.setText("Persists user/contact information so it's filled automatically when loading this app."));
        btnExit.hoverProperty().addListener(inv -> helpArea.setText("Closes the app, any unsaved change will be lost!"));
        btnLoadPic.hoverProperty().addListener(inv -> helpArea.setText("Select the art to display in the event. Small events are 195x164, while big ones are 506x2020. Avoid using other resolutions."));
        btnSaveIto.hoverProperty().addListener(inv -> helpArea.setText("Saves the event to disk as an .ito file. They must be put inside the 'custom','sandbox' or 'test' subfolders inside the wohfolder"));

        comboExtraRewards.forEach(cmb -> cmb.hoverProperty().addListener(inv -> helpArea.setText(EXTRAREWARDSINFO)));
        comboChecks.forEach(cmb -> cmb.hoverProperty().addListener(inv -> helpArea.setText(TESTINFO)));
        comboRewards.forEach(cmb -> cmb.hoverProperty().addListener(inv -> helpArea.setText(REWARDINFO)));
        comboVisual.forEach(cmb -> cmb.hoverProperty().addListener(inv -> helpArea.setText(VISUALINFO)));
    }

    /**
     * Charges the comboboxes with valid Strings.
     * @throws IOException if files cannot be accessed
     */

    public void loadData() throws IOException {

        final File wohMaker = Paths.get(System.getProperty("user.home"), "WOHMaker").toFile();
        if (!wohMaker.exists()) wohMaker.mkdir();

        // reads author data, or generates one

        final File author = Paths.get(System.getProperty("user.home"), "WOHMaker", "author.txt").toFile();
        if (!author.exists()) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(author))) {
                writer.write(System.getProperty("user.name") + System.lineSeparator() + "@" + System.getProperty("user.name"));
            }
        }

        List<String> authorData = Files.readAllLines(Paths.get(author.toURI()));
        txtAuthor.setText(authorData.get(0));
        txtContact.setText(authorData.get(1));

        // reads valid locations from disk. If no file is present, generates one with currently known strings.

        final File locations = Paths.get(System.getProperty("user.home"), "WOHMaker", "locations.txt").toFile();
        if (!locations.exists()) {
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(locations))) {
                for (String s: locationsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } else{
            locationsList = Files.readAllLines(Paths.get(locations.toURI()));
        }

        cmbLocation.getItems().addAll(locationsList);
        cmbLocation.getSelectionModel().select(0);

        // reads valid stat checks from disk. If no file is present, generates one with currently known strings.
        final File statchecks = Paths.get(System.getProperty("user.home"), "WOHMaker", "statchecks.txt").toFile();
        if (!statchecks.exists()){
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(statchecks))) {
                for (String s: checksList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }


        comboCheckA.getItems().addAll(checksList);
        comboCheckB.getItems().addAll(checksList);
        comboCheckC.getItems().addAll(checksList);
        comboCheckA.getSelectionModel().select(0);
        comboCheckB.getSelectionModel().select(0);
        comboCheckC.getSelectionModel().select(0);

        // reads valid rewards from disk. If no file is present, generates one with currently known strings.
        final File rewards = Paths.get(System.getProperty("user.home"), "WOHMaker", "rewards.txt").toFile();
        if (!rewards.exists()){
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(rewards))) {
                for (String s: rewardsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        comboRewards.forEach(cmb -> {
            cmb.getItems().addAll(rewardsList);
            cmb.getSelectionModel().select(0);
        });


        // reads valid extra rewards from disk. If no file is present, generates one with currently known strings.
        final File extraRewards = Paths.get(System.getProperty("user.home"), "WOHMaker", "extrarewards.txt").toFile();
        if (!extraRewards.exists()){
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(extraRewards))) {
                for (String s: extraRewardsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        comboExtraRewards.forEach(cmb -> {
            cmb.getItems().addAll(extraRewardsList);
            cmb.getSelectionModel().select(0);
        });

        // reads valid visual effects from disk. If no file is present, generates one with currently known strings.
        final File visualEffects = Paths.get(System.getProperty("user.home"), "WOHMaker", "visualeffects.txt").toFile();
        if (!visualEffects.exists()){
            try (final BufferedWriter writer = new BufferedWriter(new FileWriter(visualEffects))) {
                for (String s: visualEffectsList) {
                    writer.write(s + System.lineSeparator());
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        comboVisual.forEach(cmb -> {
            cmb.getItems().addAll(visualEffectsList);
            cmb.getSelectionModel().select(0);
        });

    }

    void loadIto(File ito){

        try {
            List<String> strings = Files.readAllLines(ito.toPath());
            for (String s: strings){
                String substring;
                try {
                    substring = s.substring(s.indexOf('"')+1, s.lastIndexOf('"'));
                    if (s.startsWith("name")) textTitle.setText(substring);
                    if (s.startsWith("location")) cmbLocation.getSelectionModel().select(substring);
                    if (s.startsWith("options")) cmbOptions.getSelectionModel().select(substring);
                    if (s.startsWith("about")) textDesc.setText(substring);
                    if (s.startsWith("flavor")) textFlav.setText(substring);
                    if (s.startsWith("optiona")) txtOptionA.setText(substring);
                    if (s.startsWith("optionb")) txtOptionB.setText(substring);
                    if (s.startsWith("optionc")) txtOptionC.setText(substring);
                    if (s.startsWith("testa")) comboCheckA.getSelectionModel().select(substring);
                    if (s.startsWith("testb")) comboCheckB.getSelectionModel().select(substring);
                    if (s.startsWith("testc")) comboCheckC.getSelectionModel().select(substring);
                    if (s.startsWith("successa")) txtSuccessA.setText(substring);
                    if (s.startsWith("successb")) txtSuccessB.setText(substring);
                    if (s.startsWith("successc")) txtSuccessC.setText(substring);
                    if (s.startsWith("failurea")) txtFailureA.setText(substring);
                    if (s.startsWith("failureb")) txtFailureB.setText(substring);
                    if (s.startsWith("failurec")) txtFailureC.setText(substring);
                    if (s.startsWith("author")) txtAuthor.setText(substring);
                    if (s.startsWith("contact")) txtContact.setText(substring);
                    if (s.startsWith("winprizea")) cmbRewardsA.getSelectionModel().select(substring);
                    if (s.startsWith("winprizeb")) cmbRewardsB.getSelectionModel().select(substring);
                    if (s.startsWith("winprizec")) cmbRewardsC.getSelectionModel().select(substring);
                    if (s.startsWith("failprizea")) cmbRewardsAF.getSelectionModel().select(substring);
                    if (s.startsWith("failprizeb")) cmbRewardsBF.getSelectionModel().select(substring);
                    if (s.startsWith("failprizec")) cmbRewardsCF.getSelectionModel().select(substring);
                    if (s.startsWith("extra_winprizea")) cmbExtraRewardsA.getSelectionModel().select(substring);
                    if (s.startsWith("extra_winprizeb")) cmbExtraRewardsB.getSelectionModel().select(substring);
                    if (s.startsWith("extra_winprizec")) cmbExtraRewardsC.getSelectionModel().select(substring);
                    if (s.startsWith("extra_failprizea")) cmbExtraRewardsAF.getSelectionModel().select(substring);
                    if (s.startsWith("extra_failprizeb")) cmbExtraRewardsBF.getSelectionModel().select(substring);
                    if (s.startsWith("extra_failprizec")) cmbExtraRewardsCF.getSelectionModel().select(substring);
                    if (s.startsWith("winnumbera")) txtRewardA.setText(substring);
                    if (s.startsWith("winnumberb")) txtRewardB.setText(substring);
                    if (s.startsWith("winnumberc")) txtRewardC.setText(substring);
                    if (s.startsWith("failnumbera")) txtRewardAF.setText(substring);
                    if (s.startsWith("failnumberb")) txtRewardBF.setText(substring);
                    if (s.startsWith("failnumberc")) txtRewardCF.setText(substring);
                    if (s.startsWith("extra_failnumbera")) txtExtraRewardAF.setText(substring);
                    if (s.startsWith("extra_failnumberb")) txtExtraRewardBF.setText(substring);
                    if (s.startsWith("extra_failnumberc")) txtExtraRewardCF.setText(substring);
                    if (s.startsWith("extra_winnumbera")) txtExtraRewardA.setText(substring);
                    if (s.startsWith("extra_winnumberb")) txtExtraRewardB.setText(substring);
                    if (s.startsWith("extra_winnumberc")) txtExtraRewardC.setText(substring);
                    if (s.startsWith("wineffecta")) cmbVisualA.getSelectionModel().select(substring);
                    if (s.startsWith("wineffectb")) cmbVisualB.getSelectionModel().select(substring);
                    if (s.startsWith("wineffectc")) cmbVisualC.getSelectionModel().select(substring);
                    if (s.startsWith("faileffecta")) cmbVisualAF.getSelectionModel().select(substring);
                    if (s.startsWith("faileffectb")) cmbVisualBF.getSelectionModel().select(substring);
                    if (s.startsWith("faileffectc")) cmbVisualCF.getSelectionModel().select(substring);
                    if (s.startsWith("image")) {
                        currentImage = substring;
                        imgArt.setImage(new Image(ito.getParentFile().toURI().toString().replace(".ito","").concat(File.separator).concat(substring)));
                        txtPic.setText(substring);
                        eventArt = imgArt.getImage();
                        doImageCalculations();
                    }
                }catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void saveIto(File ito) throws IOException {

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(ito.toPath(), Charset.defaultCharset())) {
            bufferedWriter.write("[event]" + System.lineSeparator());
            bufferedWriter.write(
                    "name=\"" + textTitle.getText() + "\"" + System.lineSeparator() +
                            "location=\"" + cmbLocation.getSelectionModel().getSelectedItem() + "\"" + System.lineSeparator() +
                            "about=\"" + textDesc.getText() + "\"" + System.lineSeparator() +
                            "author=\"" + txtAuthor.getText() + "\"" + System.lineSeparator() +
                            "contact=\"" + txtContact.getText() + "\"" + System.lineSeparator()+System.lineSeparator() +
                            "flavaaaor=\"" + textFlav.getText() + "\"" + System.lineSeparator() +
                            "image=\"" + txtPic.getText() +"\"" +System.lineSeparator()+
                            "big=\"" + (chkBigArt.isSelected()? "1": "0") +"\"" + System.lineSeparator() +
                            "wavy_art=\"" + (chkWavy.isSelected()? "1": "0") +"\"" + System.lineSeparator() +
                            "wavy_speed=\"" + String.format("%.1f",sldWavy.getValue()) + "\"" + System.lineSeparator() +
                            "options=\"" + cmbOptions.getSelectionModel().getSelectedItem() + "\"" + System.lineSeparator()+System.lineSeparator() +System.lineSeparator() +

                            "optiona=\"" + txtOptionA.getText() + "\"" + System.lineSeparator() +
                            "testa=\"" + comboCheckA.getSelectionModel().getSelectedItem() + "\"" + System.lineSeparator()+System.lineSeparator()+
                            "successa=\"" + txtSuccessA.getText() + "\"" + System.lineSeparator() +
                            "winprizea=\"" + (cmbRewardsA.getSelectionModel().getSelectedItem().equals("none")? "" : cmbRewardsA.getSelectionModel().getSelectedItem()) + "\"" +System.lineSeparator()+
                            "winnumbera=\"" + txtRewardA.getText() + "\"" +System.lineSeparator() +
                            "extra_winprizea=\"" +  (cmbExtraRewardsA.getSelectionModel().getSelectedItem().equals("none")? "" : cmbExtraRewardsA.getSelectionModel().getSelectedItem())+ "\"" + System.lineSeparator() +
                            "extra_winnumbera=\"" + txtExtraRewardA.getText() + "\"" +System.lineSeparator() +
                            "wineffecta=" + (cmbVisualA.getSelectionModel().getSelectedItem().equals("none")? "" : cmbVisualA.getSelectionModel().getSelectedItem())+ System.lineSeparator() +System.lineSeparator() +
                            "failurea=\"" + txtFailureA.getText() + "\"" + System.lineSeparator() +
                            "failprizea=\"" +  (cmbRewardsAF.getSelectionModel().getSelectedItem().equals("none")? "" : cmbRewardsAF.getSelectionModel().getSelectedItem())+ "\"" +System.lineSeparator()+
                            "failnumbera=\"" + txtRewardAF.getText() + "\"" + System.lineSeparator() +
                            "extra_failprizea=\"" + (cmbExtraRewardsAF.getSelectionModel().getSelectedItem().equals("none")? "" : cmbExtraRewardsAF.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator()+
                            "extra_failnumbera=\"" + txtExtraRewardAF.getText() + "\"" + System.lineSeparator() +
                            "faileffecta=\"" + (cmbVisualAF.getSelectionModel().getSelectedItem().equals("none")? "" : cmbVisualAF.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator()+System.lineSeparator() +System.lineSeparator() +

                            "optionb=\"" + txtOptionB.getText() + "\"" + System.lineSeparator() +
                            "testb=\"" + comboCheckB.getSelectionModel().getSelectedItem() + "\"" + System.lineSeparator()+System.lineSeparator() +
                            "successb=\"" + txtSuccessB.getText() + "\"" + System.lineSeparator() +
                            "winprizeb=\"" + (cmbRewardsB.getSelectionModel().getSelectedItem().equals("none")? "" : cmbRewardsB.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator()+
                            "winnumberb=\"" + txtRewardB.getText() + "\"" + System.lineSeparator() +
                            "extra_winprizeb=\"" + (cmbExtraRewardsB.getSelectionModel().getSelectedItem().equals("none")? "" : cmbExtraRewardsB.getSelectionModel().getSelectedItem())+ "\"" + System.lineSeparator()+
                            "extra_winnumberb=\"" + txtExtraRewardB.getText() + "\"" + System.lineSeparator() +
                            "wineffectb=" +(cmbVisualB.getSelectionModel().getSelectedItem().equals("none")? "" : cmbVisualB.getSelectionModel().getSelectedItem())+ System.lineSeparator() + System.lineSeparator() +
                            "failureb=\"" + txtFailureB.getText() + "\"" +System.lineSeparator() +
                            "failprizeb=\"" + (cmbRewardsBF.getSelectionModel().getSelectedItem().equals("none")? "" : cmbRewardsBF.getSelectionModel().getSelectedItem()) + "\"" +System.lineSeparator() +
                            "failnumberb=\"" + txtRewardBF.getText() + "\"" + System.lineSeparator() +
                            "extra_failprizeb=\"" + (cmbExtraRewardsBF.getSelectionModel().getSelectedItem().equals("none")? "" : cmbExtraRewardsBF.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator()+
                            "extra_failnumberb=\"" + txtExtraRewardBF.getText() + "\"" + System.lineSeparator() +
                            "faileffectb=\"" + (cmbVisualBF.getSelectionModel().getSelectedItem().equals("none")? "" : cmbVisualBF.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator()+System.lineSeparator() +System.lineSeparator()+

                            "optionc=\"" + txtOptionC.getText() + "\"" + System.lineSeparator() +
                            "testc=\"" + comboCheckC.getSelectionModel().getSelectedItem() + "\"" + System.lineSeparator()+System.lineSeparator() +
                            "successc=\"" + txtSuccessC.getText() + "\"" + System.lineSeparator() +
                            "winprizec=\"" + (cmbRewardsC.getSelectionModel().getSelectedItem().equals("none")? "" : cmbRewardsC.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator() +
                            "winnumberc=\"" + txtRewardC.getText() + "\"" +System.lineSeparator()+
                            "extra_winprizec=\"" + (cmbExtraRewardsC.getSelectionModel().getSelectedItem().equals("none")? "" : cmbExtraRewardsC.getSelectionModel().getSelectedItem())+ "\"" + System.lineSeparator() +
                            "extra_winnumberc=\"" + txtExtraRewardC.getText() + "\"" + System.lineSeparator() +
                            "wineffectc=" +(cmbVisualC.getSelectionModel().getSelectedItem().equals("none")? "" : cmbVisualC.getSelectionModel().getSelectedItem())+ System.lineSeparator()+System.lineSeparator()+
                            "failurec=\"" + txtFailureC.getText() + "\"" + System.lineSeparator()+
                            "failprizec=\"" + (cmbRewardsCF.getSelectionModel().getSelectedItem().equals("none")? "" : cmbRewardsCF.getSelectionModel().getSelectedItem()) + "\"" + System.lineSeparator() +
                            "failnumberc=\"" + txtRewardCF.getText() + "\"" + System.lineSeparator() +
                            "extra_failprizec=\"" + (cmbExtraRewardsCF.getSelectionModel().getSelectedItem().equals("none")? "" : cmbExtraRewardsCF.getSelectionModel().getSelectedItem())+ "\"" + System.lineSeparator()+
                            "extra_failnumberc=\"" + txtExtraRewardCF.getText() + "\"" + System.lineSeparator() +
                            "faileffectc=\"" + (cmbVisualCF.getSelectionModel().getSelectedItem().equals("none")? "" : cmbVisualCF.getSelectionModel().getSelectedItem()) + "\"" +System.lineSeparator()+System.lineSeparator() +

                            "--Made with WOHMaker--=" + System.lineSeparator());
        }

    }
}
