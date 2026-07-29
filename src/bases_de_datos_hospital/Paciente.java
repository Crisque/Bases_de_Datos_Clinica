/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bases_de_datos_hospital;


public class Paciente {
   private int id;
   private String nombre;
   private int edad;
   private String dni;
   private String diagnostico;

    public Paciente() {
    }

    public Paciente(String nombre, String dni, int edad, String diagnostico) {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.diagnostico = diagnostico;
    }

    public Paciente(int id, String nombre, int edad, String dni, String diagnostico) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
        this.diagnostico = diagnostico;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDni() {
        return dni;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
   
   
}
