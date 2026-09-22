import java.util.ArrayList;
import java.util.List;

/**
 * COMPOSITE - Composite
 *
 * Representa un producto compuesto por otros productos, que a su vez
 * pueden ser ProductoSimple u otros Compuesto (estructura de árbol).
 *
 * El precio y el stock se calculan recursivamente sumando los valores
 * de todos los productos contenidos.
 */
public class Compuesto extends Producto {

    private final List<Producto> productos = new ArrayList<>();

    public Compuesto(String nombre) {
        super(nombre, 0, 0);
    }

    public void agregar(Producto producto) {
        productos.add(producto);
    }

    public void quitar(Producto producto) {
        productos.remove(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public double getPrecio() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        return total;
    }

    @Override
    public int getStock() {
        int total = 0;
        for (Producto producto : productos) {
            total += producto.getStock();
        }
        return total;
    }

    @Override
    public String describir() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Compuesto: %s (precio total: $%.2f, stock total: %d)",
                nombre, getPrecio(), getStock()));
        for (Producto producto : productos) {
            String descripcionHijo = producto.describir().replace("\n", "\n    ");
            sb.append("\n  -> ").append(descripcionHijo);
        }
        return sb.toString();
    }
}
