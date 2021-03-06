package bdd;

import java.io.Serializable;
import java.util.ArrayList;

import exception.NombreArgumentInvalide;
import exception.argumentInvalide;

public class RelSchema implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nom;
	private int nbrCol;
	private ArrayList<String> typeCol = new ArrayList<String>();

	public RelSchema(String[] rep) throws argumentInvalide {
		this.argumentOK(rep);
		this.nom = rep[1];
		this.nbrCol = Integer.parseInt(rep[2]);
		for (int i = 3; i < rep.length; i++) {
			this.typeCol.add(rep[i]);
		}
	}

	public String toString() {
		String rep = new String(
				"La relation " + this.nom + " a " + this.nbrCol + " colonnes qui sont : " + this.typeCol.toString());
		return (rep);
	}

	public void argumentOK(String[] rep) throws argumentInvalide {
		for (int i = 3; i < rep.length; i++) {
			if ((!rep[i].equals("int")) && (!rep[i].equals("float")) && (!isCorrectString(rep[i]))) {
				throw new argumentInvalide();
			}
		}
	}

	public boolean isCorrectString(String str) {
		boolean bool = true;
		if (!str.substring(0, 6).equals("string")) {
			bool = false;
		}
		for (int i = 6; i < str.length(); i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				bool = false;
			}
		}
		return bool;

	}

	public int calculRecordSize() {
		int taille = 0;
		for (int i = 0; i < typeCol.size(); i++) {
			if (typeCol.get(i).equals("int")) {
				taille += 4;
			}
			if (typeCol.get(i).equals("float")) {
				taille += 4;
			}
			if (typeCol.get(i).equals("string")) {
				taille += Constante.STRINGT * 2;
			}
		}
		return (taille + 1);
	}

	public ArrayList<String> getTypeCol() {
		return (this.typeCol);
	}

	public String getNom() {
		return (this.nom);
	}
}
