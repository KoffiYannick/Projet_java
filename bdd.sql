create database java;
CREATE TABLE salle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    batiment VARCHAR(255),
    etage INT,
    numero INT
);
CREATE TABLE reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_salle INT,
    FOREIGN KEY (id_salle) REFERENCES salle(id),
    responsable VARCHAR(255),
    promo VARCHAR(255),
    date_debut DATETIME,
    date_fin DATETIME
);