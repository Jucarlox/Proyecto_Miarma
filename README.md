# Proyecto_Miarma
## Pasos a seguir para ver las historias de usuario en Postman:


### Historia de Usuario: POST  /auth/register

1. Selecionar en file una imagen
2. Selecionar en use un json para crear un usuario


### Historia de Usuario: POST  /auth/login

1. Rellenar campo email
2. Rellenar campo password


### Historia de Usuario: GET /me

1. Estar registrado y logeado


### Historia de Usuario: GET profile/{UUID}

Caso 1:
Perfil Publico
1. Crear usuario Publico
2. Buscarlo en la peticion Visualizar perfil

Caso 2:
Perfil Privado:
1. Crear usuario Privado
2. Crear otro usuario
3. Mandar solicitud al usuario privado
4. Aceptar solicitud desde el usuario privado
5. Cambiar al otro usuario y Buscar al usuario privado en la peticion Visualizar perfil


### Historia de Usuario: EDIT profile/me

1. Hacer los cambios que quieras en el json o no cambiar nada
2. Hacer los cambios que quieras en el file imagen o no cambiar nada y dejarlo vacio

### Historia de Usuario: POST /post

1. Selecionar en file una imagen o video Mp4 (Si selecionas otro tipo de archivo saltara una excepcion)
2. Selecionar en use un json para crear un usuario 







