package it.molis.laBaioApp;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;
import java.util.ResourceBundle;

import it.molis.baionetta.beans.Articolo;
import it.molis.baionetta.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class laBaioAppController {

	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtTitolo;

    @FXML
    private Label txtMostrina;

    @FXML
    private TextField txtCercaTitolo;

    @FXML
    private TextField txtCercaMostrina;

    @FXML
    private TextField txtCercaAutore;

    @FXML
    private ComboBox<DayOfWeek> cbxGiorno;

    @FXML
    private ComboBox<Month> cbxMese;

    @FXML
    private ComboBox<Year> cbxAnno;

    @FXML
    private WebView webArticolo;

    @FXML
    void doAnno(ActionEvent event) {

    }

    @FXML
    void doCercaAutore(ActionEvent event) {

    	try {
			model.getArticoliFromFile();
			model.getArticoliFromRss();
			System.out.println(model.getAllArticoli().size());
			model.updateFileBackup();
		} catch (IOException e) {
			e.printStackTrace();
		}

    	String url = model.getUltimoArticolo().getLink();
    	    SecondClass sc = new SecondClass();
    	    System.out.println(url);
    	    Browser b = new Browser(url+"?m=1");
    	    sc.setBrowser(b);
    	   // b.setUrl("http://www.oracle.com/products/index.html");
    	    try {
				sc.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    @FXML
    void doCercaMostrina(ActionEvent event) {

    }

    @FXML
    void doCercaTitolo(ActionEvent event) {

    }

    @FXML
    void doGiorno(ActionEvent event) {

    }

    @FXML
    void doMese(ActionEvent event) {

    }



    @FXML
    void initialize() {
        assert txtTitolo != null : "fx:id=\"txtTitolo\" was not injected: check your FXML file 'laBaioApp.fxml'.";
        assert txtMostrina != null : "fx:id=\"txtMostrina\" was not injected: check your FXML file 'laBaioApp.fxml'.";
        assert txtCercaTitolo != null : "fx:id=\"txtCercaTitolo\" was not injected: check your FXML file 'laBaioApp.fxml'.";
        assert txtCercaMostrina != null : "fx:id=\"txtCercaMostrina\" was not injected: check your FXML file 'laBaioApp.fxml'.";
        assert txtCercaAutore != null : "fx:id=\"txtCercaAutore\" was not injected: check your FXML file 'laBaioApp.fxml'.";
        assert cbxGiorno != null : "fx:id=\"cbxGiorno\" was not injected: check your FXML file 'laBaioApp.fxml'.";
        assert cbxMese != null : "fx:id=\"cbxMese\" was not injected: check your FXML file 'laBaioApp.fxml'.";
        assert cbxAnno != null : "fx:id=\"cbxAnno\" was not injected: check your FXML file 'laBaioApp.fxml'.";
        assert webArticolo != null : "fx:id=\"webArticolo\" was not injected: check your FXML file 'laBaioApp.fxml'.";

        cbxGiorno.getItems().addAll(DayOfWeek.values());
        cbxMese.getItems().addAll(Month.values());
        cbxAnno.getItems().addAll(Year.of(2015));
        cbxAnno.getItems().addAll(Year.of(2016));
        cbxAnno.getItems().addAll(Year.of(2017));


    }

	public void setModel(Model model) {
		this.model=model;
	}
}
