create database livraria;
use livraria;

CREATE TABLE livro(
                      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      titulo VARCHAR(100),
                      autores VARCHAR(100),
                      editora VARCHAR(100),
                      preco DECIMAL(8,2)
);

CREATE TABLE impresso (
                          id INT PRIMARY KEY,
                          frete DECIMAL(10,2),
                          estoque INT,
                          FOREIGN KEY (id) REFERENCES livro(id)
);

CREATE TABLE eletronico (
                            id INT PRIMARY KEY,
                            tamanho INT,
                            FOREIGN KEY (id) REFERENCES livro(id)
);


CREATE TABLE venda (
                       numero INT PRIMARY KEY,
                       cliente VARCHAR(100),
                       valor DECIMAL(10,2)
);


CREATE TABLE venda_livro (
                             numero_venda INT,
                             id_livro INT,

                             PRIMARY KEY (numero_venda, id_livro),
                             FOREIGN KEY (numero_venda) REFERENCES venda(numero),
                             FOREIGN KEY (id_livro) REFERENCES livro(id)
);