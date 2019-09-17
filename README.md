# MigradorBDHistorico v1.0
Migración de data historica de un Schema a otro en MySQL, estando conectados en el mismo servidor. 

# <h2>Comenzando🚀</h2>
El proyecto es una aplicación de consola que se empaqueta en un archivo .jar creado con la version 1.8 de JAVA<br>
Es de tener en cuenta que la aplicacion fue creada para sistema linux entonces todos los archivos necesarios 
para ejecutar la aplicación debera estar en la raiz bajo la siguiente ruta <b>"/migrador/"</b>.

Para poder ejecurar la aplicacion en windows se debe cambiar la ruta de la siguente manera <b>"C:\migrador\File.extension"</b>.
Contaremos con un archivo <b>config.properties</b> que contendrá los datos de las conexiones del origen del servidor MySQL.
Luego esta el archivo <b>script.txt</b> donde estaran los INSERT y consultas donde el where tiene que ser un campo que almacene el año que se insertó el registro.

<h2>Archivos Necesario</h2>
Los archivos necesarios estan en la carpeta migración, dentro de este proyecto.

