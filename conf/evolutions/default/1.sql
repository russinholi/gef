# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cliente (
  id                        integer not null,
  nome                      varchar(255),
  telefone                  varchar(255),
  ativo                     boolean,
  cpf                       varchar(255),
  rg                        varchar(255),
  constraint pk_cliente primary key (id))
;

create table endereco (
  id                        integer not null,
  logradouro                varchar(255),
  numero                    varchar(255),
  cidade                    varchar(255),
  uf                        varchar(255),
  cep                       integer,
  constraint pk_endereco primary key (id))
;

create table fatura (
  numero                    integer not null,
  valor                     double,
  vencimento                timestamp,
  situacao                  varchar(9),
  parcela                   integer,
  constraint ck_fatura_situacao check (situacao in ('ABERTA','CANCELADA','PAGA')),
  constraint pk_fatura primary key (numero))
;

create table fornecedor (
  id                        integer not null,
  nome                      varchar(255),
  telefone                  varchar(255),
  ativo                     boolean,
  cnpj                      varchar(255),
  ie                        varchar(255),
  constraint pk_fornecedor primary key (id))
;

create table item_nota_fiscal (
  id                        integer not null,
  produto_id                integer,
  valor                     double,
  quantidade                integer,
  desconto                  double,
  nota_fiscal_numero        integer,
  constraint pk_item_nota_fiscal primary key (id))
;

create table lancamento (
  id                        integer not null,
  tipo                      varchar(6),
  valor                     double,
  forma_pagamento           varchar(14),
  constraint ck_lancamento_tipo check (tipo in ('COMPRA','VENDA')),
  constraint ck_lancamento_forma_pagamento check (forma_pagamento in ('A_VISTA','A_PRAZO','CARTAO_CREDITO')),
  constraint pk_lancamento primary key (id))
;

create table nota_fiscal (
  numero                    integer not null,
  pessoa_id                 integer,
  tipo                      varchar(6),
  data                      timestamp,
  constraint ck_nota_fiscal_tipo check (tipo in ('COMPRA','VENDA')),
  constraint pk_nota_fiscal primary key (numero))
;

create table pessoa (
  id                        integer not null,
  nome                      varchar(255),
  telefone                  varchar(255),
  ativo                     boolean,
  constraint pk_pessoa primary key (id))
;

create table produto (
  id                        integer not null,
  codigo_barras             bigint,
  nome                      varchar(255),
  descricao                 varchar(255),
  apresentacao              varchar(255),
  preco                     double,
  tipo_medicamento          varchar(10),
  tipo_controle             varchar(14),
  quantidade                integer,
  constraint ck_produto_tipo_medicamento check (tipo_medicamento in ('COMPRIMIDO','XAROPE','POMADA','COLIRIO')),
  constraint ck_produto_tipo_controle check (tipo_controle in ('SEM_CONTROLE','TARJA_VERMELHA','TARJA_PRETA','ANTIBIOTICO')),
  constraint pk_produto primary key (id))
;

create table retirada (
  id                        integer not null,
  produto_id                integer,
  justificativa             varchar(255),
  quantidade                integer,
  data                      timestamp,
  constraint pk_retirada primary key (id))
;

create table usuario (
  id                        integer not null,
  login                     varchar(255),
  senha                     varchar(255),
  nome                      varchar(255),
  cpf                       varchar(255),
  rg                        varchar(255),
  percentual_comissao       double,
  ativo                     boolean,
  constraint pk_usuario primary key (id))
;

create sequence cliente_seq;

create sequence endereco_seq;

create sequence fatura_seq;

create sequence fornecedor_seq;

create sequence item_nota_fiscal_seq;

create sequence lancamento_seq;

create sequence nota_fiscal_seq;

create sequence pessoa_seq;

create sequence produto_seq;

create sequence retirada_seq;

create sequence usuario_seq;

alter table item_nota_fiscal add constraint fk_item_nota_fiscal_produto_1 foreign key (produto_id) references produto (id) on delete restrict on update restrict;
create index ix_item_nota_fiscal_produto_1 on item_nota_fiscal (produto_id);
alter table item_nota_fiscal add constraint fk_item_nota_fiscal_notaFiscal_2 foreign key (nota_fiscal_numero) references nota_fiscal (numero) on delete restrict on update restrict;
create index ix_item_nota_fiscal_notaFiscal_2 on item_nota_fiscal (nota_fiscal_numero);
alter table nota_fiscal add constraint fk_nota_fiscal_pessoa_3 foreign key (pessoa_id) references pessoa (id) on delete restrict on update restrict;
create index ix_nota_fiscal_pessoa_3 on nota_fiscal (pessoa_id);
alter table retirada add constraint fk_retirada_produto_4 foreign key (produto_id) references produto (id) on delete restrict on update restrict;
create index ix_retirada_produto_4 on retirada (produto_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists cliente;

drop table if exists endereco;

drop table if exists fatura;

drop table if exists fornecedor;

drop table if exists item_nota_fiscal;

drop table if exists lancamento;

drop table if exists nota_fiscal;

drop table if exists pessoa;

drop table if exists produto;

drop table if exists retirada;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists cliente_seq;

drop sequence if exists endereco_seq;

drop sequence if exists fatura_seq;

drop sequence if exists fornecedor_seq;

drop sequence if exists item_nota_fiscal_seq;

drop sequence if exists lancamento_seq;

drop sequence if exists nota_fiscal_seq;

drop sequence if exists pessoa_seq;

drop sequence if exists produto_seq;

drop sequence if exists retirada_seq;

drop sequence if exists usuario_seq;

