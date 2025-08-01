package org.example;

import jakarta.persistence.EntityManager;
import org.example.service.ReporteService;
import org.example.util.InicioSesion;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) throws Exception {
            EntityManager em = InicioSesion.getEntityManager();
            ReporteService service = new ReporteService(em);

            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("MENÚ DE REPORTES");
                System.out.println("1. Cursos con inscripciones y promedio de créditos");
                System.out.println("2. Estudiantes con más de 2 cursos");
                System.out.println("3. Buscar inscripciones por filtros");
                System.out.println("4. Carga académica de profesores");
                System.out.print("Seleccione: ");
                int op = sc.nextInt();
                sc.nextLine();

                switch (op) {
                    case 1 -> service.listarCursosConEstadisticas();
                    case 2 -> service.estudiantesConMasDe2Cursos();
                    default -> System.out.println("Opción no válida");
                }

            } catch (Exception e) {
                System.err.println("Error al ejecutar consulta: " + e.getMessage());
            } finally {
                em.close();
            }
        }
}