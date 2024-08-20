public class Item {
    private int index;
    private int size;
    private int value;

    public Item(int index, int size, int value) {
        this.index = index;
        this.size = size;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public int getSize() {
        return size;
    }

    public int getValue() {
        return value;
    }

    public double getValueToSizeRatio() {
        return (double) value / size;
    }

    @Override
    public String toString() {
        return String.format("Item %d: Size = %d, Value = %d", index, size, value);
    }
}
