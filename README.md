# Crud
base

create table tbDentista(
UUID_dentista varchar2(100) primary key not null,
Nombre_dentista varchar2(100) not null,
Edad_dentista varchar2(2) not null,
Peso_dentista varchar2(4),
Correo_dentista varchar2(100)
);


select * from tbDentista;
 
 commit;
UPDATE tbDentista 
SET Peso_dentista = 152, Edad_dentista = 60, Correo_dentista = 'p@gmail.com' 
WHERE UUID_dentista = '30f5b2e6-08a5-4d9c-aa46-946dba15bd7b'
