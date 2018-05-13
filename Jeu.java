package com;

import com.Plateau;
import base.Joueur;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Jeu implements Runnable{
	
	public static final int O=-1;
	public static final int X=1;
	public static int joueur = O;
	
	//public static Scanner scanner = new Scanner(System.in);
	protected Stage rootStage;
	protected Plateau plateau;
	
	public Jeu(Stage stage, Plateau plateau)
	{
		this.rootStage = stage;
		this.plateau = plateau;
	}
	
	@Override
	public void run()
	{
		int victoire = 0;
		
		//Création de deux joueurs 
		Joueur humain = new Humain("Joueur",O);
		Joueur ordinateur = new Ordinateur("Ordinateur",X,2);
		
		//Jeu
		while(!plateau.complet())
		{
			
			//Tour du joueur
			if(joueur == Jeu.O)
				humain.jouer(rootStage, plateau);
			else
				ordinateur.jouer(rootStage, plateau);
			
			//Affichage du plateau après chaque tour de jeu.
			//plateau.afficherConsole();
			
			//Mise à jour du composant graphique dans l'affichage. Besoin d'utiliser un nouveau thread pour les elements graphiques.
			Platform.runLater(new Runnable()
					{
						@Override 
						public void run()
						{
							plateau.afficher(rootStage);
							Thread.currentThread().interrupt();
						}
					});
		
			//Vérification de la victoire
			victoire = plateau.victoire();
			if(victoire != 0)
				break;
		
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(victoire != 0)
			System.out.println("Victoire du joueur " + (joueur == Jeu.X ? "X" : "O"));
		else
			System.out.println("Match nul");
		
		Thread.currentThread().interrupt();
		//Fermeture du scanner.
		//scanner.close();
	}
	
	public static void changerJoueur()
	{
		joueur *= -1;
	}

}
