-- phpMyAdmin SQL Dump
-- version 4.2.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:8889
-- Généré le :  Sam 07 Février 2015 à 22:02
-- Version du serveur :  5.5.38
-- Version de PHP :  5.5.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `ikya`
--

-- --------------------------------------------------------

--
-- Structure de la table `ACCOUNT`
--

CREATE TABLE `ACCOUNT` (
`id` int(11) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `CHALLENGE`
--

CREATE TABLE `CHALLENGE` (
`ID` int(11) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ID_CONTACT` int(11) DEFAULT NULL,
  `ID_USER` int(11) DEFAULT NULL,
  `ID_WINNER` int(11) DEFAULT NULL,
  `STATUT` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `CHALLENGE`
--

INSERT INTO `CHALLENGE` (`ID`, `DESCRIPTION`, `ID_CONTACT`, `ID_USER`, `ID_WINNER`, `STATUT`) VALUES
(1, NULL, 2, 3, -1, 20),
(2, NULL, 3, 2, 2, 50),
(3, 'jeu de loie', 2, 3, NULL, 20),
(4, 'jeu de dame', 2, 3, NULL, 20),
(5, 'jeu de chevaux', 2, 3, NULL, 30),
(6, 'jeu de cartes', 2, 3, -1, 40),
(7, 'jeu de morpion', 2, 3, -1, 50),
(8, 'Big deal', 3, 2, NULL, 10),
(9, 'Roue de la fortune', 3, 2, NULL, 20),
(10, 'Rolland Garos', 3, 2, NULL, 30),
(11, 'Inter toto', 3, 2, -1, 40),
(12, ' toto', 3, 2, -1, 50),
(13, 'aller', 4, 2, NULL, 10),
(14, 'super cool, ce challenge ... !', 1, 2, NULL, 10),
(15, 'aller', 1, 2, NULL, 10),
(16, 'o', 4, 2, NULL, 10),
(17, 'g', 9, 2, NULL, 10),
(22, 'bÃ©bÃ©', 1, 2, NULL, 10),
(23, 'allé couché le bébé en moins de 1 minute ...', 5, 2, NULL, 30),
(24, 'Faire une partie d''échec !', 1, 2, NULL, 10),
(25, 'aller à la pêche à la moule sans forcer', 4, 2, -1, 50),
(26, 'aller à la piscine', 4, 4, NULL, 10),
(27, 'super challenge', 3, 4, NULL, 10),
(28, 'super challenge', 2, 4, NULL, 10),
(29, 'jouer à la marelle', 5, 4, NULL, 20),
(30, 'jouer à la marelle', 4, 5, NULL, 10),
(31, 'jouer aux dames', 4, 5, NULL, 10),
(32, 'salut', 4, 5, 5, 50),
(33, 'bonjour', 5, 4, NULL, 10),
(34, 'jouez à candy crush', 1, 5, NULL, 10),
(35, 'salut jacasserie !', 5, 4, NULL, 10),
(36, 'salut', 4, 4, NULL, 10),
(37, 'ndfnd;nf;dn,fd', 5, 4, NULL, 10),
(38, 'Input Parameters can be positional parameters or named parameters. Positional and named parameters may not be mixed in a single query.', 9, 4, NULL, 10);

-- --------------------------------------------------------

--
-- Structure de la table `CONTACT`
--

CREATE TABLE `CONTACT` (
`ID` int(11) NOT NULL,
  `ID_CONTACT` int(11) DEFAULT NULL,
  `ID_USER` int(11) DEFAULT NULL,
  `STATUT` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `CONTACT`
--

INSERT INTO `CONTACT` (`ID`, `ID_CONTACT`, `ID_USER`, `STATUT`) VALUES
(1, 1, 4, 10),
(30, 7, 4, 10),
(31, 8, 4, 30),
(32, 5, 4, 10),
(33, 6, 4, 10),
(34, 3, 4, 10),
(35, 2, 4, 10),
(36, 9, 4, 10),
(37, 10, 4, 10);

-- --------------------------------------------------------

--
-- Structure de la table `MESSAGE`
--

CREATE TABLE `MESSAGE` (
`ID` int(11) NOT NULL,
  `DATE` datetime DEFAULT NULL,
  `ID_MESSAGERIE` int(11) DEFAULT NULL,
  `ID_USER` int(11) DEFAULT NULL,
  `MESSAGE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `MESSAGERIE`
--

CREATE TABLE `MESSAGERIE` (
`ID` int(11) NOT NULL,
  `ID_USER` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `messagerie_message`
--

CREATE TABLE `messagerie_message` (
  `MESSAGERIE_ID` int(11) DEFAULT NULL,
  `MESSAGES_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `messagerie_user`
--

CREATE TABLE `messagerie_user` (
  `MESSAGERIE_ID` int(11) DEFAULT NULL,
  `USERS_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `USER`
--

CREATE TABLE `USER` (
`ID` int(11) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `PSEUDO` varchar(255) DEFAULT NULL,
  `SCORE` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `USER`
--

INSERT INTO `USER` (`ID`, `EMAIL`, `PASSWORD`, `PHONE`, `PSEUDO`, `SCORE`) VALUES
(1, 'John@ouioui.fr', 'pass', '0608080465', 'John', 0),
(2, 'Arthur@ouioui.fr', 'pass', '0788888888', 'Arthur', 5),
(3, 'Louis@ouioui.fr', 'ssap', '0788888888', 'Louis', 0),
(4, 'olivier@gmail.com', 'passez', '607534508', 'olivier', 2),
(5, 'thomas@gmail.com', 'thomas', '607534508', 'thomas', 3),
(6, 'vincent@gmail.com', 'vincent', '607534508', 'vincent', 0),
(7, 'marc@gmail.com', 'super', '607534508', 'marc', 0),
(8, 'jean@gmail.com', 'machin', '607534508', 'jean', 0),
(9, 'roger@gmail.com', 'roger', '607534508', 'roger', 0),
(10, 'bertrand@gmail.com', 'bertrand', '607534508', 'bertrand', 0);

-- --------------------------------------------------------

--
-- Structure de la table `USER_CONTACT`
--

CREATE TABLE `USER_CONTACT` (
  `USER_ID` int(11) DEFAULT NULL,
  `CONTACTS_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `USER_CONTACT`
--

INSERT INTO `USER_CONTACT` (`USER_ID`, `CONTACTS_ID`) VALUES
(4, 30),
(4, 31),
(4, 32),
(4, 33),
(4, 34),
(4, 35),
(4, 35),
(4, 36),
(4, 37),
(4, 1),
(4, 31);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `ACCOUNT`
--
ALTER TABLE `ACCOUNT`
 ADD PRIMARY KEY (`id`);

--
-- Index pour la table `CHALLENGE`
--
ALTER TABLE `CHALLENGE`
 ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `CONTACT`
--
ALTER TABLE `CONTACT`
 ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `MESSAGE`
--
ALTER TABLE `MESSAGE`
 ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `MESSAGERIE`
--
ALTER TABLE `MESSAGERIE`
 ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `messagerie_message`
--
ALTER TABLE `messagerie_message`
 ADD KEY `I_MSSGSSG_ELEMENT` (`MESSAGES_ID`), ADD KEY `I_MSSGSSG_MESSAGERIE_ID` (`MESSAGERIE_ID`);

--
-- Index pour la table `messagerie_user`
--
ALTER TABLE `messagerie_user`
 ADD KEY `I_MSSG_SR_ELEMENT` (`USERS_ID`), ADD KEY `I_MSSG_SR_MESSAGERIE_ID` (`MESSAGERIE_ID`);

--
-- Index pour la table `USER`
--
ALTER TABLE `USER`
 ADD PRIMARY KEY (`ID`), ADD UNIQUE KEY `U_USER_PSEUDO` (`PSEUDO`), ADD UNIQUE KEY `U_USER_EMAIL` (`EMAIL`);

--
-- Index pour la table `USER_CONTACT`
--
ALTER TABLE `USER_CONTACT`
 ADD KEY `USER_ID` (`USER_ID`), ADD KEY `CONTACTS_ID` (`CONTACTS_ID`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `ACCOUNT`
--
ALTER TABLE `ACCOUNT`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `CHALLENGE`
--
ALTER TABLE `CHALLENGE`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT pour la table `CONTACT`
--
ALTER TABLE `CONTACT`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT pour la table `MESSAGE`
--
ALTER TABLE `MESSAGE`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `MESSAGERIE`
--
ALTER TABLE `MESSAGERIE`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `USER`
--
ALTER TABLE `USER`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `USER_CONTACT`
--
ALTER TABLE `USER_CONTACT`
ADD CONSTRAINT `user_contact_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`),
ADD CONSTRAINT `user_contact_ibfk_2` FOREIGN KEY (`CONTACTS_ID`) REFERENCES `CONTACT` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
