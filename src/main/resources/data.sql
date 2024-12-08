DROP TABLE IF EXISTS carte;

CREATE TABLE IF NOT EXISTS carte
(
isbn VARCHAR(13) PRIMARY KEY,
titlu VARCHAR(255) NOT NULL,
autor VARCHAR(255) NOT NULL
);

INSERT INTO carte (isbn, titlu, autor) VALUES ('ISBN1', 'Scurta istorie a omenirii', 'Yuval Noah Harari');
INSERT INTO carte (isbn, titlu, autor) VALUES ('ISBN2', 'Homo deus - Scurta istorie a viitorului', 'Yuval Noah Harari');
INSERT INTO carte (isbn, titlu, autor) VALUES ('ISBN3', 'De veghe in lanul de seca', 'J.D. Salinger');