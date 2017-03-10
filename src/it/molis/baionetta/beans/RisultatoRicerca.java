package it.molis.baionetta.beans;

import java.time.LocalDate;

public class RisultatoRicerca {

	private Articolo a;
	private Penna p;
	private Mostrina m;
	private LocalDate data;

	public RisultatoRicerca(Articolo a, Penna p, Mostrina m, LocalDate data) {
		super();
		this.a = a;
		this.p = p;
		this.m = m;
		this.data = data;
	}

	public Articolo getArticolo() {
		return a;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
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
		RisultatoRicerca other = (RisultatoRicerca) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		return true;
	}

}
