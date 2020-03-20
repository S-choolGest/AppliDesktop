-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 13 mars 2020 à 19:08
-- Version du serveur :  10.1.37-MariaDB
-- Version de PHP :  5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
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
  `date` timestamp NULL DEFAULT NULL,
  `idEtudiant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `absence`
--

INSERT INTO `absence` (`id`, `matiere`, `etat`, `date`, `idEtudiant`) VALUES
(25, 'math', 'justifié', '2020-01-27 08:00:00', 1),
(26, 'français', 'justifié', '2020-01-15 09:45:00', 8),
(27, 'français', 'justifié', '2020-01-09 12:30:00', 10),
(28, 'français', 'justifié', '2020-01-21 09:45:00', 2),
(29, 'math', 'justifié', '2020-01-27 12:30:00', 1),
(30, 'français', 'justifié', '2020-01-22 08:00:00', 1),
(31, 'math', 'non justifié', '2020-01-08 12:30:00', 8),
(32, 'math', 'non justifié', '2020-02-12 12:30:00', 1),
(33, 'français', 'non justifié', '2020-02-03 14:00:00', 1),
(34, 'math', 'non justifié', '2020-02-04 12:30:00', 2),
(35, 'math', 'non justifié', '2020-02-05 09:45:00', 8),
(36, 'math', 'non justifié', '2020-02-06 08:00:00', 10),
(37, 'math', 'non justifié', '2020-02-21 12:30:00', 2),
(38, 'math', 'non justifié', '2020-02-21 12:30:00', 8),
(39, 'math', 'non justifié', '2020-02-21 12:30:00', 10),
(40, 'math', 'non justifié', '2020-02-21 14:00:00', 10),
(41, 'math', 'non justifié', '2020-02-21 14:00:00', 8),
(42, 'math', 'non justifié', '2020-02-21 14:00:00', 2),
(43, 'français', 'non justifié', '2020-02-28 12:30:00', 2),
(44, 'français', 'non justifié', '2020-02-28 12:30:00', 8),
(45, 'français', 'non justifié', '2020-02-28 12:30:00', 10),
(46, 'math', 'non justifié', '2020-03-08 08:00:00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `attestation`
--

CREATE TABLE `attestation` (
  `idAttestation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `bibliothecaire`
--

CREATE TABLE `bibliothecaire` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `bibliotheque`
--

CREATE TABLE `bibliotheque` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` int(11) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `id_bibliothecaire` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE `classe` (
  `idClasse` int(11) NOT NULL,
  `nomClasse` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `classe`
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
  `CategorieClub` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `idcours` int(11) NOT NULL,
  `nomCours` varchar(255) NOT NULL,
  `id_prof` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `id_demande` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` int(11) NOT NULL,
  `IdClasse` int(11) NOT NULL,
  `cin_p` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `IdClasse`, `cin_p`) VALUES
(1, 1, 7623458),
(2, 2, 0),
(8, 2, 9452153),
(10, 2, 0);

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

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `idMatiere` int(11) NOT NULL,
  `nomMatiere` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`idMatiere`, `nomMatiere`) VALUES
(1, 'math'),
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
  `heure_debut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `heure_final` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
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
  `cin` int(20) NOT NULL,
  `numTel` int(11) NOT NULL,
  `datenaissance` date NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `type` int(11) NOT NULL COMMENT '0:etudiant, 1:professeur, 2:scolarite, 3:admin, 4:parent',
  `profile` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `email`, `password`, `cin`, `numTel`, `datenaissance`, `adresse`, `type`, `profile`) VALUES
(1, 'mehdi', 'majoul', 'Mehdi.Majoul@gmail.com', '1235a', 9654234, 0, '2020-02-04', 'bhibhbl', 0, 'bnkmb'),
(2, 'mouhamed', 'Ahmed', 'Mouhamed.Ahmed@gmail.com', '12355v', 9423147, 0, '2020-02-24', ',jkopnkomn', 0, 'njonj'),
(3, 'flen', 'felten', 'flen.felten@gmail.com', 'abcd12', 7211248, 0, '2020-02-19', 'njkmnjn', 1, 'nlmnjknk'),
(4, 'Rayen', 'Goucha', 'Rayen.Goucha@gmail.com', 'ddcc', 7623458, 0, '2020-02-24', ',kmkmo,', 4, 'n,lmnj'),
(5, 'William', 'Arthur', 'William.Arthur@gmail.com', '5555', 9452153, 0, '2020-02-06', 'njmnon', 4, 'njlkmnj'),
(6, 'scolarite', 'scol', 'scolarite.scol@gmail.com', '123', 9563125, 1234558, '2020-02-25', 'SDVQSDV', 2, 'cSCxC'),
(7, 'admin', 'admin', 'admin.admin@gmail.com', 'admin', 7541321, 52562, '2020-02-12', 'adapfk,afp', 3, 'cacdaca'),
(8, 'fadhel', 'shell', 'fadhel.shell@gmail.com', '1334242', 9658652, 546242, '2020-02-06', 'vkpadvojkna', 0, 'vkoadao'),
(10, 'selim', 'zaalouni', 'selim.zaalouni@gmail.com', '12324565', 12324565, 21548230, '2020-02-12', 'fadfacad', 0, 'vkoadao'),
(11, 'ikjhn', 'htr', 'uyguy', '11111111', 11111111, 22, '2020-03-08', 'tryguhi', 0, 'vkoadao');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `absence`
--
ALTER TABLE `absence`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_absence_idetudiant` (`idEtudiant`);

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
  ADD PRIMARY KEY (`idcours`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_etudiant_idclasse` (`IdClasse`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`idMatiere`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `absence`
--
ALTER TABLE `absence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `classe`
--
ALTER TABLE `classe`
  MODIFY `idClasse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `club`
--
ALTER TABLE `club`
  MODIFY `idClub` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `cours`
--
ALTER TABLE `cours`
  MODIFY `idcours` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `idMatiere` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `fk_absence_idetudiant` FOREIGN KEY (`idEtudiant`) REFERENCES `etudiant` (`id`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `fk_etudiant_idclasse` FOREIGN KEY (`IdClasse`) REFERENCES `classe` (`idClasse`),
  ADD CONSTRAINT `fk_etudiant_idetudiant` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
