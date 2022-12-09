# miniProjet_JAVA
Enseignants : T. BEN MENA Nb pages : 1
MINI PROJET
« Prête-moi ta voiture, j’ai eu mon permis ; Non j’ai peur pour ma voiture J »
On vous demande d’implémenter une application de gestion d’une auto-école. Un(e) candidat(e)
est caractérisé(e) par un numéro CIN, et la catégorie du permis qu’il souhaite passer (A : moto, B : 
voiture, C : camion) et un tableau présentant les séances affectées à ce candidat. Une auto-école est 
modélisée alors par le tableau de ses candidats
Chaque candidat peut avoir des séances de codes et de conduites. Les séances de codes sont 
caractérisées par un numéro, une date, une heure et l’ingénieur qui l’assurera. Les séances de 
conduites sont identifiées par un numéro, une date, une heure et un véhicule et l’ingénieur qui 
l’assurera. 
La flotte de l’auto-école est caractérisé par un ensemble de véhicule : motos, voiture camion. Tous
les véhicules possèdent un numéro d’immatriculation, une date de mise en service, un kilométrage
total et le nombre de km qui reste pour le prochain entretient
Le prix d’une séance dépend de son type et du type de permis : elle est calculée en fonction d’un 
prix de base fixé par l’état (15 DT pour les séances de code et 15 DT pour les séances de conduite 
bénéficié par une augmentation en fonction du type de permis selon le tableau suivant : 
Le prix d’un examen qui conclut une formation est fixé à 50 DT
Un ingénieur est caractérisé par un nom et un état de disponibilité (un ingénieur occupé s’il assure 
une séance dans cet instant). Le salaire d’un ingénieur est calculé selon le nombre des heures 
travaillées. Sa rémunération pour une séance est fixée à 10DTpour une séance de conduite et 7DT 
pour une séance de code.
On vous demande de vérifier la disponibilité des ingénieurs et du véhicule (pour les séances de 
conduite) lors de leur affectation à une séance. Un ingénieur ne peut assurer une séance que si son 
état est « disponible », sinon une exception du type NotDispoException sera lancée ainsi que pour 
le véhicule.
L’application doit permettre 
- La gestion des candidats (ajout, modification suppression et recherche pour visualisation)
- Lors de l’affichage d’un candidat il est important de voir le prix total, ce qu’il a payé et ce 
qui lui reste à payer
- La gestion des véhicules (ajout, modification suppression et recherche pour visualisation)
de visualiser un rapport sur l’état du véhicule pour tracer leur mise en maintenance
- La gestion des gestions des ingénieurs : (ajout, modification suppression et recherche 
pour visualisation) et calcul de leur salaire à la fin du mois
- La gestion et la planification des séances
BONUS : l’utilisation des fichiers au format json sera fortement recommandé pour sauvegarder les 
données.
