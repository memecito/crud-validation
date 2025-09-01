package es.nter.crud_validation;

import es.nter.crud_validation.domain.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class DataProviders {

    public static List<Person> personListMock(){
       return List.of( new Person("prof_isabel_perez", "isabelP_2025!", "Isabel", "Pérez", "isabel.perez@email.com", "isabel.perez@school.com", "Madrid", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_javier_sanchez", "javi_S_99", "Javier", "Sánchez", "javier.sanchez.personal@email.com", "javier.sanchez@university.com", "Barcelona", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_laura_gomez", "laurita_gomez", "Laura", "Gómez", "laura.g.personal@email.com", "laura.gomez@university.com", "Valencia", true, new Timestamp(System.currentTimeMillis())),
                new Person("prof_david_martin", "davidM_prof", "David", "Martín", "david.martin@email.com", "david.martin@school.com", "Sevilla", false, new Timestamp(System.currentTimeMillis())),
                new Person("user_carmen_ruiz", "carmenRuiz_23", "Carmen", "Ruiz", "carmen.ruiz@email.com", "carmen.ruiz@university.com", "Zaragoza", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_pablo_diaz", "pablo_diaz_1", "Pablo", "Díaz", "p.diaz@email.com", "pablo.diaz@university.com", "Málaga", true, new Timestamp(System.currentTimeMillis())),
                new Person("prof_ana_moreno", "anaMoreno_2024", "Ana", "Moreno", "ana.moreno@email.com", "ana.moreno@school.com", "Murcia", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_sergio_alvarez", "sergioA_user", "Sergio", "Álvarez", "sergio.alvarez@email.com", "sergio.alvarez@university.com", "Palma", false, new Timestamp(System.currentTimeMillis())),
                new Person("user_elena_romero", "elena_romero_e", "Elena", "Romero", "elena.r@email.com", "elena.romero@university.com", "Bilbao", true, new Timestamp(System.currentTimeMillis())),
                new Person("prof_miguel_alonso", "miguelon_22", "Miguel", "Alonso", "miguel.alonso@email.com", "miguel.alonso@school.com", "Alicante", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_sofia_gutierrez", "sofi_guti", "Sofía", "Gutiérrez", "sofia.gutierrez@email.com", "sofia.gutierrez@university.com", "Córdoba", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_daniel_navarro", "daniNavarro_25", "Daniel", "Navarro", "daniel.n@email.com", "daniel.navarro@university.com", "Valladolid", true, new Timestamp(System.currentTimeMillis())),
                new Person("prof_lucia_torres", "lucia_torres_t", "Lucía", "Torres", "lucia.t@email.com", "lucia.torres@school.com", "Vigo", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_hugo_dominguez", "hugo_dom_user", "Hugo", "Domínguez", "hugo.dominguez@email.com", "hugo.dominguez@university.com", "Gijón", false, new Timestamp(System.currentTimeMillis())),
                new Person("user_martina_vazquez", "martinaV_2025", "Martina", "Vázquez", "martina.vazquez@email.com", "martina.vazquez@university.com", "Granada", true, new Timestamp(System.currentTimeMillis())),
                new Person("prof_alvaro_ramos", "alvaroR_prof", "Álvaro", "Ramos", "alvaro.ramos@email.com", "alvaro.ramos@school.com", "A Coruña", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_julia_castro", "julia_castro_j", "Julia", "Castro", "julia.c@email.com", "julia.castro@university.com", "Vitoria", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_adrian_ortega", "adrian_ortega_24", "Adrián", "Ortega", "adrian.ortega@email.com", "adrian.ortega@university.com", "Elche", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_mateo_serrano", "mateoSerrano", "Mateo", "Serrano", "mateo.serrano@email.com", "mateo.serrano@university.com", "Pamplona", true, new Timestamp(System.currentTimeMillis())));
    }

    public static Page<Person> pagePersonMock(){
        List<Person> personList= List.of(
                new Person("user_martina_vazquez", "martinaV_2025", "Martina", "Vázquez", "martina.vazquez@email.com", "martina.vazquez@university.com", "Granada", true, new Timestamp(System.currentTimeMillis())),
                new Person("prof_alvaro_ramos", "alvaroR_prof", "Álvaro", "Ramos", "alvaro.ramos@email.com", "alvaro.ramos@school.com", "A Coruña", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_julia_castro", "julia_castro_j", "Julia", "Castro", "julia.c@email.com", "julia.castro@university.com", "Vitoria", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_adrian_ortega", "adrian_ortega_24", "Adrián", "Ortega", "adrian.ortega@email.com", "adrian.ortega@university.com", "Elche", true, new Timestamp(System.currentTimeMillis())),
                new Person("user_mateo_serrano", "mateoSerrano", "Mateo", "Serrano", "mateo.serrano@email.com", "mateo.serrano@university.com", "Pamplona", true, new Timestamp(System.currentTimeMillis())));

        Pageable pageable = (Pageable) PageRequest.of(0, 10);
        return new PageImpl<>(personList, (org.springframework.data.domain.Pageable) pageable,personList.size());

    }

    public static Optional<Person> personMock(){
        return Optional.of( new Person("prof_paula_gimenez",
                "paula_g_prof",
                "Paula",
                "Giménez",
                "paula.gimenez@email.com",
                "paula.gimenez@school.com",
                "Oviedo",
                false,
                new Timestamp(System.currentTimeMillis())));

    }

}


