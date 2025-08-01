Pasos para la configuración inicial:

1. Crear la base de datos:
  
  Abre MySQL Workbench.
  
  Ejecuta el archivo gestionacademica.sql para crear la base de datos.
  
  ⚠️ Es normal que aparezcan errores en esta etapa. Esto sucede porque el archivo intenta insertar registros en tablas que aún no existen.
  
2. Crear las tablas automáticamente:
  
  Ejecuta la aplicación desde el método main de la clase Main.
  
  Hibernate se encargará de crear las tablas necesarias en la base de datos.
  
  Asegúrate de haber configurado correctamente la conexión en el archivo persistence.xml.
  
3. Insertar los registros:
  
  Una vez creadas las tablas, vuelve a ejecutar el archivo gestionacademica.sql.
  
  Esta vez no se generarán errores, y se insertarán correctamente los registros de prueba.

4. Terminado.

  Ahora puedes volver a ejecutar la aplicación desde el main y utilizar todas las funcionalidades sin problemas.
