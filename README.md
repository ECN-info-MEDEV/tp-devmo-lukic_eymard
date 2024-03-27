Mail app
=================================

Objectifs
--------------

L'objectif était d'implémenter les écrans suivant de notre interface de gestion des mails :

- affichage de tous les mails avec la possibilité de cliquer sur chacun des mails pour avoir le détail du contenu. Les données des mails sont contenues dans un fichier texte (nous n'avons pas eu le temps d'implémenter une BDD et ce n'était pas l'objet du TP).

- affichage d'un mail spécifique avec l'objet du mail et la personne qui a envoyé le mail. Possibilité sur cette page d'envoyer un message à l'IA (zone de texte + bouton) et de cliquer sur un bouton pour répondre au mail. L'implémentation d'une requête vers chatGPT a été abandonnée par manque de temps (trop de difficultés à générer des clés pour l'API)

- affichage de la page de réponse au mail (rédaction d'un mail). La page contient l'objet du mail, le ou les destinataires ainsi qu'une zone de texte destinée à la redaction d'une réponse. Il y a un bouton pour envoyer le mail et un pour l'enregistrer en brouillon.
- Nous réussissons à récupérer le champ de texte rempli, mais pas à l'insérer dans notre fichier texte, à cause des droits d'accès/permissions que nous n'arrivons pas à modifier.


Réalisation
---------------

Nous avons réalisés quasiment tous les objectifs. Cependant, nous avons perdu énormement de temps à cause de bugs d'AndroidStudio (discutés en cours avec vous) qui ne reconnaissait plus les Activity dans le Manifest.
