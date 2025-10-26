import java.util.Scanner;
import Encargado.Encargado;
import models.Departament;
import models.Informe;
import models.Regi_Nomina;
import models.Trabajador;

import java.util.ArrayList;

public class Core{
    public static void main(String[] args){//inicia el programa
        Scanner input = new Scanner(System.in);//objeto scanner para recibir datos
        
        ArrayList<String> departamentosFijos = new ArrayList<>();
        departamentosFijos.add("Sistemas");
        departamentosFijos.add("Marketing");
        departamentosFijos.add("RRHH");
        departamentosFijos.add("Finanzas");
        departamentosFijos.add("Ventas"); 
        
        //creamos los departamentos
        Departament sistemas = new Departament(500,"Sistemas",173);
        Departament marketing = new Departament(450,"Marketing",125);
        Departament RRHH = new Departament(300,"RRHH",140);  
        Departament Finanzas = new Departament(560,"Finanzas",160);
        Departament ventas = new Departament(440,"Ventas",122);

        
      //INICIO DE SESION
        
      int intentos = 5;
        System.out.println("Gestor de Nóminas Iniciado.....");
        System.out.println("Ingrese el usuario:");
        String usuario = input.nextLine(); 
        for (int i = 1; i <= intentos; ) {//bucle de iteraccion para permitir el acceso
            if (usuario.equals("ADMIN")) {
            System.out.println("Ingrese la contraseña:");
            String contraseña = input.nextLine();
            
            if (contraseña.equals("BEADMIN123")) {
              
            System.out.println("Iniciando sesión como ADMIN...");
            break;
            } 
            //control de errores de acceso
            else {
                System.out.println("Contraseña incorrecta. Acceso denegado.");
                System.out.println("Intento " + i + " de " + intentos);
                i++; 
                
            }
        } 
        
        else {
            System.out.println("Usuario no reconocido. Acceso denegado.");
            System.out.println("Intento " + i + " de " + intentos);
            i++; 
        } 
        
           
        }
        //Ingresa los datos del encargado{Recordar clase encargado}
        System.out.print("Ingrese su nombre de Encargado: ");
        String nombre = input.nextLine();//variables que recibiran los datos de Encargado
        System.out.print("Ingrese su apellido: ");
        String apellido = input.nextLine();
        System.out.print("Ingrese su edad : ");
        int edad = input.nextInt();
        input.nextLine(); //pide el dato de edad
        System.out.print("Ingrese su ID de Encargado: ");
        String id = input.nextLine();
              

        Encargado encargado = new Encargado(nombre, apellido, id, edad); //creamos un encargado
        System.out.println("Acceso concedido. Bienvenido, " + encargado.nombreCompleto() + ".");

        System.out.println("Bienvenido a NOMINATORX");
        System.out.println("---------------------");
        System.out.println("MENU DE OPCIONES:");
        System.out.println("1. Generar nomina");
        System.out.println("2. Gestionar Departamentos");

        System.out.print("Seleccione una opción: ");
        int opcion = input.nextInt();
        do {
            switch (opcion) {
                case 1:
                    System.out.println("Generando nómina...");
                    // Lógica para generar nómina
                    int c;
                    String N_Trabajador = "";
                    String A_Trabajador = "";
                    int E_Trabajador = 0;
                    String C_Trabajador = "";
                    String D_Trabajador = "";

                    // Nombre
                do { 
                    System.out.println("Escriba el nombre del trabajador:");
                    N_Trabajador = input.next();
                    System.out.println("Es correcto el nombre " + N_Trabajador + " ?  (1.SI / 0.NO )");
                    c = input.nextInt();
                    input.nextLine();
                } while (c == 0);
                   

                    // Apellido
                    do {
                        System.out.println("Escriba el Apellido del trabajador:");
                        A_Trabajador = input.next();
                        System.out.println("Es correcto el nombre " + A_Trabajador + " ?  (1.SI / 0.NO )");
                        c = input.nextInt();
                        input.nextLine();
                    } 
                    while (c == 0);

                    // Edad
                    do {
                        System.out.println("Escriba la edad del trabajador:");
                        E_Trabajador = input.nextInt();
                        input.nextLine();
                        System.out.println("Es correcta la edad " + E_Trabajador + " ?  (1.SI / 0.NO )");
                        c = input.nextInt();
                        input.nextLine();
                    } 
                    while (c == 0);

                    // Cédula
                    do {
                        System.out.println("Escriba la cedula del trabajador:");
                        C_Trabajador = input.next();
                        System.out.println("Es correcta la cedula " + C_Trabajador + " ?  (1.SI / 0.NO )");
                        c = input.nextInt();
                        input.nextLine();
                    } 
                    while (c == 0);

                    // Departamento (validado contra la lista)
                    do {
                        System.out.println("Escriba el departamento del trabajador:");
                        D_Trabajador = input.next();
                        if (departamentosFijos.contains(D_Trabajador)) {
                            System.out.println("Departamento válido.");
                        } else {
                            System.out.println("Departamento no válido. Intente de nuevo.");
                            c = 0;
                            continue;
                        }
                        System.out.println("Es correcto el departamento " + D_Trabajador + " ?  (1.SI / 0.NO )");
                        c = input.nextInt();
                        input.nextLine();
                    } while (c == 0);
                    Trabajador trabajador = new Trabajador(N_Trabajador, A_Trabajador, C_Trabajador, D_Trabajador, E_Trabajador);
                    //Luego de crear el trabajado.
                    int H_Trabajador = 0;

                    // Get the corresponding department object
                    Departament departamento = null;
                    switch(D_Trabajador) {
                        case "Sistemas": departamento = sistemas; break;
                        case "Marketing": departamento = marketing; break;
                        case "RRHH": departamento = RRHH; break;
                        case "Finanzas": departamento = Finanzas; break;
                        case "Ventas": departamento = ventas; break;
                    }

                    System.out.println("cantidad de horas trabajadas en el mes:");
                    H_Trabajador = input.nextInt();
                    input.nextLine();
                    while (H_Trabajador > departamento.geth_Totales()) {
                        System.out.println("Error: El trabajador ha excedido el límite de horas del departamento.");
                        break;
                    }
                    int salarioTotal = departamento.getSalario() * (H_Trabajador / 8);
                    System.out.println("Nomina \nTrabajador: " + trabajador.nombreCompleto() + "\nPago total: " + salarioTotal + " USD. \n Perteneciente al departamento de "+ D_Trabajador+ "\nHoras trabajadas: "+ H_Trabajador+ "\nSalario por dia: "+ departamento.getSalario()+ " USD \n Encargado de nómina: "+ encargado.nombreCompleto()+"\nID: "+ encargado.getId());
                    Informe info = new Informe(trabajador);
                    info.crearInforme();
                    
                    break;
                case 2:
                    System.out.println("Gestionando Departamentos...");
                    // Lógica para gestionar departamentos
                    System.out.println("Departamentos disponibles:");
                    for (String depto : departamentosFijos) {
                        System.out.println("- " + depto);
                    }
                    System.out.println("Salarios");//Salarios de los departamentos
                    System.out.println("Sistemas: " + sistemas.getSalario() + " USD");
                    System.out.println("Marketing: " + marketing.getSalario() + " USD");
                    System.out.println("RRHH: " + RRHH.getSalario() + " USD");
                    System.out.println("Finanzas: " + Finanzas.getSalario() + " USD");
                    System.out.println("Ventas: " + ventas.getSalario() + " USD");
                    //Limites de horas
                    System.out.println("Límites de horas mensuales:");
                    System.out.println("Sistemas: " + sistemas.geth_Totales() + " horas");
                    System.out.println("Marketing: " + marketing.geth_Totales() + " horas");
                    System.out.println("RRHH: " + RRHH.geth_Totales() + " horas");
                    System.out.println("Finanzas: " + Finanzas.geth_Totales() + " horas");
                    System.out.println("Ventas: " + ventas.geth_Totales() + " horas");
                    

                    
                    break;
                case 3:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
            }
            System.out.print("Seleccione una opción: ");
            opcion = input.nextInt();
        } while (true);
        
  }
}