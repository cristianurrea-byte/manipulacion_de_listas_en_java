# Pizza-Track

Sistema de gestión de pedidos de una pizzería, implementado en consola con Java. Permite registrar pedidos y deshacer/rehacer la última acción usando **dos pilas manuales** construidas desde cero con listas ligadas (sin `java.util.Stack`).

## 🎯 Objetivo

Simular el flujo de pedidos de una pizzería aplicando la estructura de datos Pila (LIFO), implementada manualmente, para dar soporte a las operaciones de **Registrar**, **Deshacer (Undo)** y **Rehacer (Redo)** sobre los pedidos.

## 🗂️ Estructura del proyecto

```
PizzaTrack/
└── src/
    ├── Pizza.java           -> modelo de datos: nombre + 3 ingredientes (arreglo fijo)
    ├── GestionPedidos.java  -> clase Pila (LIFO manual) + clase GestionPedidos (coordina las 2 pilas: Undo/Redo)
    └── Principal.java       -> menú interactivo por consola (punto de entrada, main)
```

## ⚙️ Cómo ejecutar

1. Abrir la carpeta `PizzaTrack` en VS Code con el JDK de Eclipse Temurin instalado.
2. Compilar y ejecutar `Principal.java` (botón ▶ *Run* de VS Code, o desde terminal):
   ```
   cd src
   javac *.java
   java Principal
   ```
3. Usar el menú:
   ```
   1. Registrar Pizza
   2. Deshacer (Undo)
   3. Rehacer (Redo)
   4. Mostrar Pedido Actual
   0. Salir
   ```

## 🔁 Ejemplo de uso (ciclo Registro → Deshacer → Rehacer)

| Acción | Pila Principal (activos) | Pila Secundaria (deshechos) |
|---|---|---|
| Registrar Margarita | Margarita | (vacía) |
| Registrar Hawaiana | Hawaiana → Margarita | (vacía) |
| Registrar Pepperoni | Pepperoni → Hawaiana → Margarita | (vacía) |
| **Deshacer** | Hawaiana → Margarita | Pepperoni |
| **Rehacer** | Pepperoni → Hawaiana → Margarita | (vacía) |

## 📸 Capturas de pantalla

_(Agregar aquí capturas de la consola mostrando el registro, el undo y el redo)_

## 🎥 Video de sustentación

Link: _(pegar aquí el link de YouTube / Drive / GitHub)_

## 👤 Autor

_(Nombre del estudiante)_
