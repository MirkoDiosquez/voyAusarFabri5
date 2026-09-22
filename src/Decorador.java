/**
 * DECORATOR - Decorator (abstracto)
 *
 * Clase abstracta que envuelve un Producto y delega en él las
 * operaciones por defecto. Al heredar de Producto, un Decorador puede
 * ser tratado exactamente igual que cualquier otro producto, y a su vez
 * puede envolver a otro Decorador, permitiendo encadenar comportamientos.
 */
public abstract class Decorador extends Producto {

    protected Producto producto;

    protected Decorador(Producto producto) {
        super(producto.getNombre(), producto.getPrecio(), producto.getStock());
        this.producto = producto;
    }

    @Override
    public String getNombre() {
        return producto.getNombre();
    }

    @Override
    public double getPrecio() {
        return producto.getPrecio();
    }

    @Override
    public int getStock() {
        return producto.getStock();
    }

    @Override
    public String describir() {
        return producto.describir();
    }
}
