# Laboratorio de Conexión de una Base datos en SQL SERVER a un lenguaje de programación  

## Objetivo

Crear la base de datos para gestor de recetas de cocina en SQL SERVER y posteriormente conectarlo con un lenguaje de programación de alto nivel

- [ ] JAVA
- [X] C#
- [ ] VB

Este lenguaje se seleccionara durante la lección.

### Requisitos

* [SQL Server Express 2019 o superior.](https://www.microsoft.com/es-es/sql-server/sql-server-downloads)
* [SQL Server Management Studio (SSMS) en su ultima versión](https://learn.microsoft.com/es-es/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-ver16) - [Guía Instalación de SQL SERVER 2019 & SSMS](https://seth09work.notion.site/Instalaci-n-y-configuraci-n-del-sistema-gestor-de-bases-de-datos-de-SQL-SERVER-9027a9827de34d9a82076f2caaa3f706?pvs=4)
* Lenguaje de programación (Java, C#, VB, etc.) y su respectivo IDE de desarrollo.

## Construcción de la base de datos

Para iniciar con la creación de la aplicación se deberá elaborar la base de datos que se utilizara, junto con algunos procedimiento almacenados y funciones que se necesitaran desde la aplicación, para ello se iniciara ilustrando cuales serán las tablas y sus atributos:

### 1. Diseño de la base de datos

1. Abrir SSMS y conectarse al servidor SQL Server.
2. Crear una nueva base de datos con el nombre "dbGestorRecetas".
3. Elabore las siguientes tablas:

**Tabla 1: tblRecetas**
Atributos:

* IdReceta (INT - PRIMARY KEY - IDENTITY)
* Nombre (VARCHAR(100))
* Descripcion (TEXT)
* TiempoPreparacion (INT)
* Dificultad (INT)
* Imagen (IMAGE)

**Tabla 2: tblIngredientes**
Atributos:

* IdIngrediente (INT - PRIMARY KEY - IDENTITY)
* Nombre (VARCHAR(50))
* UnidadMedida (VARCHAR(10))

**Tabla 3: tblRecetasIngredientes**
Atributos:

* IdReceta (INT, FOREIGN KEY)
* IdIngrediente (INT, FOREIGN KEY)
* Cantidad (DECIMAL)
* PRIMARY KEY (IdReceta, IdIngrediente)

**Tabla 4: tblPasos**
Atributos:

* IdPaso (INT - PRIMARY KEY - IDENTITY)
* IdReceta (INT, FOREIGN KEY)
* NumeroPaso (INT)
* Descripcion (TEXT)

**Tabla 5: tblCategoroa**
Atributos:

* IdCategoria (INT - PRIMARY KEY - IDENTITY)
* Nombre (VARCHAR(50))

**Tabla 6: tblRecetasCategoria**
Atributos:

* IdReceta (INT - FOREIGN KEY)
* IdCategoria (INT, FOREIGN KEY)
* PRIMARY KEY (IdReceta, idCategoria)

***Se adjunta el diagrama relacional de la base de datos:***

![ModeloRelacional](./recursosTutorial/image/diagramaRelacional.png)

***Se adjunta el Script de la base de datos:***


```sql
CREATE DATABASE dbGestorRecetas;
GO

USE dbGestorRecetas;
GO

CREATE TABLE tblRecetas (
    IdReceta INT PRIMARY KEY IDENTITY,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion TEXT,
    TiempoPreparacion INT,
    Dificultad INT,
    Imagen image,
    FechaModificacion DATE
);
GO

CREATE TABLE tblIngredientes (
    IdIngrediente INT PRIMARY KEY IDENTITY,
    Nombre VARCHAR(50) NOT NULL,
    UnidadMedida VARCHAR(10) NOT NULL
);
GO

CREATE TABLE tblRecetasIngredientes (
    IdReceta INT FOREIGN KEY REFERENCES tblRecetas(IdReceta),
    IdIngrediente INT FOREIGN KEY REFERENCES tblIngredientes(IdIngrediente),
    Cantidad decimal(18,2) NOT NULL
);
GO

CREATE TABLE tblPasos (
    IdPaso INT PRIMARY KEY IDENTITY,
    IdReceta INT FOREIGN KEY REFERENCES tblRecetas(IdReceta),
    NumeroPaso INT NOT NULL,
    Descripcion TEXT NOT NULL
);
GO

CREATE TABLE tblCategorias (
    IdCategoria INT PRIMARY KEY IDENTITY,
    Nombre VARCHAR(50) NOT NULL
);
GO

CREATE TABLE tblRecetasCategorias (
    IdReceta INT FOREIGN KEY REFERENCES tblRecetas(IdReceta),
    IdCategoria INT FOREIGN KEY REFERENCES tblCategorias(IdCategoria)
);
GO
```

### 2. Implementación de funciones y procedimientos almacenados

A continuación se deberá de elaborar cada uno de los siguientes procedimientos almenados y funciones.

1. Procedimientos para la gestión de recetas:
   1. Obtener recetas: Obtener una lista de todas las recetas.
   2. Obtener receta por ID: Obtener la información de una receta específica.
   3. Agregar receta: Crear una nueva receta, tomar en cuenta la posibilidad de enviar las categorías a la que pertenece la receta
   4. Editar receta: Modificar la información de una receta existente.
   5. Eliminar recetas: Eliminar una receta del sistema.
2. Procedimientos para la gestión de Ingredientes:
   1. Obtener Ingredientes: Obtener una lista de todos los Ingredientes de un receta.
   2. Obtener Ingrediente por ID: Obtener la información de un Ingrediente específico.
   3. Agregar Ingrediente: Crear un nuevo Ingrediente.
   4. Editar Ingrediente: Modificar la información de un Ingrediente existente.
   5. Eliminar Ingrediente: Eliminar un Ingrediente del sistema.
3. Procedimientos para la gestión de categorías:
   1. Obtener categorías: Obtener una lista de todas las categorías.
   2. Obtener categoría por ID: Obtener la información de una categoría específica.
   3. Agregar categoría: Crear una nueva categoría.
   4. Editar categoría: Modificar la información de una categoría existente.
   5. Eliminar categoría: Eliminar una categoría del sistema.
4. Procedimientos para la gestión de los pasos:
   1. Obtener pasos: Obtener una lista de todas las pasos de una receta concreta.
   2. Obtener pasos por ID: Obtener la información del paso específico.
   3. Agregar paso: Crear un nuevo paso para un receta concreta.
   4. Editar paso: Modificar un nuevo paso para un receta concreta.
   5. Eliminar paso: Eliminar un paso para un receta concreta.
5. Procedimientos para la gestión de los Ingredientes por Receta:
   1. Agregar Ingredientes a una receta
   2. Editar ingrediente: Modificar un ingrediente de una receta.
   3. Eliminar ingrediente: Eliminar un  ingrediente para un receta concreta.
6. Funciones para el sistema:
   1. Función para contar cuentos ingredientes tiene un receta concreta.
7. Triggers:
   1. Trigger para actualizar la fecha de modificación de una receta.
   2. Trigger para eliminar los pasos de una receta al eliminarla
   3. Trigger para registrar el historial de cambios en una receta - Agregar la tabla con los datos idReceta, fechaCambio, columna, valorAnterior, ValorNuevo.

### Desarrollo de la aplicación
