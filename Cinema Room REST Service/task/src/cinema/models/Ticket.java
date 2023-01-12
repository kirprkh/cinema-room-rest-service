package cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Ticket {

    private UUID token;

    @JsonProperty("ticket")
    private Seat seat;

    public Ticket() {}

    public Ticket(Seat seat) {
        token = UUID.randomUUID();
        this.seat = seat;
    }

    public UUID getToken() {
        return token;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return getToken() != null ? getToken().equals(ticket.getToken()) : ticket.getToken() == null;
    }

    @Override
    public int hashCode() {
        return getToken() != null ? getToken().hashCode() : 0;
    }
}
