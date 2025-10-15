# API REST - Prueba técnica para InteliData:

## Endpoint
http://localhost:8080
 
### POST /users
Registra un nuevo usuario.

**Request:**
```json
{
  "id": 1,
  "name": "Juan Pérez",
  "email": "juan.perez@example.com"
}
```

### GET /users
Obtiene la lista de usuarios registrados.

### DELETE /users
Elimina todos los usuario registrados.

## Validaciones
- **ID único**: No permite IDs duplicados
- **Email único**: No permite emails duplicados (case insensitive)
