Descripción general del proyecto: Sistema de Control Escolar Universitario
Descripción del proyecto

El proyecto consiste en desarrollar un pequeño sistema en Java para representar algunas de las actividades del área de Control Escolar de una universidad.
El programa permitirá identificar y administrar tres tipos de integrantes de la comunidad universitaria:
1. Estudiantes.
2. Docentes.
3. Personal administrativo.

Para desarrollar el sistema se utilizará una clase principal o clase padre, denominada PersonaUniversitaria, y tres clases secundarias o clases hijas, que heredarán sus atributos y métodos.

El objetivo es comprender cómo se aplican los cuatro principios y elementos fundamentales solicitados en la Programación Orientada a Objetos:
* Herencia: permite reutilizar los atributos y métodos de una clase padre en otras clases.
* Polimorfismo: permite que diferentes objetos respondan de manera distinta cuando se utiliza un mismo método.
* Encapsulamiento: protege la información mediante atributos privados y permite acceder a ella de manera controlada mediante métodos get y set.
* Tipos de datos y colecciones: permite trabajar con diferentes tipos de información y organizar varios objetos mediante estructuras disponibles en Java.

Clase padre: PersonaUniversitaria
Esta clase representa la estructura general de cualquier integrante de la comunidad universitaria.
Su función principal es concentrar los atributos y métodos que comparten estudiantes, docentes y personal administrativo, evitando tener que escribir la misma información en cada clase.
Por ejemplo, todas las personas que forman parte de la universidad tienen datos generales como nombre, edad, identificador y otros datos personales.
Para proteger esta información, se utilizan atributos privados (private). Su consulta o modificación se realiza mediante métodos conocidos como getters y setters, lo que permite controlar el acceso a los datos.
Además, en esta clase se incorporan los ocho tipos de datos primitivos de Java y sus respectivas clases envolventes (Wrapper), con la finalidad de identificar las diferencias entre ambos y comprender cómo se utilizan dentro de un programa.

Primera clase hija: Estudiante
Esta clase representa a los estudiantes de la universidad.
Hereda los atributos y métodos generales de PersonaUniversitaria y agrega información específica relacionada con su actividad académica, como el nombre de la carrera y el número de créditos cursados.
De esta manera, un estudiante cuenta con los datos generales de cualquier integrante de la universidad, pero también con características propias de su condición académica.

Segunda clase hija: Docente
Esta clase representa al personal académico de la universidad.
Hereda la información general de PersonaUniversitaria e incorpora atributos y métodos particulares relacionados con las actividades de enseñanza, como la asignatura que imparte y las horas de clase asignadas.
Así, se demuestra que una clase hija puede reutilizar las características de la clase padre y, al mismo tiempo, incorporar información y comportamientos propios.

Tercera clase hija: Administrativo
Esta clase representa al personal que realiza actividades administrativas dentro de la universidad, particularmente en el área de Control Escolar.
Al igual que las clases anteriores, hereda los atributos y métodos de PersonaUniversitaria y agrega información específica de sus funciones, como el departamento al que pertenece y el puesto que desempeña.

Clase principal: Main

La clase Main es el punto de inicio del programa. Desde aquí se crean los objetos correspondientes a estudiantes, docentes y personal administrativo, y se ejecutan los métodos definidos en las diferentes clases.
En esta clase se demuestra principalmente el concepto de polimorfismo, mediante la creación de un arreglo cuyo tipo corresponde a la clase padre, pero que almacena objetos de las tres clases hijas.

Por ejemplo:
PersonaUniversitaria[ ] comunidad = {

    estudiante1,
    estudiante2,
    docente1,
    docente2,
    administrativo1
};

Con es código que podemos reunir diferentes tipos de personas en una sola estructura, utilizando como referencia la clase padre PersonaUniversitaria.
Aunque todos los elementos del arreglo se reconocen mediante el tipo de la clase padre, cada objeto conserva su naturaleza y comportamiento original, porque fue creado a partir de una clase hija.
Posteriormente, mediante un ciclo for, se pueden recorrer todos los elementos del arreglo y ejecutar un mismo método. Java identificará qué versión de ese método debe ejecutar, dependiendo de la clase a la que pertenece cada objeto.
Esto es lo que permite demostrar el polimorfismo.

Este proyecto permite comprender cómo se aplican los principios de la Programación Orientada a Objetos mediante un ejemplo relacionado con una situación real: el funcionamiento de un área de 
