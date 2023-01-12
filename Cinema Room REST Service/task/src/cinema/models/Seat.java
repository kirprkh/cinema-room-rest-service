package cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {

    private static final int BORDER_ROW = 4;
    private static final int TOP_ROWS_SEAT_PRICE = 8;
    private static final int BOTTOM_ROWS_SEAT_PRICE = 10;

    @JsonProperty("row")
    private int row;

    @JsonProperty("column")
    private int column;

    @JsonProperty("price")
    private int price;

    @JsonIgnore
    private boolean purchased;

    public Seat() {}

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.purchased = false;

        this.price = row <= BORDER_ROW
                ? BOTTOM_ROWS_SEAT_PRICE
                : TOP_ROWS_SEAT_PRICE;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (getRow() != seat.getRow()) return false;
        return getColumn() == seat.getColumn();
    }

    @Override
    public int hashCode() {
        int result = getRow();
        result = 31 * result + getColumn();
        return result;
    }
}