package by.moiseenko.application;

/*
    @author Ilya Moiseenko on 30.01.24
*/

import by.moiseenko.domain.Ticket;
import by.moiseenko.io.ConsoleWriter;
import by.moiseenko.io.Writer;
import by.moiseenko.service.TicketService;

import java.util.List;
import java.util.Map;

public class ConsoleApplication {

    private final TicketService ticketService = new TicketService();
    private final Writer writer = new ConsoleWriter();

    public void start() {

        /*
        Расчет минимального времени полет
        */
        Map<String, Integer> resultMap = calculateMinFlightDuration();
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println("Минимальное время полета с авиаперевозчиком " + entry.getKey() + ": " + entry.getValue() + " минут");
        }

        /*
        Расчет разницы между средней ценой и медианой
        */
        writer.write("Разница между средней ценой и медианой: " + differenceBetweenMedianAndAveragePrice());
    }

    public Map<String, Integer> calculateMinFlightDuration() {
        List<Ticket> tickets = ticketService.getAll();

        return ticketService.getMinFlightDuration(tickets);
    }

    public Double differenceBetweenMedianAndAveragePrice() {
        List<Ticket> tickets = ticketService.getAll();

        double averagePrice = ticketService.getAveragePrice(tickets);
        double medianPrice = ticketService.getMedian(tickets);

        return ticketService.calculateDifferenceBetweenMedianAndAveragePrice(medianPrice, averagePrice);
    }
}
