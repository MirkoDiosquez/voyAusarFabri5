/**
 * COMPOSITE - Component
 *
 * Clase abstracta que representa la abstracción común para todos los
 * productos del sistema, sean simples o compuestos.
 *
 * También cumple el rol de Component en el patrón DECORATOR, ya que
 * "Decorador" también hereda de esta clase para poder envolver de forma
 * transparente a cualquier Producto (ProductoSimple, Compuesto u otro
 * Decorador).
 */
public abstract class Producto {

    protected String nombre;
    protected double precio;
    protected int stock;

    protected Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    /**
     * Devuelve una descripción textual del producto, incluyendo su
     * información relevante (nombre, precio y stock). Cada subclase la
     * completa según su comportamiento particular.
     */
    public abstract String describir();
}
