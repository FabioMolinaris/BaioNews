package it.molis.baionetta.main;

import java.io.IOException;

import it.molis.baionetta.beans.Articolo;
import it.molis.baionetta.beans.Mostrina;
import it.molis.baionetta.beans.Penna;
import it.molis.baionetta.feed.FxmlReader;
import it.molis.baionetta.model.Model;

public class Main {

	//Model bc = new Model();

	public static void main(String[] args) {

		Model bc = new Model();


		try {
			//FxmlReader fxmlr = new FxmlReader();
			//fxmlr.read();
			bc.getArticoliFromFile();
			bc.getArticoliFromRss();
			System.out.println(bc.getAllArticoli().size());
			bc.updateFileBackup();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		for(Mostrina m : bc.getAllMostrine()){
			System.out.println(m.getMostrina());
		}

		for(Penna p : bc.getAllPenne()){
			System.out.println(p.getNome());
		}

		for(Articolo a : bc.getAllArticoliFromPenna(new Penna("Daniele Barale"))){
			System.out.println(a.getTitolo());
		}
		for(Articolo a : bc.getAllArticoliFromMostrina(new Mostrina("Obice"))){
			System.out.println(a.getTitolo());
		}

		for(Articolo a : bc.getAllArticoliFromPenna(new Penna("Daniele Barale"))){
			System.out.println(a.getTitolo());
		}

		for(Articolo a : bc.getArticoloFromTitolo("realismo")){
			System.out.println(a.getTitolo() + a.getArticoloId());
		}

		for(int i = 0; i<5; i++){
			System.out.println(bc.getAllArticoliOrderByDate().get(i).getTitolo());
			System.out.println(bc.getAllArticoliOrderByDate().get(i).getData());
		}

		for(Articolo a : bc.getAllArticoliFromMostrina(new Mostrina("Cinematografo dell'alpino"))){
				System.out.println(a.getTitolo());
		}
		*/
	}
}
