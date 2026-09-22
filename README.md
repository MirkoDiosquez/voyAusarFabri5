# voyAusarFabri5

Ejercicio de Patrones de Diseño en Java: **Composite** + **Decorator**, aplicados a un sistema de productos.

## Contenido

```
src/
├── Producto.java       -> Component (Composite) / Component (Decorator)
├── ProductoSimple.java  -> Leaf (Composite)
├── Compuesto.java       -> Composite (Composite)
├── Decorador.java       -> Decorator abstracto (Decorator)
├── Descuento.java       -> Concrete Decorator
├── Packaging.java       -> Concrete Decorator abstracto (base de familia)
├── PackBasico.java      -> Concrete Decorator
├── PackPremium.java     -> Concrete Decorator
└── Main.java            -> Demostración de uso
```

Cómo compilar y ejecutar (desde la carpeta `src`):

```bash
javac *.java
java Main
```

---

## 1. Idea general

El sistema modela **productos** que pueden ser:

- **Simples** (`ProductoSimple`) o **compuestos por otros productos** (`Compuesto`) → patrón **Composite**.
- **Decorados** con funcionalidades extra como descuentos o packaging, sin modificar las clases originales → patrón **Decorator**.

Ambos patrones comparten la misma abstracción base: `Producto`. Esto es lo que permite combinarlos: un `Decorador` puede envolver tanto a un `ProductoSimple` como a un `Compuesto`, y a su vez el resultado sigue siendo un `Producto`, por lo que puede volver a decorarse o incluso agregarse dentro de otro `Compuesto`.

---

## 2. Patrón Composite

### `Producto` (Component)

Clase abstracta que define el "contrato" común a todos los productos:

- Atributos: `nombre`, `precio`, `stock`.
- Métodos: `getNombre()`, `getPrecio()`, `getStock()` (con implementación por defecto basada en los atributos) y `describir()` (abstracto, cada subclase decide cómo mostrarse).

Es abstracta (no interface) porque necesita **guardar estado** (los tres atributos) y **compartir comportamiento por defecto** (los getters), evitando así duplicar código en `ProductoSimple`.

### `ProductoSimple` (Leaf)

Representa la unidad más chica del árbol: un producto individual (ej: "Teclado mecánico"). No contiene ninguna colección; simplemente hereda los atributos de `Producto` y define su propia forma de `describir()`.

### `Compuesto` (Composite)

Representa un producto formado por otros productos (ej: un combo, un kit, una PC completa). Internamente mantiene:

```java
private final List<Producto> productos = new ArrayList<>();
```

Esta lista puede contener tanto `ProductoSimple` como otros `Compuesto`, lo que permite construir un **árbol de profundidad arbitraria**.

- `agregar(Producto)` / `quitar(Producto)`: administran la colección.
- `getPrecio()` y `getStock()`: **no** usan campos propios, sino que **recorren recursivamente** la lista y suman el resultado de cada hijo (que a su vez puede ser otro `Compuesto`, disparando la recursión hacia abajo hasta llegar a las hojas).
- `describir()`: arma un texto jerárquico (indentado) mostrando cada nivel del árbol.

**Flujo de datos en Composite:** cuando se llama `pcCompleta.getPrecio()`, el `Compuesto` no conoce ni le importa si sus hijos son simples o compuestos: solo les pide `getPrecio()` a cada uno (polimorfismo) y suma. Si un hijo es a la vez un `Compuesto`, este repite el mismo proceso con sus propios hijos. Así, el cálculo se propaga de las hojas hacia la raíz del árbol sin que ningún nivel necesite saber la estructura interna del siguiente.

---

## 3. Patrón Decorator

### `Decorador` (Decorator abstracto)

Extiende `Producto` (por eso puede sustituir a cualquier producto) y mantiene una referencia al producto que envuelve:

```java
protected Producto producto;
```

Por defecto, **delega** todas las operaciones (`getNombre()`, `getPrecio()`, `getStock()`, `describir()`) al producto envuelto. Esto evita que cada decorador concreto tenga que reescribir la delegación básica: solo sobrescribe lo que realmente necesita modificar.

### `Descuento` (Concrete Decorator)

Agrega un `porcentaje` de descuento. Sobrescribe:

- `getPrecio()`: toma el precio del producto envuelto y le resta el porcentaje.
- `getNombre()` y `describir()`: agregan la información del descuento al texto/nombre original, sin perder la descripción del producto interno.

### `Packaging` (Concrete Decorator abstracto)

Clase abstracta intermedia que agrupa el comportamiento común a **cualquier tipo de empaquetado**: suma un costo fijo (`getCostoPackaging()`) al precio del producto envuelto y arma el texto descriptivo reutilizando `getNombrePackaging()`. Evita duplicar la lógica de "sumar costo y describir" en cada tipo de packaging.

### `PackBasico` y `PackPremium` (Concrete Decorators)

Solo definen el costo (`5.0` y `15.0` respectivamente) y el nombre de su packaging. Toda la lógica de cálculo y descripción la heredan de `Packaging`.

**Flujo de datos en Decorator:** cada decorador envuelve a un `Producto` (que puede ser otro decorador). Al pedir `getPrecio()` al decorador más externo, este calcula su propio "extra" (descuento o costo de packaging) y le pide `getPrecio()` al `producto` que tiene adentro. Esa llamada se repite en cadena hacia adentro hasta llegar al `ProductoSimple` o `Compuesto` original, que devuelve el valor base. El resultado final "burbujea" hacia afuera, aplicando cada capa de decoración en el orden en que fue envuelta (de adentro hacia afuera).

---

## 4. Cómo se combinan Composite y Decorator

Como `Decorador` **es-un** `Producto`, y `Compuesto`/`ProductoSimple` también son `Producto`, cualquier decorador puede envolver indistintamente a cualquiera de los tres. Esto permite, sin crear ninguna clase nueva:

- Decorar una hoja: `new Descuento(new ProductoSimple(...), 10)`
- Decorar un árbol completo: `new PackPremium(compuesto)`
- Encadenar varios decoradores: `new PackPremium(new Descuento(producto, 15))`
- Meter un producto ya decorado dentro de un `Compuesto`, y luego decorar ese `Compuesto` de nuevo.

Todo esto se resuelve por **polimorfismo puro** (todo es un `Producto`), sin `instanceof` ni condicionales gigantes.

---

## 5. Qué demuestra `Main.java`

| Paso | Código | Patrón demostrado |
|---|---|---|
| 1 | `new ProductoSimple(...)` | Composite – Leaf |
| 2 | `Compuesto` con varios `ProductoSimple` agregados | Composite – Composite con hijos hoja |
| 3 | Un `Compuesto` que contiene a otro `Compuesto` | Composite – árbol recursivo (Composite dentro de Composite) |
| 4 | `new Descuento(teclado, 10)` | Decorator – Concrete Decorator sobre una hoja |
| 5 | `new PackBasico(mouse)` | Decorator – Concrete Decorator (familia Packaging) |
| 6 | `new PackPremium(monitor)` | Decorator – otro Concrete Decorator de la misma familia |
| 7 | `new PackPremium(new Descuento(teclado, 15))` | Decorator – encadenamiento de decoradores |
| 8 | `new PackBasico(new Descuento(webcam, 5))` y `new Descuento(new PackPremium(pcCompleta), 20)` | Composite + Decorator combinados: se decora tanto un `ProductoSimple` como un `Compuesto` completo |

Cada `describir()` muestra en cascada cómo se fue construyendo el resultado (estructura del árbol y capas de decoración aplicadas), evidenciando que ambos patrones funcionan sobre la misma abstracción `Producto` sin necesidad de clases combinadas como "ProductoConDescuento" o similares.
