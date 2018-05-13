package com;
import java.util.List;

import com.Noeud;

//Classe qui va générer l'arbre des noeuds possibles et faire l'évaluation de ces noeuds.
public class Evaluation {
	
	/*Genere un arbre contenant tous les noeuds possibles sur une certaine profondeur.
	 * Le noeud n est le premier noeud de l'arbre ( racine ), la profondeur correspond à combiend d'itération de l'arbre nous souhaitons générer
	 * Le token correspond au tour à partir du quel on va générer les noeuds. ( varie entre 1 et 2 ).
	 */
	public static void genererArbre(Noeud courant, int profondeur)
	{
		if(profondeur > 0)
		{
			courant.genererFils();
			
			//Récupération des noeuds fils au noeud courant
			List<Noeud> fils = courant.getFils();
			
			//Pour chacun des noeuds fils, on réitère le processus, mais on change le tour et la profondeur diminue.
			for(Noeud f : fils)
				genererArbre(f, profondeur-1);
			
		}
	}
	
	//Fonction qui va attribuer des valeurs aux noeuds
	public static void evaluer_noeuds(Noeud courant, int profondeur)
	{
		if(profondeur <= 0)
			courant.valeur_noeud = danger(courant);
		
		//On attribue une valeur aux noeuds feuilles
		if(courant.estFeuille())
		{
			//On considère que l'adversaire ( le joueur ) jouera les O
			if(courant.plateau.victoire() == Jeu.X)
				courant.valeur_noeud = 1000;
			else if(courant.plateau.victoire() == Jeu.O)
				courant.valeur_noeud = -1000 + danger(courant);
			else 
				courant.valeur_noeud = danger(courant);			
		}
		else
		{
			//Sinon la valeur du noeud courant correspond au max ou au min de tous ses noeuds fils.
			if(courant.type == Noeud.MAX)//Noeud correspondant au tour de l'IA
			{
				for(Noeud f : courant.fils)
				{
					evaluer_noeuds(f, profondeur - 1);
				}
				courant.valeur_noeud = courant.filsMin().valeur_noeud;
			}
			else if(courant.type == Noeud.MIN)//Noeud correspondant au tour du joueur
			{
				for(Noeud f : courant.fils)
				{
					evaluer_noeuds(f, profondeur - 1);
				}
				courant.valeur_noeud = courant.filsMax().valeur_noeud;
			}
		}
		
	}
	
	public static void afficherArbre(Noeud courant)
	{
		for(Noeud f : courant.fils)
		{
			f.plateau.afficherConsole();
			System.out.println("Score du noeud : " + f.valeur_noeud);
		}
	}
	
	//Renvoie une valeur de danger pour un noeud.
	protected static int danger(Noeud courant)
	{
		int danger = 0;
		
		int[][] matrix = courant.plateau.getPlateau();
		int nombre_ligne_X = 0;
		int nombre_vide_ligne = 0;
		int nombre_colonne_X = 0;
		int nombre_vide_colonne = 0;
		int nombre_dd_X = 0;
		int nombre_dg_X = 0;
		int nombre_vide_dd = 0;
		int nombre_vide_dg = 0;
		
		for(int i = 0 ;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				if(matrix[j][i] == Jeu.O)
					nombre_ligne_X++;
				else if(matrix[j][i] == 0)
					nombre_vide_ligne++;
				if(matrix[i][j] == Jeu.O)
					nombre_colonne_X++;
				else if(matrix[i][j] == 0)
					nombre_vide_colonne++;
			}
			
			if(nombre_ligne_X == 2 && nombre_vide_ligne == 1)
				danger -= 250;//Plus la valeur est basse, plus c'est dangereux.
			if(nombre_colonne_X == 2 && nombre_vide_colonne == 1)
				danger -= 250;
			
			//On vérifie si l
			nombre_ligne_X = 0;
			nombre_colonne_X = 0;
			nombre_vide_ligne = 0;
			nombre_vide_colonne = 0;
			
		}
		
		//diagonales
		int j = 0;
		for(int i = 0;i < matrix.length;i++)
		{
			if(matrix[j][i] == Jeu.O)
				nombre_dg_X++;
			else if(matrix[j][i] == 0)
				nombre_vide_dg++;
			j++;
		} 
		if(nombre_dg_X == 2 && nombre_vide_dg==0)
			danger-=250;
		j=2;
		for(int i = 0;i < matrix.length;i++)
		{
			if(matrix[j][i] == Jeu.O)
				nombre_dd_X++;
			else if(matrix[j][i] == 0)
				nombre_vide_dd++;
			j--;
		} 
		if(nombre_dd_X == 2 && nombre_vide_dd==0)
			danger-=250;
		
		return danger;
	}
}
