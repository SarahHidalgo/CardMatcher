# Utiliser Git dans Eclipse

## Copier le repository Git dans Eclipse

- Premièrement aller sur Eclipse et se rendre sur le Workspace où se trouve votre clonage du repository git (cf Readme_projet) avec File-Switch Workspace
Maintenant que vous êtes au bon endroit sur Eclipse, il va falloir cloner le repository git non pas sur votre pc (déjà fait) mais dans Eclipse.
- Pour cela aller dans Window-Show view-Other-Git-Git Repositories
- Une fenêtre s'ouvre à gauche Eclipse. Cliquer sur cloner un repository Git
- Une nouvelle fenêtre s'ouvre. Aller sur Gitlab-Repository-Clone puis copier l'url http pour cloner le repository.
- Coller cette url dans la fenêtre "Clone Git Repository" sur Eclipse. Tout se remplit automatiquement normalement. Vous pouvez aussi remplir la partie "Authentication" (avec vos id et mdp de l'intranet TSE).
- Vous pouvez finir de copier le repository.

## Push votre travail depuis Eclipse 

L'avantage de Eclipse, c'est qu'on est pas obligé de passer par Git Bash pour push et commit notre travail (on reste cependant obligé de pull).
Quand vous effectuez des modifications dans un fichiers, des "Unstaged Changes" apparaissent dans l'onglet "Git Staging". Vous pouvez les ajouter à l'index des changements, puis les commit en ajoutant un commentaire pour décrire le travail effectué. 
