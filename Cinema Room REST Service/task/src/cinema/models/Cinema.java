package cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    @JsonProperty("total_rows")
    private final int rows;

    @JsonProperty("total_columns")
    private final int columns;

    @JsonProperty("available_seats")
    private final List<Seat> seats;

    @JsonIgnore
    private final List<Ticket> purchasedTickets;

    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        purchasedTickets = new ArrayList<>();

        seats = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats.add(new Seat(i + 1, j + 1));
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<Ticket> getPurchasedTickets() {
        return purchasedTickets;
    }

    public void addPurchasedTicket(Ticket ticket) {
        purchasedTickets.add(ticket);
    }
}
