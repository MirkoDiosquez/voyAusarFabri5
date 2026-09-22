/**
 * COMPOSITE - Leaf
 *
 * Representa un producto individual (hoja del árbol de Composite).
 * No contiene una colección de productos: es la unidad más básica
 * del sistema.
 */
public class ProductoSimple extends Producto {

    public ProductoSimple(String nombre, double precio, int stock) {
        super(nombre, precio, stock);
    }

    @Override
    public String describir() {
        return String.format("Producto simple: %s (precio: $%.2f, stock: %d)", nombre, precio, stock);
    }
}
