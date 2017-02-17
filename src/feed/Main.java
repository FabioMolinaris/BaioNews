package feed;

import java.io.IOException;

import baionetta.Articolo;
import baionetta.Mostrina;
import baionetta.Penna;
import controller.BaioController;

public class Main {

	public static void main(String[] args) {

		//FxmlReader fr = new FxmlReader();
		//fr.read();

		//FeedReader parser = new FeedReader("https://labaionetta.blogspot.com/feeds/posts/default?alt=rss");

		//Feed feed = parser.readFeed();
		//System.out.println(feed);
		//for (FeedMessage message : feed.getMessages()) {
		//	System.out.println(message);
		//}

		BaioController bc = new BaioController();
		//bc.getArticoliFromRss();

		//for(Mostrina m : bc.getAllMostrine()){
		//	System.out.println(m.getMostrina());
		//}

		//for(Penna p : bc.getAllPenne()){
		//	System.out.println(p.getNome());
		//}

		//for(Articolo a : bc.getAllArticoliFromPenna(new Penna("Daniele Barale"))){
		//	System.out.println(a.getTitolo());
		//}
		//for(Articolo a : bc.getAllArticoliFromMostrina(new Mostrina("Obice"))){
		//	System.out.println(a.getTitolo());
		//}
		try {
			bc.getArticoliFromFile();
			bc.getArticoliFromRss();
			//System.out.println(bc.getAllArticoli().size());
			bc.updateFileBackup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//for(Articolo a : bc.getAllArticoliFromPenna(new Penna("Daniele Barale"))){
		//	System.out.println(a.getTitolo());
		//}
		
		//for(Articolo a : bc.getArticoloFromTitolo("realismo")){
		//	System.out.println(a.getTitolo() + a.getArticoloId());
		//}
		
		for(int i = 0; i<5; i++){
			System.out.println(bc.get5ArticoliOrderByDate().get(i).getTitolo());
			System.out.println(bc.get5ArticoliOrderByDate().get(i).getData());

		}
		System.out.println(bc.getAllArticoli().size());

	}

}
