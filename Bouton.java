package com;

import javafx.scene.control.Button;

public class Bouton extends Button{
	public int i;
	public int j;//Coordonn�es auxquelles le bouton est associ� � la matrice.
	
	public Bouton(int i, int j)
	{
		super();
		this.i = i;
		this.j = j;
	}
	
	public Bouton(String text, int i, int j)
	{
		super(text);
		this.i = i;
		this.j = j;
	}
}

