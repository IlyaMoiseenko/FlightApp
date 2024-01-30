package by.moiseenko.repository;

/*
    @author Ilya Moiseenko on 30.01.24
*/

import by.moiseenko.domain.Ticket;

import java.util.List;

public interface TicketRepository {

    List<Ticket> findAll();
}
