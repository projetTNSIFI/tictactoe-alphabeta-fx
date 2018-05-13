package com;

import javafx.scene.control.Button;

public class Bouton extends Button{
	public int i;
	public int j;//Coordonnées auxquelles le bouton est associé à la matrice.
	
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

