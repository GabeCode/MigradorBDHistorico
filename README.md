# MigradorBDHistorico💾📆
Migración de data historica de un Schema a otro en MySQL, estando conectados en el mismo servidor. 

## Comenzando🚀
El proyecto es una aplicación de consola que se empaqueta en un archivo .jar creado con la version 1.8 de JAVA.<br>
Es de tener en cuenta que la aplicacion fue creada para sistema linux entonces todos los archivos necesarios 
para ejecutar la aplicación debera estar en la raiz bajo la siguiente ruta **"/migrador/"**.

Para poder ejecurar la aplicacion en windows se debe cambiar la ruta de la siguente manera **"C:\migrador\File.extension"**.
Contaremos con un archivo **config.properties** que contendrá los datos de las conexiones del origen del servidor MySQL.
Luego esta el archivo **script.txt** donde estaran los INSERT y consultas donde el where tiene que ser un campo que almacene el año que se insertó el registro.

## Archivos Necesario📂
Los archivos necesarios estan en la carpeta migración, dentro de este proyecto.

**💻 with ❤️ by [GabeCode](https://github.com/GabeCode "GabeCode")**
