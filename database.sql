CREATE DATABASE db_estudo_java;

CREATE TABLE tb_usuario(
	id_usuario SERIAL PRIMARY KEY NOT NULL,
	ds_login   VARCHAR(30) NOT NULL,
	ds_senha   VARCHAR(30) NOT NULL   	
);

COMMENT ON COLUMN tb_usuario.id_usuario IS 'C�digo do usu�rio';
COMMENT ON COLUMN tb_usuario.ds_login IS 'Login do usu�rio para acesso ao sistema';
COMMENT ON COLUMN tb_usuario.ds_senha IS 'Senha do usu�rio para acesso ao sistema';

CREATE TABLE tb_pessoa(
    id_pessoa           SERIAL PRIMARY KEY NOT NULL,
    nm_pessoa           VARCHAR(70)  NOT NULL,
    fl_sexo	        CHAR(1)	     NOT NULL,
    dt_cadastro         DATE     NOT NULL,
    ds_email	        VARCHAR(80)  NOT NULL,
    ds_endereco         VARCHAR(200) NOT NULL,
    fl_origemCadastro   CHAR(1)	     NOT NULL,	
    id_usuario_cadastro	INT	     NOT NULL 
);


COMMENT ON COLUMN tb_pessoa.id_pessoa IS 'C�digo da pessoa';
COMMENT ON COLUMN tb_pessoa.nm_pessoa IS 'Nome da pessoa';
COMMENT ON COLUMN tb_pessoa.fl_sexo IS 'Informar M ou F';
COMMENT ON COLUMN tb_pessoa.dt_cadastro IS 'Data de cadastro';
COMMENT ON COLUMN tb_pessoa.ds_email IS 'E-mail da pessoa';
COMMENT ON COLUMN tb_pessoa.ds_endereco IS 'Descri��o do endere�o';
COMMENT ON COLUMN tb_pessoa.fl_origemCadastro IS 'Origem do cadastro (I) = INPUT ou (X) = XML';
COMMENT ON COLUMN tb_pessoa.id_usuario_cadastro IS 'Usu�rio logado que cadastrou a pessoa';

ALTER TABLE tb_pessoa ADD FOREIGN KEY (id_usuario_cadastro) REFERENCES tb_usuario(id_usuario);

INSERT INTO tb_usuario (ds_login,ds_senha) VALUES('admin','123456');