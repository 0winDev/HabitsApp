# Habit Tracker App

Offline-First Habit Tracking App para Android, desarrollada en **Kotlin**, diseñada para ayudar a los usuarios a mantener sus hábitos diarios mediante recordatorios y seguimiento automático.  
El proyecto sigue **Clean Architecture** y principios **SOLID**.



## 🛠 Tech Stack


- **Clean Architecture:** Separación de responsabilidades y mantenibilidad del código.  
- **SOLID:** Código limpio, modular y extensible.  
- **Kotlin**  
- **Jetpack Compose:** UI flexible y dinámica.  
- **Dagger-Hilt:** Inyección de dependencias para escalabilidad y pruebas unitarias.  
- **Firebase Authentication:** Autenticación segura de usuarios.  
- **Room:** Persistencia local rápida y eficiente.  
- **Retrofit:** Sincronización con API remota.  
- **AlarmManager:** Programación de notificaciones.  
- **Broadcast Receivers:** Gestionan alarmas; se activan al reiniciar el dispositivo o cuando suena una alarma para programar la siguiente.  
- **WorkManager:** Tareas en segundo plano y sincronización offline.  
- **Unit Tests + UI Tests:** Garantía de calidad de los componentes clave. 



## Features

- Autenticación con Login y Registro  
- Seguimiento de hábitos diarios con recordatorios personalizables  
- Crear, actualizar y eliminar hábitos  
- Notificaciones programadas para cada hábito  
- Sincronización offline-first: los hábitos se guardan localmente y se sincronizan cuando hay conexión  
- Persistencia local con Room  
- Pruebas unitarias y de UI para componentes clave  
