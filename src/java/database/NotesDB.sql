DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;

DROP TABLE Note;

CREATE TABLE Note( 
    noteid INT NOT NULL AUTO_INCREMENT, 
    dateCreated DATE NOT NULL,
    contents VARCHAR(1000) NOT NULL,
    PRIMARY KEY (noteid)
);

InSERT INTO Note VALUES (Default,'2008-7-04','this is the first line');