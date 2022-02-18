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

1. Crear usuario
2. Logearte
3. Crear post publico
4. Hacer la peticion

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

### Historia de Usuario: GET /post/me

1. Crear usuario
2. Logearte
3. Crear post publicos o privados
4. Hacer la peticion

### Historia de Usuario: GET /post/?nick=

Caso 1:
Perfil Publico
1. Crear usuario Publico
2. Hacer la peticion

Caso 2:
Perfil Privado
1. Crear usuario Privado y otro cualquiera
2. Usuario2 manda solicitud
3. UsuarioPrivado acepta la solicitud
4. UsuarioPrivado crea post
5. Usuario2 se logea y hace la peticion con el nick del UsuarioPrivado

### Historia de Usuario: POST /follow/{nick}

1. Crear dos Usuarios
2. Logearte con Usuario1
3. Hacer la peticion con el nick de Usuario2

### Historia de Usuario: POST /follow/accept/{id}

1. Mandar solicitud
2. Logearte con el usuario que le llego la peticion
3. Aceptar la solicitud con el id de la peticion que se envio (si no te acuerdas puedes ver todas las solicitudes con la peticion "/follow/list")

### Historia de Usuario: DELETE /follow/decline/1

1. Mandar solicitud
2. Logearte con el usuario que le llego la peticion
3. Eliminar la solicitud con el id de la peticion que se envio (si no te acuerdas puedes ver todas las solicitudes con la peticion "/follow/list")


### Historia de Usuario: GET /follow/list

1. Mandar solicitud
2. Logearte con el usuario que le llego la peticion
3. Hacer la peticion con el usuario al que se le mando la solicitud

















