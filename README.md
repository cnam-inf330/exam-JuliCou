Mission Mars Rover : Centre de Commandement
=========

Programme : Exploration de Mars
---------
Un groupe d'engins robotisés appelés *Rovers* ont été envoyés par la NASA sur la planète Mars.
Un périmètre d'exploration rectangulaire modélisé sous la forme d'une grille a été défini.
Les *Rovers* se déplacent sur cette grille afin de récolter des échantillons et des images qui seront
envoyées vers la Terre pour analyse.
La position d'un *Rover* est représentée par des coordonnées X et Y sur la grille d'exploration,
ainsi qu'une lettre correspondant à l'orientation de l'engin par rapport aux points cardinaux (Nord, Est, Sud, Ouest).
Par exemple, la position d'un *Rover* (0, 0, N) signifie qu'il se trouve en bas à gauche de la grille, face au Nord.

Pour contrôler un *Rover*, la NASA lui envoie une séquence d'instructions sous la forme d'une chaîne de caractères.
Chaque caractère correspond à une instruction spécifique :
- 'L' : effectuer une rotation de 90 degrés vers la gauche, sans changer de position sur la grille
- 'R' : effectuer une rotation de 90 degrés vers la droite, sans changer de position sur la grille
- 'M' : avancer sur la grille, sans changer d'orientation
On définit que la position se trouvant directement au Nord de (x, y) est (x, y+1).

Entrée du Programme
---------
En entrée, le programme lit un fichier au format suivant : 
- La première ligne encode la taille (largeur L correspond à l'axe X et hauteur H correspondant à l'axe axe Y)
de la grille d'exploration, au format L H
- Les lignes suivantes permettent d'initialiser et de contrôler les *Rovers* qui ont été déployés.
À chaque *Rover* correspond deux lignes successives.
La première ligne donne la position et l'orientation initiales du *Rover* au format X Y O,
avec X et Y les coordonnées sur la grille et O l'orientation.
La seconde ligne donne la séquence d'instructions permettant de déplacer le *Rover* jusqu'à
sa position finale sur la grille.

Le fichier étant lu séquentiellement par le programme, chaque *Rover* ne pourra commencer à se déplacer sur la grille
qu'après que le *Rover* précédent ait atteint sa position finale.

Sortie du Programme
---------
En sortie, le programme doit écrire une ligne pour chaque *Rover* au format X Y O,
avec X et Y les coordonnées sur la grille et O l'orientation.

Exemple
---------
- Données en entrée :

5 5

1 2 N

LMLMLMLMM

3 3 E

MMRMMRMRRM

- Données en sortie :

1 3 N

5 1 E
