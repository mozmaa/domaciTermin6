DROP SCHEMA IF EXISTS dostava;
CREATE SCHEMA dostava DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE dostava;

CREATE TABLE korisnici (
	korisnickoIme VARCHAR(20),
	lozinka VARCHAR(20) NOT NULL,
	eMail VARCHAR(20) NOT NULL,
	pol ENUM('muški', 'ženski') DEFAULT 'muški',
	administrator BOOL DEFAULT false,
	PRIMARY KEY(korisnickoIme)
);

CREATE TABLE restorani (
	id BIGINT AUTO_INCREMENT,
	naziv VARCHAR(75) NOT NULL,
	datumOsnivanja DATE NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE kategorije (
	id BIGINT AUTO_INCREMENT,
	naziv VARCHAR(25) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE restoranKategorija (
    restoranId BIGINT,
    kategorijaId BIGINT,
    PRIMARY KEY(restoranId, kategorijaId),
    FOREIGN KEY(restoranId) REFERENCES restorani(id)
		ON DELETE CASCADE,
    FOREIGN KEY(kategorijaId) REFERENCES kategorije(id)
		ON DELETE CASCADE
);

CREATE TABLE artikli (
	id BIGINT AUTO_INCREMENT,
	naziv VARCHAR(75) NOT NULL,
	opis VARCHAR(255) NOT NULL,
	cena DECIMAL(10,2) NOT NULL,
	restoranId BIGINT NOT NULL,
	PRIMARY KEY(id),
    FOREIGN KEY(restoranId) REFERENCES restorani(id)
		ON DELETE CASCADE
);

INSERT INTO korisnici (korisnickoIme, lozinka, eMail, pol, administrator) VALUES ('a', 'a', 'a@a.com', 'muški', true);
INSERT INTO korisnici (korisnickoIme, lozinka, eMail, pol, administrator) VALUES ('b', 'b', 'b@b.com', 'ženski', false);
INSERT INTO korisnici (korisnickoIme, lozinka, eMail, pol, administrator) VALUES ('c', 'c', 'c@c.com', 'muški', false);

INSERT INTO kategorije (id, naziv) VALUES (1, 'Burgeri');
INSERT INTO kategorije (id, naziv) VALUES (2, 'Sendviči');
INSERT INTO kategorije (id, naziv) VALUES (3, 'Suši');
INSERT INTO kategorije (id, naziv) VALUES (4, 'Onigiri');
INSERT INTO kategorije (id, naziv) VALUES (5, 'Burito');

INSERT INTO restorani (id, naziv, datumOsnivanja) VALUES (1, 'Restoran 1', '2001-01-01');
INSERT INTO restorani (id, naziv, datumOsnivanja) VALUES (2, 'Restoran 2', '2012-02-01');
INSERT INTO restorani (id, naziv, datumOsnivanja) VALUES (3, 'Restoran 3', '2023-03-01');

INSERT INTO restoranKategorija (restoranId, kategorijaId) VALUES (1, 1);
INSERT INTO restoranKategorija (restoranId, kategorijaId) VALUES (1, 2);

INSERT INTO restoranKategorija (restoranId, kategorijaId) VALUES (2, 3);
INSERT INTO restoranKategorija (restoranId, kategorijaId) VALUES (2, 4);

INSERT INTO restoranKategorija (restoranId, kategorijaId) VALUES (3, 5);

INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES ('Dupli burger', 'Burger meso 120 grama, toljeni sir, burger majonez, heinz kečap, ajsberg, kiseli krastavac', 650.0, 1);
INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES ('Pileći burger', 'Pileći burger, tost sir, pančeta, burger majonez, ajsberg, paradajz', 550.0, 1);
INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES ('Indeks sendvič', 'Praška šunka, sir, šampinjoni', 320.0, 1);
INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES ('Vratolomija sendvič', 'Suvi vrat, sir, jaja', 290.0, 1);
INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES ('Crispy specijal', 'Hrskave sushi rolnice, pohovane u tempuri. Nori alga, pirinač, pohovana rolnica sa dodatkom krastavca, krem sira, lososa i japanskog nitsume sosa', 920.0, 2);
INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES ('Samurai roll', '8 komada, nori alga, pirinač, losos, tuna, avokado, obložena breniranim lososom i tunom,unagi sos, samuraj sos, hrskavi crunch krompir', 990.0, 2);
INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES ('Onigiri sa tunom', 'pirinač, tuna, crni susam, japanski majonez i nori alge', 510.0, 2);
INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES ('Onigiri sa lososom', 'pirinač, losos, crni susam, japanski majonez i nori alge', 670.0, 2);
INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES ('Achiote piletina burito', 'Meksički pirinač, Crni pasulj, Mokahete Salsa, Pico de gallo, Kukuruz salsa, Marinirani Krastavac, Kačkavalj, Crema Fresca', 560.0, 3);
INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES ('Chorizo burito', 'Meksički pirinač, Pinto pasulj, Mokahete Salsa, Pico de gallo, Kukuruz Salsa, Kačkavalj, Marinirani krastavac, Marinirani crveni luk, Chipotle aioli', 610.0, 3);
INSERT INTO artikli (naziv, opis, cena, restoranId) VALUES ('Pečene pečurke burito', 'Beli pirinač, Crni pasulj, Pečene pečurke, Pico de gallo, Kukuruz salsa, Marinirani krastavac, Mokahete salsa, Mix zelenih salata, Krema Freska', 480.0, 3);

