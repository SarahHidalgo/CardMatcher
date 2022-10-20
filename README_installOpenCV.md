
# Installation de la librairie OpenCV pour Java

# Table of Contents

1. [ Présentation](#presentation)

2. [ Prérequis](#prerequis)

3. [ Windows](#windows)

4. [ Macos](#macos)

6. [ Configuration de Eclipse](#config)



<a name="presentation"></a>
## Présentation 

Cette documentation a pour but de faciliter l'installation de OpenCV, afin de pouvoir l'utiliser sur Eclipse.
Ceci permettra l'utilisation de notre application CardMatcher.



<a name="prerequis"></a>
## Prérequis

- Eclipse 


MacOs: 

- [Homebrew ](https://brew.sh) 
- Xcode 
- [Apache Ant](https://formulae.brew.sh/formula/ant)


<a name="windows"></a>
## Installation de OpenCV sous Windows

- Rendez-vous tout d'abord sur https://opencv.org/releases/ puis cliquer sur "Windows".
- Télécharger le fichier.

<a name="macos"></a>
## Installation de OpenCV sous MacOs
-  Saisissez Terminal dans le champ de recherche, puis cliquez sur Terminal.
-  Tout dabord on commence par modifier le fichier de configuration de l'installation de opencv dans homebrew  en tapant  la commande 
```
brew edit opencv
```
&nbsp; Remplacer la ligne qui contient '-DBUILD_opencv_java=OFF', 'DBUILD_OPENEXR=ON' par '-DBUILD_opencv_java=ON, 'DBUILD_OPENEXR=OFF' respectivement  et sauvegarder.

- Installer opencv a partir du fichier source
```
brew install --build-from-source opencv
```

Le fichier .jar sera localisé a /usr/local/Cellar/opencv/4.6.0_1.x/share/java/opencv4


<a name="config"></a>
## Configuration de Eclipse pour openCV
- Allez sur Eclipse -> Window -> Preferences -> Java -> Build Path -> User libraries.
- Cliquer sur "New", mettre OpenCV comme nom de librarie. Cliquer sur la librairie créée et cliquer sur "Choose Add External JARs" sur le côté droit. Parcourir vos fichiers (fichier opencv venant d'étre téléchargé -> build -> java) et sélectionner "opencv-460.jar". Après avoir ajouté le jar, sélectionner "Native library location" et cliquer sur "Edit".
- Sélectionner "External Folder" et parcourir vos fichierspour sélectionner le dossier contenant les libraries OpenCV (opencv -> build -> java-> x64).

