package cinema.controllers;

import cinema.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CinemaController {

    private static final String ULTRA_SECRET_STATS_PASSWORD = "super_secret";

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;

    private final Cinema cinema =
            new Cinema(ROWS, COLUMNS);

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity purchaseTicket(@RequestBody Seat seat) {
        List<Seat> seats = cinema.getSeats();

        if (!seats.contains(seat)) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "The number of a row or a column is out of bounds!"));
        }

        Seat foundSeat = seats.get(seats.indexOf(seat));

        if (foundSeat.isPurchased()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "The ticket has been already purchased!"));
        }

        foundSeat.setPurchased(true);

        Ticket ticket = new Ticket(foundSeat);
        cinema.addPurchasedTicket(ticket);

        return ResponseEntity.ok().body(ticket);
    }

    @PostMapping("/return")
    public ResponseEntity returnTicket(@RequestBody Ticket ticket) {
        List<Ticket> tickets = cinema.getPurchasedTickets();

        if (!tickets.contains(ticket)) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Wrong token!"));
        }

        Ticket foundTicket = tickets.get(tickets.indexOf(ticket));
        foundTicket.getSeat().setPurchased(false);
        tickets.remove(foundTicket);

        return ResponseEntity
                .ok()
                .body(Map.of("returned_ticket", foundTicket.getSeat()));

    }

    @PostMapping("/stats")
    public ResponseEntity getStats(@RequestParam(required = false) String password) {
        if (password == null || !password.equals(ULTRA_SECRET_STATS_PASSWORD)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "The password is wrong!"));
        }

        CinemaStatistics cinemaStatistics = new CinemaStatistics(cinema);

        return ResponseEntity
                .ok()
                .body(cinemaStatistics);
    }
}