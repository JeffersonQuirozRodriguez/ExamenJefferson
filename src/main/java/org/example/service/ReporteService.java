package org.example.service;

import jakarta.persistence.EntityManager;
import org.example.entity.EstadoInscripcion;
import org.example.entity.Inscripcion;
import org.example.util.InicioSesion;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class ReporteService {
    private final EntityManager em;

    public ReporteService(EntityManager em) {
        this.em = em;
    }

    // a) JOIN y Agregación
    public void listarCursosConEstadisticas() {
        String jpql = "SELECT c.nombre, COUNT(i.id), AVG(c.creditos) " +
                "FROM Curso c JOIN c.inscripciones i " +
                "GROUP BY c.id, c.nombre " +
                "HAVING COUNT(i.id) >= 2";

        List<Object[]> resultados = em.createQuery(jpql, Object[].class).getResultList();

        if (resultados.isEmpty()) {
            System.out.println("No hay cursos con al menos 2 inscripciones.");
        } else {
            System.out.println("Cursos con inscripciones >= a 2 y promedio de créditos:");
            for (Object[] r : resultados) {
                System.out.println("- Curso: " + r[0] + " | Inscripciones: " + r[1] + " | Prom. de Créditos: " + r[2]);
            }
        }
    }
    // b) Consulta con subconsulta
    public void estudiantesConMasDe2Cursos() {
        String jpql = "SELECT e.nombre, e.email, COUNT(i.id) " +
                "FROM Estudiante e JOIN e.inscripciones i " +
                "GROUP BY e.id, e.nombre, e.email " +
                "HAVING COUNT(DISTINCT i.curso.id) > 2";

        List<Object[]> resultados = em.createQuery(jpql, Object[].class).getResultList();

        if (resultados.isEmpty()) {
            System.out.println("No hay estudiantes con más de 2 cursos.");
        } else {
            System.out.println("Estudiantes con más de 2 cursos:");
            for (Object[] r : resultados) {
                System.out.println("- " + r[0] + " | Email: " + r[1] + " | Cursos: " + r[2]);
            }
        }
    }

}