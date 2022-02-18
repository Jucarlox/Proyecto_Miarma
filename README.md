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


### Historia de Usuario: PUT profile/me

2. Selecionar json editar perfil (El nick y email no podran ser cambiados)
3. Selecionar una nueva imagen o dejarla vacia si no la quieres cambiar

### Historia de Usuario: POST /post

1. Selecionar en file una imagen o video Mp4 (Si selecionas otro tipo de archivo saltara una excepcion)
2. Selecionar en use un json para crear un usuario 

### Historia de Usuario: PUT /post/{id}

1. Selecionar el id del post
2. Selecionar json de editar post
3. Selecionar una nueva imagen, video o dejarla vacia si no la quieres cambiar


### Historia de Usuario: DELETE /post/{id}

1. Selecionar el id del post


### Historia de Usuario: GET /post/public

1. Hazla la peticion

### Historia de Usuario: GET post/{id}

Caso 1:
Publicacion Publica
1. El post lo podran ver todos
2. Hacer la peticion

Caso 2:
Publicacion Privada
1. Crear 2 Usuarios
2. Usuario1 manda solicitud a Usuario2
3. Usuario2 acepta solicitud y crea un post privado
4. Usuario1 hace la peticion para ver el post privado















