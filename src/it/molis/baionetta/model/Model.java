package it.molis.baionetta.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
				Mostrina m = new Mostrina(message.getCategory());
				Penna p = creaPenna(message.getAuthor());
				//System.out.println(p.getNome());

				LocalDate date = LocalDate.parse(message.getPubDate(), DateTimeFormatter.RFC_1123_DATE_TIME);

				if (message.getGuid()!=null){
					Articolo a = new Articolo(message.getGuid(), message.getTitle(), m, p, message.getLink(), date);
					if(!articoli.contains(a))
						articoli.add(a);
					penne.add(p);
					mostrine.add(m);
				}
			}
		}
	}

	public void getArticoliFromFile() throws IOException{
		articoli.clear();
		String s;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("/home/fabio/workspace/BaioNews/src/BaioBackup.txt") );
			while( (s = reader.readLine()) != null ){
				//System.out.println(s);
				//System.out.println(s.split(", ")[0]);
				//System.out.println(s.split(", ")[1]);
				//System.out.println(s.split(", ")[2]);
				//System.out.println(s.split(", ")[3]);
				//System.out.println(s.split(", ")[4]);
				//System.out.println(s.split(", ")[5]);

				Articolo a = new Articolo(null, null, null, null, null, null);

				a.setArticoloId(s.split(", ")[0]);

				LocalDate date = LocalDate.parse(s.split(", ")[1], DateTimeFormatter.ISO_LOCAL_DATE);
				a.setData(date);

				//if(!s.split(", ")[2].isEmpty()){
					Mostrina m = new Mostrina(s.split(", ")[2]);
					a.setMostrina(m);
				//	}

				a.setTitolo(s.split(", ")[3]);

				a.setLink(s.split(", ")[4]);

				Penna p = new Penna(s.split(", ")[5]);
				a.setPenna(p);

				articoli.add(a);
				penne.add(p);
				mostrine.add(m);
			}

			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/fabio/workspace/BaioNews/src/BaioBackup.txt")));
		for (Articolo a : articoli){
			String content = ""+a.getArticoloId()+", "
					+a.getData().toString()+", "
					+a.getMostrina().getMostrina()+", "
					+a.getTitolo()+", "
					+a.getLink()+", "
					+a.getPenna().getNome()+"\n";

			out.append(content);
		}
		//System.out.println("Done");
		out.close();

	}

	public List<Articolo> getAllArticoliFromPenna(Penna p){
		for(Articolo a : getAllArticoliOrderByDate()){
			if(a.getPenna().equals(p)){
				p.addArticoli(a);
			}
		}
		return p.getAllArticoli();
	}

	public Set<Penna> getAllPenne(){
		return penne;
	}

	public List<Articolo> getAllArticoliFromMostrina(Mostrina m) {
		for(Articolo a : getAllArticoliOrderByDate()){
			if(a.getMostrina().equals(m)){
				m.addArticoli(a);
			}
		}
		return m.getAllArticoli();
	}

	public Set<Mostrina> getAllMostrine() {
		return mostrine;

	}

	public List<Articolo> getAllArticoli() {
		return getAllArticoliOrderByDate();
	}

	public List<Articolo> getArticoloFromTitolo(String titolo) {
		List<Articolo> trovati = new ArrayList<>();
		for(Articolo a : getAllArticoliOrderByDate()){
			if(a.getTitolo().contains(titolo)){
				trovati.add(a);
			}
		}
		return trovati;

	}

	public List<Articolo> getAllArticoliOrderByDate() {
		List<Articolo> articoliOrdinati = new ArrayList<>(articoli);
		Collections.sort(articoliOrdinati);
		return articoliOrdinati;
	}
}
