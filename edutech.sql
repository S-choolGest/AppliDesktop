-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Sam 22 Février 2020 à 10:23
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
  `idAbsence` int(11) NOT NULL,
  `idMatiere` int(11) NOT NULL,
  `dateAbs` date NOT NULL,
  `id_etudiant` int(11) NOT NULL,
  `etatAbs` enum('abs_jstf','abs_non_jstf') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `id` int(11) NOT NULL,
  `id_bibliotheque` int(11) DEFAULT NULL,
  `heure_debut` time DEFAULT NULL,
  `heure_arret` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `bibliotheque`
--

CREATE TABLE `bibliotheque` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `capacite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `bibliotheque`
--

INSERT INTO `bibliotheque` (`id`, `nom`, `capacite`) VALUES
(1, 'biblio_1', 1200);

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE `classe` (
  `idClasse` int(11) NOT NULL,
  `numClasse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `club`
--

CREATE TABLE `club` (
  `idClub` int(11) NOT NULL,
  `nomClub` varchar(255) NOT NULL,
  `categorieClub` enum('entreuprenariat','sport','social') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `dateRendu` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `emprunt`
--

INSERT INTO `emprunt` (`id`, `idEmprunteur`, `idLivre`, `etat`, `dateEmprunt`, `dateConfirmation`, `dateRendu`) VALUES
(11, 1, 11, 0x72656e6475, '2020-02-19', '2020-02-20', '2020-02-20'),
(12, 1, 12, 0x7265667573, '2020-02-19', '2020-02-20', NULL),
(14, 1, 13, 0x72656e6475, '2020-02-20', '2020-02-21', '2020-02-21'),
(15, 2, 11, 0x7265667573, '2020-02-21', '2020-02-21', NULL),
(16, 2, 12, 0x7265667573, '2020-02-21', '2020-02-21', NULL),
(17, 2, 13, 0x7265667573, '2020-02-21', '2020-02-21', NULL),
(21, 3, 11, 0x617474656e7465, '2020-02-21', NULL, NULL),
(22, 3, 13, 0x617474656e7465, '2020-02-21', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `encadrement`
--

CREATE TABLE `encadrement` (
  `id` int(11) NOT NULL,
  `id_pfe` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` int(11) NOT NULL,
  `niveauE` enum('premiere','deuxieme','troisieme','quatreieme','cinquieme') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `niveauE`) VALUES
(1, 'premiere'),
(2, 'troisieme');

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE `events` (
  `idEvent` int(11) NOT NULL,
  `nomEvent` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `typeEvent` enum('conference','workshop','forum','competition') NOT NULL,
  `dateEvent` date NOT NULL,
  `idEmprunt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(11, 2, 'koo', 'ccc2', 'bbb2', 'ddd2', '2020-01-02', 504, 300, 'http://localhost/mobile/book.jpg', '2020-02-13'),
(12, 1, 'titre1', 'auteur1', 'editeur1', 'categorie1', '2020-02-18', 200, 30, 'http://localhost/mobile/book.jpg', '2020-02-12'),
(13, 1, 'titre2', 'auteur2', 'editeur2', 'categorie2', '2020-02-05', 100, 2, 'http://localhost/mobile/book.jpg', '2020-02-11'),
(14, 1, 'titre3', 'auteur3', 'editeur3', 'categorie3', '2020-01-29', 300, 12, 'http://localhost/mobile/book.jpg', '2020-02-18'),
(15, 1, 'titt', 'auza', 'aezr', 'ae', '2020-02-11', 123, 12, 'http://localhost/mobile/book.jpg', '2020-02-22');

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `idMatiere` int(11) NOT NULL,
  `nomMatiere` varchar(255) NOT NULL,
  `coefMatiere` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

CREATE TABLE `professeur` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0 : etudiant, 1 : professeur, 2 :scolarite, 3: admin, 4 : parent',
  `profil` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `email`, `password`, `cin`, `numTel`, `datenaissance`, `adresse`, `type`, `profil`) VALUES
(1, 'arthur', 'william', 'admin@gmail.com', 'admin', '532132', 55555555, NULL, NULL, 3, NULL),
(2, 'biblio', 'thecaire', 'biblio@gmail.com', '123', 'zea654e6za5', 45656454, '2020-02-02', 'ariana', 5, NULL),
(3, 'arthur', 'william', 'william@gmail.com', 'william', 'cze45zae', 4654654, '2020-02-04', 'bizerte', 0, NULL);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `absence`
--
ALTER TABLE `absence`
  ADD PRIMARY KEY (`idAbsence`);

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
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`idClasse`);

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
  ADD KEY `fk_pfe_encardrement` (`id_pfe`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`idEvent`,`idEmprunt`);

--
-- Index pour la table `formulaire`
--
ALTER TABLE `formulaire`
  ADD PRIMARY KEY (`idFormulaire`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`idMatiere`);

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
  ADD PRIMARY KEY (`id`);

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
  MODIFY `idAbsence` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `attestation`
--
ALTER TABLE `attestation`
  MODIFY `idAttestation` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `classe`
--
ALTER TABLE `classe`
  MODIFY `idClasse` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `club`
--
ALTER TABLE `club`
  MODIFY `idClub` int(11) NOT NULL AUTO_INCREMENT;
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `events`
--
ALTER TABLE `events`
  MODIFY `idEvent` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `formulaire`
--
ALTER TABLE `formulaire`
  MODIFY `idFormulaire` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `idMatiere` int(11) NOT NULL AUTO_INCREMENT;
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `professeur`
--
ALTER TABLE `professeur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `bibliothecaire`
--
ALTER TABLE `bibliothecaire`
  ADD CONSTRAINT `fk_user_bibliothecaire` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

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
  ADD CONSTRAINT `fk_pfe_encardrement` FOREIGN KEY (`id_pfe`) REFERENCES `pfe` (`id`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `fk_utilisateur_etudiant` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `pfe`
--
ALTER TABLE `pfe`
  ADD CONSTRAINT `fk_etudiant_pfe` FOREIGN KEY (`id`) REFERENCES `etudiant` (`id`);

--
-- Contraintes pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD CONSTRAINT `fk_utilisateur_professeur` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
