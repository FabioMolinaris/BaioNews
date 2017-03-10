package it.molis.baionetta.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.molis.baionetta.beans.Articolo;
import it.molis.baionetta.beans.Mostrina;
import it.molis.baionetta.beans.Penna;
import it.molis.baionetta.beans.RisultatoRicerca;
import it.molis.baionetta.feed.Feed;
import it.molis.baionetta.feed.FeedMessage;
import it.molis.baionetta.feed.FeedReader;

public class Model {

	private Set<Articolo> articoli = new HashSet<>();
	private Set<Penna> penne = new HashSet<>();
	private Set<Mostrina> mostrine = new HashSet<>();

	private String feedUrl = "https://labaionetta.blogspot.com/feeds/posts/default?alt=rss";

	public void getArticoliFromRss(){

		FeedReader fd = new FeedReader(feedUrl);

		if(fd.read() == null){
			System.out.println("Internet!!");
		}else{
			Feed feed = fd.readFeed();

			for (FeedMessage message : feed.getMessages()) {

				if (message.getLink()!=null){
					Mostrina m = new Mostrina(message.getCategory());
					Penna p = creaPenna(message.getAuthor());
					//System.out.println(p.getNome());

					LocalDate date = LocalDate.parse(message.getPubDate(), DateTimeFormatter.RFC_1123_DATE_TIME);
					Articolo a = new Articolo(message.getTitle(), m, p, message.getLink(), date);
					if(!articoli.contains(a))
						articoli.add(a);
					penne.add(p);
					mostrine.add(m);
				}
			}
		}
	}

	public void getArticoliFromFile() throws IOException{
		String s;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("/home/fabio/workspace/BaioNews_App/src/BaioBackup.molis"));
			while( (s = reader.readLine()) != null ){

				Articolo a = new Articolo(null, null, null, null, null);

				LocalDate date = LocalDate.parse(s.split("<> ")[0], DateTimeFormatter.ISO_LOCAL_DATE);
				a.setData(date);

				Mostrina m = new Mostrina(s.split("<> ")[1]);
				a.setMostrina(m);

				a.setTitolo(s.split("<> ")[2]);

				a.setLink(s.split("<> ")[3]);

				Penna p = new Penna(s.split("<> ")[4]);
				a.setPenna(p);

				articoli.add(a);
				penne.add(p);
				mostrine.add(m);
				p.setArticolo(a);
				m.setArticolo(a);
			}

			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(Articolo a : articoli)
			for(Penna p : penne)
				if(a.getPenna().equals(p))
					p.setArticolo(a);

		for(Articolo a : articoli)
			for(Mostrina m : mostrine)
				if(a.getMostrina().equals(m))
					m.setArticolo(a);
	}

	private Penna creaPenna(String s) {
		if(s.contains("Baionetta")){
			return new Penna("La Baionetta");
		}
		if(s.contains("Fabio")){
			return new Penna("Fabio Molinaris");
		}
		if(s.contains("Daniele")){
			return new Penna("Daniele Barale");
		}
		if(s.contains("Darth")){
			return new Penna("Darth Gender");
		}
		if(s.contains("Federico")){
			return new Penna("Federico Montagnani");
		}
		return null;
	}

	public void updateFileBackup() throws IOException{
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/fabio/workspace/BaioNews_App/src/BaioBackup.molis")));
		for (Articolo a : articoli){
			String content = ""+a.getData().toString()+"<> "
					+a.getMostrina().getMostrina()+"<> "
					+a.getTitolo()+"<> "
					+a.getLink()+"<> "
					+a.getPenna().getNome()+"\n";

			out.append(content);
		}
		//System.out.println("Done");
		out.close();

	}

	public Set<Articolo> getAllArticoliFromPenna(Penna p){
		return p.getAllArticoli();
	}

	public Set<Penna> getAllPenne(){
		return penne;
	}

	public Set<Articolo> getAllArticoliFromMostrina(Mostrina m) {
		return m.getAllArticoli();
	}

	public Set<Mostrina> getAllMostrine() {
		return mostrine;

	}

	public List<Articolo> getAllArticoli() {
		return getAllArticoliOrderByDate();
	}

	public List<Articolo> getArticoloFromTitolo(String titolo) {
		Set<Articolo> trovati = new HashSet<>();
		for(Articolo a : articoli){
			if(a.getTitolo().contains(titolo)){
				trovati.add(a);
			}
		}
		return orderByDate(trovati);
	}

	public List<Articolo> getAllArticoliOrderByDate() {
		List<Articolo> articoliOrdinati = new ArrayList<>(articoli);
		Collections.sort(articoliOrdinati);
		return articoliOrdinati;
	}

	public List<Articolo> orderByDate(Set<Articolo> art){
		List<Articolo> articoliOrdinati = new ArrayList<>(art);
		Collections.sort(articoliOrdinati);
		return articoliOrdinati;
	}

	public Articolo getUltimoArticolo() {
		return getAllArticoliOrderByDate().get(0);
	}

	public List<Articolo> getAllArticoliFromData(LocalDate data){
		List<Articolo> trovati = new ArrayList<>();
		for (Articolo a : articoli){
			if(data != null && a.getData().isEqual(data)){
				System.out.println(data+" // "+a.getData());
				trovati.add(a);
			}
		}
		return trovati;
	}

	public List<Articolo> ricerca(String testo, Penna p, Mostrina m, LocalDate data){
		Set<RisultatoRicerca> rrTutto = new HashSet<>();

		Set<Articolo> rrSet = new HashSet<>();
		Set<Articolo> articoliRicerca = new HashSet<>();
		System.out.println("Inizio ricerca");

		boolean flag;
		
		for (Articolo a : articoli){
			flag = true;
			if(!testo.isEmpty() && !a.getTitolo().contains(testo)){
				flag = false;
			}
			if(p!=null && !a.getPenna().equals(p)){
				flag = false;
			}
			if(m!=null && !a.getMostrina().equals(m)){
				flag = false;
			}
			if(data!=null && data.isBefore(LocalDate.now()) && !a.getData().isEqual(data)){
				flag = false;
			}
			if(flag == true){
				rrSet.add(a);
			}
		}

		return orderByDate(rrSet);
	}
}
