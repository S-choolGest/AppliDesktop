-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Ven 20 Mars 2020 à 16:37
-- Version du serveur :  5.7.14
-- Version de PHP :  7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `edutech`
--

-- --------------------------------------------------------

--
-- Structure de la table `absence`
--

CREATE TABLE `absence` (
  `id` int(11) NOT NULL,
  `matiere` varchar(20) NOT NULL,
  `etat` varchar(20) NOT NULL,
  `date` timestamp NOT NULL,
  `idEtudiant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `absence`
--

INSERT INTO `absence` (`id`, `matiere`, `etat`, `date`, `idEtudiant`) VALUES
(5, 'maths', 'justifié', '2020-02-05 12:30:00', 5);

-- --------------------------------------------------------

--
-- Structure de la table `attestation`
--

CREATE TABLE `attestation` (
  `idAttestation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `bibliothecaire`
--

CREATE TABLE `bibliothecaire` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `bibliothecaire`
--

INSERT INTO `bibliothecaire` (`id`) VALUES
(2),
(13);

-- --------------------------------------------------------

--
-- Structure de la table `bibliotheque`
--

CREATE TABLE `bibliotheque` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `capacite` int(11) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `id_bibliothecaire` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `bibliotheque`
--

INSERT INTO `bibliotheque` (`id`, `nom`, `capacite`, `adresse`, `id_bibliothecaire`) VALUES
(1, 'biblio_1', 1200, 'sfax', NULL),
(2, 'biblio2', 132, 'Tunis', NULL),
(3, 'biblio3', 132, 'sousse', NULL),
(4, 'biblio4', 200, 'Bizerte', 13),
(8, 'biblio5', 132, 'Ariana', 2),
(11, 'nom', 2, 'adresse', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE `classe` (
  `idClasse` int(11) NOT NULL,
  `nomClasse` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `classe`
--

INSERT INTO `classe` (`idClasse`, `nomClasse`) VALUES
(1, '3a11'),
(2, '3a12');

-- --------------------------------------------------------

--
-- Structure de la table `club`
--

CREATE TABLE `club` (
  `idClub` int(11) NOT NULL,
  `nomClub` varchar(255) NOT NULL,
  `categorieClub` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `club`
--

INSERT INTO `club` (`idClub`, `nomClub`, `categorieClub`) VALUES
(1, 'yujik', 'yujik');

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `idCours` int(11) NOT NULL,
  `nomCours` varchar(255) DEFAULT NULL,
  `fichier` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `idDemande` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `emprunt`
--

CREATE TABLE `emprunt` (
  `id` int(11) NOT NULL,
  `idEmprunteur` int(11) NOT NULL,
  `idLivre` int(11) NOT NULL,
  `etat` blob NOT NULL,
  `dateEmprunt` date NOT NULL,
  `dateConfirmation` date DEFAULT NULL,
  `dateRendu` date DEFAULT NULL,
  `datedebut` date NOT NULL,
  `datefin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `emprunt`
--

INSERT INTO `emprunt` (`id`, `idEmprunteur`, `idLivre`, `etat`, `dateEmprunt`, `dateConfirmation`, `dateRendu`, `datedebut`, `datefin`) VALUES
(34, 3, 23, 0x7265667573, '2020-02-28', '2020-02-28', NULL, '2020-02-04', '2020-02-14'),
(35, 3, 24, 0x617474656e7465, '2020-02-28', NULL, NULL, '2020-02-04', '2020-02-14'),
(40, 14, 23, 0x617474656e7465, '2020-02-28', NULL, NULL, '2020-01-31', '2020-02-01'),
(41, 3, 25, 0x617474656e7465, '2020-02-28', NULL, NULL, '2020-01-28', '2020-01-29'),
(42, 14, 24, 0x617474656e7465, '2020-02-28', NULL, NULL, '2020-01-30', '2020-03-07');

-- --------------------------------------------------------

--
-- Structure de la table `encadrement`
--

CREATE TABLE `encadrement` (
  `id` int(11) NOT NULL,
  `id_pfe` int(11) NOT NULL,
  `id_prof` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `encadrement`
--

INSERT INTO `encadrement` (`id`, `id_pfe`, `id_prof`, `etat`) VALUES
(1, 1, 19, 'acceptee');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` int(11) NOT NULL,
  `idclasse` int(11) NOT NULL,
  `cin_p` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `idclasse`, `cin_p`) VALUES
(3, 1, 456);

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `idEvent` int(11) NOT NULL,
  `nomEvent` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `timestamp` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `event`
--

INSERT INTO `event` (`idEvent`, `nomEvent`, `description`, `timestamp`) VALUES
(1, 'rtyui', 'tyzazae', '2020-02-12 09:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `formulaire`
--

CREATE TABLE `formulaire` (
  `idFormulaire` int(11) NOT NULL,
  `descriptionFormulaire` varchar(255) NOT NULL,
  `dateEnvoi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` int(11) NOT NULL,
  `id_bibliotheque` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `auteur` varchar(255) NOT NULL,
  `editeur` varchar(20) NOT NULL,
  `categorie` varchar(20) NOT NULL,
  `dateSortie` date NOT NULL,
  `taille` int(11) NOT NULL,
  `quantite` int(11) NOT NULL DEFAULT '0',
  `img` varchar(255) DEFAULT NULL,
  `dateajout` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `livre`
--

INSERT INTO `livre` (`id`, `id_bibliotheque`, `titre`, `auteur`, `editeur`, `categorie`, `dateSortie`, `taille`, `quantite`, `img`, `dateajout`) VALUES
(22, 4, 'rayen', 'rayen', 'goucha', 'blague', '2020-02-06', 201, 1, 'http://localhost/upload/uploads/f_5e587123a0ef7.jpg', '2020-02-28'),
(23, 4, 'king', 'king', 'king', 'king', '2020-02-11', 100, 12, 'http://localhost/upload/uploads/f_5e5858932b4e8.png', '2020-02-28'),
(24, 4, 'kingarthh', 'kingarthh', 'kingarthh', 'kingarthh', '2020-03-06', 100, 12, 'http://localhost/upload/uploads/f_5e5858b28f38f.png', '2020-02-28'),
(25, 4, 'musso', 'musso', 'musso', 'musso', '2020-04-11', 100, 12, 'http://localhost/upload/uploads/f_5e5858db0f322.jpg', '2020-02-28'),
(26, 8, 'titre1rayen', 'auteur1', 'editeur1', 'categorie1', '2020-02-04', 200, 1, 'http://localhost/upload/uploads/f_5e58de7894ed8.jpg', '2020-02-28'),
(27, 8, 'livrevalidation', 'auteurvalidation', 'validation', 'categorie1', '2020-02-05', 0, -1, 'http://localhost/upload/uploads/f_5e58ea5a699c0.jpg', '2020-02-28');

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `idmatiere` int(11) NOT NULL,
  `nomMatiere` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `matiere`
--

INSERT INTO `matiere` (`idmatiere`, `nomMatiere`) VALUES
(1, 'maths'),
(2, 'français');

-- --------------------------------------------------------

--
-- Structure de la table `membrescolarite`
--

CREATE TABLE `membrescolarite` (
  `idMscolarite` int(11) NOT NULL,
  `nomMscolarite` varchar(255) NOT NULL,
  `prenomMscolaire` varchar(255) NOT NULL,
  `cinMscolaire` int(11) NOT NULL,
  `dateNaissMscolaire` date NOT NULL,
  `numTelMscolaire` int(11) NOT NULL,
  `emailMscolaire` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

CREATE TABLE `module` (
  `idModule` int(11) NOT NULL,
  `nomModule` varchar(255) NOT NULL,
  `coefModule` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `parent`
--

CREATE TABLE `parent` (
  `idParent` int(11) NOT NULL,
  `idEtudiant` int(11) NOT NULL,
  `emailParent` varchar(255) NOT NULL,
  `numTelParent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `pfe`
--

CREATE TABLE `pfe` (
  `id` int(11) NOT NULL,
  `id_etudiant` int(11) NOT NULL,
  `sujet` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `pfe`
--

INSERT INTO `pfe` (`id`, `id_etudiant`, `sujet`, `titre`) VALUES
(1, 3, 'sujet pfe', 'pfe');

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

CREATE TABLE `professeur` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `professeur`
--

INSERT INTO `professeur` (`id`) VALUES
(19);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `idReclamation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `id_bibliotheque` int(11) NOT NULL,
  `heure_debut` timestamp NOT NULL,
  `heure_final` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `resultat`
--

CREATE TABLE `resultat` (
  `idResultat` int(11) NOT NULL,
  `etatResultat` enum('admis','refuse') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE `salle` (
  `idSalle` int(11) NOT NULL,
  `nomSalle` varchar(255) NOT NULL,
  `disponibilite` tinyint(1) NOT NULL,
  `nbPlaces` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `specialite`
--

CREATE TABLE `specialite` (
  `idSpecialite` int(11) NOT NULL,
  `nomSpecialite` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `cin` varchar(20) NOT NULL,
  `numTel` int(11) NOT NULL,
  `datenaissance` date DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0 : etudiant, 1 : professeur, 2 :scolarite, 3: admin, 4 : parent, 5 : bibliothecaire',
  `profil` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `email`, `password`, `cin`, `numTel`, `datenaissance`, `adresse`, `type`, `profil`) VALUES
(1, 'arthur', 'william', 'admin@gmail.com', 'admin', '532132', 55555555, '1981-02-10', 'tunis', 3, 'http://localhost/upload/uploads/f_5e59128538540.png'),
(2, 'biblio', 'thecaire', 'biblio@gmail.com', '123', 'zea654e6za5', 45656454, '2020-02-02', 'ariana', 5, 'http://localhost/upload/uploads/icons8_user_male_200px.png'),
(3, 'arthur', 'william', 'william@gmail.com', 'william', 'cze45zae', 4654654, '2020-02-04', 'bizerte', 0, 'http://localhost/upload/uploads/icons8_user_male_200px.png'),
(5, 'arthur', 'william', 'a', 'az', '5321', 55349497, '2020-02-05', 'tunis', 5, 'http://localhost/upload/uploads/icons8_user_male_200px.png'),
(13, 'aazza', 'ezrzze', 'b', 'b', 'aze', 45645645, '2020-02-02', 'zez', 5, 'http://localhost/upload/uploads/icons8_user_male_200px.png'),
(14, 'aza', 'eza', 'ac', 'ac', '456', 56455665, '2020-02-14', 'az', 0, 'http://localhost/upload/uploads/icons8_user_male_200px.png'),
(16, 'mehdi', 'mehdi', 'mehdi@gmail.com', '111', '111', 45612345, '2020-02-05', 'tunis', 4, 'https://localhost/upload/uploads/icons8_user_male_200px.png'),
(18, 'mehdi', 'mehdi', 'mehdai@gmail.com', '111zae', '111zae', 45612345, '2020-02-05', 'tunis', 4, 'https://localhost/upload/uploads/icons8_user_male_200px.png'),
(19, 'prof', 'prof', 'prof@gmail.com', 'a', 'cacaca', 45645665, '2020-02-12', 'tunis', 1, 'http://localhost/upload/uploads/icons8_user_male_200px.png');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `absence`
--
ALTER TABLE `absence`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_absence_idetudiant` (`idEtudiant`);

--
-- Index pour la table `attestation`
--
ALTER TABLE `attestation`
  ADD PRIMARY KEY (`idAttestation`);

--
-- Index pour la table `bibliothecaire`
--
ALTER TABLE `bibliothecaire`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `adresse` (`adresse`),
  ADD UNIQUE KEY `unique_id_bibliothecaire` (`id_bibliothecaire`);

--
-- Index pour la table `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`idClub`);

--
-- Index pour la table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`idCours`);

--
-- Index pour la table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`idDemande`);

--
-- Index pour la table `emprunt`
--
ALTER TABLE `emprunt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_livre` (`idLivre`),
  ADD KEY `fk_emprunt_user` (`idEmprunteur`);

--
-- Index pour la table `encadrement`
--
ALTER TABLE `encadrement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_pfe_encardrement` (`id_pfe`),
  ADD KEY `encadrement_ibfk_1` (`id_prof`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`idEvent`);

--
-- Index pour la table `formulaire`
--
ALTER TABLE `formulaire`
  ADD PRIMARY KEY (`idFormulaire`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_livre_bibliotheque` (`id_bibliotheque`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`idmatiere`);

--
-- Index pour la table `membrescolarite`
--
ALTER TABLE `membrescolarite`
  ADD PRIMARY KEY (`idMscolarite`);

--
-- Index pour la table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`idModule`);

--
-- Index pour la table `parent`
--
ALTER TABLE `parent`
  ADD PRIMARY KEY (`idParent`);

--
-- Index pour la table `pfe`
--
ALTER TABLE `pfe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_etudiant_pfe` (`id_etudiant`);

--
-- Index pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`idReclamation`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `resultat`
--
ALTER TABLE `resultat`
  ADD PRIMARY KEY (`idResultat`);

--
-- Index pour la table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`idSalle`);

--
-- Index pour la table `specialite`
--
ALTER TABLE `specialite`
  ADD PRIMARY KEY (`idSpecialite`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cin` (`cin`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `absence`
--
ALTER TABLE `absence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `attestation`
--
ALTER TABLE `attestation`
  MODIFY `idAttestation` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT pour la table `club`
--
ALTER TABLE `club`
  MODIFY `idClub` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `cours`
--
ALTER TABLE `cours`
  MODIFY `idCours` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `demande`
--
ALTER TABLE `demande`
  MODIFY `idDemande` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `emprunt`
--
ALTER TABLE `emprunt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT pour la table `encadrement`
--
ALTER TABLE `encadrement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `idEvent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `formulaire`
--
ALTER TABLE `formulaire`
  MODIFY `idFormulaire` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `idmatiere` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `membrescolarite`
--
ALTER TABLE `membrescolarite`
  MODIFY `idMscolarite` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `module`
--
ALTER TABLE `module`
  MODIFY `idModule` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `parent`
--
ALTER TABLE `parent`
  MODIFY `idParent` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pfe`
--
ALTER TABLE `pfe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `professeur`
--
ALTER TABLE `professeur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `idReclamation` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `resultat`
--
ALTER TABLE `resultat`
  MODIFY `idResultat` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `salle`
--
ALTER TABLE `salle`
  MODIFY `idSalle` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `specialite`
--
ALTER TABLE `specialite`
  MODIFY `idSpecialite` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `bibliothecaire`
--
ALTER TABLE `bibliothecaire`
  ADD CONSTRAINT `fk_user_bibliothecaire` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  ADD CONSTRAINT `fk_id_bibliothecaire` FOREIGN KEY (`id_bibliothecaire`) REFERENCES `bibliothecaire` (`id`);

--
-- Contraintes pour la table `emprunt`
--
ALTER TABLE `emprunt`
  ADD CONSTRAINT `fk_emprunt_user` FOREIGN KEY (`idEmprunteur`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `fk_livre` FOREIGN KEY (`idLivre`) REFERENCES `livre` (`id`);

--
-- Contraintes pour la table `encadrement`
--
ALTER TABLE `encadrement`
  ADD CONSTRAINT `encadrement_ibfk_1` FOREIGN KEY (`id_prof`) REFERENCES `professeur` (`id`),
  ADD CONSTRAINT `fk_pfe_encardrement` FOREIGN KEY (`id_pfe`) REFERENCES `pfe` (`id`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `fk_etudiant_idetudiant` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `fk_livre_bibliotheque` FOREIGN KEY (`id_bibliotheque`) REFERENCES `bibliotheque` (`id`);

--
-- Contraintes pour la table `pfe`
--
ALTER TABLE `pfe`
  ADD CONSTRAINT `fk_etudiant_pfe` FOREIGN KEY (`id_etudiant`) REFERENCES `etudiant` (`id`);

--
-- Contraintes pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD CONSTRAINT `fk_utilisateur_professeur` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
