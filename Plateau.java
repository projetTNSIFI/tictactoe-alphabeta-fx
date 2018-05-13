package com;

import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Plateau {
	protected int matrix[][];
	
	public Plateau()
	{
		this.matrix = new int[3][3];
	}
	
	//Constructeur de copie
	public Plateau(Plateau copy)
	{
		int[][] m = copy.getPlateau();
		matrix = new int[m.length][];
		
		for(int i = 0;i < m.length;i++)
		{
			matrix[i] = Arrays.copyOf(m[i], m[i].length);
		}
	}
	
	//Place un pion sur le plateau
	public void placer(int joueur,int x, int y)
	{
		matrix[y][x] = joueur;
	}
	
	public boolean complet()
	{
		for(int i = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				if(matrix[j][i] == 0)
					return false;
			}
		}
		return true;
	}
	
	//TOKEN = -1 : O ; TOKEN = 1 : X
	public void afficher(Stage rootStage)
	{
		
		//Récupération du root de la scene
		Scene scene = rootStage.getScene();
		Group group = (Group) scene.getRoot();
		GridPane grid = (GridPane)group.getChildren().get(0);
		
		//Ajout d'un bouton pour chaque case dans le groupe.
		if(grid.getChildren().isEmpty())
		{
			//On ajoute les boutons au groupe
			for(int i = 0; i < matrix.length;i++)
			{
				for(int j = 0; j < matrix[i].length;j++)
				{
					Bouton btn;
					switch(matrix[j][i])
					{
					case 0:
						btn = new Bouton("-",i,j);
						break;
					case Jeu.X:
						btn = new Bouton("X",i,j);
						break;
					case Jeu.O:
						btn = new Bouton("O",i,j);
						break;
					default :
						btn = new Bouton("-",i,j);
						break;
					}
					
					//Positionnement des boutons
					//btn.setLayoutX(100 * i);
					//btn.setLayoutY(100 * j);
					btn.setPadding(new Insets(90));
					//Ajout du bouton au groupe.
					grid.add(btn,i,j);
				}
			}
		}
		//Si le groupe contient déjà des boutons, on met à jour leur contenu.
		else
		{
			/*
			for(int i = 0;i < matrix.length;i++)
			{
				for(int j = 0; j < matrix[i].length;j++)
				{
					Bouton btn = (Bouton) grid.getChildren().get(j + 3 * i);
					switch(matrix[j][i])
					{
					case 0:
						btn.setText("-");
						break;
					case Jeu.X:
						btn.setText("X");
						break;
					case Jeu.O:
						btn.setText("O");
						break;
					default :
						btn.setText("-");
						break;
					}
				}
			}*/
			
			for(int i = 0;i < grid.getChildren().size();i++)
			{
				Bouton bouton = (Bouton) grid.getChildren().get(i);
				switch(matrix[bouton.j][bouton.i])
				{
				case 0:
					bouton.setText("-");
					break;
				case Jeu.X:
					bouton.setText("X");
					break;
				case Jeu.O:
					bouton.setText("O");
					break;
				default :
					bouton.setText("-");
					break;
				}
			}
		}
	}
	
	public void afficherConsole()
	{
		
		for(int i  = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				switch(matrix[i][j])
				{
				case 0:
					System.out.print(".");
					break;
				case Jeu.O:
					System.out.print("O");
					break;
				case Jeu.X:
					System.out.print("X");
					break;
				}
			
			}
			System.out.println();
		}
	}
	
	//Retourne le joueur vainqueur, 0 si aucun joueur ne gagne.
	public int victoire()
	{
		int nombre_cases_identiques_O = 0;
		int nombre_cases_identiques_X = 0;
		
		//Controle des lignes
		for(int i = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				if(matrix[j][i]==Jeu.O)
					nombre_cases_identiques_O++;
				else if(matrix[j][i]==Jeu.X)
					nombre_cases_identiques_X++;
				
				
				if(nombre_cases_identiques_O == 3)
					return Jeu.O;
				else if(nombre_cases_identiques_X == 3)
					return Jeu.X;
			}
			nombre_cases_identiques_O = 0;
			nombre_cases_identiques_X = 0;
		}
		
		//Controle des colonnes
		for(int i = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				if(matrix[i][j]==Jeu.O)
					nombre_cases_identiques_O++;
				else if(matrix[i][j]==Jeu.X)
					nombre_cases_identiques_X++;
			
				if(nombre_cases_identiques_O == 3)
					return Jeu.O;
				else if(nombre_cases_identiques_X == 3)
					return Jeu.X;
			}
			nombre_cases_identiques_O = 0;
			nombre_cases_identiques_X = 0;
		}
		
		//Controle des diagonales
		if(matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2] && matrix[1][1] == Jeu.O)
			return Jeu.O;
		if(matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0] && matrix[1][1] == Jeu.O)
			return Jeu.O;
		
		if(matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2] && matrix[1][1] == Jeu.X)
			return Jeu.X;
		if(matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0] && matrix[1][1] == Jeu.X)
			return Jeu.X;
		
		//Pas de victoire
		return 0;
	}
	
	//Retourne la grille de jeu
	public int[][] getPlateau()
	{
		return matrix;
	}
	
	public void setPlateau(Plateau plateau)
	{
		this.matrix = plateau.matrix;
	}
}
