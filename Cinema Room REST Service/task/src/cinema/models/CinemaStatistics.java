package cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CinemaStatistics {

    @JsonProperty("current_income")
    private int income;

    @JsonProperty("number_of_available_seats")
    private int availableSeats;

    @JsonProperty("number_of_purchased_tickets")
    private int purchasedTickets;

    public CinemaStatistics(Cinema cinema) {
        for (Ticket ticket : cinema.getPurchasedTickets()) {
            this.income += ticket.getSeat().getPrice();
        }

        for (Seat seat : cinema.getSeats()) {
            if (!seat.isPurchased()) {
                this.availableSeats += 1;
            }
        }

        this.purchasedTickets = cinema.getPurchasedTickets().size();
    }

    public int getIncome() {
        return income;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getPurchasedTickets() {
        return purchasedTickets;
    }
}
