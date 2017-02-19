package it.molis.baionetta.beans;

import java.util.ArrayList;
import java.util.List;

public class Mostrina {

	private String mostrina;
	private List<Articolo> articoli = new ArrayList<>();

	public List<Articolo> getAllArticoli() {
		return articoli;
	}

	public void addArticoli(Articolo articoli) {
		this.articoli.add(articoli);
	}

	public String getMostrina() {
		return mostrina;
	}

	public void setMostrina(String mostrina) {
		this.mostrina = mostrina;
	}

	public Mostrina(String mostrina) {
		super();
		this.mostrina = mostrina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mostrina == null) ? 0 : mostrina.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mostrina other = (Mostrina) obj;
		if (mostrina == null) {
			if (other.mostrina != null)
				return false;
		} else if (!mostrina.equals(other.mostrina))
			return false;
		return true;
	}

}
