package by.moiseenko.service;

/*
    @author Ilya Moiseenko on 30.01.24
*/

import by.moiseenko.domain.Ticket;
import by.moiseenko.repository.JsonTicketRepository;
import by.moiseenko.repository.TicketRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TicketService {

    private final TicketRepository ticketRepository = new JsonTicketRepository();

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getAllByDestinations(String from, String to) {
        List<Ticket> tickets = this.getAll();

        return this.sortByDestinations(from, to, tickets);
    }

    private int getFlightDuration(Ticket ticket) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date departure = format.parse(ticket.getDeparture_time());
            Date arrival = format.parse(ticket.getArrival_time());

            return (int) ((arrival.getTime() - departure.getTime()) / (60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Map<String, Integer> getMinFlightDuration(List<Ticket> tickets) {
        int minDuration = Integer.MAX_VALUE;
        Map<String, Integer> result = new HashMap<>();

        for (Ticket ticket : tickets) {
            String carrier = ticket.getCarrier();

            int duration = getFlightDuration(ticket);

            result.put(
                    carrier,
                    Math.min(duration, result.getOrDefault(carrier, minDuration))
            );
        }

        return result;
    }

    public double getAveragePrice(List<Ticket> tickets) {
        double totalPrice = 0;

        for (Ticket ticket : tickets) {
            totalPrice += ticket.getPrice();
        }

        return totalPrice / tickets.size();
    }

    public double getMedian(List<Ticket> tickets) {
        List<Integer> totalPrices = new ArrayList<>();

        for (Ticket ticket : tickets) {
            totalPrices.add(ticket.getPrice());
        }

        Collections.sort(totalPrices);
        if (totalPrices.size() % 2 == 1)
            return totalPrices.get((totalPrices.size() + 1) / 2 - 1);
        else {
            double lower = totalPrices.get(totalPrices.size() / 2 - 1);
            double upper = totalPrices.get(totalPrices.size() / 2);

            return (lower + upper) / 2.0;
        }
    }

    public Double calculateDifferenceBetweenMedianAndAveragePrice(double medianPrice, double averagePrice) {
        return averagePrice - medianPrice;
    }

    private List<Ticket> sortByDestinations(String from, String to, List<Ticket> tickets) {
        return tickets.stream()
                .filter(ticket ->
                        ticket.getOrigin_name().equalsIgnoreCase(from) && ticket.getDestination_name().equalsIgnoreCase(to))
                .collect(Collectors.toList());
    }
}
