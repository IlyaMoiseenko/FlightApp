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
        List<Ticket> tickets = ticketService.getAllByDestinations("Владивосток", "Тель-Авив");

        /*
        Расчет минимального времени полета
        */
        Map<String, Integer> resultMap = calculateMinFlightDuration(tickets);
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            writer.write("Минимальное время полета с авиаперевозчиком " + entry.getKey() + ": " + entry.getValue() + " минут");
        }

        /*
        Расчет разницы между средней ценой и медианой
        */
        writer.write("Средняя цена: " + ticketService.getAveragePrice(tickets));
        writer.write("Медиана: " + ticketService.getMedian(tickets));
        writer.write("Разница между средней ценой и медианой: " + differenceBetweenMedianAndAveragePrice(tickets));
    }

    public Map<String, Integer> calculateMinFlightDuration(List<Ticket> tickets) {
        return ticketService.getMinFlightDuration(tickets);
    }

    public Double differenceBetweenMedianAndAveragePrice(List<Ticket> tickets) {
        double averagePrice = ticketService.getAveragePrice(tickets);
        double medianPrice = ticketService.getMedian(tickets);

        return ticketService.calculateDifferenceBetweenMedianAndAveragePrice(medianPrice, averagePrice);
    }
}
