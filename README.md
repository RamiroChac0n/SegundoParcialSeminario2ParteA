# Himnario — Módulo de Consumo de API
 
**Segundo Parcial · Seminario II · Grupo 3**
 
---
 
## Integrantes
 
| Nombre | Carné |
|---|---|
| Ramiro | 201940859 |
| Jeysson | 201940572 |
 
---
 
## Funcionalidades Principales
 
- **Listado dinámico** de himnos cargado desde repositorio de datos
- **Indicador de carga** mientras se obtienen los registros
- **Manejo de errores** con mensaje descriptivo y botón "Reintentar"
- **Refresco manual** deslizando hacia abajo (pull-to-refresh)
- **Caché en memoria** para evitar recargas innecesarias
- **Pantalla de detalle** con cifrado, letra e indicador de video disponible
- **Navegación** entre listado y detalle sin librerías externas
---
 
## API Utilizada
 
El módulo trabaja con el siguiente contrato de API definido por el proyecto:
 
```
GET /registros          → Obtiene todos los himnos
GET /registros/{id}     → Obtiene el detalle de un himno específico
```
 
> Actualmente la app usa datos simulados (`MockRegistroData`) que replican
> exactamente la estructura que devolvería la API real. Ver sección de integración.
 
---
 
## Conceptos Técnicos Clave
 
### Jetpack Compose
Framework declarativo de UI de Android. En lugar de manipular vistas XML manualmente, se describe *cómo debe verse la pantalla según el estado actual*. Cuando el estado cambia, Compose actualiza automáticamente solo los elementos afectados.
 
### ViewModel
Componente que sobrevive a cambios de configuración como la rotación de pantalla. Contiene toda la lógica de presentación y expone el estado a la UI a través de `StateFlow`. La UI nunca toma decisiones de negocio directamente.
 
### Repository Pattern
Capa que abstrae el origen de los datos. El `ViewModel` no sabe si los datos vienen de una API, de una base de datos local o de datos simulados — simplemente llama al repositorio. Esto hace que cambiar la fuente de datos no afecte al resto de la app.
 
### Retrofit *(preparado para integración)*
Librería para consumir APIs REST en Android. Permite definir los endpoints como interfaces Kotlin con anotaciones (`@GET`, `@POST`, etc.) y convierte automáticamente el JSON de la respuesta en objetos Kotlin.
 
### Room *(mejora futura)*
Base de datos local sobre SQLite para Android. Permitiría guardar los himnos en el dispositivo y acceder a ellos sin conexión a internet, implementando una estrategia *offline-first*.
 
---
 
## Cómo Integrar la API Real (en lugar de Mocks)
 
El diseño del proyecto permite conectar la API real **modificando únicamente el repositorio**, sin tocar el ViewModel ni la UI.
 
**Paso 1 — Agregar dependencias en `build.gradle.kts`:**
```kotlin
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
```
 
**Paso 2 — Agregar permiso de internet en `AndroidManifest.xml`:**
```xml
<uses-permission android:name="android.permission.INTERNET" />
```
 
**Paso 3 — Crear `data/RegistroApi.kt`:**
```kotlin
interface RegistroApi {
    @GET("registros")
    suspend fun getRegistros(): List<Registro>
 
    @GET("registros/{id}")
    suspend fun getRegistroPorId(@Path("id") id: Int): Registro
}
```
 
**Paso 4 — Crear `data/RetrofitClient.kt`:**
```kotlin
object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8000/"
 
    val api: RegistroApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RegistroApi::class.java)
    }
}
```
 
**Paso 5 — Actualizar `RegistroRepository.kt`:**
```kotlin
suspend fun obtenerRegistros(forzarRecarga: Boolean = false): List<Registro> {
    if (!forzarRecarga && cache != null) return cache!!
    return try {
        val datos = RetrofitClient.api.getRegistros() // ← API real
        cache = datos
        datos
    } catch (e: Exception) {
        MockRegistroData.registros // ← Fallback a mock si falla la red
    }
}
```
 
> El `ViewModel` y todas las pantallas no requieren ningún cambio.
 
---
