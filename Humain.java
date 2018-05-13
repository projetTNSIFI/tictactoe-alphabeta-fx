package com;

import base.Joueur;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Humain extends Joueur{
	
	public Humain(String nom, int pion)
	{
		super(nom,pion);
	}
	
	//Tour du joueur humain.
	/*
	public void jouer(Plateau plateau) 
	{
		int length = plateau.getPlateau().length;
		int x,y;
		
		System.out.println("Tour du joueur " + nom);
		//L'utilisateur entre les coordonnées.
		do
		{
			System.out.println("Entrez x : ");
			x = Jeu.scanner.nextInt();
			System.out.println("Entrez y : ");
			y = Jeu.scanner.nextInt();
		}while(x < 0 || x >= length || y < 0 || y >= length || plateau.getPlateau()[y][x] != 0);
		
		//Place le pion sur le plateau.
		plateau.placer(pion, x, y);
		
		//Affichage du plateau après le tour de jeu.
		//plateau.Afficher();
	}
	*/
	
	public void jouer(Stage rootStage, Plateau plateau)
	{
		Scene scene= rootStage.getScene();
		Group group = (Group) scene.getRoot();
		GridPane grid = (GridPane) group.getChildren().get(0);
		
		//On autorise le clic sur chaque bouton
		for(int i = 0;i < grid.getChildren().size();i++)
		{
			Bouton bouton = (Bouton) grid.getChildren().get(i);
			//Fonction qui devra être réalisé lors du clic sur le bouton.
			bouton.setOnMouseReleased(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent e)
						{
							int i = bouton.i;
							int j = bouton.j;
							if(plateau.getPlateau()[j][i] == 0)
							{
								plateau.placer(Jeu.O, i, j);
								bouton.setText("O");
								Jeu.changerJoueur();
							}
						}
					});
		}
		
		//On désactive les evenements de la souris.
		/*for(int i = 0;i < grid.getChildren().size();i++)
		{
			Bouton bouton = (Bouton) grid.getChildren().get(i);
				
			//Fonction qui devra être réalisé lors du clic sur le bouton.
			bouton.setOnMouseClicked(null);
		}
		*/
		
	}
}
