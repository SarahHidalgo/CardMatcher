# Installation de OpenCV sous Windows

## Présentation

Cette documentation a pour but de faciliter l'installation de OpenCV sous Windows, afin de pouvoir l'utiliser sur Eclipse.
Ceci permettra l'utilisation de notre application CardMatcher.

## Etapes à suivre

- Rendez-vous tout d'abord sur [](https://opencv.org/releases/) puis cliquer sur "Windows".
- Télécharger le fichier.
- Allez sur Eclipse -> Window -> Preferences -> Java -> Build Path -> User libraries.
- Cliquer sur "New", mettre OpenCV comme nom de librarie. Cliquer sur la librairie créée et cliquer sur "Choose Add External JARs" sur le côté droit. Parcourir vos fichiers (fichier opencv venant d'étre téléchargé -> build -> java) et sélectionner "opencv-460.jar". Après avoir ajouté le jar, sélectionner "Native library location" et cliquer sur "Edit".
- Sélectionner "External Folder" et parcourir vos fichierspour sélectionner le dossier contenant les libraries OpenCV (opencv -> build -> java-> x64).
