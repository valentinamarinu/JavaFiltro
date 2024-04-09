package entity;

public class Contratacion {
    private int id;
    private String fecha_aplicacion;
    private String estado;
    private Double salario;
    private int id_vacante;
    private int id_coder;
    private Vacante objVacante;
    private Coder objCoder;

    public Contratacion() {
    }

    public Contratacion(String estado, Double salario, int id_vacante, int id_coder) {
        this.estado = estado;
        this.salario = salario;
        this.id_vacante = id_vacante;
        this.id_coder = id_coder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(String fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public int getId_vacante() {
        return id_vacante;
    }

    public void setId_vacante(int id_vacante) {
        this.id_vacante = id_vacante;
    }

    public int getId_coder() {
        return id_coder;
    }

    public void setId_coder(int id_coder) {
        this.id_coder = id_coder;
    }

    public Vacante getObjVacante() {
        return objVacante;
    }

    public void setObjVacante(Vacante objVacante) {
        this.objVacante = objVacante;
    }

    public Coder getObjCoder() {
        return objCoder;
    }

    public void setObjCoder(Coder objCoder) {
        this.objCoder = objCoder;
    }

    @Override
    public String toString() {
        return
                "Salario:" + salario;
    }
}
