#FROM: nos indica la imagen base de la cual iniciaremos la nuestra.
FROM openjdk:11.0.11-jre-slim

#run se utiliza para ejecutar un comando en el monto de construir la imagen.
#se crea carpeta app (proque asi le quice llamar)
RUN mkdir /app

#copy es la directiva que se usa para copiar archivo a la imagen.
# . indica que se copie todo lo que esta en la carpeta donde esta el docker file.
#y copielo a X carpeta dentro la imagen.
COPY ./build/libs/mongoexample-0.0.1-SNAPSHOT.jar  /app/app.jar

#el entrypoint es la instruccion que se ejecuta al inicio del contenedor
ENTRYPOINT ["java", "-jar", "/app/app.jar"]






