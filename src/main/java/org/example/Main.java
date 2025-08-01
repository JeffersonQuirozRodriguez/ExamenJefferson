package org.example;

import jakarta.persistence.EntityManager;
import org.example.entity.EstadoInscripcion;
import org.example.service.ReporteService;
import org.example.util.InicioSesion;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) throws Exception {
            EntityManager em = InicioSesion.getEntityManager();
            ReporteService service = new ReporteService(em);

            try {
                Scanner sc = new Scanner(System.in);
                int op;

                do {
                    System.out.println("\n______________________________________________________________");
                    System.out.println("\nMENÚ DE REPORTES");
                    System.out.println("1. Cursos con inscripciones y promedio de créditos");
                    System.out.println("2. Estudiantes con más de 2 cursos");
                    System.out.println("3. Buscar inscripciones por filtros");
                    System.out.println("4. Carga académica de profesores");
                    System.out.println("0. Salir");
                    System.out.print("Seleccione: ");

                    while (!sc.hasNextInt()) {
                        System.out.println("Debe ingresar un número.");
                        sc.next();
                        System.out.print("Seleccione: ");
                    }

                    op = sc.nextInt();
                    sc.nextLine();

                    switch (op) {
                        case 1 -> service.listarCursosConEstadisticas();
                        case 2 -> service.estudiantesConMasDe2Cursos();
                        case 3 -> {
                            try {
                                System.out.print("Desde (año-mes-dia) (Ejemplo: 2020-04-04): ");
                                LocalDate desde = LocalDate.parse(sc.nextLine());
                                System.out.print("Hasta (año-mes-dia) (Ejemplo: 2025-09-09): ");
                                LocalDate hasta = LocalDate.parse(sc.nextLine());
                                System.out.print("Estado (PENDIENTE, CONFIRMADA, CANCELADA): ");
                                EstadoInscripcion estado = EstadoInscripcion.valueOf(sc.nextLine().toUpperCase());
                                System.out.print("Nombre estudiante (opcional): ");
                                String nom = sc.nextLine();
                                System.out.print("Código curso (opcional): ");
                                String cod = sc.nextLine();
                                service.buscarInscripciones(desde, hasta, estado, nom, cod);
                            } catch (Exception ex) {
                                System.err.println("Error en los filtros: " + ex.getMessage());
                            }
                        }
                        case 4 -> service.cargaAcademicaPorProfesor();
                        case 0 -> System.out.println("Saliendo del sistema...");
                        default -> System.out.println("Opción no válida");
                    }

                } while (op != 0);

            } catch (Exception e) {
                System.err.println("Error al ejecutar consulta: " + e.getMessage());
            } finally {
                em.close();
            }
        }
}