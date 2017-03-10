package it.molis.laBaioApp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.molis.baionetta.beans.Articolo;
import it.molis.baionetta.beans.Mostrina;
import it.molis.baionetta.beans.Penna;
import it.molis.baionetta.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class laBaioAppController {

	Model model;
	private int N=0;
	private List<Articolo> articoli;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblRicerca;

    @FXML
    private Label lblTitolo0;

    @FXML
    private Button btnLeggi0;

    @FXML
    private Label lblTitolo1;

    @FXML
    private Button btnLeggi1;

    @FXML
    private Label lblTitolo2;

    @FXML
    private Button btnLeggi2;

    @FXML
    private Label lblTitolo3;

    @FXML
    private Button btnLeggi3;

    @FXML
    private Label lblTitolo4;

    @FXML
    private Button btnLeggi4;

    @FXML
    private HBox txtCercaTitolo;

    @FXML
    private TextField txtTitolo;

    @FXML
    private ComboBox<Penna> cbxAutore;

    @FXML
    private ComboBox<Mostrina> cbxMostrina;

    @FXML
    private DatePicker cbxData;

    @FXML
    private Button btnRecenti;

    @FXML
    private Button btnVecchi;

    @FXML
    private Button btnReset;

    @FXML
    void doCerca(ActionEvent event) {
    	articoli.clear();
    	List<Articolo> risultatoRicerca = new ArrayList<>(model.ricerca(txtTitolo.getText(), 
    			cbxAutore.getValue(), cbxMostrina.getValue(), cbxData.getValue()));
    	articoli.addAll(risultatoRicerca);
    	set();
    	btnReset.setDisable(false);
    }

    @FXML
    void doLeggi0(ActionEvent event) {
    	Browser b = new Browser(articoli.get(N-4).getLink()+"?m=1");
    	SecondClass sc = new SecondClass();
    	sc.setBrowser(b);
    	sc.start(new Stage());
    }

    @FXML
    void doLeggi1(ActionEvent event) {
    	Browser b = new Browser(articoli.get(N-3).getLink()+"?m=1");
    	SecondClass sc = new SecondClass();
    	sc.setBrowser(b);
    	sc.start(new Stage());
    }

    @FXML
    void doLeggi2(ActionEvent event) {
    	Browser b = new Browser(articoli.get(N-2).getLink()+"?m=1");
    	SecondClass sc = new SecondClass();
    	sc.setBrowser(b);
    	sc.start(new Stage());
    }

    @FXML
    void doLeggi3(ActionEvent event) {
    	Browser b = new Browser(articoli.get(N-1).getLink()+"?m=1");
    	SecondClass sc = new SecondClass();
    	sc.setBrowser(b);
    	sc.start(new Stage());
    }

    @FXML
    void doLeggi4(ActionEvent event) {
    	Browser b = new Browser(articoli.get(N-0).getLink()+"?m=1");
    	SecondClass sc = new SecondClass();
    	sc.setBrowser(b);
    	sc.start(new Stage());
    }

    @FXML
    void doRecenti(ActionEvent event) {
    	N -= 9;
    	if(N<=0){
    		N=0;
    		btnRecenti.setDisable(true);
    	}
    	lblTitolo0.setDisable(false);
    	btnLeggi0.setDisable(false);
   		lblTitolo0.setText(articoli.get(N).toString());
    	System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
    	N++;
    	lblTitolo1.setDisable(false);
    	btnLeggi1.setDisable(false);
		lblTitolo1.setText(articoli.get(N).toString());
		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
		N++;
		lblTitolo2.setDisable(false);
    	btnLeggi2.setDisable(false);
		lblTitolo2.setText(articoli.get(N).toString());
		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
		N++;
		lblTitolo3.setDisable(false);
    	btnLeggi3.setDisable(false);
		lblTitolo3.setText(articoli.get(N).toString());
		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
		N++;
		lblTitolo4.setDisable(false);
    	btnLeggi4.setDisable(false);
		lblTitolo4.setText(articoli.get(N).toString());
		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);

		if(N<=articoli.size())
			btnVecchi.setDisable(false);
		if(N>=articoli.size())
			btnVecchi.setDisable(true);

    }

    @FXML
    void doReset(ActionEvent event) {
    	reSet();
    }

    @FXML
    void doVecchi(ActionEvent event) {
    	N++;
    	if(N<articoli.size()){
    		lblTitolo0.setText(articoli.get(N).toString());
    		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
    	}else {
    		lblTitolo0.setText("Hai letto tutti gli articoli corrispondenti a questa ricerca");
        	lblTitolo0.setDisable(true);
        	btnLeggi0.setDisable(true);
    	}
		N++;
    	if(N<articoli.size()){
    		lblTitolo1.setText(articoli.get(N).toString());
    		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
    	}else{
    		lblTitolo1.setText("Hai letto tutti gli articoli corrispondenti a questa ricerca");
        	lblTitolo1.setDisable(true);
        	btnLeggi1.setDisable(true);
    	}
		N++;
    	if(N<articoli.size()){
    		lblTitolo2.setText(articoli.get(N).toString());
    		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
    	}else {
    		lblTitolo2.setText("Hai letto tutti gli articoli corrispondenti a questa ricerca");
        	lblTitolo2.setDisable(true);
        	btnLeggi2.setDisable(true);
    	}
		N++;
    	if(N<articoli.size()){
    		lblTitolo3.setText(articoli.get(N).toString());
    		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
    	}else {
    		lblTitolo3.setText("Hai letto tutti gli articoli corrispondenti a questa ricerca");
        	lblTitolo3.setDisable(true);
        	btnLeggi3.setDisable(true);
    	}
		N++;
    	if(N<articoli.size()){
    		lblTitolo4.setText(articoli.get(N).toString());
    		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
    	}else {
    		lblTitolo4.setText("Hai letto tutti gli articoli corrispondenti a questa ricerca");
        	lblTitolo4.setDisable(true);
        	btnLeggi4.setDisable(true);
    	}

		if(N>=articoli.size())
			btnRecenti.setDisable(false);
		if(N>=articoli.size())
			btnVecchi.setDisable(true);
    }


    @FXML
    void initialize() {
    	 assert lblRicerca != null : "fx:id=\"lblRicerca\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert lblTitolo0 != null : "fx:id=\"lblTitolo0\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert lblTitolo1 != null : "fx:id=\"lblTitolo1\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert lblTitolo2 != null : "fx:id=\"lblTitolo2\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert lblTitolo3 != null : "fx:id=\"lblTitolo3\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert lblTitolo4 != null : "fx:id=\"lblTitolo4\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert txtCercaTitolo != null : "fx:id=\"txtCercaTitolo\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert txtTitolo != null : "fx:id=\"txtTitolo\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert cbxAutore != null : "fx:id=\"cbxAutore\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert cbxMostrina != null : "fx:id=\"cbxMostrina\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert cbxData != null : "fx:id=\"cbxData\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert btnRecenti != null : "fx:id=\"btnRecenti\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert btnVecchi != null : "fx:id=\"btnVecchi\" was not injected: check your FXML file 'laBaioApp.fxml'.";
         assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'laBaioApp.fxml'.";
    }

	public void setModel(Model model) {
		this.model=model;
		try {
			model.getArticoliFromFile();
			System.out.println("Reading File");
			model.getArticoliFromRss();
			System.out.println("Reading RSS");
			model.updateFileBackup();
			System.out.println("Caricati "+model.getAllArticoli().size()+" articoli");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cbxAutore.getItems().addAll(model.getAllPenne());
		cbxMostrina.getItems().addAll(model.getAllMostrine());

		reSet();

		for (Penna p : model.getAllPenne()){
			System.out.println(p.getNome());
			System.out.println(p.getAllArticoli().size());
		}
		for (Mostrina m : model.getAllMostrine()){
			System.out.println(m.getMostrina());
			System.out.println(m.getAllArticoli().size());
		}
	}

	private void reSet(){
		articoli = new ArrayList<>(model.getAllArticoliOrderByDate());
				
		set();
		
		btnVecchi.setDisable(false);
		btnReset.setDisable(true);
		txtTitolo.clear();
		cbxAutore.getSelectionModel().clearSelection();
		cbxMostrina.getSelectionModel().clearSelection();
		cbxData.getEditor().clear();
	}

	private void set(){
		N=0;
		if(N<articoli.size()){
			lblTitolo0.setDisable(false);
        	btnLeggi0.setDisable(false);
    		lblTitolo0.setText(articoli.get(N).toString());
    		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
    	}else {
    		lblTitolo0.setText("Hai letto tutti gli articoli corrispondenti a questa ricerca");
        	lblTitolo0.setDisable(true);
        	btnLeggi0.setDisable(true);
    	}
		N=1;
        if(N<articoli.size()){
        	lblTitolo1.setDisable(false);
        	btnLeggi1.setDisable(false);
        	lblTitolo1.setText(articoli.get(N).toString());
        	System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
        }else {
        	lblTitolo1.setText("Hai letto tutti gli articoli corrispondenti a questa ricerca");
            lblTitolo1.setDisable(true);
            btnLeggi1.setDisable(true);
        }
        N=2;
        if(N<articoli.size()){
        	lblTitolo2.setDisable(false);
        	btnLeggi2.setDisable(false);
    		lblTitolo2.setText(articoli.get(N).toString());
    		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
    	}else {
    		lblTitolo2.setText("Hai letto tutti gli articoli corrispondenti a questa ricerca");
        	lblTitolo2.setDisable(true);
        	btnLeggi2.setDisable(true);
    	}
        N=3;
        if(N<articoli.size()){
        	lblTitolo3.setDisable(false);
        	btnLeggi3.setDisable(false);
    		lblTitolo3.setText(articoli.get(N).toString());
    		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
    	}else {
    		lblTitolo3.setText("Hai letto tutti gli articoli corrispondenti a questa ricerca");
        	lblTitolo3.setDisable(true);
        	btnLeggi3.setDisable(true);
    	}
        N=4;
        if(N<articoli.size()){
        	lblTitolo4.setDisable(false);
        	btnLeggi4.setDisable(false);
    		lblTitolo4.setText(articoli.get(N).toString());
    		System.out.println(""+articoli.get(N).getTitolo()+"//"+N);
    	}else {
    		lblTitolo4.setText("Hai letto tutti gli articoli corrispondenti a questa ricerca");
        	lblTitolo4.setDisable(true);
        	btnLeggi4.setDisable(true);
    	}
        System.out.println(N);
		if(N<=articoli.size())
			btnVecchi.setDisable(false);
		if(N>=articoli.size())
			btnVecchi.setDisable(true);
	}
}
