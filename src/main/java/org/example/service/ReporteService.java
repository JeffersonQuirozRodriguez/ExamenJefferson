package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entity.EstadoInscripcion;
import org.example.entity.Inscripcion;
import org.example.util.InicioSesion;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
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
    // c) Filtros dinámicos
    public void buscarInscripciones(LocalDate desde, LocalDate hasta, EstadoInscripcion estado,
                                    String nombreEstudiante, String codigoCurso) {

        StringBuilder sb = new StringBuilder("SELECT i FROM Inscripcion i WHERE i.fechaInscripcion BETWEEN :desde AND :hasta");

        if (estado != null)
            sb.append(" AND i.estado = :estado");
        if (nombreEstudiante != null && !nombreEstudiante.isEmpty())
            sb.append(" AND LOWER(i.estudiante.nombre) LIKE :nombre");
        if (codigoCurso != null && !codigoCurso.isEmpty())
            sb.append(" AND LOWER(i.curso.codigo) LIKE :codigo");

        sb.append(" ORDER BY i.fechaInscripcion DESC");

        TypedQuery<Inscripcion> query = em.createQuery(sb.toString(), Inscripcion.class);
        query.setParameter("desde", java.sql.Date.valueOf(desde));
        query.setParameter("hasta", java.sql.Date.valueOf(hasta));

        if (estado != null)
            query.setParameter("estado", estado);
        if (nombreEstudiante != null && !nombreEstudiante.isEmpty())
            query.setParameter("nombre", "%" + nombreEstudiante.toLowerCase() + "%");
        if (codigoCurso != null && !codigoCurso.isEmpty())
            query.setParameter("codigo", "%" + codigoCurso.toLowerCase() + "%");

        List<Inscripcion> resultados = query.getResultList();

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron inscripciones con los filtros.");
        } else {
            System.out.println("Inscripciones encontradas:");
            for (Inscripcion i : resultados) {
                System.out.println("- Estudiante: " + i.getEstudiante().getNombre() +
                        " | Curso: " + i.getCurso().getNombre() +
                        " | Estado: " + i.getEstado() +
                        " | Fecha: " + i.getFechaInscripcion());
            }
        }
    }

}