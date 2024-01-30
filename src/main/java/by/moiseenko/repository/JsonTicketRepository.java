package by.moiseenko.repository;

/*
    @author Ilya Moiseenko on 30.01.24
*/

import by.moiseenko.domain.Ticket;
import by.moiseenko.domain.Wrapper;
import by.moiseenko.exception.FileReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonTicketRepository implements TicketRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File pathToJson = new File("src/main/java/resources/tickets.json");

    @Override
    public List<Ticket> findAll() {
        List<Ticket> tickets;

        try {
            Wrapper<Ticket> wrapper = objectMapper.readValue(pathToJson, new TypeReference<Wrapper<Ticket>>() {});
            tickets = wrapper.getTickets();
        } catch (IOException e) {
            throw new FileReadException(e.getMessage());
        }

        return tickets;
    }
}
