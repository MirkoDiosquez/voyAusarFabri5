/**
 * DECORATOR - Concrete Decorator (abstracto, base de una familia)
 *
 * Agrupa el comportamiento común de los distintos tipos de empaquetado.
 * Cada subclase concreta (PackBasico, PackPremium) define su propio
 * costo adicional, que se suma al precio del producto envuelto.
 */
public abstract class Packaging extends Decorador {

    protected Packaging(Producto producto) {
        super(producto);
    }

    protected abstract double getCostoPackaging();

    protected abstract String getNombrePackaging();

    @Override
    public double getPrecio() {
        return producto.getPrecio() + getCostoPackaging();
    }

    @Override
    public String getNombre() {
        return producto.getNombre() + " + " + getNombrePackaging();
    }

    @Override
    public String describir() {
        return producto.describir()
                + String.format("%n     + %s: +$%.2f", getNombrePackaging(), getCostoPackaging());
    }
}
